package dao;

import java.lang.annotation.Target;

import domain.StudentInfo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentInfoDaoTest {

  @Test
  public void test_add() {
    boolean resp = StudentInfoDao.studentInfoAdd(300L, "li", "female", 23, "wu", 1);
    assertTrue(resp = true);
  }

  @Test
  public void test_delete() {
    boolean resp = StudentInfoDao.studentInfoAdd(400L, "yi", "female", 23, "wu", 1);
    assertTrue(resp = true);
    boolean resp2 = StudentInfoDao.studentInfoDelete(400L, "yi");
    assertTrue(resp2 = true);
  }

  @Test
  public void test_modify() {
    boolean resp = StudentInfoDao.studentInfoAdd(500L, "liu", "female", 23, "wu", 1);
    assertTrue(resp = true);
    boolean resp2 = StudentInfoDao.studentInfoModify(500L, "li", "male", 20, "shi", 0);
    assertTrue(resp2 == true);
  }

  @Test
  public void test_query() {
    boolean resp = StudentInfoDao.studentInfoAdd(600L, "hsbu", "female", 23, "wu", 1);
    assertTrue(resp = true);
    StudentInfo studentInfo = StudentInfoDao.studentInfoQuery(600L);
    assertEquals("hsbu", studentInfo.getName());
  }

  @Test
  public void test_update_state() {
    boolean resp = StudentInfoDao.studentInfoAdd(700L, "hsbu", "female", 23, "wu", 1);
    assertTrue(resp = true);
    boolean resp2 = StudentInfoDao.studentInfoUpdateState(700L, "hsbu", 0);
    assertTrue(resp2 == true);
  }
}
