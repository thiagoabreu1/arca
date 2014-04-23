package br.com.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//import br.servlet.instalador.InstaladorServlet;

public class Conexao {

	private static Connection con;

	private Conexao() {
	}
	public static String URL = "jdbc:mysql://localhost:3306/arca";
	public static String URL_NO_DB = "jdbc:mysql://localhost:3306/";
	public static String usuario = "root";
	public static String senha = "123123";
	
	public static Connection getConexao() {
		//Singleton
		if (con == null) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(URL, usuario, senha);

			} catch (SQLException sqlE) {
				System.out.println("Falha ao acessar o BD - COD: " + sqlE.getErrorCode());
				if (sqlE.getErrorCode() == 1049) {
					System.out.println("Banco de dados não encontrado, execute o instalador!");
				}else{
					sqlE.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Driver mysql não encontrado");
			}
		}

		return con;
	}
	
	
}
