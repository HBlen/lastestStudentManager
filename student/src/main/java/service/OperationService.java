package service;

import java.util.ArrayList;
import java.util.List;

import dao.StudentGradeDao;
import dao.StudentInfoDao;
import domain.StudentGrade;
import domain.StudentInfo;

public class OperationService {

  public void queryByStuCodeAndYear(Long stuCode,int year){

    StudentInfo studentInfo = StudentInfoDao.studentInfoQuery(stuCode);
    List<StudentGrade> studentGradeList = new ArrayList<StudentGrade>();

  }
}

