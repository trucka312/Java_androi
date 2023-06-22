package com.example.pj_04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> studentList;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_listview, null);

        TextView txt_id = convertView.findViewById(R.id.txt_id);
        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_point = convertView.findViewById(R.id.txt_point);

        Student student = studentList.get(position);
        txt_id.setText(student.getID()+"");
        txt_name.setText(student.getName());
        txt_point.setText(student.getPoint()+"");
        return convertView;
    }
}
