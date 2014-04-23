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
import br.com.DAO.DAOCliente;
import br.com.vo.Cliente;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet{
	
	DAOCliente dao = new DAOCliente();
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
			dao.cadastrarCliente(c);
			resp.sendRedirect("home.jsp");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String acao = req.getParameter("acao");	

		if("listar".equals(acao)){
			HttpSession session = req.getSession();
			session.setAttribute("listaDeCliente", dao.listarTodosClientes());
			resp.sendRedirect("home.jsp?p=cliente/consultar.jsp");
		}
		if("pesquisar".equals(acao)){
			HttpSession session = req.getSession();
			c.setNome(req.getParameter("nome"));
			session.setAttribute("listaDeClientePesquisa", dao.pesquisarCliente(c));
			System.out.println("PESQUISA:" + c.getNome());
			resp.sendRedirect("home.jsp?p=cliente/pesquisar.jsp");
		}
	}
}
