package com.dev.model;

public class InputModel {

	private String userName;

	private String txnMode;

	private String txnType;

	private String purpose;

	private Integer amount;

	private String month;

	private String year;

	private String txnDate;

	private String fromDate;

	private String toDate;

	private String debitFrom;

	private String creditTo;

	private String invCategory;

	public String getInvCategory() {
		return invCategory;
	}

	public void setInvCategory(String invCategory) {
		this.invCategory = invCategory;
	}

	public String getDebitFrom() {
		return debitFrom;
	}

	public void setDebitFrom(String debitFrom) {
		this.debitFrom = debitFrom;
	}

	public String getCreditTo() {
		return creditTo;
	}

	public void setCreditTo(String creditTo) {
		this.creditTo = creditTo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTxnMode() {
		return txnMode;
	}

	public void setTxnMode(String txnMode) {
		this.txnMode = txnMode;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "InputModel [userName=" + userName + ", txnMode=" + txnMode + ", txnType=" + txnType + ", purpose="
				+ purpose + ", amount=" + amount + ", month=" + month + ", year=" + year + ", txnDate=" + txnDate
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", debitFrom=" + debitFrom + ", creditTo="
				+ creditTo + "]";
	}

}
