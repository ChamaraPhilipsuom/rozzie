package org.rozzie.processor;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class RozzieApp implements CommandLineRunner {

	 @Override
	 public void run(String... args) throws Exception {
//		 try {
//			 InputStream inputStream = new FileInputStream
//					 ("/home/chamarap/Desktop/docs/rozzie/schemas/model.json");
//			 JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
//			 Schema schema = SchemaLoader.load(rawSchema);
//			 InputStream example = new FileInputStream("/home/chamarap/Desktop/docs/rozzie/schemas/example.json");
//			 JSONObject sample = new JSONObject(new JSONTokener(example));
//			 schema.validate(sample); // throws a ValidationException if this object is invalid
//		 } catch (ValidationException e) {
//			 System.out.println("Exception caught");
//		 }

	 }
	public static void main(String[] args) {
		SpringApplication.run(RozzieApp.class, args);
	}
}
