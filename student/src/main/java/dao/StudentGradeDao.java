package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentGradeDao {


  public static void studentGradeUpdateScore(Long code, String name, Double score){
    String sql = "update student_grade set course_score=? where stu_code=? and stu_name=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    try{
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setDouble(1,score);
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
