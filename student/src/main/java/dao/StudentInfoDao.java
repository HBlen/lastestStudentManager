package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.StudentInfo;

public class StudentInfoDao {

  public static void studentInfoAdd(Long code, String name, String sex, int age, String classname, int state){
    String sql = "insert into student_info(stu_code,stu_name,stu_sex,stu_age,stu_class,stu_state) values(?,?,?,?,?,?)";
    Connection conn = null; //连接数据库
    PreparedStatement pstmt = null; //创建statement
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      //占位符赋值
      pstmt.setLong(1,code);
      pstmt.setString(2,name);
      pstmt.setString(3,sex);
      pstmt.setInt(4,age);
      pstmt.setString(5,classname);
      pstmt.setInt(6,state);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

  public static void studentInfoDelete(Long code, String name){
    String sql = "delete from student_info where stu_code = ?,stu_name = ?";
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

  //学生code不可更改
  public static void studentInfoModify(Long code, String name, String sex, int age, String classname, int state){
    String sql = "update student_info set stu_name=?,stu_sex=?,stu_age=?,stu_class=?,stu_state=? where stu_code=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1,name);
      pstmt.setString(2,sex);
      pstmt.setInt(3,age);
      pstmt.setString(4,classname);
      pstmt.setInt(5,state);
      pstmt.setLong(6,code);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();

    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

  public static StudentInfo studentInfoQuery(Long code, String name){
    String sql = "select * from student_info where stu_code=? and stu_name=?";
    Connection conn = null;
    PreparedStatement pstmt = null;

    ResultSet rs=null;
    StudentInfo studentInfo = null;
    try{
      conn=DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setLong(1,code);
      pstmt.setString(2,name);
      rs =(ResultSet) pstmt.executeQuery();

      studentInfo.setCode(code);
      studentInfo.setName(name);
      studentInfo.setSex(rs.getString("stu_sex"));
      studentInfo.setAge(rs.getInt("stu_age"));
      studentInfo.setClassname(rs.getString("stu_class"));
      studentInfo.setState(rs.getInt("stu_state"));

    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
    return studentInfo;
  }


  public static void studentInfoUpdateState(Long code, String name, int state){
    String sql = "update student_info set stu_state=? where stu_code=? and stu_name=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setInt(1,state);
      pstmt.setLong(2,code);
      pstmt.setString(3,name);

      pstmt.executeUpdate();
    }catch (SQLException e){
      e.printStackTrace();

    }finally{
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
  }

}
