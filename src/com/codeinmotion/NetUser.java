package com.codeinmotion;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.*;

/**
 * Created by austsa01 on 23/04/2014.
 */
public class NetUser {

    public NetUser(){}

    public HashMap<String,Object> getUserByEmployeeID(String employeeID){

        /**
         * get hashMap document  from netUsers collection
         */

        HashMap<String,Object> userObject = new HashMap<String, Object>();
        Database db = new Database();
        db._ip="10.29.210.41";
        db._port=27017;
        db._dbName="sambaCircles";
        db.connect();

        HashMap<String,Object> query = new HashMap<String, Object>();
        query.put("employeeNumber",employeeID);
        userObject = db.select(query,"netUsers");
        //System.out.println("userObject =>" + userObject);



        db.close();

        return  userObject;
    }



    public void syncLanID(){

        /**
         * update initials field on "netUsers" Collection
         * according to "lanid" collection which comes from CSV file.
         */

        Database db = new Database();
        db._ip="10.29.210.41";
        db._port=27017;
        db._dbName="sambaCircles";
        db.connect();


         /*All documents*/

        List<HashMap> usersSet = new ArrayList<HashMap>();
        usersSet = db.getAllDocuments("netUsers");
        int usersCount = 0;
        int usersWithoutLanID = 0;
        int usersBothLanID = 0;

        HashMap<String,Object> query = new HashMap<String, Object>();
        HashMap<String,Object> lanid = new HashMap<String, Object>();

        String employee = "";
        String uid = "";
        String lanID = "";
        String initials = "";


        //query.put("employeeID", "3306");
        //lanid = db.select(query, "lanid");
        //System.out.println(lanid.get("lanID"));


        for (int i=0; i < usersSet.size(); i++)
        {



            if(usersSet.get(i).get("employeeNumber") != null ){
                employee = (String) usersSet.get(i).get("employeeNumber");
            }else {
                employee = "";
            }


            query.put("employeeID", employee);
            lanid = db.select(query, "lanid");



            if(lanid.get("lanID") != null ){
                lanID = (String) lanid.get("lanID");
            }else {
                lanID = "";
            }

            if(usersSet.get(i).get("initials") != null ){
                initials = (String) usersSet.get(i).get("initials");
            }else {
                initials = "";
            }





            if(lanID.length() == 0 & initials.length() == 0){

                usersWithoutLanID+=1;


            }else if (lanID.length() > 0 & initials.length() > 0){



                if(lanID.equals(initials)){
                    usersBothLanID+=1;
                }
                else {
                    System.out.println("--");
                    System.out.println("-"+employee+"-");
                    System.out.println("-"+lanID+"-");
                    System.out.println("-"+initials+"-");
                }


            }
            else {

            }


            //System.out.println("uid =>" +usersSet.get(i).get("uid"));






            query.clear();
            lanid.clear();
            usersCount+=1;
        }
        System.out.println("Total of Users =>" + usersCount);
        System.out.println("No LanID available =>" + usersWithoutLanID);
        System.out.println("Both LanID =>" + usersBothLanID);



        db.close();



    }




    public String  getLanID(String uid){
        /**
         * get LANid if both exist on netUSers (ldap) and lanid colelction (csv)
         * this function takes from lanid collection.
         */

        String result = "";

        Database db = new Database();
        db._ip="10.29.210.41";
        db._port=27017;
        db._dbName="sambaCircles";
        db.connect();



        HashMap<String,Object> query = new HashMap<String, Object>();
        HashMap<String,Object> netUser = new HashMap<String, Object>();
        HashMap<String,Object> lanidCollection = new HashMap<String, Object>();

        query.put("uid", uid);
        netUser = db.select(query, "netUsers");


        String employeeID = "";
        String lanID = "";
        String initials = "";


        if(netUser.get("employeeNumber") != null ){
            employeeID = (String) netUser.get("employeeNumber");
        }else {
            employeeID = "";
        }

        if(netUser.get("initials") != null ){
            initials = (String) netUser.get("initials");
        }else {
            initials = "";
        }


        query.clear();
        query.put("employeeID", employeeID);
        lanidCollection = db.select(query,"lanid");



        if(lanidCollection.get("lanID") != null ){
            lanID = (String) lanidCollection.get("lanID");
        }else {
            lanID = "";
        }



        //System.out.println("uid => "+uid);
        //System.out.println("employeeID => "+employeeID);
        //System.out.println("initials => "+initials);
        //System.out.println("lanID => "+lanID);



        db.close();


        if(initials.length() > 0 & lanID.length() > 0){
            result = lanID;
        }
        else if(initials.length() == 0 & lanID.length() >0){
            result = lanID;
        }
        else if(initials.length() == 0 & lanID.length() == 0){
            result = "TBD";
        }
        else {
            result = initials;
        }



        return result;
    }



    public int  isValidAccount(String uid){

        // 0 => user ok, 1 => Not Exist,  2 => Inactive , 3 => Lock


        int result = 0 ;

        /*set LDAP context */
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://172.17.5.45:389/dc=ibopeagb,dc=com,dc=mx");
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,"cn=dummy,dc=ibopeagb,dc=com,dc=mx"); // specify the username
        env.put(Context.SECURITY_CREDENTIALS,"dummyus3r");

        DirContext ctx = null;
        NamingEnumeration results = null;

        try{
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);


            results = ctx.search("ou=People", "uid="+uid, controls);



            if (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();


                //System.out.println("accountStatus => " + attributes.get("accountStatus").get());
                //System.out.println("sambaAcctFlags => " + attributes.get("sambaAcctFlags").get());
                //System.out.println("shadowExpire => " + attributes.get("shadowExpire").get());
                //System.out.println("sambaBadPasswordCount => " + attributes.get("sambaBadPasswordCount").get());


                if(attributes.get("accountStatus").get().toString().equals("inactive")){

                    System.out.println("User Inactive");
                    result = 2;


                }
                if( attributes.get("sambaAcctFlags").get().toString().indexOf("L") != -1 ){

                    System.out.println("User is Lock");
                    result = 3;
                }



            }
            else{

                result = 1;
                System.out.println("User Not Exist");
            }



            if(result == 0){
                System.out.println("User is OK !¡");
            }


            ctx.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }





        return  result;
    }

}
