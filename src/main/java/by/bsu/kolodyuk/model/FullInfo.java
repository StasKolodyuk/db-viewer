package by.bsu.kolodyuk.model;


import java.util.List;

public class FullInfo {

    private CreditRequest creditRequest;
    private AccountInfo accountInfo;
    private List<CreditInfo> creditHistrory;

    public FullInfo(CreditRequest creditRequest, AccountInfo accountInfo, List<CreditInfo> creditHistrory) {
        this.creditRequest = creditRequest;
        this.accountInfo = accountInfo;
        this.creditHistrory = creditHistrory;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public List<CreditInfo> getCreditHistrory() {
        return creditHistrory;
    }

    public void setCreditHistrory(List<CreditInfo> creditHistrory) {
        this.creditHistrory = creditHistrory;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(creditRequest.toString());

        result.append("Account Info ID: " + accountInfo.getAccountId() + "\n");
        result.append("Current Money Amount: " + accountInfo.getMoneyAmount() + "\n");
        result.append("Account Info: " + accountInfo.getAccountInfo() + "\n");

        result.append("Credit History:\n");
        for(CreditInfo creditInfo : creditHistrory) {
            result.append("   Credit Info ID: " + creditInfo.getCreditInfoId() + "\n");
            result.append("   Credit Info: " + creditInfo.getCreditInfo() + "\n");
        }

        return result.toString();
    }
}
