<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%

String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "intern";
String userId = "root";
String password = "sar115";
%>

<%

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Retrieve data from database </strong></font></h2>
<table border="2cm">
<tr>
<td>name</td>
<td>mob no</td>
<td>clg name</td>
<td>clg location</td>
<td>course</td>
<td>dob</td>
</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();%>
<% 



String sql ="SELECT * FROM UserDetails ";

resultSet = statement.executeQuery(sql); 
while(resultSet.next()){

%>



<tr>
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("mobno") %></td>
<td><%=resultSet.getString("clgname") %></td>
<td><%=resultSet.getString("clgloc") %></td>
<td><%=resultSet.getString("course") %></td>
<td><%=resultSet.getString("dob") %></td>
</tr>



<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>