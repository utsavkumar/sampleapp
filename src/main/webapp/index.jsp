<%@page import="com.hp.fb.integration.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<%
	FBConnection fbConnection = new FBConnection();
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook Integration</title>
</head>
<body>


<div id="wrapper">
		
		<div id="header">
		<%@ include file="header.jsp"%>
		</div><!-- #header -->
		
		<div id="content">
		<div align="center">
		<a id="fblink" href="<%=fbConnection.getFBAuthUrl()%>">     <!-- here redirect the FBConnection class -->
		<img style="margin-top: 138px;" src="./img/facebookloginbutton.png" /></a>
	</div>
		</div><!-- #content -->
		
		<div id="footer">
		<%@ include file="footer.jsp"%>
		
		</div><!-- #footer -->
		
	</div><!-- #wrapper -->



	
	
</body>

</html>