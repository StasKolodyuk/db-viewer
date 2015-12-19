package by.bsu.kolodyuk.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditInfo {

    @Id
    @GeneratedValue
    private Long creditInfoId;
    private Long userId;
    private String creditInfo;

    public Long getCreditInfoId() {
        return creditInfoId;
    }

    public void setCreditInfoId(Long creditInfoId) {
        this.creditInfoId = creditInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreditInfo() {
        return creditInfo;
    }

    public void setCreditInfo(String creditInfo) {
        this.creditInfo = creditInfo;
    }

    @Override
    public String toString() {
        return "Credit Info ID: " + creditInfoId + "\n" +
               "User ID: " + userId + "\n" +
               "Credit Info: " + creditInfo + "\n";
    }
}
