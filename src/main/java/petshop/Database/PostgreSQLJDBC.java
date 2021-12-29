package petshop.Database;

import java.sql.*;

public class PostgreSQLJDBC {
    private static Connection c = null;
    private static Statement stmt = null;
    public static void ConnectDatabase() throws SQLException, ClassNotFoundException
    {
        String url = "jdbc:postgresql://john.db.elephantsql.com:5432/ftwadznd";
        String user = "ftwadznd";
        String password = "bi2s-Mm5wQ6UDK-J9I4wKdzyBJetNDmF";
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
        System.out.println("Database connected successfully");
    }
    public static void DisconnectDatabase() throws SQLException
    {
        try {
            c.close();
            System.out.println("Database disconnected successfully!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void Insert(String insert_command) throws SQLException {
        stmt  = c.createStatement();
        stmt.executeUpdate(insert_command);
        stmt.close();
        //c.commit();
    }

    public static ResultSet Read(String read_command) throws SQLException{
        stmt = c.createStatement();
        //stmt.close();
        return stmt.executeQuery(read_command);
    }
    public static void CloseStatement() throws SQLException{
        try{
            stmt.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String S2S(String input)
    {
        return ("'" + input + "'");
    }
    public static int CountResult(String count_command)
    {
        int result = 0;
        try
        {
            ResultSet rs = PostgreSQLJDBC.Read(count_command);
            rs.next();
            result = rs.getInt(1);
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
