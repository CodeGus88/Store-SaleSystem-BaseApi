package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.Purchase;
import com.store.store.entities.dtos.PurchaseDto;
import com.store.store.entities.dtos.PurchaseForDetailDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClsCompra
 */
public class PurchaseRepository {

    private Connection connection = new ClsConexion().getConection();

    public void agregarCompra(Purchase purchase) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Compra(?,?,?,?,?,?,?,?,?)}");
        statement.setLong("pidtipodocumento",purchase.getId());
        statement.setLong("pidproveedor",purchase.getSupplierId());
        statement.setLong("pidempleado",purchase.getEmployeeId());
        statement.setString("pnumero",purchase.getNumber());
        statement.setDate ("pfecha", new java.sql.Date(purchase.getDate().getTime()));
        statement.setDouble("psubtotal",purchase.getSubtotal());
        statement.setDouble("pigv",purchase.getIgv());
        statement.setDouble("ptotal",purchase.getTotal());
        statement.setString("pestado",purchase.getState());
        statement.execute();
    }
    public void modificarCompra(long id, Purchase purchase) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_U_Compra(?,?,?,?,?,?,?,?,?,?)}");
        statement.setLong("pidcompra",id);
        statement.setLong("pidtipodocumento",purchase.getDocumentTypeId());
        statement.setLong("pidproveedor",purchase.getSupplierId());
        statement.setLong("pidempleado",purchase.getEmployeeId());
        statement.setString("pnumero",purchase.getNumber());
        statement.setDate ("pfecha", new java.sql.Date(purchase.getDate().getTime()));
        statement.setDouble("psubtotal",purchase.getSubtotal());
        statement.setDouble("pigv",purchase.getIgv());
        statement.setDouble("ptotal",purchase.getTotal());
        statement.setString("pestado",purchase.getState());
        statement.executeUpdate();
    }

    public List<PurchaseDto> listarCompra() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Compra}");
        ResultSet rs = statement.executeQuery();
        return purchaseDtoList(rs);
    }

    public long obtenerUltimoIdCompra() throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_UltimoIdCompra()}");
        ResultSet rs = statement.executeQuery();
        long id = -1;
        while (rs.next())
            id = rs.getLong("id");
        return id;
    }      
    
    public List<PurchaseDto> listarCompraPorFecha(String criterio,Date fechaini, Date fechafin, String doc) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_CompraPorFecha(?,?,?,?)}");
        statement.setString ("pcriterio", criterio);
        statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
        statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
        statement.setString("pdocumento", doc);
        ResultSet rs = statement.executeQuery();
        return purchaseDtoList(rs);
    }

    public void actualizarCompraEstado(String codigo, PurchaseDto Compra) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_ActualizarCompraEstado(?,?)}");
        statement.setString("pidcompra",codigo);
        statement.setString("pestado",Compra.getState());
        statement.executeUpdate();
    }

    public List<PurchaseForDetailDto> listarCompraPorDetalle(String criterio, Date fechaini, Date fechafin) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_CompraPorDetalle(?,?,?)}");
        statement.setString ("pcriterio", criterio);
        statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
        statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
        ResultSet rs = statement.executeQuery();
//        p.Codigo,Producto,Categoria,Precio,Cantidad,Total
        List<PurchaseForDetailDto> list = new ArrayList<>();
        while(rs.next()){
            PurchaseForDetailDto purchaseForDetailDto = new PurchaseForDetailDto();
            purchaseForDetailDto.setCode(rs.getString("codigo"));
            purchaseForDetailDto.setProduct(rs.getString("producto"));
            purchaseForDetailDto.setCategory(rs.getString("categoria"));
            purchaseForDetailDto.setPrice(rs.getDouble("precio"));
            purchaseForDetailDto.setAmount(rs.getDouble("cantidad"));
            purchaseForDetailDto.setTotal(rs.getDouble("total"));
            list.add(purchaseForDetailDto);
        }
        return list;
    }

    private List<PurchaseDto> purchaseDtoList(ResultSet rs) throws SQLException {
        ArrayList<PurchaseDto> compras=new ArrayList<>();
        while (rs.next()){
            PurchaseDto compra=new PurchaseDto();
            compra.setId(rs.getLong("IdCompra"));
            compra.setDocumentType(rs.getString("TipoDocumento"));
            compra.setSupplier(rs.getString("Proveedor"));
            compra.setEmployee(rs.getString("Empleado"));
            compra.setPurchaseNumber(rs.getString("Numero"));
            compra.setDate(rs.getDate("Fecha"));
            compra.setSubtotal(rs.getString("SubTotal"));
            compra.setIgv(rs.getString("Igv"));
            compra.setTotal(rs.getString("Total"));
            compra.setState(rs.getString("Estado"));
            compras.add(compra);
        }
        return compras;
    }
}
