package com.example.pj_exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_4 extends BaseAdapter {
    private Context context;
    private ArrayList<SinhVien> data;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.lv_item, null);
        TextView tv_name = view.findViewById(R.id.tv_hoten);
        TextView tv_id = view.findViewById(R.id.tv_sbd);
        TextView tv_diem = view.findViewById(R.id.tv_tongdiem);
        SinhVien sinhVien = data.get(i);
        tv_name.setText(sinhVien.getName() + "");
        tv_id.setText(sinhVien.getsId() + "");
        tv_diem.setText(sinhVien.getDiem() + "");
        return view;
    }

    public Adapter_4(Context context, ArrayList<SinhVien> data) {
        this.context = context;
        this.data = data;

    }
}
