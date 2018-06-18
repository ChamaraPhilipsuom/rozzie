package org.rozzie.processor.models;

import org.rozzie.processor.utils.Constants;
import org.rozzie.processor.utils.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Schema {
	private String tableName;
	private Properties dataFieldMapping;
	private List<String> keys;

	public Schema(){
	    dataFieldMapping = new Properties();
	    keys = new ArrayList<>();
	    keys.add(Constants.DEFAULT_KEY);
	    dataFieldMapping.put(Constants.DEFAULT_KEY, Util.getTime());
    }

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Properties getDataFieldMapping() {
		return dataFieldMapping;
	}

	public void addDataFieldMapping(Properties properties) {
		this.dataFieldMapping.putAll(properties);
	}

	public List<String> getKeys() {
		return keys;
	}

	public void addKeys(List<String> keys) {
		this.keys.addAll(keys);
	}

	public void addKey(String key) {
		if (!this.keys.contains(key)) {
			this.keys.add(key);
		}
	}

	public List<String> getColumns(){
		List<String> columns = new ArrayList<>();
		for(Object key : dataFieldMapping.keySet()){
			columns.add((String)key);
		}
        return columns;
    }
}
