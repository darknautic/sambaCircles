package com.codeinmotion;


import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.sound.midi.SysexMessage;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



public class Main {

    public static void main(String[] args) {
        /**
         * Run from command line.
         *
         * \IdeaProjects\sambaCircles\out\production\sambaCircles>java com.codeinmotion.Main
         * or
         * \IdeaProjects\sambaCircles\out\artifacts\sambaCircles_jar>java -jar sambaCircles.jar  loadCSV
         *
         *
         *
         * Monitoring Connections on LDAP server
         * netstat --inet --numeric-hosts --numeric-ports --numeric-users |   awk '{print $6}'| sort | uniq -c  | awk '{n+=$1; print} END {printf("%7d\n", n)}'
         *
         */

        String option = "";
        if ( args.length > 0){

            option = args[0];
            System.out.println();



            if(option.equals("cleanGroups")){

                System.out.println("Review Groups..");
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, "ldap://172.17.5.45:389/dc=ibopeagb,dc=com,dc=mx");
                env.put(Context.SECURITY_AUTHENTICATION,"simple");
                env.put(Context.SECURITY_PRINCIPAL,"cn=dummy,dc=ibopeagb,dc=com,dc=mx"); // specify the username
                env.put(Context.SECURITY_CREDENTIALS,"dummyus3r");           // specify the password
                //env.put(Context.SECURITY_PRINCIPAL,"cn=admin,dc=ibopeagb,dc=com,dc=mx");
                //env.put(Context.SECURITY_CREDENTIALS,"baby");
                //env.put(Context.REFERRAL, "throw");
                //env.put(Context.REFERRAL, "ignore");





                //   .............. Review Groups .........................
                //reviewGroups(env);

                /*ou=Groups*/

                //cleanGroup(env,"polluxTDT-w");
                //cleanGroup(env,"Proxy_outdoors");

                /*ou=Proxy_ACLs*/

                //cleanGroup(env,"Proxy_ALL");
                //cleanGroup(env,"Proxy_outdoors");



                 /*ou=Lists*/

                //cleanGroup(env,"altas");
                //cleanGroup(env,"incidencias");









                //   -----------------------------------------------------


                DirContext ctx = null;
                NamingEnumeration results = null;



                try {
                    ctx = new InitialDirContext(env);
                    SearchControls controls = new SearchControls();
                    controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

                    //person
                    //results = ctx.search("", "(objectclass=person)", controls);
                    results = ctx.search("", "uid=sajid.austria", controls);





                    while (results.hasMore()) {
                        SearchResult searchResult = (SearchResult) results.next();
                        Attributes attributes = searchResult.getAttributes();
                        Attribute attr = attributes.get("cn");
                        String cn = (String) attr.get();
                        //System.out.println(cn);
                        //System.out.println(" Name : " + attributes.get("cn"));
                        //System.out.println(" Status : " + attributes.get("accountStatus"));
                        /*System.out.println(" Person logonhours = " + attributes.get("logonhours"));
                        System.out.println(" Person MemberOf = " + attributes.get("memberOf"));
                        */
                    }

                    //groups
                    //results = ctx.search("","(objectclass=posixGroup)", controls);
                    results = ctx.search("","cn=fsredes", controls);
                    while (results.hasMore()){
                        SearchResult searchResult = (SearchResult) results.next();
                        Attributes attributes = searchResult.getAttributes();
                        //Attribute attr = attributes.get("memberUid");
                        //String cn = (String) attr.get();

                        //System.out.println("groupCN : " + attributes.get("cn"));

                        //String member = (String) attr.get();

                        //
                        //
                        // System.out.println(attributes.get("memberUid"));
                       // System.out.println(attributes.get("memberUid").get(1));



                        /* Specify the changes to make*/

                        // ModificationItem[] mods = new ModificationItem[2];


                        /* Remove the "memberUid" attribute */
                        //mods[0] = new ModificationItem(ctx.REMOVE_ATTRIBUTE,
                          //      new BasicAttribute("memberUid"));

                        /* Replace the "mail" attribute with a new value */
                        //mods[1] = new ModificationItem(ctx.ADD_ATTRIBUTE,
                          //      new BasicAttribute("memberUid", "z.z"));

                        /* Perform the requested modifications on the named object */
                        //ctx.modifyAttributes("cn=fsredes,ou=Groups", mods);

                        //Attributes membersList = new BasicAttributes(true);
                        //membersList.put("memberUid", "wow.wow");
                        //ctx.modifyAttributes("cn=fsredes,ou=Groups", ctx.ADD_ATTRIBUTE, membersList); // might throw NamingException
                        //membersList.put("memberUid", "wow.z");
                        //ctx.modifyAttributes("cn=fsredes,ou=Groups", ctx.ADD_ATTRIBUTE, membersList);
                        //membersList.put("memberUid", "wow.x");
                        //ctx.modifyAttributes("cn=fsredes,ou=Groups", ctx.ADD_ATTRIBUTE, membersList);


                        //membersList.put("memberUid", "wow.wow");
                        //ctx.modifyAttributes("cn=fsredes,ou=Groups", ctx.REMOVE_ATTRIBUTE,membersList);


                        //System.out.println();


                    }




                    ctx.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }



            } // review option end


            else if(option.equals("loadCSV")){
                System.out.println("Load CSV File in a  mongoDB Collection.");
                System.out.println(" /**");
                System.out.println(" * comments.");
                System.out.println(" */\n");

                ReadCVS readCVS = new ReadCVS();
                readCVS.run();


            } // output option end



            else if(option.equals("importLDAPAttrs")){

                System.out.println("Import Attributes from LDAP  in a  mongoDB Collection.");
                System.out.println(" /**");
                System.out.println(" * comments.");
                System.out.println(" */\n");

                LdapMatrix ldapMatrix = new LdapMatrix();
                ldapMatrix.createMatrix();




            }


            else if(option.equals("-membersOf")){

                //java -jar sambaCircles.jar  -membersOf fsredes-w

                String groupName = "";

                if(args.length > 1){
                    groupName = args[1];
                }
                else {

                    System.out.println("\n\tPlease specify a group.");
                }



                LDAPGroup ldapGroup = new LDAPGroup();
                ldapGroup.getMembers(groupName,"both");
                ldapGroup.close();



            }


            else if(option.equals("syncAccounts")){

                System.out.println("\n");
                System.out.println("/**");
                System.out.println("* Sync Accounts lanID vs netUser on \"netUsers\" Collection");
                System.out.println("./");

                NetUser netuser = new NetUser();

                //netuser.getUserByEmployeeID("3786");
                System.out.println(netuser.getUserByEmployeeID("3578").get("uid"));
                System.out.println(netuser.getLanID("sajid.austria"));
                System.out.println(netuser.getLanID("edgar.gonzalez"));
                //netuser.syncLanID();


            }


            else if(option.equals("-isValidAccount")){

                if(args.length > 1 ){

                    String uid = args[1];
                    NetUser netUser = new NetUser();
                    netUser.isValidAccount(uid);


                }else {

                    System.out.println("\n\tPlease specify a UID.");
                }




            }
            else if(option.equals("option7")){ }
            else if(option.equals("option8")){ }
            else if(option.equals("option9")){ }
            else if(option.equals("option9")){ }
            else if(option.equals("option9")){ }
            else if(option.equals("option9")){ }
            else if(option.equals("option9")){ }

            else
                System.out.println("-"+option+"-" + " Wrong Option !¡ " );





        }
        else{
            System.out.println("\nPlease select an option ");
            System.out.println("\n\t> cleanGroups \n" +
                    "\t> loadCSV \n" +
                    "\t> importLDAPAttrs \n" +
                    "\t> -membersOf \n" +
                    "\t> syncAccounts \n" +
                    "\t> -isValidAccount ");
        }


    }



    public static int verifyUser(Hashtable env, String uid){
        /**
         * 0  => Valid User comply with all constraints.
         * 1+ => one or simply more than 0  means that it does NOT comply requirements to be a valid user
         *
         * how to  :
         * System.out.println("verifyUSer : " +verifyUser(env,"jennifer.jimenez"));
         * System.out.println("verifyUSer : " +verifyUser(env,"sajid.austria"));
         *
         */
        int validUser = 0;

        DirContext ctx = null;
        NamingEnumeration results = null;

        try {

            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "uid="+uid, controls);

            if(results.hasMore()){


                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("uid");
                String attrString = (String) attr.get();

                if(attrString.equals(uid)){

                    try{

                        attr = attributes.get("accountStatus");
                        attrString = (String) attr.get();
                        if(attrString.equals("active")){
                            //user ok
                        }else{ validUser+=1;}


                    }catch (Throwable  e){
                        //System.out.println(e);
                        System.out.println(uid + "  => accountStatus attr( Not Found )");
                    }




                } else { validUser+=1;}




            }else{ validUser =+ 1;}


            ctx.close();

        } catch (Throwable e) {
            e.printStackTrace();
        }




        return validUser;
    }


    public static void cleanGroup(Hashtable env, String groupName){

        /**
         *
         * how to :
         *   cleanGroup(env,"fsredes");
         */

        DirContext ctx = null;
        NamingEnumeration results = null;
        int  invalidCases = 0;

        try {

            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "cn="+groupName, controls);

            if(results.hasMore()){


                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attributes membersList = new BasicAttributes(true); //used to store users to be deleted

                try{


                    for(int i = 0; i < attributes.get("memberUid").size() ; i ++)
                    {
                        //System.out.println("==> : " + attributes.get("memberUid").get(i));

                        if(verifyUser(env,attributes.get("memberUid").get(i).toString()) > 0){
                            //System.out.println(attributes.get("memberUid").get(i).toString() + " --> Not Valid");
                            invalidCases+=1;
                            membersList.put("memberUid", attributes.get("memberUid").get(i).toString() );


                            try{
                                /*next line execute cleaning task*/
                                //ctx.modifyAttributes("cn="+groupName+",ou=Groups", ctx.REMOVE_ATTRIBUTE,membersList);
                                //ctx.modifyAttributes("cn="+groupName+",ou=Proxy_ACLs", ctx.REMOVE_ATTRIBUTE,membersList);
                                //ctx.modifyAttributes("cn="+groupName+",ou=Lists", ctx.REMOVE_ATTRIBUTE,membersList);
                                System.out.println("  " + attributes.get("memberUid").get(i).toString() + " --> deleted");
                            }catch (Throwable e ){}



                        }
                        else {
                            //System.out.println(attributes.get("memberUid").get(i).toString() + " --> ok");
                        }

                    }

                }catch (Throwable  e){
                    System.out.println(groupName + " => memberUid attr( Not Found )" );
                }



                //System.out.println(groupName + " : " + invalidCases);
                if(invalidCases > 0){
                    System.out.println();
                    System.out.println("  Total deleted in "+"\""+groupName+"\"" + " : " + invalidCases);
                }
                else{
                    System.out.println();
                    System.out.println("  "+"\""+groupName+"\"" + "--> is OK ");
                }





            }else{ System.out.println("\n group  "+"\""+groupName+"\""+" - Not Found");}


            ctx.close();

        } catch (Throwable e) {
            e.printStackTrace();
        }



    }

    public static void reviewGroups(Hashtable env){

        DirContext ctx = null;
        NamingEnumeration results = null;

        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            results = ctx.search("","(objectclass=posixGroup)", controls);

            while (results.hasMore()){
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();

                //System.out.println(cn);

                //Pause for 1 seconds 1000
                Thread.sleep(1000);
                System.out.println("");
                System.out.println("");
                System.out.println("--------******* "+cn+" *******-------");
                cleanGroup(env,cn);



            }



            ctx.close();

        } catch (Throwable e) {
            e.printStackTrace();
        }



    }

}




