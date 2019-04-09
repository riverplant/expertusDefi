package com.riverplant.expertus.defi.outils;
import java.sql.*;
import org.h2.tools.Server;

public class H2DBUtil {

    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/defi";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    public static void startDatabase() {
        try {
            Server.createTcpServer("-tcpPort", "8082", "-tcpAllowOthers").start();
            Class.forName("org.h2.Driver");
            initSchema();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initSchema() throws SQLException {
        try (Connection conn = getConnection()) {
            dropTable(conn, "employees_projects");
            dropTable(conn, "projects");
            dropTable(conn, "employees");
            dropTable(conn, "departments");
            conn.createStatement().execute("CREATE TABLE departments (id int, name varchar);");
            conn.createStatement().execute("CREATE TABLE employees (id int, first_name varchar, last_name varchar, department_id int);");
            conn.createStatement().execute("CREATE TABLE projects (id int, title varchar, start_date date, end_date date);");
            conn.createStatement().execute("CREATE TABLE employees_projects (project_id int, employee_id int);");
        }
    }

  
    private static void dropTable(Connection conn, String tableName) {
        try {
            conn.createStatement().execute("DROP TABLE " + tableName + ";");
        } catch (SQLException sqle) {
            // Clean-up tables
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.
                getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connection Established: " + conn.getMetaData().getDatabaseProductName() + "/" + conn.getCatalog());
        return conn;
    }

    public static void stopDatabase() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    public static void displayTableRows(Connection conn, String tableName) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM " + tableName);
        System.out.println();
        System.out.println("================");
        System.out.println("TABLE NAME : " + tableName);
        displayResultSet(resultSet);
    }

    public static void displayResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("----------------");
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.print("\t" + metaData.getColumnName(i));
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.print("\t" + resultSet.getString(i));
            }
            System.out.println();
        }
        System.out.println("================");
    }
}
