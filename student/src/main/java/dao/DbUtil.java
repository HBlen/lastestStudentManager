package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

  //初始化驱动并建立JDBC和数据库之间的连接
  public static Connection getConnection(){
    Connection conn = null;
    try{
      //初始化驱动
      Class.forName("com.mysql.jdbc.Driver");
      conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanager?characterEncoding=UTF-8","root","newpass");
    }catch (ClassNotFoundException e){
      e.printStackTrace();
    }catch (SQLException e){
      e.printStackTrace();
    }
    return conn;
  }

  //关闭资源
  public static void close(PreparedStatement pstmt){
    if(pstmt != null){
      try{
        pstmt.close();
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }

  public static void close(Connection conn){
    if(conn != null){
      try{
        conn.close();
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }

  public static void close(ResultSet rs){
    if(rs != null){
      try{
        rs.close();
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }


}
