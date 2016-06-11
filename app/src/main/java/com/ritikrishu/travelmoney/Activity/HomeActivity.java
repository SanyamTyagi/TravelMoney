package com.ritikrishu.travelmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ritikrishu.travelmoney.Adapters.HomeListAdapter;
import com.ritikrishu.travelmoney.ContinuousCaptureActivity;
import com.ritikrishu.travelmoney.Model.EmployeeDataBase;
import com.ritikrishu.travelmoney.R;

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
        mScanButton = (TextView) findViewById(R.id.scanButton);
        mGenerateButton = (TextView) findViewById(R.id.generateButton);
        EmployeeDataBase.EmployeeDataFill();
        listAdapter = new HomeListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ContinuousCaptureActivity.class);
                startActivity(intent);
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
}
