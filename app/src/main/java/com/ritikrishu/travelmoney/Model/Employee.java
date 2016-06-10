package com.ritikrishu.travelmoney.Model;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ritikrishu on 09/06/16.
 */
public class Employee {
    private String name;
    private String EmployeeID;
    private double RemainingBalance;
    private long checkInTime;
    private long checkOutTime;
    private boolean checkedIn;

    public Employee(String name, String employeeID, double remainingBalance, boolean checkedIn) {
        this.name = name;
        EmployeeID = employeeID;
        RemainingBalance = remainingBalance;
        this.checkedIn = checkedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCheckOutTime() {
        return checkOutTime;
    }

    public String getFormattedCheckOutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(this.getCheckOutTime()));
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }

    public void setCheckOutTime(long checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public long getCheckInTime() {
        return checkInTime;
    }

    public String getFormattedCheckInTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(this.getCheckInTime()));
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }

    public void setCheckInTime(long checkInTime) {
        this.checkInTime = checkInTime;
    }


    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkIn) {
        this.checkedIn = checkIn;
    }


    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public double getRemainingBalance() {
        return RemainingBalance;
    }

    public String getFormattedRemainingBalance(){
        DecimalFormat f = new DecimalFormat("##.##");
        return f.format(this.getRemainingBalance());
    }

    public void setRemainingBalance(double remainingBalance) {
        RemainingBalance = remainingBalance;
    }
}
