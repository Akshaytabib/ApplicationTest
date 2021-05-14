package com.example.applicationtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "Task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "price")
    String  price;
    @ColumnInfo(name = "quantity")
    String  quantity;
    @ColumnInfo(name = "total")
    String  total;

    public Task(int id, String name, String price, String quantity, String total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

   public static Comparator<Task> task=new Comparator<Task>() {
       @Override
       public int compare(Task t1, Task t2) {
           return t1.getName().compareTo(String.valueOf(t2));
       }
   };

    public static Comparator<Task> totalMoney=new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getName().compareTo(String.valueOf(t2));
        }
    };

}
