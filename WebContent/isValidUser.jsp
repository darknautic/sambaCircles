<%@ page import="java.util.*" %>
<%@ page import="com.codeinmotion.*" %>

<%

String uid = request.getParameter("uid").toString();

NetUser netUser =  new NetUser();
out.println(netUser.isValidAccount(uid));

%>
