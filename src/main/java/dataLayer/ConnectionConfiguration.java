package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {
    public static Connection getConnection(){

        Connection connection = null;

        String DB_Url = System.getenv("JDBC_CONNECTION_STRING");
        String DB_User = System.getenv("JDBC_USER");
        String DB_Password = System.getenv("JDBC_PASSWORD");
        String DB_Connection_String = DB_Url + "?user=" + DB_User + "&password=" + DB_Password;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_Url, DB_User, DB_Password);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(DB_Connection_String);
        }
        return connection;
    }
}
