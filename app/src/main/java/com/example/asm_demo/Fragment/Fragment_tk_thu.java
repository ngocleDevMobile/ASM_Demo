package com.example.asm_demo.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asm_demo.Adapter.Tk_thu_Adapter;
import com.example.asm_demo.DAO.Giaodich_DAO;
import com.example.asm_demo.Modal.Giaodich;
import com.example.asm_demo.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fragment_tk_thu extends Fragment {
    Button btn_date1, btn_date2;
    TextView tv_total;
    RecyclerView rv_tk_thu;
    public static Tk_thu_Adapter khoanthu_adapter;
    public static ArrayList<Giaodich> ds_tk_thu;
    public static ArrayList<Giaodich> ds_tk_thus;
    Giaodich_DAO giaodich_dao;
    double total_1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk_thu, container, false);
        btn_date1 = view.findViewById(R.id.btn_date1);
        btn_date2 = view.findViewById(R.id.btn_date2);
        tv_total = view.findViewById(R.id.tv_total);
        rv_tk_thu = view.findViewById(R.id.rv_tk_thu);
        rv_tk_thu.setLayoutManager(new LinearLayoutManager(getContext()));
        giaodich_dao = new Giaodich_DAO(getContext());

        DecimalFormat formatter = new DecimalFormat("#,###");
        total_1 = giaodich_dao.getTotalthu();
        String s = formatter.format(total_1);
        tv_total.setText("Tổng tiền: "+s+" VNĐ");

        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date1();
            }
        });
        btn_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date2();
            }
        });

        ds_tk_thu = new ArrayList<>();
        giaodich_dao = new Giaodich_DAO(getContext());

        ds_tk_thu = giaodich_dao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Tk_thu_Adapter(ds_tk_thu, getContext());
        rv_tk_thu.setAdapter(khoanthu_adapter);
        return view;
    }
    private void Date1(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,i1,i2);
                btn_date1.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }

    private void Date2(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(i,i1,i2);
                btn_date2.setText(simpleDateFormat.format(calendar.getTime()));

                ds_tk_thus = giaodich_dao.get_Tk_Thu(btn_date1.getText().toString(), btn_date2.getText().toString());
                khoanthu_adapter = new Tk_thu_Adapter(ds_tk_thus, getContext());
                rv_tk_thu.setAdapter(khoanthu_adapter);

                String date1 = btn_date1.getText().toString();
                DecimalFormat formatter = new DecimalFormat("#,###");
                double total_2 = giaodich_dao.getTotalthubyDate(date1, simpleDateFormat.format(calendar.getTime()));
                String s = formatter.format(total_2);
                tv_total.setText("Tổng tiền: "+s+"VNĐ");




            }
        },years,months,dayOfWeek);


        datePickerDialog.show();

    }

}
