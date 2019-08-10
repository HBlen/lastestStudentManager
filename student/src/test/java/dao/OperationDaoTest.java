package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OperationDaoTest {

  @Test
  public void test_query(){
    Map<String,String> mapResp = new HashMap<String, String>();
    mapResp = OperationDao.queryByStuCodeAndYear(1L,2019);
    assertEquals("1",mapResp.get("学号"));
  }
  @Test
  public void test_get_Top10(){
    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    mapList = OperationDao.getTop10();
  }
  @Test
  public void test_get_gpa(){
    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    mapList = OperationDao.getGpa();
  }
}
