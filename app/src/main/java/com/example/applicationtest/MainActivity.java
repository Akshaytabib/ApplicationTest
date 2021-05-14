package com.example.applicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView adddata;
    Task task;
    String quantity;
    RecyclerView recyclerView, recyclerView1, recyclerView2;
    ModelAdapter modelAdapter;
    TextView sortNaem;
    TextView sortPrice;
    List<Task> taskList = new ArrayList<>();
    int count;

    TextView noofproduct, noofprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = DatabaseClient.getInstance(this).getAppDatabase().taskDao().getAll();

        adddata = findViewById(R.id.iv_add);
        noofproduct = findViewById(R.id.tv_noofproduct);
        noofprice = findViewById(R.id.tv_noofprice);
        sortNaem = findViewById(R.id.tv_sortName);
        sortPrice = findViewById(R.id.tv_sortprice);


        for(int i=0;i<taskList.size();i++){
            String abc=taskList.get(i).getPrice();
            count+=Integer.parseInt(abc);
            noofprice.setText(count);
        }

        sortPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(taskList,Task.totalMoney);
                modelAdapter.notifyDataSetChanged();
            }
        });

        sortNaem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Collections.sort(taskList,Task.task);
                modelAdapter.notifyDataSetChanged();

            }
        });

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        modelAdapter = new ModelAdapter(this, taskList);
        recyclerView.setAdapter(modelAdapter);

        modelAdapter.notifyDataSetChanged();

        for (int i = 0; i < taskList.size(); i++) {
            noofproduct.setText(String.valueOf(i));
        }


        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Product");
                View mview = getLayoutInflater().inflate(R.layout.layout_dialog, null);

                EditText pname = mview.findViewById(R.id.productnamess);
                EditText price = mview.findViewById(R.id.productprice);
                EditText total = mview.findViewById(R.id.productTotal);

                Spinner spinner = mview.findViewById(R.id.quant);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.counts));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        quantity = (String) spinner.getSelectedItem();
                        Toast.makeText(MainActivity.this, quantity.toString(), Toast.LENGTH_SHORT).show();

                        task = new Task();
                        String names = (String) pname.getText().toString();
                        task.setName(names);
                        task.setPrice(price.getText().toString());
                        task.setQuantity(quantity);
                        task.setTotal(total.getText().toString());
                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().insert(task);
                        Toast.makeText(MainActivity.this, "Sucessfully Inserted", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setView(mview);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }


}