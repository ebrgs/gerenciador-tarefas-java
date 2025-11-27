import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/estudos_java";
        String usuario = "postgres";
        String senha = "admin";

        return DriverManager.getConnection(url, usuario, senha);
    }
}
