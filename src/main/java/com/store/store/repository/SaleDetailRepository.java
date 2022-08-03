package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.SaleDetail;
import com.store.store.entities.dtos.SaleDetailDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsDetalleVenta
 */
public class SaleDetailRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarDetalleVenta(SaleDetail DetalleVenta) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_DetalleVenta(?,?,?,?,?,?)}");
        statement.setString("pidventa",DetalleVenta.getStrIdVenta());
        statement.setString("pidproducto",DetalleVenta.getStrIdProducto());
        statement.setString("pcantidad",DetalleVenta.getStrCantidadDet());
        statement.setString("pcosto",DetalleVenta.getStrCostoDet());
        statement.setString("pprecio",DetalleVenta.getStrPrecioDet());
        statement.setString("ptotal",DetalleVenta.getStrTotalDet());
        statement.execute();
    }

    public void modificarDetalleVenta(String codigo, SaleDetail DetalleVenta) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_DetalleVenta(?,?,?,?,?,?)}");
        statement.setString("pidventa",codigo);
        statement.setString("pidproducto",DetalleVenta.getStrIdProducto());
        statement.setString("pcantidad",DetalleVenta.getStrCantidadDet());
        statement.setString("pcosto",DetalleVenta.getStrCostoDet());
        statement.setString("pprecio",DetalleVenta.getStrPrecioDet());
        statement.setString("ptotal",DetalleVenta.getStrTotalDet());
        statement.executeUpdate();
    }
    
    public List<SaleDetailDto> listarDetalleVentaPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_DetalleVentaPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
//        IdVenta,IdProducto,p.Codigo,p.Nombre,p.Descripcion,dv.Cantidad,dv.Precio,dv.Total
        List<SaleDetailDto> list = new ArrayList<>();
        while(rs.next()){
            SaleDetailDto saleDetailDto = new SaleDetailDto();
            saleDetailDto.setSaleId(rs.getLong("IdVenta"));
            saleDetailDto.setProductId(rs.getLong("IdProducto"));
            saleDetailDto.setProductCode(rs.getString("Codigo"));
            saleDetailDto.setProductName(rs.getString("Nombre"));
            saleDetailDto.setProductDescription(rs.getString("Descripcion"));
            saleDetailDto.setAmount(rs.getDouble("Cantidad"));
            saleDetailDto.setPrice(rs.getDouble("Precio"));
            saleDetailDto.setTotal(rs.getDouble("Total"));
            list.add(saleDetailDto);
        }
        return list;
    }  

}
