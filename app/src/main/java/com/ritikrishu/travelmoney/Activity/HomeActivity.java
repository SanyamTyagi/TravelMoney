package com.ritikrishu.travelmoney.Activity;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ritikrishu.travelmoney.Adapters.HomeListAdapter;
import com.ritikrishu.travelmoney.CaptureQr;
import com.ritikrishu.travelmoney.Model.Employee;
import com.ritikrishu.travelmoney.Model.EmployeeDataBase;
import com.ritikrishu.travelmoney.R;

import java.util.Calendar;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    TextView mScanButton;
    TextView mGenerateButton;
    RecyclerView mRecyclerView;
    HomeListAdapter listAdapter;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

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

                scanQRCode();


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
        employee.setRemainingBalance(calculateBalance(employee));
        EmployeeDataBase.travelersList.add(employee);
    }

    private double distanceTravelled(Employee employee){
        return (((double) (employee.getCheckOutTime()-employee.getCheckInTime()))/3600000) * 100;
    }

    //in peso
    private double calculateBalance(Employee employee){
        return employee.getRemainingBalance() - (distanceTravelled(employee) * 10);
    }





    void scanQRCode() {
        IntentIntegrator integrator = new IntentIntegrator(HomeActivity.this);
        integrator.addExtra("SCAN_CAMERA_ID", getFrontCameraId());
        integrator.setBeepEnabled(true);
        integrator.addExtra("PROMPT_MESSAGE", "Place Your QR Inside The Box.");
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(CaptureQr.class);
        integrator.initiateScan();
    }

    private int getFrontCameraId() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                return cameraId;
            }
        }
        return -1;
    }


    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String id = result.getContents();
                Employee employee = null;
                for(Employee employee1 : EmployeeDataBase.employeesList){
                    if(employee1.getEmployeeID().equals(id)){
                        employee = employee1;
                        break;
                    }
                }
                if(employee != null) {
                    if (employee.isCheckedIn()) {
                        onCheckOut(employee);
                        Log.d("TAG", "onCheckout returned: " + employee.getName() + "   " + employee.getFormattedCheckInTime());
                    } else {
                        onCheckIn(employee);
                        listAdapter.addData(employee);
                        listAdapter.notifyDataSetChanged();
                        Log.d("TAG", "onCheckin() returned: " + employee.getName() + "   " + employee.getFormattedCheckOutTime());
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }


}
