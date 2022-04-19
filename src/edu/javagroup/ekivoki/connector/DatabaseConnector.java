package edu.javagroup.ekivoki.connector;

import edu.javagroup.ekivoki.config.provider.ConfigProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Optional;

public class DatabaseConnector {

    public void createConnection() {
        ConnectionSingleton.instance(
                createConnection(
                        new ConfigProvider().getConnectionConf()
                )
        );
    }

    private Optional<Connection> createConnection(Map<String, String> conf) {
        try {
            Class.forName(conf.getOrDefault("driver", "com.mysql.jdbc.Driver"));
            return Optional.of(
                    DriverManager.getConnection(
                            conf.get("url"),
                            conf.get("username"),
                            conf.get("password")
                    )
            );
        } catch (Exception ex) {
            System.out.println(this.getClass().getName() + " / " + ex.getMessage());
        }
        return Optional.empty();
    }
}
