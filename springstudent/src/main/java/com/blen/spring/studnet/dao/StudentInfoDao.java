package com.blen.spring.studnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.blen.spring.studnet.domain.StudentInfo;

@Component("studentInfoDao")
public class StudentInfoDao {
  @Autowired private JdbcTemplate jdbcTemplate;

  public  boolean studentInfoAdd(
      Long code, String name, String sex, int age, String classname, int state) {
    String sql =
        "insert into student_info(stu_code,stu_name,stu_sex,stu_age,stu_class,stu_state) values(?,?,?,?,?,?)";
    int resp = 0;
    try{
      resp = jdbcTemplate.update(sql, code, name, sex, age, classname, state);

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

  public boolean studentInfoDelete(Long code, String name) {
    String sql = "delete from student_info where stu_code =? and stu_name =?";
    int resp = this.jdbcTemplate.update(sql, code, name);
    if (resp > 0) {
      return true;
    } else {
      return false;
    }
  }

  // 学生code不可更改
  public boolean studentInfoModify(
      Long code, String name, String sex, int age, String classname, int state) {
    String sql =
        "update student_info set stu_name=?,stu_sex=?,stu_age=?,stu_class=?,stu_state=? where stu_code=?";
    int resp = jdbcTemplate.update(sql, name, sex, age, classname, state, code);
    if (resp > 0) {
      return true;
    } else {
      return false;
    }
  }

  public StudentInfo studentInfoQuery(Long code) throws SQLException {
    String sql = "select * from student_info where stu_code=?";
    StudentInfo studentInfo = new StudentInfo();

    try{
      jdbcTemplate.query(sql, new Object[]{code}, new RowCallbackHandler() {
        @Override
        public void processRow(ResultSet resultSet) throws SQLException {

            studentInfo.setCode(code);
            studentInfo.setName(resultSet.getString("stu_name"));
            studentInfo.setSex(resultSet.getString("stu_sex"));
            studentInfo.setAge(resultSet.getInt("stu_age"));
            studentInfo.setClassname(resultSet.getString("stu_class"));
            studentInfo.setState(resultSet.getInt("stu_state"));
        }
      });
    }catch (DataAccessException e){
      e.printStackTrace();
    }
    return studentInfo;
  }

  public boolean studentInfoUpdateState(Long code, String name, int state) {
    String sql = "update student_info set stu_state=? where stu_code=? and stu_name=?";
    int resp = jdbcTemplate.update(sql, state, code, name);
    if (resp > 0) {
      return true;
    } else {
      return false;
    }
  }
}
