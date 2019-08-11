package com.blen.spring.studnet.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class OperationDaoTest {

  @Autowired
  private OperationDao operationDao;
  @Test
  public void test_query(){
    Map<String,String> mapResp = new HashMap<String, String>();
    try{
      mapResp = operationDao.queryByStuCodeAndYear(1L,2019);
      assertEquals("1",mapResp.get("学号"));
    }catch (SQLException e){
      e.printStackTrace();
      Assert.fail();
    }
  }
  @Test
  public void test_get_Top10(){
    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    try{
      mapList = operationDao.getTop10();
    }catch (SQLException e){
      e.printStackTrace();
      Assert.fail();
    }
  }
  @Test
  public void test_get_gpa(){
    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    try{
      mapList = operationDao.getGpa();
    }catch (SQLException e){
      e.printStackTrace();
    }
  }
}
