package dto;

public class PrizeDTO {
    private int prizeNo;
    private String prizeName;
    private int scholarship;
    private String prizeDate;
    private int studentId;


    public PrizeDTO() {
    }

    public PrizeDTO(int prizeNo, String prizeName, int scholarship,String prizeDate, int studentId) {
        this(prizeName,scholarship,studentId);
        this.prizeDate = prizeDate;
        this.prizeNo = prizeNo;
    }
    public PrizeDTO( String prizeName, int scholarship, int studentId) {
        this.prizeName = prizeName;
        this.scholarship = scholarship;
        this.studentId = studentId;
    }

    public int getPrizeNo() {
        return prizeNo;
    }

    public void setPrizeNo(int prizeNo) {
        this.prizeNo = prizeNo;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getScholarship() {
        return scholarship;
    }

    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }

    public String getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(String prizeDate) {
        this.prizeDate = prizeDate;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("상장 번호: ").append(prizeNo);
        sb.append(", 상장 이름: ").append(prizeName);
        sb.append(", 장학금: ").append(scholarship);
        sb.append(", 등록시간: ").append(prizeDate);

        return sb.toString();
    }
}
