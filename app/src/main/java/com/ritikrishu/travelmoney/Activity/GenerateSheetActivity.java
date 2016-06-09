package com.ritikrishu.travelmoney.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ritikrishu.travelmoney.Adapters.SheetListAdapter;
import com.ritikrishu.travelmoney.Model.EmployeeDataBase;
import com.ritikrishu.travelmoney.R;

import butterknife.BindView;

public class GenerateSheetActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_sheet);
        mRecyclerView = (RecyclerView) findViewById(R.id.sheet_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(GenerateSheetActivity.this));
        SheetListAdapter listAdapter = new SheetListAdapter(this);
        listAdapter.addData(EmployeeDataBase.travelersList);
        mRecyclerView.setAdapter(listAdapter);

        //TODO set data to recycler view
    }
}
