<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" type="text/css" href="style.css" />	
<style>
    ul { display:table; margin:0 auto;}
</style>
</head>
<body>

<div id="wrapper">
		
		<div id="header">
		<%@ include file="header.jsp"%>
		</div><!-- #header -->
		
		<div id="content">
		<div align="center"><h1>Welcome to Facebook</h1> </div>
	
		<ul style="font-size: 24px">
  <li><a href='PersonalInfoServlet'>Personal info</a></li>
  <li><a href='FriendsServlet'>Friends list</a></li>
  <li><a href='LikesServlet'>User likes</a></li>
  <li><a href='ProfilePictureServlet'>User Profile</a></li>
  <li><a href='/facebook-integration'>Logout</a></li>
</ul>
		</div><!-- #content -->
		
		<div id="footer">
		<%@ include file="footer.jsp"%>
		</div><!-- #footer -->
		
	</div><!-- #wrapper -->



</body>

</html>