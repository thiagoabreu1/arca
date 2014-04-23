package br.com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import br.com.DAO.Conexao;
import br.com.vo.Autenticador;

public class LoginServlet extends HttpServlet {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String acao = req.getParameter("acao");
		Autenticador auth = new Autenticador();

		if ("indexLogin".equals(acao)) {
			auth.setLogin(req.getParameter("login"));
			auth.setSenha(req.getParameter("senha"));


			try {
				con = Conexao.getConexao();
				String SQL = "SELECT * FROM login WHERE login = '" +auth.getLogin()+ "' AND senha = '" + auth.getSenha() + "'";
				stmt = con.createStatement();
				rs = stmt.executeQuery(SQL);

				if (rs.first() == true) {
					HttpSession session = req.getSession();
					session.setAttribute("user", auth.getLogin());
					//Timeout da session 30*60 = 30min
					session.setMaxInactiveInterval(30*60);
					Cookie login = new Cookie("user", auth.getLogin());
					login.setMaxAge(30*60);
					resp.addCookie(login);
					resp.sendRedirect("home.jsp");
					System.out.println("[LOG] Usuário "+auth.getLogin()+" fez login.");
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
					PrintWriter out= resp.getWriter();
					out.println("<font color=red>Either user name or password is wrong.</font>");
					rd.include(req, resp);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally{

				try {
					if (stmt != null)
						stmt.close();
				} catch (Exception e) {
					System.out.println("Erro ao fechar conexão - Ignorado");
				}

			}
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

}
