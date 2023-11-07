package cad_livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connect.Conexao;

public class LivrosDAO {
	public boolean insertLivro(String editora, String titulo, String isbn) throws SQLException {
		Connection conexao = new Conexao("nada").getConnection();
		String sql = "INSERT INTO livros_cadastrados (editora, titulo, isbn) VALUES ('"+editora+"', '"+titulo+"', '"+isbn+"') ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();
		return true;
	}
	
	public boolean editLivro(String editora, String titulo, String isbn, String codi) throws SQLException {
	    Connection conexao = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        conexao = new Conexao("nada").getConnection();
	        String sql = "UPDATE livros_cadastrados SET editora = ?, titulo = ? , isbn = ? WHERE código = ?";
	        preparedStatement = conexao.prepareStatement(sql);
	        preparedStatement.setString(1, editora);
	        preparedStatement.setString(2, titulo);
	        preparedStatement.setString(3, isbn);
	        preparedStatement.setString(4, codi);

	        int linhasAfetadas = preparedStatement.executeUpdate();
	        return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi atualizada
	    } finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (conexao != null) {
	            conexao.close();
	        }
	    }
	}

		
		
	
	
	public boolean delRegistro(String cod) throws SQLException {
	    Connection conexao = null;
	    PreparedStatement statement = null;
	    
	    try {
	        // Criar a consulta SQL para deletar o registro com base nos critérios
	    	conexao = new Conexao("nada").getConnection();
	    	String query = "DELETE FROM livros_cadastrados WHERE código = ? ";
	        statement = conexao.prepareStatement(query);
	        statement.setString(1, cod);
	        
	        
	        if (statement.executeUpdate() > 0) {
	            return true; // Registro deletado com sucesso
	        } else {
	            System.out.println("Erro ao deletar registro");
	            return false; // Falha ao deletar registro
	        }
	    } finally {
	        if (statement != null) {
	            statement.close();
	        }
	        conexao.close();
	    }
	}

	
}
