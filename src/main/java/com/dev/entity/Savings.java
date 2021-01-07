package com.dev.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "savings")
public class Savings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "savings_id")
	private Integer savingsId;

	@Column(name = "txn_type")
	private String txnType;

	@Column(name = "txn_date")
	private Date txnDate;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

	@Column(name = "debit_from")
	private String debitFrom;

	@Column(name = "credit_to")
	private String creditTo;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private ExpenseUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "txn_id", nullable = false)
	@JsonIgnore
	private Transaction txn;

	public Integer getSavingsId() {
		return savingsId;
	}

	public void setSavingsId(Integer savingsId) {
		this.savingsId = savingsId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ExpenseUser getUser() {
		return user;
	}

	public void setUser(ExpenseUser user) {
		this.user = user;
	}

	public Transaction getTxn() {
		return txn;
	}

	public void setTxn(Transaction txn) {
		this.txn = txn;
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

	@Override
	public String toString() {
		return "Savings [savingsId=" + savingsId + ", txnType=" + txnType + ", txnDate=" + txnDate + ", month=" + month
				+ ", year=" + year + ", debitFrom=" + debitFrom + ", creditTo=" + creditTo + ", amount=" + amount
				+ ", user=" + user + ", txn=" + txn + "]";
	}

}
