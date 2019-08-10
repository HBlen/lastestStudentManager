package domain.resp;

public class InfoRespData {
  private Long stuCode;
  private String name;
  private String classname;
  private int year;
  private String courseName;
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

  public void setName(String name) {
    this.name = name;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
}
