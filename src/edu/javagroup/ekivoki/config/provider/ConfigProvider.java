package edu.javagroup.ekivoki.config.provider;

import edu.javagroup.ekivoki.config.reader.ConfigReader;

import java.util.Map;

import static edu.javagroup.ekivoki.constants.Constants.CONNECTION_CONF;
import static edu.javagroup.ekivoki.constants.Constants.QUERY_LIST;

public class ConfigProvider {

    public Map<String, String> getConnectionConf() {
        return new ConfigReader().getMap(CONNECTION_CONF);
    }

    public Map<String, String> queryMap() {
        return new ConfigReader().getMap(QUERY_LIST);
    }
}
