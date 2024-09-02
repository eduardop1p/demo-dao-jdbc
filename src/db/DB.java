package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            try {
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        // String path =
        // Paths.get("").toAbsolutePath().getParent().resolve("db.properties").toString();
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException err) {
            throw new DbException(err.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
    }
}
