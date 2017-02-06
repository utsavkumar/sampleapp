<%@page import="java.util.Map"%>
<%@page import="com.hp.fb.integration.FBGraph"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hp.fb.integration.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

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

			<div align="center"><h1>Friends list</h1></div>

<div align="center">
<%
ArrayList<String> friendsArray =(ArrayList<String>)request.getAttribute("friendsArray");
int total_friends=friendsArray.size();
out.println("<h3>Total number of Friends:"+total_friends+"</h3>");
int i=0;
out.println("<table style='width: 50%;font-size: 24px'>");
out.println("<tr bgcolor='#4da5ff'><td align='center'><font color='white'>S.No.</font></td><td><font color='white'>Friend's Name</font></td></tr>");
String color="";
for(i=0;i<total_friends;i++)
{
	if(i%2==0)
		color="#e5f2ff";
	else
		color="#b3d9ff";
	out.println("<tr bgcolor='"+color+"'>");
	out.println("<td align='center' width='30%'>"+(i+1)+"</td>");
	out.println("<td width='70%'>"+friendsArray.get(i).toString()+"</td>");
	out.println("</tr>");
}

out.println("</table>");
%>
		

</div>

		</div><!-- #content -->
		
		<div id="footer">
		<%@ include file="footer.jsp"%>
		</div><!-- #footer -->
		
	</div><!-- #wrapper -->

</body>
</html>