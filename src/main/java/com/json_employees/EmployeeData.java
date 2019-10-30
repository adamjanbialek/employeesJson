package com.json_employees;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeData implements Runnable{

    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();
        employeeData.run();
    }

    public void printJsonContent(String json){
        JSONArray jsonArray = new JSONArray(json);
        int jsonLength = json.length();
        String jsonToPrint = json.substring(1,jsonLength-2);
        String[] print = jsonToPrint.split("},");

        for(int i=0; i<jsonArray.length(); i++){
            String ready=print[i].substring(1);
            System.out.println(ready);
        }
    }

    public List<Employee> parseJsonToObjects(String json) {
        JSONArray jsonArray = new JSONArray(json);
        int id;
        String name;
        int salary;
        int employeeAge;
        List<Employee> employeeList= new ArrayList<>();
        for(int i=0; i<jsonArray.length();i++){
            JSONObject person = jsonArray.getJSONObject(i);
            name=person.getString("employee_name");
            salary=person.getInt("employee_salary");
            id=person.getInt("id");
            employeeAge=person.getInt("employee_age");
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setSalary(salary);
            employee.setEmployeeAge(employeeAge);
            employeeList.add(employee);
        }

        return employeeList;
    }

    @Override
    public void run() {
        Connection connection = new Connection();
        String response="";
        try {
            response=connection.connect("http://dummy.restapiexample.com/api/v1/employees");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        printJsonContent(response);

        List<Employee> employees = parseJsonToObjects(response);

        for (Employee e: employees
        ) {
            System.out.println(e);
        }
    }
}
