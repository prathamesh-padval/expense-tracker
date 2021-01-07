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
@Table(name = "investments")
public class Investment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inv_id")
	private Integer invId;

	@Column(name = "category")
	private String category;

	@Column(name = "txn_type")
	private String txnType;

	@Column(name = "txn_date")
	private Date txnDate;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

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

	public Integer getInvId() {
		return invId;
	}

	public void setInvId(Integer invId) {
		this.invId = invId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public String toString() {
		return "Investment [invId=" + invId + ", category=" + category + ", txnType=" + txnType + ", txnDate=" + txnDate
				+ ", month=" + month + ", year=" + year + ", amount=" + amount + ", user=" + user + ", txn=" + txn
				+ "]";
	}

}
