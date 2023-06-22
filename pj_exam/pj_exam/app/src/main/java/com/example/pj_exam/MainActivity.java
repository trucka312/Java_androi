package com.example.pj_exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv_student;
    private SearchView sv_search;
    private FloatingActionButton fab_add;
    int selectedId = -1;
    int databaseId = -1;

    DB_HoangVinh_4 database = new DB_HoangVinh_4(MainActivity.this, "Restaurant", null, 1);
    ArrayList<SinhVien> sinhVienArrayList = new ArrayList<>();
    int listIndex = -1, idDatabase;
    Adapter_4 adapter;

    private void handleMapping() {
        lv_student = findViewById(R.id.lv_student);
        fab_add = findViewById(R.id.fab_add);
    }

    private void handleClick() {
        lv_student.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedId = i;
                databaseId = sinhVienArrayList.get(selectedId).getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.deleteSinhVien(databaseId);
                        sinhVienArrayList.remove(selectedId);
                        adapter.notifyDataSetChanged();
                        lv_student.setAdapter(adapter);
                        Toast.makeText(MainActivity.this,  " Đã xóa", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Đã hủy",Toast.LENGTH_LONG).show();
                    }
                });

                builder.create().show();
                return false;
            }
        });
    }

    private void addSth() {
        database.addSinhVien(new SinhVien("CD28765", "Hoàng Hữu Toàn ", 8, 8, 9));
        database.addSinhVien(new SinhVien("CD28765", "Hoàng Hữu Toàn ", 8, 8, 9));
        database.addSinhVien(new SinhVien("CD28765", "Hoàng Hữu Toàn ", 8, 8, 9));
        database.addSinhVien(new SinhVien("CD28765", "Hoàng Hữu Toàn ", 8, 8, 9));
        database.addSinhVien(new SinhVien("CD28765", "Hoàng Hữu Toàn ", 8, 8, 9));
        database.addSinhVien(new SinhVien("CD200057", "Hoàng Đông Vĩnh ", 9.5, 9, 10));
    }

    private void loadAdapter() {
        sinhVienArrayList = (ArrayList<SinhVien>) database.getAllSinhVien();
        adapter = new Adapter_4(this, sinhVienArrayList);
        lv_student.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleMapping();
        handleClick();
        if(database.getAllSinhVien().size()==0){
            addSth();
        }
        loadAdapter();
    }
}