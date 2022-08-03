package com.store.store.entities.dtos;

/**
 * Se usa para las estadísticas de venta y ventas a crédito en un determinado mes
 */
public class MonthlyStatistics {

    private String date;
    private double total;
    private float percentage;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
