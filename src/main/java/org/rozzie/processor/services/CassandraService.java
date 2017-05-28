package org.rozzie.processor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Service;


@Service
@EnableCassandraRepositories(basePackages = "org.rozzie.processor.repositories.cassandra")
public class CassandraService {



}
