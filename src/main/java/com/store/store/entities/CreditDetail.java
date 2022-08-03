package com.store.store.entities;

/**
 * ClsEntidadDetalleCredito
 */
public class CreditDetail {

    private long id;
    private String strIdCredito;
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

    public String getStrIdCredito() {
        return strIdCredito;
    }

    public void setStrIdCredito(String strIdCredito) {
        this.strIdCredito = strIdCredito;
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
