package com.stocktrader.API.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Double purchasePrice;
	
	@Column(nullable = false)
	private Integer quantity;
	
	private String ownerCpf;
	
	public Stock(String name, Double purchasePrice, Integer quantity, String ownerCpf) {
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.quantity = quantity;
		this.ownerCpf = ownerCpf;
	}

	public Stock() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOwnerCpf() {
		return ownerCpf;
	}

	public void setOwnerCpf(String ownerCpf) {
		this.ownerCpf = ownerCpf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, ownerCpf, purchasePrice, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(name, other.name) && Objects.equals(ownerCpf, other.ownerCpf)
				&& Objects.equals(purchasePrice, other.purchasePrice) && Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", purchasePrice=" + purchasePrice + ", quantity=" + quantity
				+ ", owner=" + ownerCpf + "]";
	}

}	