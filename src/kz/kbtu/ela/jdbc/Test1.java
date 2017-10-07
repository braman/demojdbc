package kz.kbtu.ela.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {

    //C - create
    //R - read 
    //U - update 
    //D - delete
    
    public static void main(String[] args) throws SQLException {
        
        Connection newConn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            String url = "jdbc:postgresql://localhost:5434/kmk_hl";
            String username = "postgres";
            String password = "root";
            
            newConn = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        
        System.out.println("Everything is OK!");
        
        Statement stmt = newConn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        
        while (rs.next()) {
            String email = rs.getString("EMAIL");
            System.out.println(email);
        }
        
        
        /*
        PreparedStatement pstm = newConn.prepareStatement("INSERT INTO users VALUES(?, ?, ? ,?, ?, ? ,?)");
        
        pstm.setLong(1, 1L);
        pstm.setString(2, "Medet");
        pstm.setString(3, "Ashimgaliyev");
        pstm.setString(4, "medet@gmail.com");
        pstm.setString(5, "234h234u");
        pstm.setString(6, "23j42l3");
        pstm.setString(7, "A");

        
        int rows = pstm.executeUpdate();
        
        
        System.out.printf("%d rows were inserted!\n", rows);
        */
        //update
        
        PreparedStatement pstm = newConn.prepareStatement("UPDATE users set email = ?");
        
        pstm.setString(1, "a.medet@gmail.com");

        int rows = pstm.executeUpdate();
        
        System.out.printf("%d row(s) were updated!\n", rows);
        
        //delete
        
        pstm = newConn.prepareStatement("DELETE FROM users WHERE user_id = ?");
        
        pstm.setLong(1, 1L);

        rows = pstm.executeUpdate();
        
        System.out.printf("%d row(s) were deleted!\n", rows);
        
        
        
        rs.close();
        stmt.close();
        newConn.close();
        
        
    }
    
}
