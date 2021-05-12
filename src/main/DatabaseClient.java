
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nouro
 */
public class DatabaseClient {
      public String clientname;
       public boolean checkpassword (String username , String passowrd) throws SQLException{
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hRE8Y9tEAK9rUkD");
          PreparedStatement stmt =con.prepareStatement("INSERT INTO userinfo VALUES ( ? , ? , ?)");
          ResultSet rs = stmt.executeQuery("SELECT * FROM userinfo ");
          while (rs.next()){
                 String email1 =rs.getString("E-mail");
                 String username1 = rs.getString("username");
                 String pss=rs.getString("password");
                 if (email1.equals(username) || username1.equals(username)  && pss.equals(passowrd) )
                     return true ;
             }
      return false ;
    }
    public String setstringtolabel (String s) {
           return s;
    } 
    public String saymyname (){
    return clientname;
    }
    public boolean checkdatabase(String email ,String username ) { 
      
         try {
             
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hRE8Y9tEAK9rUkD");
             PreparedStatement stmt =con.prepareStatement("INSERT INTO userinfo VALUES ( ? , ? , ?)");
             ResultSet rs = stmt.executeQuery("SELECT * FROM userinfo ");
             while (rs.next()){
                 String na =rs.getString("E-mail");
                 String n = rs.getString("username");
                 if (na.equals(email) || n.equals(username)){
                   clientname=username;  
                   return false;
                   
                 }
                     
                 
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
         }
        clientname=username; 
        return true ;
    
    }
 
        String databasework(String x , String y , String z){ 
          boolean checks = false;
         MainUiController test = new MainUiController();
        String a =x;
        String b =y; 
        String c = z;
        System.out.print(a+ " " +b );
          try {
               Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hRE8Y9tEAK9rUkD");
                //System.out.println("database works");
                //System.out.println("enter the name ");
                PreparedStatement stmt =con.prepareStatement("INSERT INTO userinfo VALUES ( ? , ? , ?)");
                String name = a;
                String email =b;
                String passowrd =c;
                boolean check=checkdatabase(email,name);
                  if (!check){
                   setstringtolabel("you have already this email or username "); 
                    //System.out.println("you have already this email r username ");
                  }
                  else {
                      checks=true;
               setstringtolabel("welcome");   
                stmt.setString(1,a);   
                stmt.setString(2,b);
                stmt.setString(3, c);
                stmt.execute();
                
                 ResultSet rs = stmt.executeQuery("SELECT * FROM userinfo ");
                while (rs.next())
                {
                   String name1 =rs.getString("username");
                   String email1 = rs.getString("E-mail");
                   String Password = rs.getString("password");
                   System.out.println(name1  + " " + email1 + " " + Password);
                }
                  }
               
               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
            }           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           if (!checks)
                 return "you have username";
             return "welcome";
            
}
        public boolean checkroom (String room ) throws SQLException{
            
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hRE8Y9tEAK9rUkD");
             PreparedStatement stmt =con.prepareStatement("INSERT INTO rooms VALUES ( ? , ? , ?)");
             ResultSet rs = stmt.executeQuery("SELECT * FROM rooms ");
             while (rs.next()){
                 String na =rs.getString("idrooms");
                 
                 if (na.equals(room))
                     return true;
        }
             return false;
}
        
        public boolean createroom(String room){
            boolean checks=false;
             try {
               Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hRE8Y9tEAK9rUkD");
                PreparedStatement stmt =con.prepareStatement("INSERT INTO rooms VALUES ( ? )");
                String roomname =room ;

                boolean check=checkroom(roomname);
                
                  if (check){
                   setstringtolabel("you have already this email r username "); 
                    //System.out.println("you have already this email r username ");
                  }
                  else {
                    checks=true;
                    setstringtolabel("welcome");   
                    stmt.setString(1,roomname);   
                    stmt.execute();
              
                 ResultSet rs = stmt.executeQuery("SELECT * FROM rooms ");
                while (rs.next())
                {
                   String name1 =rs.getString("idrooms");
               
      
                }
                  }
               
               
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
            }           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           if (checks)
                 return false;
             return true;
            
        }
}
        