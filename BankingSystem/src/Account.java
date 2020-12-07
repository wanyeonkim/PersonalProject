public class Account extends DataBase{
    private String UserName;
    private double Value;
    private double InitialValue;
    private String reason;


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public double getInitialValue() {
        return InitialValue;
    }

    public void setInitialValue(double initialValue) {
        InitialValue = initialValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    Account(){

    }

    public void DepositAccount(String reason){
        setInitialValue(getInitialValue()+getValue());
        setReason(reason);
    }

    public void WithdrawAccount(String reason){
        setInitialValue(getInitialValue()-getValue());
        setReason(reason);
    }


}
