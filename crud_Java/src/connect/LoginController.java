package connect;
//import java.sql.Connection;
import java.sql.SQLException;

import crud_Java.CriarConta;


public class LoginController {

	public void criarConta(CriarConta conta) throws SQLException {
		
		LoginDAO crConta = new LoginDAO();
		
		crConta.criarConta(conta.getNomeField().getText(), conta.getLoginField().getText(), conta.getEmailField().getText(), conta.getPasswordField().getPassword());
	}
}
