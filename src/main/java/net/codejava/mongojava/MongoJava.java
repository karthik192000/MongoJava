/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.mongojava;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author karthik
 */
public class MongoJava {
    public static void main(String[] args){
        try{
        Scanner in = new Scanner(System.in);
        String username = in.next();
        String url = "https://api.github.com/users/" + username;
        //httprequest
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var Client = HttpClient.newBuilder().build();
        var response = Client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String json1 = response.body();
        MongoClient client = MongoClients.create("mongodb+srv://Admin92000:Karthik020@cluster0.3dtkv.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("SampleDB");
        MongoCollection col = db.getCollection("content");
        //BsonDocument document = BsonDocument.parse(response.body());
        Document doc = Document.parse(json1);
        col.insertOne(doc);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
