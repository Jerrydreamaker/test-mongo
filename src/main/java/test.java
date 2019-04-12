/**
 * Created by Dreamaker on 2017/7/24.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class test {
    public static void main(String[] args) {
        int a = 0;
        try {
            ServerAddress serverAddress = new ServerAddress("192.168.129.103",27017);
            List addrs = new ArrayList();
            addrs.add(serverAddress);
            MongoClient mongoClient = new MongoClient(addrs);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("MongoDB Connection-----------------Successfully");
            MongoCollection collection = mongoDatabase.getCollection("coll");
            FindIterable findIterable  = collection.find();
            MongoCursor <Document>mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                a=a+1;
                Document document = mongoCursor.next();
                Set set = document.keySet();
                Iterator <String>it = set.iterator();
                while(it.hasNext()){
                    String tags = it.next();
                    if(tags.equals("_id")){
                        continue;
                    }
                    //test.addRow(tableName, document.getString("_id"), columnFamilys, tags, document.getString(tags));
                }
                System.out.println("Insert Into HBase Success"+"This is the "+a+" data");
            }
            System.out.println("Compelete All Insert");
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+ ":" +e.getMessage());
        }
    }
}






