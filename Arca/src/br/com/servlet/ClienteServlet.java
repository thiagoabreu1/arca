package br.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.DAO.Conexao;
import br.com.vo.Cliente;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet{

	Cliente c = new Cliente();
	List<Cliente> listaCliente = new ArrayList<Cliente>();
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String acao = req.getParameter("acao");

		if("novoCliente".equals(acao)){
			c = new Cliente();
			c.setCpf(req.getParameter("cpf"));
			c.setNome(req.getParameter("nome"));
			c.setTelefone(Integer.parseInt(req.getParameter("telefone")));
			c.setBairro(Integer.parseInt(req.getParameter("bairro")));
			c.setEndereco(req.getParameter("endereco"));
			cadastrarCliente(c);
			resp.sendRedirect("home.jsp");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String acao = req.getParameter("acao");	
		String pesquisa = req.getParameter("pesquisa");

		if("listar".equals(acao)){
			HttpSession session = req.getSession();
			System.out.println("PESQUISA:" + acao);
			session.setAttribute("listaDeCliente", montaListaCliente());
			resp.sendRedirect("home.jsp?p=cliente/consultar.jsp");
		}
		if("pesquisar".equals(acao)){
			HttpSession session = req.getSession();
			session.setAttribute("listaDeClientePesquisa", montaListaClientePesquisa(pesquisa));
			resp.sendRedirect("home.jsp?p=cliente/pesquisar.jsp");
		}
	}



	//Listar cliente 19/04/2014
	private List<Cliente> montaListaCliente() {
		listaCliente = new ArrayList<Cliente>();

		try {
			con = Conexao.getConexao();
			String SQL = "SELECT * FROM cliente";
			stmt = con.prepareStatement(SQL);
			rs = stmt.executeQuery(SQL);

			while(rs.next()){
				c = new Cliente();
				c.setId(Integer.parseInt(rs.getString("id_cliente")));
				c.setCpf(rs.getString("cpf"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(Integer.parseInt(rs.getString("telefone")));
				c.setBairro(Integer.parseInt(rs.getString("bairro")));
				c.setEndereco(rs.getString("endereco"));
				listaCliente.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCliente;
	}

	private List<Cliente> montaListaClientePesquisa(String pesquisa) {
		List<Cliente> listaClientePesquisa = new ArrayList<Cliente>();
		listaClientePesquisa = new ArrayList<Cliente>();

		try {
			con = Conexao.getConexao();
			String SQL = "SELECT * FROM cliente WHERE nome LIKE '%" + pesquisa + "%'";
			System.out.println("[Query]: " + SQL);
			stmt = con.prepareStatement(SQL);
			rs = stmt.executeQuery(SQL);

			while(rs.next()){
				c = new Cliente();
				c.setId(Integer.parseInt(rs.getString("id_cliente")));
				c.setCpf(rs.getString("cpf"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(Integer.parseInt(rs.getString("telefone")));
				c.setBairro(Integer.parseInt(rs.getString("bairro")));
				c.setEndereco(rs.getString("endereco"));
				listaClientePesquisa.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaClientePesquisa;
	}

	//Cadastrar cliente 19/04/2014
	private void cadastrarCliente(Cliente c) {
		try{
			con = Conexao.getConexao();

			String SQL = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(SQL);

			stmt.setString(1, null);
			stmt.setString(2, c.getCpf());
			stmt.setString(3, c.getNome());
			stmt.setInt(4, c.getTelefone());
			stmt.setInt(5, c.getBairro());
			stmt.setString(6, c.getEndereco());


			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Fim cadastrar cliente

}
