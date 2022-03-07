<html>

<body bgcolor="white">

<%
	String select = request.getParameter("select");
%>

<%

   out.println( "Partenza ..." );

   String driver     = "sun.jdbc.odbc.JdbcOdbcDriver";
   String connection = "jdbc:odbc:local";
   String user       = "";
   String password   = "";

   java.sql.Connection con = null;
   try {
      Class.forName( driver );

      out.println( "Connessione ..." );
      con = java.sql.DriverManager.getConnection( connection, user, password );

      out.println( "Select ..." );
      String cCall = select; //"SELECT top 10 * FROM Customers";
      java.sql.Statement stmt = con.createStatement();
      java.sql.ResultSet tableSQL = stmt.executeQuery(cCall);

      if( tableSQL!=null ) {
         while( tableSQL.next() ){
            String cID  = tableSQL.getString( "CompanyName" );

            out.println( "<br>"+cID );

         }
      }

   } catch (Throwable e) {
      e.printStackTrace();
      out.println( e.getMessage() );
   }

   try {
      con.close();
   } catch (Throwable e) { }


%>

</body>
</html>
