package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/sms_system";
    private static final String USER = "postgres"; // Your PostgreSQL username
    private static final String PASSWORD = "root"; // Your PostgreSQL password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Load driver
            System.out.println("connected");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found!", e);
        }
    }
}
