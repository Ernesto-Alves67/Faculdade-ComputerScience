package connect;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	private String base;
	
	public Conexao(String op) {
		base = op;
	}
	
    public Connection getConnection() throws SQLException {
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base, "root", "1234");
        return conexao;
    }
}

   
 
