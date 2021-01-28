package com.books.apirest.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="registry")
public class Registry{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name = "allocated_date")
	private Date allocated_date;
	
	@Column(name = "delivery_date")
	private Date delivery_date;
	
	@Column(name = "tax")
	private boolean tax;
	
	@Column(name = "value_tax")
	private double value_tax;
	
	@Column(name = "reserved")
	private boolean reserved;
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getAllocated_date() {
		return allocated_date;
	}

	public void setAllocated_date(Date allocated_date) {
		this.allocated_date = allocated_date;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public boolean isTax() {
		return tax;
	}

	public void setTax(boolean tax) {
		this.tax = tax;
	}

	public double getValue_tax() {
		return value_tax;
	}

	public void setValue_tax(double value_tax) {
		this.value_tax = value_tax;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}
