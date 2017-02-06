<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Map"%>
<%@page import="com.hp.fb.integration.FBGraph"%>
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

<div align="center">
<h1>User Likes</h1>
<%
ArrayList<String> likedPages =(ArrayList<String>) request.getAttribute("likedPages");
ArrayList<String> likesOnPages =(ArrayList<String>) request.getAttribute("likesOnPages");
int lengthPages=likedPages.size();
int i=0;
out.println("<table style='width: 80%;font-size: 24px' border='0'>");
out.println("<tr bgcolor='#4da5ff'><td align='center'><font color='white'>S.No.</font></td><td><font color='white'>Page Name</font></td><td><font color='white'>Likes on page</font></td></tr>");
String color="";
for(i=0;i<lengthPages;i++)
{
	if(i%2==0)
		color="#e5f2ff";
	else
		color="#b3d9ff";
	out.println("<tr bgcolor='"+color+"'>");
	out.println("<td align='center' width='20%'>"+(i+1)+"</td>");
	out.println("<td width='60%'>"+likedPages.get(i).toString()+"</td>");
	out.println("<td width='20%'>"+likesOnPages.get(i).toString()+"</td>");
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