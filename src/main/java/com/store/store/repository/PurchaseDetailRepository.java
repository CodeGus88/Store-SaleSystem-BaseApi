package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.PurchaseDetail;
import com.store.store.entities.dtos.PurchaseDetailDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsDetalleCompra
 */
public class PurchaseDetailRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarDetalleCompra(PurchaseDetail DetalleCompra) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_DetalleCompra(?,?,?,?,?)}");
        statement.setLong("pidcompra",DetalleCompra.getPurchaseId());
        statement.setLong("pidproducto",DetalleCompra.getProductId());
        statement.setString("pcantidad",DetalleCompra.getAmount());
        statement.setString("pprecio",DetalleCompra.getPrice());
        statement.setString("ptotal",DetalleCompra.getTotal());
        statement.execute();
    }

    public void modificarDetalleCompra(long id, PurchaseDetail DetalleCompra) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_DetalleCompra(?,?,?,?,?)}");
        statement.setLong("pidcompra",id);
        statement.setLong("pidproducto",DetalleCompra.getProductId());
        statement.setString("pcantidad",DetalleCompra.getAmount());
        statement.setString("pprecio",DetalleCompra.getPrice());
        statement.setString("ptotal",DetalleCompra.getTotal());
        statement.executeUpdate();
    }
    
    public List<PurchaseDetailDto> listarDetalleCompraPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_DetalleCompraPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
//        IdCompra, IdProducto, Codigo, Nombre, Descripcion, Cantidad, Precio, Total
        List<PurchaseDetailDto> list = new ArrayList<>();
        while(rs.next()){
            PurchaseDetailDto purchaseDetailDto = new PurchaseDetailDto();
            purchaseDetailDto.setPurchaseId(rs.getLong("idcompra"));
            purchaseDetailDto.setPurchaseId(rs.getLong("idproducto"));
            purchaseDetailDto.setProductCode(rs.getString("codigo"));
            purchaseDetailDto.setProductName(rs.getString("nombre"));
            purchaseDetailDto.setProductDescription(rs.getString("descripcion"));
            purchaseDetailDto.setAmount(rs.getDouble("cantidad"));
            purchaseDetailDto.setPrice(rs.getDouble("precio"));
            purchaseDetailDto.setTotal(rs.getDouble("total"));
            list.add(purchaseDetailDto);
        }
        return list;
    }     

}
