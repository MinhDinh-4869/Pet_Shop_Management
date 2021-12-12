package petshop.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    private static Connection c = null;
    public static void ConnectDatabase() throws SQLException, ClassNotFoundException
    {
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "megane4869";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        finally
        {
            c.close();
        }
        System.out.println("Database connected successfully");
    }
    public static void DisconnectDatabase() throws SQLException
    {
        try {
            c.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("Database disconnected successfully!");
    }
}
