package com.ritikrishu.travelmoney.Model;

import java.util.ArrayList;

/**
 * Created by ritikrishu on 09/06/16.
 */
public class EmployeeDataBase {
    public static ArrayList<Employee> employeesList = new ArrayList<>();

    public static ArrayList<Employee> travelersList = new ArrayList<>();

    public static void EmployeeDataFill(){
        employeesList.add(new Employee("Ritik Rishu", "tikshu01", 1000.34, false));
        employeesList.add(new Employee("Sanyam Tyagi", "yamagi02", 958.94, false));
        employeesList.add(new Employee("Abhishek Sengar", "hekgar03", 1758.00, false));
        employeesList.add(new Employee("Rahul Agarwal", "hulwal04", 1400.99, false));
        employeesList.add(new Employee("Sajal Nehra", "jalhra05", 800.00, false));
        employeesList.add(new Employee("Anuj Whatever", "nujver06", 700.52, false));
        employeesList.add(new Employee("Aseem Agarwal", "eemwal07", 799.99, false));
        employeesList.add(new Employee("Shivansh Mittal", "nshtal08", 957.45, false));
        employeesList.add(new Employee("Rishi Chandak", "shidak09", 709.98, false));
        employeesList.add(new Employee("Saurabh Saluja", "abhuja10", 709.98, false));
        employeesList.add(new Employee("Antesh Anand", "eshand11", 710.99, false));
        employeesList.add(new Employee("Shadab Ahmad", "dabmad12", 710.99, false));
        employeesList.add(new Employee("Aquib Khan", "uibhan13", 810.99, false));
        employeesList.add(new Employee("Zeeshan Khan", "hanhan14", 750.96, false));
        employeesList.add(new Employee("Rahul Arora", "hulora15", 889.89, false));
        employeesList.add(new Employee("Rishabh Verma", "abhrma16", 810.74, false));
        employeesList.add(new Employee("Shivam Bhardwaj", "lachha007", 500.00, false));
        employeesList.add(new Employee("Pranay Sharma", "nayrma18", 500.00, false));
        employeesList.add(new Employee("Pranay Singh", "nayngh19", 700.91, false));
    }
    //String name, String employeeID, Double remainingBalance, boolean checkedIn



}
