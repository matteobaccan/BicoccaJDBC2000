// {$R frmQuery.JFM}

import java.awt.*;
import java.sql.*;

// Class frmQuery
public class frmQuery extends Frame
{
   final int MenuBarHeight = 0;

   // Component Declaration
   public Button Button1;
   public TextField driver;
   public TextField connessione;
   public TextField utente;
   public TextField password;
   public Label Label1;
   public Label Label2;
   public Label Label3;
   public Label Label4;
   public TextArea error;
   public FileDialog OpenDlg1;
   public TextArea query;
   public Button Exec;
   // End of Component Declaration

   // Constructor
   public frmQuery()
   {
      // Frame Initialization
      setForeground(Color.black);
      setBackground(Color.lightGray);
      setFont(new Font("TimesRoman",Font.BOLD,12));
      setTitle("Aggiornamento base dati");
      setLayout(null);
      // End of Frame Initialization

      // Component Initialization
      Button1 = new Button("Esci");
      Button1.setFont(new Font("Dialog",Font.BOLD,12));
      driver = new TextField("sun.jdbc.odbc.JdbcOdbcDriver");
      driver.setForeground(Color.black);
      driver.setBackground(Color.white);
      driver.setFont(new Font("Dialog",Font.BOLD,12));
      connessione = new TextField("jdbc:odbc:local");
      connessione.setForeground(Color.black);
      connessione.setBackground(Color.white);
      connessione.setFont(new Font("Dialog",Font.BOLD,12));
      utente = new TextField("");
      utente.setForeground(Color.black);
      utente.setBackground(Color.white);
      utente.setFont(new Font("Dialog",Font.BOLD,12));
      password = new TextField("");
      password.setForeground(Color.black);
      password.setBackground(Color.white);
      password.setFont(new Font("Dialog",Font.BOLD,12));
      Label1 = new Label("Driver",Label.LEFT);
      Label1.setFont(new Font("Dialog",Font.BOLD,12));
      Label2 = new Label("Connessione",Label.LEFT);
      Label2.setFont(new Font("Dialog",Font.BOLD,12));
      Label3 = new Label("Utente",Label.LEFT);
      Label3.setFont(new Font("Dialog",Font.BOLD,12));
      Label4 = new Label("Password",Label.LEFT);
      Label4.setFont(new Font("Dialog",Font.BOLD,12));
      error = new TextArea("");
      error.setForeground(Color.black);
      error.setBackground(Color.white);
      error.setFont(new Font("Dialog",Font.BOLD,12));
      OpenDlg1 = new FileDialog(this);
      OpenDlg1.setTitle("Open");
      OpenDlg1.setMode(FileDialog.LOAD);
      OpenDlg1.setDirectory("");
      OpenDlg1.setFile("");
      query = new TextArea("");
      query.setForeground(Color.black);
      query.setBackground(Color.white);
      query.setFont(new Font("Dialog",Font.BOLD,12));
      Exec = new Button("Exec");
      Exec.setFont(new Font("Dialog",Font.BOLD,12));
      // End of Component Initialization

      // Add()s
      add(Exec);
      add(query);
      add(error);
      add(Label4);
      add(Label3);
      add(Label2);
      add(Label1);
      add(password);
      add(utente);
      add(connessione);
      add(driver);
      add(Button1);
      // End of Add()s

      InitialPositionSet();
   }

   public void InitialPositionSet()
   {
      // InitialPositionSet()
      reshape(27,32,757,534);
      Button1.reshape(680,33+MenuBarHeight,55,25);
      driver.reshape(143,28+MenuBarHeight,361,27);
      connessione.reshape(144,64+MenuBarHeight,362,27);
      utente.reshape(144,96+MenuBarHeight,161,27);
      password.reshape(461,97+MenuBarHeight,163,27);
      Label1.reshape(13,30+MenuBarHeight,74,19);
      Label2.reshape(12,67+MenuBarHeight,111,19);
      Label3.reshape(12,104+MenuBarHeight,106,19);
      Label4.reshape(333,98+MenuBarHeight,107,19);
      error.reshape(17,248+MenuBarHeight,713,270);
      query.reshape(16,144+MenuBarHeight,649,85);
      Exec.reshape(678,144+MenuBarHeight,60,25);
      // End of InitialPositionSet()
   }

   public boolean handleEvent(Event evt)
   {
      // handleEvent()
      if (evt.id == Event.WINDOW_DESTROY && evt.target == this) frmQuery_WindowDestroy(evt.target);
      else if (evt.id == Event.ACTION_EVENT && evt.target == Button1) Button1_Action(evt.target);
      else if (evt.id == Event.ACTION_EVENT && evt.target == Exec) Exec_Action(evt.target);
      // End of handleEvent()

      return super.handleEvent(evt);
   }

   // main()
   public static void main(String args[])
   {
      frmQuery frmQuery = new frmQuery();
      frmQuery.InitialPositionSet();
      frmQuery.show();
   } // End of main()

   // Event Handling Routines
   public void frmQuery_WindowDestroy(Object target)
   {
      System.exit(0);
   }

   public void Exec_Action(Object target)
   {
      DriverManager.setLogStream(System.out);		//
      Connection con=null;
      try {
         Class.forName( driver.getText().trim() );

         con = DriverManager.getConnection( connessione.getText(), utente.getText(), password.getText() );

         error.setText("");

         Statement stmt     = con.createStatement();
         ResultSet tableSQL = stmt.executeQuery( query.getText() );
         ResultSetMetaData md = tableSQL.getMetaData();

         StringBuffer cMessage = new StringBuffer();
         for( int nCol=1; nCol<=md.getColumnCount(); nCol++ )
            cMessage.append( md.getColumnName(nCol) +"\t" );
         cMessage.append( "\n" );
         error.append( cMessage.toString() );

         while( tableSQL.next() ){
            cMessage.setLength(0);
            for( int nCol=1; nCol<=md.getColumnCount(); nCol++ )
               cMessage.append( tableSQL.getString(nCol) +"\t" );
            cMessage.append( "\n" );
            error.append( cMessage.toString() );
         }
         tableSQL.close();
         stmt.close();

      } catch (Throwable e) {
         error.setText( e.toString() );
         e.printStackTrace();
         System.out.println( e.getMessage() );
      }

      try {
         con.close();
      } catch (Throwable e) {
      }
   }

   public void Button1_Action(Object target)
   {
      System.exit(0);
   }

   // End of Event Handling Routines

} // End of Class frmQuery
