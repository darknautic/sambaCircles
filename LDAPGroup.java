package com.codeinmotion;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by s47id on 23/04/2014.
 */
public class LDAPGroup {

    private Hashtable env = new Hashtable();
    private DirContext ctx = null;
    private NamingEnumeration results = null;

    public LDAPGroup(){


        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://172.17.5.45:389/dc=ibopeagb,dc=com,dc=mx");
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,"cn=dummy,dc=ibopeagb,dc=com,dc=mx"); // specify the username
        env.put(Context.SECURITY_CREDENTIALS,"dummyus3r");

        ctx = null;
        results = null;

        try {

            ctx = new InitialDirContext(env);



        }catch (Throwable e) {
            e.printStackTrace();
        }

    }


    public void close(){

        try {

            ctx.close();

         }catch (Throwable e) {
        e.printStackTrace();
        }
    }


    public List<String> getMembers(String groupName,String option){
        List<String> membersList = new ArrayList<String>();
        List<String> lanID = new ArrayList<String>();
        NetUser netUser = new NetUser();

        membersList.clear();
        lanID.clear();


        //System.out.println(option);

        try{

            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            //results = ctx.search("ou=Groups", "(objectclass=posixGroup)", controls);
            results = ctx.search("ou=Groups","cn="+groupName, controls);

            if(results.hasMore()){


                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();


                try{


                    for(int i = 0; i < attributes.get("memberUid").size() ; i ++)
                    {
                        //System.out.println("==> : " + attributes.get("memberUid").get(i));
                        membersList.add(attributes.get("memberUid").get(i).toString());
                        try{
                            lanID.add(netUser.getLanID( attributes.get("memberUid").get(i).toString()  ));
                        }
                        catch (Throwable  e){
                            System.out.println("Not DB Connection");
                        }


                    }

                    //System.out.println(membersList);
                    //System.out.println(option);



                    if(option.equals("both")){
                        System.out.println(membersList);
                        System.out.println(lanID);
                    }
                    else if(option.equals("lanid")){
                        System.out.println(lanID);
                    }
                    else if(option.equals("netusers")){
                        System.out.println(membersList);
                    }
                    else{
                        System.out.println(membersList);
                    }


                }catch (Throwable  e){
                    System.out.println(groupName + " => memberUid attr( Not Found )" );
                }







            }else{ System.out.println("\n group  "+"\""+groupName+"\""+" - Not Found");}




        }catch (Throwable e ){

        }





        return  membersList;
    }

}
