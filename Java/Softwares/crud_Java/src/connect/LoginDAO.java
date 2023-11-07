package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LoginDAO {
	/* *
	  	ACESSO AO BANCO DE DADOS
	
	 * */
	
	public boolean criarConta(String nome, String login, String email, String senha, String privilegio, String cargo) throws SQLException {
		Connection conexao = new Conexao().getConnection();
		String sql = "INSERT INTO users_1 (nome_usuario, login_usuario, email_usuario, senha_usuario, permissao_usuario, cargo_usuario) VALUES ('"+nome+"', '"+login+"', '"+email+"', crypt('"+senha+"', gen_salt('bf')), '"+privilegio+"', '"+cargo+"' ) ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();
		return true;
	}
	public boolean verificarLogin(String login, String senha) throws SQLException {
		//System.out.println(senha);
	    Connection conexao = new Conexao().getConnection();
	    String sql = "SELECT * FROM users_1 WHERE login_usuario = ? AND senha_usuario = crypt(?, senha_usuario)";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    statement.setString(1, login);
	    statement.setString(2, senha);
	    ResultSet resultSet = statement.executeQuery();
	    boolean loginValido = resultSet.next();
	    conexao.close();
	    return loginValido;
	}

}
