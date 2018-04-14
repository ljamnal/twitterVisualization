package com.uottawa.twittervisual.business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/dbServlet")
public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void geoMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Creating a Mongo client 
	      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
	   
	      // Creating Credentials 
	      MongoCredential credential; 
	      credential = MongoCredential.createCredential("sampleUser", "local", 
	         "password".toCharArray()); 
	      System.out.println("Checking connection");  
	      
	      
	      // Accessing the database 
	      MongoDatabase database = mongo.getDatabase("cdpTweets"); 
  
	      
//	      //Creating a collection 
//	      database.createCollection("sampleCollection"); 
//	      System.out.println("Collection created successfully");
	      
	      // Retieving a collection
	      MongoCollection<Document> collection = database.getCollection("tweets-2018-4-3"); 
	      System.out.println("Collection MyDB selected successfully");
	      
	   // Getting the iterable object 
	     // FindIterable<Document> iterDoc = collection.find(); 
	      //int i = 1; 

	      
	      JSONArray jsonArray = new JSONArray();
	      
	      List<Document> employees = (List<Document>) collection.find().into(
					new ArrayList<Document>());
	      
	      int i=0;
	      int x=0;
	      int n=1;
	      for(Document emp : employees)
	      {
	    	  i++;
	    	  
	    	  
	    	  Document location = (Document) emp.get("place");	    	  
	    	  if(location!=null) { 
	    		  JSONObject jsonObject = new JSONObject();
					System.out.println("country = " + location.getString("country"));
					System.out.println("country code = " + location.getString("country_code"));
					x++;
					
					      jsonObject.put("name", location.getString("country"));
				          jsonObject.put("id", location.getString("country_code"));
				          jsonObject.put("area", n++);
				          jsonObject.put("density", "999");
				          jsonObject.put("population", "4422");
				          
				          jsonArray.put(jsonObject);
	    	  }
	      }
	      
	      
          System.out.println(jsonArray);
	      
	      System.out.println("Total number of tweets:"+i);
	      System.out.println("Geo enabled tweets:"+x);
	      System.out.println(jsonArray);
	    
	      
	      
	      // Getting the iterator 
	      //Iterator it = iterDoc.iterator(); 
	    
//	      while (it.hasNext()) {  
//	    	  
//	    	  it.
//	         //System.out.println(it.next());  
//	      i++; 
//	      }
	      

          
          FileWriter fileWriter = new FileWriter("C:\\Users\\ankur\\Documents\\GitHub\\twitterVisualization\\WebContent\\resources\\data\\data.json");
        
          

          // Writting the jsonObject into sample.json
          fileWriter.write(jsonArray.toString());
          fileWriter.close();

          System.out.println("JSON Object Successfully written to the file!!");

	      PrintWriter out = response.getWriter(  ); 
	      response.setContentType("text/html"); 
	      out.println("Connection successful");
		
		
		
	}

}
