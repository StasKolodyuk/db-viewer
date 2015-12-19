package by.bsu.kolodyuk.model;

public class Session {

    private Long userId;
    private UserType userType;
    private Long userUnderValidationId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getUserUnderValidationId() {
        return userUnderValidationId;
    }

    public void setUserUnderValidationId(Long userUnderValidationId) {
        this.userUnderValidationId = userUnderValidationId;
    }
}
