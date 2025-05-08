package com.backendapp.faturabcknd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
 

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "faturalar")
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productNumber;   
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	private String productName;     
    private int count;              
    private String unit;            
    private double unitPrice;       
    private double totalAmount;     
}
