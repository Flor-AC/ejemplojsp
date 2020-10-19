package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    
    private final String url = "jdbc:postgresql://localhost:5432/crud?user=postgres&password=2198";
    
    private Connection connection = null;

    private static ConnectionDB instance = null;

    private ConnectionDB() {
         try{
            Class.forName("org.postgresql.Driver");     
       }

       catch(ClassNotFoundException e)
       {
          e.printStackTrace();
       }
        try {
            connection = DriverManager.getConnection(url);
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.INFO, "Conexión exitosa");
        } catch (SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "There was an error", e);
        }
    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }

        return instance;
    }

    public boolean execute(String sql) {
        boolean result = false;
        int rows = 0;

        try {
            Statement st = connection.createStatement();

            rows = st.executeUpdate(sql);

            if (rows != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ResultSet executeQuery(String sql) {
        ResultSet result = null;

        try {
            Statement st = connection.createStatement();

            result = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}