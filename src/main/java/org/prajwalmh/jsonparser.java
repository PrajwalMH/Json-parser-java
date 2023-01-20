package org.prajwalmh;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class jsonparser {
    public static void main(String[] args) throws IOException {

        //Reading JSON from employees.json file
        BufferedReader br=new BufferedReader(new FileReader(".\\jsonfolder\\data.json"));
        String line;
        StringBuilder sbuilderObj = new StringBuilder();
        while((line=br.readLine()) !=null){
            sbuilderObj.append(line);
        }
        System.out.println("Original Json :: "+sbuilderObj.toString());

        //Reading 2nd JSON from employees2.json file
        BufferedReader br2=new BufferedReader(new FileReader(".\\jsonfolder\\reference.json"));
        String line2;
        StringBuilder sbuilderObj2 = new StringBuilder();
        while((line2=br2.readLine()) !=null){
            sbuilderObj2.append(line2);
        }
        System.out.println("Original Json :: "+sbuilderObj2.toString());


        //Using JSONObject for file 1
        JSONObject jsonObj = new JSONObject(sbuilderObj.toString());


        //Using JSONObject for file 2
        JSONObject jsonObj2=new JSONObject(sbuilderObj2.toString());

        //Fetching nested Json using JSONArray for file 1
        JSONArray arrObj = jsonObj.getJSONArray("employees");

        //Fetching nested Json using JSONArray for file 1
        JSONArray arrObj2 = jsonObj2.getJSONArray("employees");
        JSONObject finalOutput=new JSONObject();
        int count=0;

        for (int i = 0; i < arrObj .length(); i++) {
            int eid1 = arrObj.getJSONObject(i).getInt("eid");
            String ename1 = arrObj.getJSONObject(i).getString("ename");
            int age1 = arrObj.getJSONObject(i).getInt("age");
            String dept1 = arrObj.getJSONObject(i).getString("dept");
            int eid2 = arrObj2.getJSONObject(i).getInt("eid");
            String ename2 = arrObj2.getJSONObject(i).getString("ename");
            int age2 = arrObj2.getJSONObject(i).getInt("age");
            String dept2 = arrObj2.getJSONObject(i).getString("dept");
            if(eid1==eid2||ename1==ename2||age1==age2||dept1==dept2){
                finalOutput.put(String.valueOf(i),"pass");
                count+=1;
            }else{
                finalOutput.put(String.valueOf(i),"fail");

            }
        }

        System.out.println(count);

        try {
            FileWriter output=new FileWriter(".\\jsonfolder\\result.json");
            output.write(finalOutput.toString());
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("JSON file created: "+finalOutput);
    }
}
