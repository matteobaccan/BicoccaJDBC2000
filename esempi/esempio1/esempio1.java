/******************************************************************************
Project     : Sample1
Description : Esempio uso JDBC-ODBC
Programmer  : Baccan Matteo
******************************************************************************/

import java.sql.*;
import java.util.*;
import java.text.*;

public class esempio1 {

   public static void main( String []argv ) {
      //String driver     = "com.ms.jdbc.odbc.JdbcOdbcDriver";
      System.out.println( "Partenza ..." );

      String driver     = "sun.jdbc.odbc.JdbcOdbcDriver";
      String connection = "jdbc:odbc:local";
      String user       = "";
      String password   = "";

      Connection con = null;
      try {
         Class.forName( driver );
         System.out.println( "Connessione ..." );
         con = DriverManager.getConnection( connection, user, password );

         System.out.println( "Select ..." );
         String cCall = "SELECT top 10 * FROM Customers";
         Statement stmt = con.createStatement();
         ResultSet tableSQL = stmt.executeQuery(cCall);

         if( tableSQL!=null ) {
            while( tableSQL.next() ){
               String cID  = tableSQL.getString( "CompanyName" );

               System.out.println( cID  );

            }
         }

      } catch (Throwable e) {
         e.printStackTrace();
         System.out.println( e.getMessage() );
      }

      try {
         con.close();
      } catch (Throwable e) { }

   }

}
