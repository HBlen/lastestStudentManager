package com.blen.spring.studnet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.blen.spring.studnet.domain.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

@Component("courseInfoDao")
public class CourseInfoDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  public boolean courseInfoAdd(Long code, String name, Double score) {
    String sql = "insert into course_info(course_code,course_name,course_score) values(?,?,?)";
    int resp = 0;
    try{
      resp = jdbcTemplate.update(sql, code, name, score);

    }catch (DataAccessException e){
      resp =1;
      //e.printStackTrace();
    }finally{
      if (resp > 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public boolean courseInfoDelete(Long code, String name) {
    String sql = "delete from course_info where course_code =?,course_name =?";
    int resp = 0;
    try{
      resp = jdbcTemplate.update(sql, code, name);

    }catch (DataAccessException e){
      resp =1;
      //e.printStackTrace();
    }finally{
      if (resp > 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  // 课程code不可更改
  public boolean courseInfoModify(Long code, String name, Double score) {
    String sql = "update course_info set course_name=?,course_score=? where course_code=?";
    int resp = 0;
    try{
      resp = jdbcTemplate.update(sql, name, score, code);

    }catch (DataAccessException e){
      resp =1;
      //e.printStackTrace();
    }finally{
      if (resp > 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public CourseInfo courseInfoQuery(Long code, String name) throws SQLException{
    String sql = "select * from course_info where course_code=? and course_name=?";
    CourseInfo courseInfo = new CourseInfo();

    try{
      jdbcTemplate.query(sql, new Object[]{code}, new RowCallbackHandler() {
        public void processRow(ResultSet resultSet) throws SQLException {

          courseInfo.setCode(resultSet.getLong("course_code"));
          courseInfo.setName(resultSet.getString("course_name"));
          courseInfo.setScore(resultSet.getDouble("score"));
        }
      });
    }catch (DataAccessException e){
      e.printStackTrace();
    }
    return courseInfo;
  }
}
