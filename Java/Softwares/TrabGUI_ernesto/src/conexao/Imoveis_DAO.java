package conexao;


import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Imoveis_DAO {
	
	public boolean crConta(String nome, String login, String senha) throws SQLException {
		Connection conexao = new Conexao().getConnection();
	    
	    String sql = "INSERT INTO usuarios (nome, login, senha) VALUES (?, ?, SHA2(?, 256))";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    
	    
	    statement.setString(1, nome);
	    statement.setString(2, login);
	    statement.setString(3, senha);
	    
	    try {
	        statement.executeUpdate(); 
	        return true;
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	        return false;
	    } finally {
	        statement.close();
	        conexao.close();
	    }
	}
	
	
	public boolean verificarLogin(String login, String senha) throws SQLException {
		//System.out.println(senha);
	    Connection conexao = new Conexao().getConnection();
	    String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = SHA2(?, 256)";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    statement.setString(1, login);
	    statement.setString(2, senha);
	    ResultSet resultSet = statement.executeQuery();
	    boolean loginValido = resultSet.next();
	    conexao.close();
	    return loginValido;
	}
	
	public boolean crAcordo(String idImovel, String tipo, String intgr1, String intgr2, String mediador, Object durAcordo) throws SQLException {
		Connection conexao = new Conexao().getConnection();
	    
	    String sql = "INSERT INTO acordos (id_imovel, tipo_acordo, integrante_1, integrante_2, mediador, data, duracao_acordo ) VALUES (?, ?, ?, ?, ?, ?, ? )";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    
	    if(durAcordo == null) {
	    	durAcordo = 0;
	    }
	    
	    Date currentDate = new Date();
	    java.sql.Timestamp data_acordo = new java.sql.Timestamp(currentDate.getTime());
	    System.out.println(""+idImovel+" "+tipo+" "+intgr1+" "+intgr2+" "+mediador+" "+durAcordo);
	    statement.setString(1, idImovel);
	    statement.setString(2, tipo);
	    statement.setString(3, intgr1);
	    statement.setString(4, intgr2);
	    statement.setString(5, mediador);
	    statement.setString(6, ""+data_acordo);
	    statement.setString(7, ""+durAcordo);
	    
	    try {
	        statement.executeUpdate(); 
	        return true;
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	        return false;
	    } finally {
	        statement.close();
	        conexao.close();
	    }
	}
	
	public boolean cadastrarClientes(String nome, String endereco, String cpf, String telefone, String tipo) throws SQLException {
		Connection conexao = new Conexao().getConnection();
	    
	    String sql = "INSERT INTO clientes (nome, endereco, cpf, telefone, tipo_cliente) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    
	    // Tratando a string sql
	    statement.setString(1, nome);
	    statement.setString(2, endereco);
	    statement.setString(3, cpf);
	    statement.setString(4, telefone);
	    statement.setString(5, tipo);
	    
	    try {
	        statement.executeUpdate(); // Use executeUpdate() para INSERT
	        return true;
	    } catch (SQLException e) {
	        // Trate a exceção conforme necessário
	        e.printStackTrace();
	        return false;
	    } finally {
	        statement.close();
	        conexao.close();
	    }
	}
	
	public boolean cadastrarImoveis(String tipo, String area, String ano, String zona, String valor, String situacao, String id_prop) throws SQLException {
		Connection conexao = new Conexao().getConnection();
	    
	    String sql = "INSERT INTO imoveis (tipologia, area_construida, ano_construcao, zona, valor, situacao, id_proprietario) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    
	    // Tratando a string sql
	    statement.setString(1, tipo);
	    statement.setString(2, area);
	    statement.setString(3, ano);
	    statement.setString(4, zona);
	    statement.setString(5, valor);
	    statement.setString(6, situacao);
	    statement.setString(7, id_prop);
	    
	    try {
	        statement.executeUpdate(); 
	        return true;
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
			JOptionPane.showMessageDialog(null, "O cliente informa não possui cadastro no sistema.", "Falha no Cadastro", JOptionPane.INFORMATION_MESSAGE);

	        return false;
	    } finally {
	        statement.close();
	        conexao.close();
	    }
	}
	
	
    public static int realizarBusca(String nome) throws SQLException {
    	Connection conexao = new Conexao().getConnection();
	    String sql = "SELECT id FROM clientes WHERE nome = ? ";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    statement.setString(1, nome);
	    
	    ResultSet resultSet = statement.executeQuery();
	    int idCliente = -1; // Valor padrão caso nenhum resultado seja encontrado
	    
	    if (resultSet.next()) {
	        idCliente = resultSet.getInt("id");
	    }
	    
	    conexao.close();
	    return idCliente;
    }
    
    public static void updateSituacao(String id) throws SQLException {
    	Connection conexao = new Conexao().getConnection();
	    
	    String sql = "UPDATE imoveis SET situacao = ? WHERE id = ?";
	    PreparedStatement statement = conexao.prepareStatement(sql);
	    
	    
	    statement.setString(1, "Vendida");
	    statement.setString(2, ""+id);
	    
	    
	    try {
	        statement.executeUpdate();
	        System.out.println("ok");
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	        System.out.println("falha");
	    } finally {
	        statement.close();
	        conexao.close();
	    }
    }

}
