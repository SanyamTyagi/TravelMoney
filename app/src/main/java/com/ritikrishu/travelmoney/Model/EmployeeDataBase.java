package com.ritikrishu.travelmoney.Model;

import java.util.ArrayList;

/**
 * Created by ritikrishu on 09/06/16.
 */
public class EmployeeDataBase {
    public static ArrayList<Employee> employeesList = new ArrayList<>();

    public static ArrayList<Employee> travelersList = new ArrayList<>();

    public static void EmployeeDataFill(){
        for(int i = 0; i < 20; i++){
            employeesList.add(new Employee("Name " + i, i*10, 100, false));
        }
    }
    //String name, int employeeID, int remainingBalance, boolean checkedIn



}
