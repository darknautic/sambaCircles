package com.codeinmotion;

/**
 * Created by s47id on 22/04/2014.
 */


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Database {


    public String _ip;
    public int _port;
    public String _dbName;
    private DB db;
    private MongoClient mongoClient;


    public Database(){}


    public void connect(){
        try {
            //MongoClient mongoClient = new MongoClient(_ip,_port);
            mongoClient = new MongoClient(_ip,_port);
            db = mongoClient.getDB(_dbName);

        }catch (UnknownHostException e){
            System.err.println("Connection Failed: " + e);

        }
    }


    public void close(){
        mongoClient.close();
    }




    public void insertDocumentS(String collectionName,HashMap<String,Object> newDoc){
        DBCollection collection = db.getCollection(collectionName);


        BasicDBObject newDocument = new BasicDBObject("employeeID",newDoc.get("employeeID")).
                append("fullName", newDoc.get("fullName")).
                append("lanID", newDoc.get("lanID"));


        //newDocument = new BasicDBObject(newDoc);
        collection.insert(newDocument);


    }

    public void insertDocument(String collectionName,List<String> keys,HashMap<String,Object> newDoc){
        DBCollection collection = db.getCollection(collectionName);

        BasicDBObject newDocument = new BasicDBObject();

        for(int i  = 0 ; i < keys.size() ; i++){
            newDocument.append(keys.get(i),newDoc.get(keys.get(i)));
        }

        System.out.println(newDocument);
        collection.insert(newDocument);


    }


    public HashMap<String,Object> select(HashMap<String,Object> query,String collectionName){
        HashMap<String,Object> resultSet = new HashMap<String, Object>();

        DBCollection collection = db.getCollection(collectionName);
        BasicDBObject findQuery = new BasicDBObject();

        for ( String key : query.keySet() ) {
            findQuery.append(key,query.get(key));
        }


        //System.out.println("Query =>" + findQuery );

        DBCursor cursor = collection.find(findQuery);
        DBObject doc;


        try {
            while(cursor.hasNext()) {

                //System.out.println(cursor.next());

                doc = cursor.next();
                for( String key : doc.keySet()){
                    //System.out.println(key);
                    //System.out.println(doc.get(key));
                    resultSet.put(key,doc.get(key));
                }


            }
        } finally {
            cursor.close();
        }



        return resultSet;
    }



    public List<HashMap> getAllDocuments(String collectionName) {
        List<HashMap> resultSet = new ArrayList<HashMap>();
        DBCollection collection = db.getCollection(collectionName);


        DBCursor cursor = collection.find();

        int count = 0;

        try {
            while(cursor.hasNext()) {

                resultSet.add((HashMap) cursor.next());
                count+=1;

            }



        } finally {
            cursor.close();
        }



        return resultSet;
    }


}
