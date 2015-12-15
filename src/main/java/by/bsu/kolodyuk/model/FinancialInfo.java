package by.bsu.kolodyuk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FinancialInfo {

    @Id
    @GeneratedValue
    private Long financialInfoId;
    private Long userId;
    private String job;
    private Integer salary;
    @Column(length = 2000)
    private String financialReport;
    @Column(nullable = true)
    private Boolean validated;

    public Long getFinancialInfoId() {
        return financialInfoId;
    }

    public void setFinancialInfoId(Long financialInfoId) {
        this.financialInfoId = financialInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserId: " + userId + "\n" +
               "Job: " + job + "\n" +
               "Salary: " + salary + "\n" +
               "Financial Report: " + financialReport;
    }
}
