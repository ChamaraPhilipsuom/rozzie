package org.rozzie.processor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableNeo4jRepositories(basePackages = "org.rozzie.processor.repositories.neo")
public class NeoService {

}
