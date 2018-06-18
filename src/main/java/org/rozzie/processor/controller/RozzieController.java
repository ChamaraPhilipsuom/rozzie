package org.rozzie.processor.controller;

import org.rozzie.processor.exceptions.WrongKeyException;
import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.services.RuleService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RequestUri.Rozzie.CONTROLLER)
public class RozzieController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private RuleService ruleService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.PROCESS_JSON, method = RequestMethod.POST, produces = "application/json")
	public void processJSON(@RequestParam String json, @RequestParam String messageType) {
		try {
			cassandraService.saveJSON(json, messageType);
			ruleService.runRulesFortheMessage(json, messageType);
		} catch (WrongKeyException | WrongSchemaException e) {
			System.out.println("Error occured when processing JSON " + e.toString());
		}
	}


}
