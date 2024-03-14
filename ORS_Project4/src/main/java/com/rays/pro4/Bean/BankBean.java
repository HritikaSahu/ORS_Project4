package com.rays.pro4.Bean;

public class BankBean extends BaseBean {

	private String name;
	private int accountNo;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String getkey() {
		return null;
	}
	@Override
	public String getValue() {
		return null;
	}
	
	
	
}
