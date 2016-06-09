package com.ritikrishu.travelmoney.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritikrishu.travelmoney.Model.Employee;
import com.ritikrishu.travelmoney.R;

import java.util.ArrayList;


/**
 * Created by ritikrishu on 09/06/16.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.DataObjectHolder> {

    Context mContext;
    ArrayList<Employee> mEmployeesList = new ArrayList<>();

    public HomeListAdapter(Context context) {
        mContext = context;
    }

    public void addData(Employee employee) {
        mEmployeesList.add(employee);
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataObjectHolder(LayoutInflater.from(mContext).inflate(R.layout.home_list, parent, false));
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Employee employee = mEmployeesList.get(position);
        holder.employeeName.setText(employee.getName());
    }

    @Override
    public int getItemCount() {
        return mEmployeesList == null ? 0 : mEmployeesList.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {

        ImageView employeePicture;
        TextView employeeName;

        public DataObjectHolder(View itemView) {
            super(itemView);
            employeePicture = (ImageView) itemView.findViewById(R.id.employee_picture);
            employeeName = (TextView) itemView.findViewById(R.id.employee_name);
        }
    }
}