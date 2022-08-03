
package com.store.store.entities.dtos;

/**
 * ItemProductoCotizacion
 */
public class ProductValueKardex {
    
    private long id;
    private String code;
    private String name;
    private String category;
    private double incomeAmount; //cantidadIngreso;
    private double priceIncome; // precioIngreso;
    private double expenseIncome; // gastoIngreso;
    private double qualitySale; // cantidadVenta;
    private double salePrice; // precioVenta;
    private double expenseSale;// gastoVenta;
    private double stock;
    private double pricePurchase; // precioCompra;
    private double totalValued; // totalValorizado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public double getPriceIncome() {
        return priceIncome;
    }

    public void setPriceIncome(double priceIncome) {
        this.priceIncome = priceIncome;
    }

    public double getExpenseIncome() {
        return expenseIncome;
    }

    public void setExpenseIncome(double expenseIncome) {
        this.expenseIncome = expenseIncome;
    }

    public double getQualitySale() {
        return qualitySale;
    }

    public void setQualitySale(double qualitySale) {
        this.qualitySale = qualitySale;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getExpenseSale() {
        return expenseSale;
    }

    public void setExpenseSale(double expenseSale) {
        this.expenseSale = expenseSale;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public double getTotalValued() {
        return totalValued;
    }

    public void setTotalValued(double totalValued) {
        this.totalValued = totalValued;
    }
}
