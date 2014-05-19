package com.codeinmotion;

/**
 * Created by s47id on 22/04/2014.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReadCVS {


    public void run() {

        String csvFile = "C:\\Users\\austsa01\\Desktop\\employeeID-lanID.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            Database db = new Database();
            db._ip="10.29.210.41";
            db._port=27017;
            db._dbName="sambaCircles";
            db.connect();

            List<String> keys = new ArrayList<String>();
            keys.add("employeeID");
            keys.add("fullName");
            keys.add("lanID");
            //System.out.println(keys.get(0));
            //System.out.println(keys.get(1));
            //System.out.println(keys.get(2));




            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                //System.out.println("**********************");

                String[] theLine = line.split(cvsSplitBy);
                HashMap<String,Object> doc = new HashMap<String, Object>();


                for(int i=0 ; i < theLine.length ;i++ ){
                    //System.out.println(theLine[i]);
                    doc.put(keys.get(i),theLine[i]);
                }

                System.out.println(doc);
                db.insertDocumentS("lanid",doc);





            }  //while


            db.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Done");
    }

}
