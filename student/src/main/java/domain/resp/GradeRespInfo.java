package domain.resp;

public class GradeRespInfo {
  private Long stuCode;
  private String stuName;
  private String stuClassname;
  private Double avgScore;
  private int rank;

  public void setStuCode(Long stuCode){
    this.stuCode = stuCode;
  }
  public void setStuname(String stuName){
    this.stuName = stuName;
  }
  public void setStuClassname(String stuClassname){
    this.stuClassname = stuClassname;
  }
  public void setAveScore(Double aveScore){
    this.avgScore = avgScore;
  }
  public void setRank(int rank){
    this.rank = rank;
  }

}
