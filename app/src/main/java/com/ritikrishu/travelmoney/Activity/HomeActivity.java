package com.ritikrishu.travelmoney.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ritikrishu.travelmoney.Adapters.HomeListAdapter;
import com.ritikrishu.travelmoney.Model.Employee;
import com.ritikrishu.travelmoney.Model.EmployeeDataBase;
import com.ritikrishu.travelmoney.R;

import java.util.Calendar;
import java.util.Random;

import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    TextView mScanButton;
    TextView mGenerateButton;
    RecyclerView mRecyclerView;
    HomeListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView) findViewById(R.id.Home_RecyclerView);
        mScanButton = (TextView)findViewById(R.id.scanButton);
        mGenerateButton = (TextView) findViewById(R.id.generateButton);
        EmployeeDataBase.EmployeeDataFill();
        listAdapter = new HomeListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = EmployeeDataBase.employeesList.get(new Random().nextInt(20));
                if(employee.isCheckedIn()){
                    onCheckOut(employee);
                    listAdapter.addData(employee);
                    listAdapter.notifyDataSetChanged();
                    Log.d("TAG", "onCheckout returned: " + employee.getName() + "   " + employee.getFormattedCheckInTime());
                }
                else{
                    onCheckIn(employee);
                    Log.d("TAG", "onCheckin() returned: " + employee.getName() + "   " + employee.getFormattedCheckOutTime());
                }
            }
        });

        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GenerateSheetActivity.class));
            }
        });
        mRecyclerView.setAdapter(listAdapter);
    }

    private void onCheckIn(Employee employee){
        employee.setCheckedIn(true);
        employee.setCheckInTime(Calendar.getInstance().getTimeInMillis());
    }

    private void onCheckOut(Employee employee){
        employee.setCheckedIn(false);
        employee.setCheckOutTime(Calendar.getInstance().getTimeInMillis());
        employee.setRemainingBalance((int)calculateBalance(employee));
        EmployeeDataBase.travelersList.add(employee);
    }

    private double distanceTravelled(Employee employee){
        return ((employee.getCheckOutTime()-employee.getCheckInTime())/3600000) * 60;
    }

    //in peso
    private double calculateBalance(Employee employee){
        return employee.getRemainingBalance() - distanceTravelled(employee) * 10;
    }


}
