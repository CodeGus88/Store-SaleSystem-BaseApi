package com.store.store.entities.dtos;

public class BaseDetailDto {

    private String code; // p.Codigo
    private String productName; // p.Nombre AS Producto
    private String category; // c.Descripcion AS Categoria
    private double cost; // dv.Costo
    private double price; // dv.Precio
    private double amount; // SUM(dv.Cantidad) AS Cantidad
    private double total; // SUM(dv.Total) AS Total
    private double utility; // SUM(TRUNCATE((Total-(dv.Costo*dv.Cantidad)),2)) AS Ganancia

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

}
