package conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	public Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/imoveis", "root", "1234");
		return conexao;
	}
	
	

}
