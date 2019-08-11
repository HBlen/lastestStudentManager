package com.blen.spring.studnet.domain;

import sun.java2d.pipe.ValidatePipe;

public class StudentGrade {
  private Long stuCode;
  private int year;
  private Long courseCode;
  private Double score;

  public void setStuCode(Long stuCode) {
    this.stuCode = stuCode;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setCourseCode(Long courseCode) {
    this.courseCode = courseCode;
  }

  public void setScore(Double score) {
    this.score = score;
  }
}
