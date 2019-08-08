import dao.StudentInfoDao;

public class MainApp {
  public static void main(String[] args){
    StudentInfoDao.studentInfoAdd(100L, "name","sex",10, "classname", 0);
}
}
