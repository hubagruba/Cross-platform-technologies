package application;

import java.sql.*;
import java.util.List;
import java.util.StringJoiner;

public class DBUtil {
    /**
     * Declare JDBC Driver
     */
    private static final String JDBC_DRIVER = "org.postgresql.Driver";

    /**
     * Connection object
     */
    private static Connection conn = null;

    /**
     * Connection String
     */
    private static final String connStr = "jdbc:postgresql://localhost:5432/zoodb";

    // Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        // Setting JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");

        // Establish the Connection using Connection String
        try {
            conn = DriverManager.getConnection(connStr,"postgres","postgres");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw e;
        }

        if (conn != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
        System.out.println("Connection Close!");
    }

    // SELECTING ALL data FROM Table
    public static ResultSet getDataFromTable(String table){
        ResultSet rs = null;
        try{
            String SQL = "SELECT * from " + table;
            rs = conn.createStatement().executeQuery(SQL);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return rs;
    }

    // DELETE ONE row FROM Table
    public static void deleteRowById(String tableName, int id) {
        String sql = "DELETE FROM "+tableName+" WHERE id = " + id;
        System.out.println(tableName);
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // INSERT ONE row INTO Table
    public static void addRow(String tableName, List<String> fields, List<String> data) {

        String joinedfields = String.join(",", fields);
        String joineddata = String.join(",", data);

        String sql = "INSERT INTO "+tableName+"("+joinedfields+") VALUES ("+joineddata+")";
        System.out.println(sql);
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // UPDATE ONE row WFOM Table
    public static void updateRow(String tableName, String field, String data, String id) {
        String sql = "UPDATE "+tableName+" SET " +field+ " = '" +data+ "' WHERE id =" + id;
        System.out.println(sql);
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}