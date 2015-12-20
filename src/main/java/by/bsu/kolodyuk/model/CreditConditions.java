package by.bsu.kolodyuk.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditConditions {

    @Id
    @GeneratedValue
    private Long creditConditionsId;
    private Long userId;
    private Long moneyAmount;
    private Integer timeRange;
    private Integer interestPercentage;
    @Column(length = 2000)
    private String creditConditions;
    private Boolean accepted;

    public Long getCreditConditionsId() {
        return creditConditionsId;
    }

    public void setCreditConditionsId(Long creditConditionsId) {
        this.creditConditionsId = creditConditionsId;
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

    public String getCreditConditions() {
        return creditConditions;
    }

    public void setCreditConditions(String creditConditions) {
        this.creditConditions = creditConditions;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
