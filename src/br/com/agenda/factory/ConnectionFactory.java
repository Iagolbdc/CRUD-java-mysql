package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// nome do usuario
	private static final String USERNAME = "root";
	
	// Senha do banco
	private static final String PASSWORD = "";
	
	// caminho banco de dados, porta, nome do banco
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	/* 
	 * conexão com o banco de dados
	*/
	
	public static Connection createConnectionToMysql()  throws Exception{
		//faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//cria conexao com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception{
		// recuperar uma conexão com o banco de dados se houver
		
		Connection con = createConnectionToMysql();
		
		// testar se a conexão é nula
		
		if(con != null) {
			System.out.println("Conexão obetida com sucesso");
			con.close();
		}
	}
	
}
