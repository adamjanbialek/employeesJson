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

    public void parseJson(String json) {
        JSONArray jsonArray = new JSONArray(json);
        ObjectMapper objectMapper = new ObjectMapper();
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

        printJsonContent(response);

        parseJson(response);
    }
}