/**
 * Help   -------------------
 */


/*
JNDI
====================

https://edu.enterpriselab.ch/el/public:development:java:ldap_vs_ad
http://programcreek.com/java-api-examples/index.php?api=javax.naming.directory.ModificationItem
http://docs.oracle.com/javase/tutorial/jndi/ops/modattrs.html
http://svn.codehaus.org/plexus/tags/plexus-ftp-1.0-alpha-1/src/main/java/org/apache/ftpserver/usermanager/LdapUserManager.java
http://www-01.ibm.com/support/docview.wss?uid=swg21516788
http://docs.oracle.com/javase/8/docs/api/javax/naming/NamingException.html
http://docs.oracle.com/javase/tutorial/jndi/ldap/exceptions.html
http://code.google.com/p/java-use-examples/source/browse/trunk/src/com/aw/ad/LdapBasicExample.java?r=2
https://publib.boulder.ibm.com/infocenter/iseries/v5r3/index.jsp?topic=%2Frzatz%2F51%2Fprogram%2Fjndicntx.htm

http://www.javaworld.com/article/2076073/java-web-development/ldap-and-jndi--together-forever.html
http://www.javaworld.com/article/2076073/java-web-development/ldap-and-jndi--together-forever.html?page=2
http://docs.oracle.com/javase/tutorial/jndi/ldap/jndi.html

 */
