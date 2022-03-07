/******************************************************************************
Project     : Sample2
Description : Esempio uso Connection POOL
Programmer  : Baccan Matteo
******************************************************************************/

import java.sql.*;
import java.util.*;
import java.text.*;

public class esempio2 {

   public static void main( String []argv ) {
      ...

      // ConnectionPoolDS implementa l'interfaccia ConnectionPoolDataSource
      com.acme.jdbc.ConnectionPoolDS cpds =
            new com.acme.jdbc.ConnectionPoolDS();

      // Impostiamo i valori di connessione
      cpds.setServerName("nome_server");
      cpds.setDatabaseName("database");
      cpds.setPortNumber(9040);
      cpds.setDescription("Connection pool per bookserver");

      // Registriamo ConnectionPoolDS con JNDI, usando il nome logico
      // "jdbc/pool/bookserver_pool"
      Context ctx = new InitialContext();
      ctx.bind("jdbc/pool/nome_server", cpds);





      // Quando serve prendo una connessione dal datapool
      Context ctx = new InitialContext();
      DataSource ds = (DataSource)ctx.lookup("jdbc/pool/nome_server");
      Connection con = ds.getConnection("utente","password");

      ...

   }

}
