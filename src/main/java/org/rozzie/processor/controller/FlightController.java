package org.rozzie.processor.controller;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(Constants.RequestUri.Flight.CONTROLLER)
public class FlightController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.VALIDATE_JSON, method = RequestMethod.POST, produces = "application/json")
	public void validateJSON(@RequestParam String json) {

		try {
			InputStream inputStream = new FileInputStream("/home/chamarap/Desktop/docs/rozzie/schemas/model.json");
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			InputStream example = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
			JSONObject sample = new JSONObject(new JSONTokener(example));
			schema.validate(sample); // throws a ValidationException if this object is invalid
		} catch (ValidationException | FileNotFoundException e) {
			System.out.println("Exception caught");
		}
	}

}
