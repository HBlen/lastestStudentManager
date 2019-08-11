package com.blen.spring.studnet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blen.spring.studnet.domain.CourseInfo;
import com.blen.spring.studnet.domain.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

@Component("studentGradeDao")
public class StudentGradeDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;


  public boolean studentGradeUpdateScore(
      Long stuCode, int year, Long courseCode, Double score) {
    String sql =
        "update student_grade set course_score=? where stu_code=? and year=? and course_code=?";
    int resp = jdbcTemplate.update(sql, score, stuCode, year, courseCode);
    if (resp > 0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean studentGradeAdd(Long stuCode, int year, Long courseCode, Double score) {
    String sql = "insert into student_grade(stu_code,year,course_code,score) values(?,?,?,?)";
    int resp = jdbcTemplate.update(sql,stuCode, year, courseCode, score);
    if (resp > 0) {
      return true;
    } else {
      return false;
    }
  }

  public List<StudentGrade> studentGradeQuery(Long code, int year) throws SQLException {
    String sql = "select * from course_info where course_code=? and course_name=?";

    List<StudentGrade> studentGradeList = new ArrayList<>();
    try{
      jdbcTemplate.query(sql, new Object[]{code}, new RowCallbackHandler() {
        public void processRow(ResultSet resultSet) throws SQLException {
         do {
            StudentGrade studentGrade = new StudentGrade();
            studentGrade.setStuCode(code);
            studentGrade.setYear(resultSet.getInt("year"));
            studentGrade.setCourseCode(resultSet.getLong("course_code"));
            studentGrade.setScore(resultSet.getDouble("course_score"));

            studentGradeList.add(studentGrade);
          } while (resultSet.next());
        }
      });
    }catch (DataAccessException e){
      e.printStackTrace();
    }finally{
      return studentGradeList;
    }

  }
}
