<%@include file="../login/autenticacao.jsp"%>
<%@ page import="br.com.vo.Cliente, java.util.*"%>
<%!List<Cliente> cliente;%>
<%	cliente = (List<Cliente>) session.getAttribute("listaDeClientePesquisa"); %>

<script type="text/javascript" src="js/arca.js"></script>

<div class="container">
	<hr>
	<div class="row">
		<div class="panel panel-primary filterable">
			<div class="panel-heading">
				<h3 class="panel-title">Clientes</h3>
				<div class="pull-right">
					<button class="btn btn-default btn-xs btn-filter">
						<span class="glyphicon glyphicon-filter"></span> Filtrar
					</button>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr class="filters">
						<th><input type="text" class="form-control" placeholder="#"
							disabled></th>
						<th><input type="text" class="form-control"
							placeholder="Nome" disabled></th>
						<th><input type="text" class="form-control"
							placeholder="Telefone" disabled></th>
						<th><input type="text" class="form-control"
							placeholder="Endereço" disabled></th>
					</tr>
				</thead>
				<tbody>
						<%
							for (int i = 0; i < cliente.size(); i++) {
								Cliente c = cliente.get(i);
						out.print("<tr>");
						out.print("<td>" + c.getId() +		 "</td>");
						out.print("<td>" + c.getNome() +	 "</td>");
						out.print("<td>" + c.getTelefone() + "</td>");
						out.print("<td>" + c.getEndereco() + "</td>");
						out.print("</tr>");
							}	
						%>
				
				</tbody>
			</table>
		</div>
	</div>
</div>