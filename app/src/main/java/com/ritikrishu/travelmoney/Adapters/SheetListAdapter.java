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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ritikrishu on 09/06/16.
 */
public class SheetListAdapter extends RecyclerView.Adapter<SheetListAdapter.DataObjectHolder> {

    Context mContext;
    ArrayList<Employee> mEmployeesList = new ArrayList<>();
    public SheetListAdapter(Context context){
        mContext = context;
    }

    public void addData(ArrayList<Employee> employees){
        mEmployeesList.addAll(employees);
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DataObjectHolder dataObjectHolder = new DataObjectHolder(LayoutInflater.from(mContext).inflate(R.layout.sheet, parent, false));
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Employee employee = mEmployeesList.get(position);
        holder.employeeName.setText(employee.getName());
        holder.checkInTime.setText(employee.getFormattedCheckInTime());
        holder.checkOutTime.setText(employee.getFormattedCheckOutTime());
        holder.remainingBalance.setText(employee.getFormattedRemainingBalance() + " peso");
    }

    @Override
    public int getItemCount() {
        return mEmployeesList == null ? 0 : mEmployeesList.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder{

        ImageView employeePicture;
        TextView employeeName;
        TextView checkInTime;
        TextView checkOutTime;
        TextView remainingBalance;

        public DataObjectHolder(View itemView) {
            super(itemView);
            employeePicture = (ImageView) itemView.findViewById(R.id.employee_picture);
            employeeName = (TextView) itemView.findViewById(R.id.employee_name);
            checkInTime = (TextView) itemView.findViewById(R.id.checkInTime);
            checkOutTime = (TextView) itemView.findViewById(R.id.checkOutTime);
            remainingBalance = (TextView) itemView.findViewById(R.id.balance);
        }
    }
}
