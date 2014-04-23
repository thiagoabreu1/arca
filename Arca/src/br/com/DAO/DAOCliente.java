package br.com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.DAO.Conexao;
import br.com.vo.Cliente;

public class DAOCliente {

	List<Cliente> listaCliente = new ArrayList<Cliente>();
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	//Listar todos os clientes
	public List<Cliente> listarTodosClientes() {
		Cliente c = new Cliente();
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

	//Pesquisar cliente por nome
	public List<Cliente> pesquisarCliente(Cliente cliente) {
		List<Cliente> listaClientePesquisa = new ArrayList<Cliente>();
		listaClientePesquisa = new ArrayList<Cliente>();

		try {
			con = Conexao.getConexao();
			String SQL = "SELECT * FROM cliente WHERE nome LIKE '%" + cliente.getNome() + "%'";
			System.out.println("[Query]: " + SQL);
			stmt = con.prepareStatement(SQL);
			rs = stmt.executeQuery(SQL);

			while(rs.next()){
				cliente = new Cliente();
				cliente.setId(Integer.parseInt(rs.getString("id_cliente")));
				System.out.println(cliente.getId());
				cliente.setCpf(rs.getString("cpf"));
				System.out.println(cliente.getCpf());
				cliente.setNome(rs.getString("nome"));
				System.out.println(cliente.getNome());
				cliente.setTelefone(Integer.parseInt(rs.getString("telefone")));
				System.out.println(cliente.getTelefone());
				cliente.setBairro(Integer.parseInt(rs.getString("bairro")));
				System.out.println(cliente.getBairro());
				cliente.setEndereco(rs.getString("endereco"));
				System.out.println(cliente.getEndereco());
				listaClientePesquisa.add(cliente);
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
	public void cadastrarCliente(Cliente cliente) {
		try{
			con = Conexao.getConexao();

			String SQL = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(SQL);

			stmt.setString(1, null);
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getNome());
			stmt.setInt(4, cliente.getTelefone());
			stmt.setInt(5, cliente.getBairro());
			stmt.setString(6, cliente.getEndereco());


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

	//Excluir cliente do banco de dados

	public void excluirCliente(Cliente cliente){

		try {
			con = Conexao.getConexao();
			String SQL = "DELETE * FROM cliente WHERE id =" + cliente.getId() + "%'";
			System.out.println("[Query]: " + SQL);
			stmt = con.prepareStatement(SQL);
			stmt.executeQuery(SQL);

			cliente.setId(Integer.parseInt(rs.getString("id_cliente")));

		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
