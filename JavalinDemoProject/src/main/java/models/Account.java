package models;

public class Account {
	
    private int accountid;
    private String type;
    private double balance;
    private int userid;
    public Account() {
    	
    }
	public Account(int accountid, String type, double balance, int userid) {
		super();
		this.accountid = accountid;
		this.type = type;
		this.balance = balance;
		this.userid = userid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", type=" + type + ", balance=" + balance + ", userid=" + userid
				+ "]";
	}
	
    
  
}