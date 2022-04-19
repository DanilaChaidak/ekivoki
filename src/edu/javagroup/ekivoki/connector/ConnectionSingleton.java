package edu.javagroup.ekivoki.connector;

import java.sql.Connection;
import java.util.Optional;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private final Optional<Connection> connection;

    public ConnectionSingleton(Optional<Connection> connection) {
        this.connection = connection;
    }

    public static ConnectionSingleton instance(Optional<Connection> connection) {
        if (instance == null) {
            instance = new ConnectionSingleton(Optional.empty());
        }
        if (connection.isPresent()) {
            instance = new ConnectionSingleton(connection);
        }
        return instance;
    }

    public Optional<Connection> getConnection() {
        return connection;
    }
}
