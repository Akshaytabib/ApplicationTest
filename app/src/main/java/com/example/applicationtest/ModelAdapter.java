package com.example.applicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {
    Context context;
    List<Task> taskList;

    public ModelAdapter(MainActivity mainActivity, List<Task> taskList) {
        this.context=mainActivity;
        this.taskList=taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task=taskList.get(position);
        holder.productName.setText(task.getName());
        holder.productprice.setText(task.getTotal());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName;
        TextView productprice;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.tv_pname);
            productprice=itemView.findViewById(R.id.tv_pprice);
            delete=itemView.findViewById(R.id.delete);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task taskss=new Task();
            int dele=taskList.get(getAdapterPosition()).getId();
            taskss.setId(dele);
            DatabaseClient.getInstance(context).getAppDatabase().taskDao().delete(taskss);
        }
    }
}
