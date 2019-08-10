package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.StudentGrade;
import domain.StudentInfo;
import domain.resp.InfoRespData;

public class OperationDao {

  public static Map<String, String> queryByStuCodeAndYear(Long stuCode, int year) {
    StudentInfo studentInfo = StudentInfoDao.studentInfoQuery(stuCode);
    Map<String, String> map = new HashMap<String, String>();
    map.put("学号", stuCode.toString());
    map.put("姓名", studentInfo.getName());
    map.put("班级", studentInfo.getClassname());
    map.put("学年", Integer.toString(year));

    String sql =
        "select grade.course_code,"
            + "grade.score,"
            + "course.course_name from student_grade grade "
            + "inner join course_info course "
            + "on grade.course_code=course.course_code "
            + "where grade.stu_code=? and year=?";
    Connection conn = null;
    PreparedStatement pstmt = null;

    ResultSet rs = null;
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.setLong(1, stuCode);
      pstmt.setInt(2, year);
      rs = (ResultSet) pstmt.executeQuery();

      while (rs.next()) {
        map.put(rs.getString("course_name"), Double.toString(rs.getDouble("score")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);
    }
    return map;
  }
  // select uid, sum(money) as total from tb_user_finance group by uid order by total desc limit 10;
  public static List<Map<String, String>> getTop10() {
    String sql =
        "select student.stu_name as 姓名,"
            + "student.stu_code as 学号,"
            + "student.stu_class as 班级,"
            + "grade.year as 学年,"
            + "avg(grade.score) as 平均分 from student_grade grade "
            + "inner join student_info student on grade.stu_code=student.stu_code "
            + "group by grade.stu_code,grade.year order by 平均分 DESC limit 10";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      int i = 0;
      while (rs.next()) {
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("姓名", rs.getString("姓名"));
        stringMap.put("学号", Long.toString(rs.getLong("学号")));
        stringMap.put("班级", rs.getString("班级"));
        stringMap.put("学年", Integer.toString(rs.getInt("学年")));
        stringMap.put("平均分", Double.toString(rs.getDouble("平均分")));
        i++;
        stringMap.put("名次", Integer.toString(i));
        mapList.add(stringMap);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);
      return mapList;
    }
  }

  public static List<Map<String, String>> getGpa() {//student.stu_name as 姓名,""+ "student.stu_class as 班级,"

    String sql =
        "select grade.stu_code as 学号,"
            + "grade.year as 学年,"
            + "grade.course_code as 课程号,"
            + "course.course_score as 学分,"
            + "grade.score as 成绩 "
            + "from student_grade grade "
            + "inner join course_info course on grade.course_code=course.course_code ";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    try {
      conn = DbUtil.getConnection();
      pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      int i = 0;
      while (rs.next()) {
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("学号", Long.toString(rs.getLong("学号")));
        stringMap.put("学年",Integer.toString(rs.getInt("学年")));
        stringMap.put("课程号", Long.toString(rs.getLong("课程号")));
        stringMap.put("学分",Double.toString(rs.getDouble("学分")));
        stringMap.put("成绩",Double.toString(rs.getDouble("成绩")));

        mapList.add(stringMap);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbUtil.close(pstmt);
      DbUtil.close(conn);
      return mapList;
    }




  }
}
