package dto;

import java.util.List;

public class BoardDTO {
    private int studentID;
    private String student;
    private String major;
    private String grade;
    private int score;
    private List<PrizeDTO> prizeList;

    public List<PrizeDTO> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<PrizeDTO> prizeList) {
        this.prizeList = prizeList;
    }

    public BoardDTO(){};

    public BoardDTO(int studentID, String student, String major, String grade, int score) {
        this.studentID = studentID;
        this.student = student;
        this.major = major;
        this.grade = grade;
        this.score = score;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" 학번 : ").append(studentID);
        sb.append(" 학생 이름: '").append(student).append('\'');
        sb.append(", 전공: '").append(major).append('\'');
        sb.append(", 등급: '").append(grade).append('\'');
        sb.append(", 학점: ").append(score);

        return sb.toString();
    }
}
