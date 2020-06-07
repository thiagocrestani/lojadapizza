
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%

    List<Integer> listadesabores = new ArrayList<Integer>();
    if (session.getAttribute("listadesabores") != null) {
        listadesabores = (List) session.getAttribute("listadesabores");
    }
    listadesabores.add(Integer.parseInt(request.getParameter("id")));
    session.setAttribute("listadesabores", listadesabores);
%>
