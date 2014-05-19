package com.codeinmotion;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by s47id on 23/04/2014.
 */
public class LdapMatrix {


    public  void LdapMatrix(){}


    public void createMatrix(){


        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://172.17.5.45:389/dc=ibopeagb,dc=com,dc=mx");
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,"cn=dummy,dc=ibopeagb,dc=com,dc=mx"); // specify the username
        env.put(Context.SECURITY_CREDENTIALS,"dummyus3r");


        DirContext ctx = null;
        NamingEnumeration results = null;



        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);


            results = ctx.search("ou=People", "(objectclass=person)", controls);



            int countPeople = 0;
            HashMap<String,Object> doc = new HashMap<String, Object>();
            List<String> keys = new ArrayList<String>();
            doc.clear();

            Database db = new Database();
            db._ip="10.29.210.41";
            db._port=27017;
            db._dbName="sambaCircles";
            db.connect();



            keys.add("employeeNumber");
            keys.add("uid");
            keys.add("cn");
            keys.add("initials");


            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                //Attribute attr = attributes.get("cn");
                //String cn = (String) attr.get();
                //System.out.println(cn);

                doc.clear();

                try {
                    doc.put("employeeNumber",(String)attributes.get("employeeNumber").get());
                    }catch (Throwable e){
                    doc.put("employeeNumber","");
                }
                try {
                    doc.put("uid",(String)attributes.get("uid").get());
                }catch (Throwable e){
                    doc.put("uid","");
                }
                try {
                    doc.put("cn",(String)attributes.get("cn").get());
                }catch (Throwable e){
                    doc.put("cn","");
                }
                try {
                    doc.put("initials",(String)attributes.get("initials").get());
                }catch (Throwable e){
                    doc.put("initials","");
                }


                //System.out.println(doc);
                db.insertDocument("netUsers",keys,doc);





                //System.out.println(" Name : " + attributes.get("cn"));
                //System.out.println(" Status : " + attributes.get("accountStatus"));
                        /*System.out.println(" Person logonhours = " + attributes.get("logonhours"));
                        System.out.println(" Person MemberOf = " + attributes.get("memberOf"));
                        */
                countPeople+=1;

            }

            db.close();
            System.out.println(countPeople);




            ctx.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
