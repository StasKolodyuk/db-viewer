package by.bsu.kolodyuk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditRequest {

    @Id
    @GeneratedValue
    private Long creditRequestId;
    private Long userId;
    private Long moneyAmount;
    private Integer timeRange;
    private Integer interestPercentage;
    private String job;
    private Integer salary;
    @Column(length = 2000)
    private String financialReport;
    private Boolean validated;
    private Boolean confirmed;

    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public Integer getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(Integer interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getFinancialReport() {
        return financialReport;
    }

    public void setFinancialReport(String financialReport) {
        this.financialReport = financialReport;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString()  {
        return "User ID: " + userId + "\n" +
               "Loan Money Amount: " + moneyAmount + "\n" +
               "Loan Interest: " + interestPercentage + "%" + "\n" +
               "Loan Time: " + timeRange + " year(s)" + "\n" +
               "Job: " + job + "\n" +
               "Salary: " + salary + "\n" +
               "Financial Report: " + financialReport + "\n";
    }
}
