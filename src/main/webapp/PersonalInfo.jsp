<%@page import="java.util.Map"%>
<%@page import="com.hp.fb.integration.FBGraph"%>
<%@page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body>

<div id="wrapper">
		
		<div id="header">
		<%@ include file="header.jsp"%>
		</div><!-- #header -->
		
		<div id="content">
		
		<div align="center"> <h1>Personal Info</h1></div>

<%

ArrayList<String> personalInfoArray=(ArrayList<String>)request.getAttribute("personalInfoArray");

%>
<div align="center">
<table style="width: 50%;font-size: 24px" >
<tr bgcolor="#b3d9ff">
	<td width="50%" >ID:</td>
	<td width="50%"><%=personalInfoArray.get(0)%></td>
</tr>
<tr bgcolor="#e5f2ff">
	<td align="left">First Name:</td>
	<td><%=personalInfoArray.get(1)%></td>
</tr>
<tr bgcolor="#b3d9ff">
	<td>Last name:</td>
	<td><%=personalInfoArray.get(2)%></td>
</tr>
<tr bgcolor="#e5f2ff">
	<td>User name:</td>
	<td><%=personalInfoArray.get(3)%></td>
</tr>
<tr bgcolor="#b3d9ff">
	<td>Gender:</td>
	<td><%=personalInfoArray.get(4) %></td>
</tr>
<tr bgcolor="#e5f2ff">
	<td>Email:</td>
	<td><%=personalInfoArray.get(5) %></td>
</tr>

</table>



 </div>
		
		</div><!-- #content -->
		
		<div id="footer">
		<%@ include file="footer.jsp"%>
		</div><!-- #footer -->
		
	</div><!-- #wrapper -->

</body>

</html>