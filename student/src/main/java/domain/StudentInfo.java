package domain;

public class StudentInfo {
  private Long code;
  private String name;
  private String sex;
  private int age;
  private String classname;
  private int state;

  public void setCode(Long code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getName() {
    return name;
  }

  public String getClassname() {
    return classname;
  }
}
