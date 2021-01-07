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
@Table(name = "txn_history")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "txn_id")
	private Integer txnId;

	@Column(name = "txn_mode")
	private String txnMode;

	@Column(name = "txn_type")
	private String txnType;

	@Column(name = "txn_date")
	private Date txnDate;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private ExpenseUser user;

	public Integer getTxnId() {
		return txnId;
	}

	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
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

	public ExpenseUser getUser() {
		return user;
	}

	public void setUser(ExpenseUser user) {
		this.user = user;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Transaction [txnId=" + txnId + ", txnMode=" + txnMode + ", txnType=" + txnType + ", txnDate=" + txnDate
				+ ", month=" + month + ", year=" + year + ", purpose=" + purpose + ", amount=" + amount + ", user="
				+ user + "]";
	}

}
