package connect;
//Estabelece conexão com o banco de dados em PostgreSQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Estoque_java", "postgres", "void467");
		return conexao;
	}
}
