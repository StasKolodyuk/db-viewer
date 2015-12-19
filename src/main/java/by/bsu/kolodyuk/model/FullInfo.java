package by.bsu.kolodyuk.model;


import java.util.List;

public class FullInfo {

    private FinancialInfo financialInfo;
    private AccountInfo accountInfo;
    private List<CreditInfo> creditHistrory;

    public FullInfo(FinancialInfo financialInfo, AccountInfo accountInfo, List<CreditInfo> creditHistrory) {
        this.financialInfo = financialInfo;
        this.accountInfo = accountInfo;
        this.creditHistrory = creditHistrory;
    }

    public FinancialInfo getFinancialInfo() {
        return financialInfo;
    }

    public void setFinancialInfo(FinancialInfo financialInfo) {
        this.financialInfo = financialInfo;
    }

    public FullInfo setFinancialInfoAndReturn(FinancialInfo financialInfo) {
        this.financialInfo = financialInfo;
        return this;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public FullInfo setAccountInfoAndReturn(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
        return this;
    }

    public List<CreditInfo> getCreditHistrory() {
        return creditHistrory;
    }

    public void setCreditHistrory(List<CreditInfo> creditHistrory) {
        this.creditHistrory = creditHistrory;
    }

    public void setCreditHistroryAndReturn(List<CreditInfo> creditHistrory) {
        this.creditHistrory = creditHistrory;
    }

    public Long getUserId() {
        return accountInfo != null ? accountInfo.getUserId() : -1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(financialInfo.toString());

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
