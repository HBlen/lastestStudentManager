package com.blen.spring.studnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blen.spring.studnet.domain.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

@ComponentScan
@Component("operationDao")
public class OperationDao {

  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private StudentInfoDao studentInfoDao;

  public Map<String, String> queryByStuCodeAndYear(Long stuCode, int year) throws SQLException {
    StudentInfo studentInfo = new StudentInfo();
    try {
      studentInfo = studentInfoDao.studentInfoQuery(stuCode);
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
    try {
      jdbcTemplate.query(
          sql,
          new Object[] {stuCode, year},
          new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
              do {
                map.put(
                    resultSet.getString("course_name"),
                    Double.toString(resultSet.getDouble("score")));
              } while (resultSet.next());
            }
          });
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return map;
  }

  // select uid, sum(money) as total from tb_user_finance group by uid order by total desc limit 10;
  public List<Map<String, String>> getTop10() throws SQLException {
    String sql =
        "select student.stu_name,"
            + "student.stu_code,"
            + "student.stu_class,"
            + "grade.year,"
            + "avg(grade.score) as avgScore from student_grade grade "
            + "inner join student_info student on grade.stu_code=student.stu_code "
            + "group by grade.stu_code,grade.year order by avg(grade.score) DESC limit 10";

    List<Map<String, String>> mapList = new ArrayList<>();
    try {
      jdbcTemplate.query(
          sql,
          new Object[] {},
          new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
              int i = 0;
              do {
                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("姓名", resultSet.getString("stu_name"));
                stringMap.put("学号", Long.toString(resultSet.getLong("stu_code")));
                stringMap.put("班级", resultSet.getString("stu_class"));
                stringMap.put("学年", Integer.toString(resultSet.getInt("year")));
                stringMap.put("平均分", Double.toString(resultSet.getDouble("avgScore")));
                i++;
                stringMap.put("名次", Integer.toString(i));
                mapList.add(stringMap);
              } while (resultSet.next());
            }
          });
    } catch (DataAccessException e) {
      e.printStackTrace();
    } finally {
      return mapList;
    }
  }

  public List<Map<String, String>> getGpa()
      throws SQLException { // student.stu_name as 姓名,""+ "student.stu_class as 班级,"

    String sql =
        "select grade.stu_code,"
            + "grade.year,"
            + "grade.course_code,"
            + "course.course_score,"
            + "grade.score "
            + "from student_grade grade "
            + "inner join course_info course on grade.course_code=course.course_code ";

    List<Map<String, String>> mapList = new ArrayList<>();
    try {
      jdbcTemplate.query(
          sql,
          new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
              int i = 0;
              do {
                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("学号", Long.toString(resultSet.getLong("stu_code")));
                stringMap.put("学年", Integer.toString(resultSet.getInt("year")));
                stringMap.put("课程号", Long.toString(resultSet.getLong("course_code")));
                stringMap.put("学分", Double.toString(resultSet.getDouble("course_score")));
                stringMap.put("成绩", Double.toString(resultSet.getDouble("score")));

                mapList.add(stringMap);
              } while (resultSet.next());
            }
          });
    } catch (DataAccessException e) {
      e.printStackTrace();
    } finally {
      return mapList;
    }
  }
}
