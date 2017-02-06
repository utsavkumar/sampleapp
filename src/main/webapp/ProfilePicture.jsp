<%@page import="java.util.Map"%>
<%@page import="com.hp.fb.integration.FBGraph"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		
		<div align="center"> <h1>User Profile</h1></div>

<%
ArrayList<String> profilePictureArray =(ArrayList<String>)request.getAttribute("profilePictureArray");
%>
<font color="white"></font>
<div align="center">
<table style="width: 50%;font-size: 24px" border="0" >

<tr  bgcolor="#e5f2ff">
	<td align="left">First Name:</td>
	<td><%=profilePictureArray.get(0) %></td>
</tr>
<tr bgcolor="#b3d9ff">
	<td>Last name:</td>
	<td><%=profilePictureArray.get(1) %></td>
</tr>

<tr bgcolor="#e5f2ff">
	<td>Profile Image:</td>
	<td align="center"><img src=<%=profilePictureArray.get(3).toString() %> width="100px" height='100px'> </td>

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