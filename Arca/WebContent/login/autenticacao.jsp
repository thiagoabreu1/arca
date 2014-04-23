<%
	//Verifica autenticação
	String user = null;
	if (session.getAttribute("user") == null) {
		//Usuário pulou login
		response.sendRedirect("../Arca");
	} else
		user = (String) session.getAttribute("user");
	String login = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user"))
				login = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();
		}
	}
%>
