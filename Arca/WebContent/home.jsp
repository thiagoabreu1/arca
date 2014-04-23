<title>Arca de Noé Festas</title>

<%@include file="login/autenticacao.jsp" %>
<% String pagina = request.getParameter("p");
	//Envia para página inicial caso ainda não exista parameter
	if (request.getParameter("p") == null)
			pagina = "home.html";
%>


<script type="text/javascript" src="js/jquery-latest.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/arca.js"></script>
<link href="css/arca.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.css" rel="stylesheet" media="screen">
<script> 
    $(function(){
      $("#menu").load("menu.html");
      $("#pagina_central").load("<% out.write(pagina); %>");
    });
</script>

<body>
	<div id="menu"></div>
	<div id="pagina_central"></div>
</body>


<div class="container">
	<div class="navbar-template text-center">
<!-- TEXTO CENTRAL -->
	</div>
</div>