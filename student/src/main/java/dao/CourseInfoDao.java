package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.CourseInfo;

public class CourseInfoDao {

  public static void courseInfoAdd(Long code, String name, Double score){
    String sql = "insert into course_info(course_code,course_name,course_score) values(?,?,?)";
    Connection conn = null; //连接数据库
    PreparedStatement pstmt = null; //创建statement
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      //占位符赋值
      pstmt.setLong(1,code);
      pstmt.setString(2,name);
      pstmt.setDouble(3,score);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

  public static void courseInfoDelete(Long code, String name){
    String sql = "delete from course_info where course_code = ?,course_name = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setLong(1,code);
      pstmt.setString(2,name);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

  //课程code不可更改
  public static void courseInfoModify(Long code, String name, Double score){
    String sql = "update course_info set course_name=?,course_score=? where course_code=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1,name);
      pstmt.setDouble(2,score);
      pstmt.setLong(3,code);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();

    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

  public static CourseInfo courseInfoQuery(Long code, String name){
    String sql = "select * from course_info where course_code=? and course_name=?";
    Connection conn = null;
    PreparedStatement pstmt = null;

    ResultSet rs=null;
    CourseInfo courseInfo = null;
    try{
      conn=DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setLong(1,code);
      pstmt.setString(2,name);
      rs =(ResultSet) pstmt.executeQuery();

      courseInfo.setCode(code);
      courseInfo.setName(name);
      courseInfo.setScore(rs.getDouble("course_score"));

    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
    return courseInfo;
  }

}
