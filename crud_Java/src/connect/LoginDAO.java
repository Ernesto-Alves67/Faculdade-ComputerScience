package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAO {
	
	
	public void criarConta(String nome, String login, String email, char[] senha) throws SQLException {
		Connection conexao = new Conexao().getConnection();
		String sql = "INSERT INTO users_1 (nomeusuario, loginusuario, emailusuario, senhausuario) VALUES\r\n"
				+ "('"+nome+"', '"+login+"', '"+email+"', crypt('"+senha+"', gen_salt('bf'))) ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();
	}
}
