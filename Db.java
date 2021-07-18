
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db 
{
    public static Connection doConnect()
    {
        Connection con=null;
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3308/lms?autoReconnect=true&useSSL=false", "root", "");
        }
        catch(SQLException e)
        {
            System.out.println("Connection Esbtalish Issue");
        }
        return con;
    }
}
