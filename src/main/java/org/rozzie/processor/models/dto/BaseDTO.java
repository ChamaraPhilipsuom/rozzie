package org.rozzie.processor.models.dto;

import org.rozzie.processor.models.dao.cassandra.BaseCas;
import org.rozzie.processor.models.dao.neo.BaseNeo;

import java.util.Objects;

public interface BaseDTO {
	public BaseDTO completeObject(Object... objects);
}
