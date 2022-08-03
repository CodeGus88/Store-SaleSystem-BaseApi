package com.store.store.entities;

/**
 * ClsEntidadDetalleVenta
 */
public class SaleDetail {

    private long id;
    private String strIdVenta;
    private String strIdProducto;
    private String strCantidadDet;
    private String strCostoDet;
    private String strPrecioDet;
    private String strTotalDet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrIdVenta() {
        return strIdVenta;
    }

    public void setStrIdVenta(String strIdVenta) {
        this.strIdVenta = strIdVenta;
    }

    public String getStrIdProducto() {
        return strIdProducto;
    }

    public void setStrIdProducto(String strIdProducto) {
        this.strIdProducto = strIdProducto;
    }

    public String getStrCantidadDet() {
        return strCantidadDet;
    }

    public void setStrCantidadDet(String strCantidadDet) {
        this.strCantidadDet = strCantidadDet;
    }

    public String getStrCostoDet() {
        return strCostoDet;
    }

    public void setStrCostoDet(String strCostoDet) {
        this.strCostoDet = strCostoDet;
    }

    public String getStrPrecioDet() {
        return strPrecioDet;
    }

    public void setStrPrecioDet(String strPrecioDet) {
        this.strPrecioDet = strPrecioDet;
    }

    public String getStrTotalDet() {
        return strTotalDet;
    }

    public void setStrTotalDet(String strTotalDet) {
        this.strTotalDet = strTotalDet;
    }

}
