package com.example.pj_04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    Button btn_update, btn_delete, btn_deleteAll, btn_back;
    EditText edt_id, edt_name, edt_ponit;
    Database database = new Database(AddActivity.this, Database.DB_NAME, null, Database.DB_VERSION);
    private void Map() {
        btn_back = findViewById(R.id.btn_back);
        btn_deleteAll = findViewById(R.id.btn_deleteAll);
        btn_delete = findViewById(R.id.btn_delete);
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_ponit = findViewById(R.id.edt_point);
    }
    private void LoadData(){
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        edt_id.setText(bundle.getInt("Key_Id")+"");
        edt_name.setText(bundle.getString("Key_Name"));
        edt_ponit.setText(bundle.getFloat("Key_Point")+"");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Map();
        LoadData();

        btn_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                builder.setTitle("Canh bao!!!");
                builder.setMessage("Ban chac chan muon xoa?");
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.deleteAll();
                        edt_name.setText("");
                        edt_name.setText("");
                        edt_ponit.setText("");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AddActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
}
