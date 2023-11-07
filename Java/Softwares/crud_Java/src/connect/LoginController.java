package connect;
//import java.sql.Connection;
import java.sql.SQLException;

import crud_Java.CriarConta;


public class LoginController {

	public boolean criarConta(CriarConta conta) throws SQLException {
		
		LoginDAO crConta = new LoginDAO();
		
		if(crConta.criarConta(conta.getNomeField().getText(), conta.getLoginField().getText(),
				conta.getEmailField().getText(), String.valueOf(conta.getPasswordField().getPassword()),
				conta.getPrivSelecionada(), conta.getCargSelecionada())) {
			return true;
		}else {
		return false;}
	}
}
