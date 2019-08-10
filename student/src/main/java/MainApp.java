import java.util.Map;

import dao.OperationDao;
import dao.StudentInfoDao;
import domain.StudentInfo;
import domain.resp.InfoRespData;

public class MainApp {
  public static void main(String[] args){
    StudentInfoDao.studentInfoAdd(200L, "hou","sex",10, "classname", 0);
    StudentInfo studentInfo = StudentInfoDao.studentInfoQuery(100L);
    Map<String,String> map = OperationDao.queryByStuCodeAndYear(100L,2019);
  }
}

