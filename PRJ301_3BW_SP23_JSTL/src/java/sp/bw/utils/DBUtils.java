/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Huynh Van Phuot
 */
public class DBUtils {

    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "12345";

    public static Connection getConnection1()
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        // 1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. Create Connection String
        String url = "jdbc:sqlserver://localhost:1433;databaseName=UserManagement";
        // 3. Open Connnection
        conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
        return conn;
    }
    
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBCon");
        conn = ds.getConnection();
        return conn;
    }
}
