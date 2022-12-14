package com.store.store.entities.dtos;

import java.util.Date;



/**
 * ClsEntidadProducto
 */
public class ProductDto {
    
    private long strIdProducto;
    private String strCodigoProducto;
    private String strNombreProducto;
    private String strDescripcionProducto;
    private String strStockProducto;
    private String strStockMinProducto;
    private String strPrecioCostoProducto;
    private String strPrecioVentaProducto;
    private String strUtilidadProducto;
    private String strEstadoProducto;
    private String strDescripcionCategoria;
    private String strImagen;
    private Date fechaVencimiento;
    
    public String getStrImagen() {
        return strImagen;
    }

    public void setStrImagen(String strImagen) {
        this.strImagen = strImagen;
    }
    
    public long getStrIdProducto() {
        return strIdProducto;
    }

    public void setStrIdProducto(long strIdProducto) {
        this.strIdProducto = strIdProducto;
    }

    public String getStrCodigoProducto() {
        return strCodigoProducto;
    }

    public void setStrCodigoProducto(String strCodigoProducto) {
        this.strCodigoProducto = strCodigoProducto;
    }

    public String getStrNombreProducto() {
        return strNombreProducto;
    }

    public void setStrNombreProducto(String strNombreProducto) {
        this.strNombreProducto = strNombreProducto;
    }

    public String getStrDescripcionProducto() {
        return strDescripcionProducto;
    }

    public void setStrDescripcionProducto(String strDescripcionProducto) {
        this.strDescripcionProducto = strDescripcionProducto;
    }

    public String getStrStockProducto() {
        return strStockProducto;
    }

    public void setStrStockProducto(String strStockProducto) {
        this.strStockProducto = strStockProducto;
    }

    public String getStrStockMinProducto() {
        return strStockMinProducto;
    }

    public void setStrStockMinProducto(String strStockMinProducto) {
        this.strStockMinProducto = strStockMinProducto;
    }

    public String getStrPrecioCostoProducto() {
        return strPrecioCostoProducto;
    }

    public void setStrPrecioCostoProducto(String strPrecioCostoProducto) {
        this.strPrecioCostoProducto = strPrecioCostoProducto;
    }

    public String getStrPrecioVentaProducto() {
        return strPrecioVentaProducto;
    }

    public void setStrPrecioVentaProducto(String strPrecioVentaProducto) {
        this.strPrecioVentaProducto = strPrecioVentaProducto;
    }

    public String getStrUtilidadProducto() {
        return strUtilidadProducto;
    }

    public void setStrUtilidadProducto(String strUtilidadProducto) {
        this.strUtilidadProducto = strUtilidadProducto;
    }

    public String getStrEstadoProducto() {
        return strEstadoProducto;
    }

    public void setStrEstadoProducto(String strEstadoProducto) {
        this.strEstadoProducto = strEstadoProducto;
    }

    public String getStrDescripcionCategoria() {
        return strDescripcionCategoria;
    }

    public void setStrDescripcionCategoria(String strDescripcionCategoria) {
        this.strDescripcionCategoria = strDescripcionCategoria;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
}
