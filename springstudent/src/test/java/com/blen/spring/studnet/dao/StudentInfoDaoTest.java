package com.blen.spring.studnet.dao;


import java.sql.SQLException;

import com.blen.spring.studnet.domain.StudentInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class StudentInfoDaoTest {

  @Autowired
  private StudentInfoDao studentInfoDao;

  @Test
  public void test_add() {
    boolean resp = studentInfoDao.studentInfoAdd(300L, "li", "female", 23, "wu", 1);
    assertTrue(resp == true);
  }

  @Test
  public void test_delete() {
    boolean resp = studentInfoDao.studentInfoAdd(400L, "yi", "female", 23, "wu", 1);
    assertTrue(resp == true);
    boolean resp2 = studentInfoDao.studentInfoDelete(400L, "yi");
    assertTrue(resp2 == true);
  }

  @Test
  public void test_modify() {
    boolean resp = studentInfoDao.studentInfoAdd(500L, "liu", "female", 23, "wu", 1);
    assertTrue(resp == true);
    boolean resp2 = studentInfoDao.studentInfoModify(500L, "li", "male", 20, "shi", 0);
    assertTrue(resp2 == true);
  }

  @Test
  public void test_query() {
    boolean resp = studentInfoDao.studentInfoAdd(600L, "hsbu", "female", 23, "wu", 1);
    assertTrue(resp == true);
    try{
      StudentInfo studentInfo = studentInfoDao.studentInfoQuery(600L);
      assertEquals("hsbu", studentInfo.getName());
      }
    catch (SQLException e){
      e.printStackTrace();
      Assert.fail();
    }

  }

  @Test
  public void test_update_state() {
    boolean resp = studentInfoDao.studentInfoAdd(700L, "hsbu", "female", 23, "wu", 1);
    assertTrue(resp == true);
    boolean resp2 = studentInfoDao.studentInfoUpdateState(700L, "hsbu", 0);
    assertTrue(resp2 == true);
  }
}
