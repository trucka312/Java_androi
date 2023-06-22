package com.example.pj_04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageButton ibtn_search;
    EditText edt_search;
    ListView lsv_students;

    Database database = new Database(MainActivity.this, Database.DB_NAME, null, Database.DB_VERSION);
    ArrayList<Student> studentsList = new ArrayList<>();
    int listIndex = -1, idDatabase;
    StudentAdapter adapter;

    private void Map() {
        ibtn_search = findViewById(R.id.btn_search);
        edt_search = findViewById(R.id.edt_search);
        lsv_students = findViewById(R.id.lsv_students);
    }

    private void LoadList() {
        studentsList = (ArrayList<Student>) database.getAll();
        adapter = new StudentAdapter(MainActivity.this, R.layout.item_listview, studentsList);
        lsv_students.setAdapter(adapter);
    }

    private void addSth() {
        database.addStudent(new Student("DaLab", (float) 3.45));
        database.addStudent(new Student("Đen", (float) 4.10));
        database.addStudent(new Student("Sơn Tùng M-TP", (float) 3.10));
        database.addStudent(new Student("Hoàng Dũng", (float) 3.20));
        //LoadList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map();
        addSth();
        LoadList();
        ibtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentsList = (ArrayList<Student>) database.searchBySinger(edt_search.getText().toString());
                if (studentsList.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo!!!");
                    builder.setMessage("Không tìm thấy kết quả phù hợp");
                    builder.setIcon(R.drawable.ic_launcher_background);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LoadList();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    adapter = new StudentAdapter(MainActivity.this, R.layout.item_listview, studentsList);
                    lsv_students.setAdapter(adapter);
                }
            }
        });
        lsv_students.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this, AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Key_Id", studentsList.get(i).getID());
                bundle.putString("Key_Name", studentsList.get(i).getName());
                bundle.putFloat("Key_Point", studentsList.get(i).getPoint());
                it.putExtras(bundle);
                startActivity(it);
                return false;
            }
        });
    }
}