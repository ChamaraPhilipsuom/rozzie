package org.rozzie.processor.controller;

import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.RuleService;
import org.rozzie.processor.utils.Constants;
import org.rozzie.processor.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping(Constants.RequestUri.Schema.CONTROLLER)
public class SchemaController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private RuleService ruleService;

	@RequestMapping(value = Constants.RequestUri.Schema.CREATE_JSON_SCHEMA, method = RequestMethod.POST, produces = "application/json")
	public void createJSONSchema(@RequestParam String json, @RequestParam String messageType) {
        String fileLocation = "/home/chamara/Desktop/docs/rozzie/schemas/" + messageType + "-model.json";
		Util.saveFile(json,fileLocation);
	}

	@RequestMapping(value = Constants.RequestUri.Schema.CREATE_DB_SCHEMA, method = RequestMethod.POST, produces = "application/json")
	public void createDBSchema(@RequestParam String dbSchema, @RequestParam String messageType) {
		try {
			cassandraService.createDBSchema(dbSchema, messageType);
		} catch (WrongSchemaException e) {
			System.out.println("Error Occured in creating DBSchema. " + e.toString());
		}

	}

	@RequestMapping(value = Constants.RequestUri.Schema.CREATE_RULE, method = RequestMethod.POST, produces = "application/json")
	public void createRules(@RequestParam String rules, @RequestParam String messageType){
		ruleService.createRules(rules,messageType);
	}
}
