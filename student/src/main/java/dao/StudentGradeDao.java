package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.CourseInfo;
import domain.StudentGrade;

public class StudentGradeDao {

  public static boolean studentGradeUpdateScore(
      Long stuCode, int year, Long courseCode, Double score) {
    String sql =
        "update student_grade set course_score=? where stu_code=? and year=? and course_code=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    int resp = 0;
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setDouble(1, score);
      pstmt.setLong(2, stuCode);
      pstmt.setInt(3, year);
      pstmt.setLong(4, courseCode);

      resp = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);
      if (resp > 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static boolean studentGradeAdd(Long stuCode, int year, Long courseCode, Double score) {
    String sql = "insert into student_grade(stu_code,year,course_code,score) values(?,?,?,?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    int resp = 0;
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setDouble(1, stuCode);
      pstmt.setInt(2, year);
      pstmt.setLong(3, courseCode);
      pstmt.setDouble(4, score);

      resp = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);

      if (resp > 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static List<StudentGrade> studentGradeQuery(Long code, int year) {
    String sql = "select * from course_info where course_code=? and course_name=?";
    Connection conn = null;
    PreparedStatement pstmt = null;

    ResultSet rs = null;
    List<StudentGrade> studentGradeList = null;
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);


      pstmt.setLong(1, code);
      pstmt.setInt(2, year);
      rs = (ResultSet) pstmt.executeQuery();

      while (rs.next()) {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStuCode(code);
        studentGrade.setYear(rs.getInt("year"));
        studentGrade.setCourseCode(rs.getLong("course_code"));
        studentGrade.setScore(rs.getDouble("course_score"));

        studentGradeList.add(studentGrade);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
    return studentGradeList;
  }
}
