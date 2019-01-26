package com.usecase.webapp;
import static spark.Spark.*;
import com.google.gson.*;
import com.google.protobuf.util.*;
import java.io.*;
import java.io.File;
public class App 
{
    public static String filePath="/tmp/items";
    public static void main( String[] args )
    {
  	   get("/hello", (request, response) -> "Hello World!");
   	   post("/post/data", "application/json", (request,response) -> {
		
		ItemProtos.Item.Builder user = ItemProtos.Item.newBuilder();		   	
		JsonFormat.parser().merge(request.body(), user);
		ItemProtos.Items items = ItemProtos.Items.newBuilder().addItem(user).build();
		FileOutputStream fos = new FileOutputStream(filePath);
		items.writeTo(fos);
       		Thread.sleep(5000);

		File file = new File(filePath);
        if(file.delete()){
            System.out.println("Deleted");
        }else{
	    System.out.println("Fail to Delete");
	}

		return "Done"; 
	   });
    }
}
