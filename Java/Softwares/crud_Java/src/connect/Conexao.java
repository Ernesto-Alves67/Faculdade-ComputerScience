package connect;
//Estabelece conexão com o banco de dados em PostgreSQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	public Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Estoque_java", "postgres", "void467");
		return conexao;
	}
	
	public static boolean verificarCredenciais(String login, String senha) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Configurar a conexão com o banco de dados
	    	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Estoque_java", "postgres", "void467");


	        // Criar a consulta SQL para verificar as credenciais
	        String query = "SELECT * FROM users_1 WHERE login_usuario = ? AND senha_usuario = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, login);
	        statement.setString(2, senha);

	        // Executar a consulta
	        resultSet = statement.executeQuery();

	        // Verificar se a consulta retornou algum resultado
	        if (resultSet.next()) {
	        	System.out.println(" "+resultSet.next());
	            return true; // Credenciais válidas
	        } else {
	            return false; // Credenciais inválidas
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    } finally {
	        // Fechar as conexões, declarações e resultados
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
