package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.CreditDetail;
import com.store.store.entities.dtos.CreditDetailDto;
import com.store.store.entities.dtos.accountsreceivable.ProductDetailItem;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsDetalleCredito
 */
public class CreditDetailRepository {
    
    private Connection connection=new ClsConexion().getConection();
    
    public void agregarDetalleCredito(CreditDetail DetalleCredito){
        try{
            CallableStatement statement=connection.prepareCall("{call 001_SP_I_DetalleCredito(?,?,?,?,?,?)}");
            statement.setInt("pidcredito",Integer.parseInt(DetalleCredito.getStrIdCredito()));
            statement.setInt("pidproducto",Integer.parseInt(DetalleCredito.getStrIdProducto()));
            statement.setString("pcantidad",DetalleCredito.getStrCantidadDet());
            statement.setString("pcosto",DetalleCredito.getStrCostoDet());
            statement.setString("pprecio",DetalleCredito.getStrPrecioDet());
            statement.setString("ptotal",DetalleCredito.getStrTotalDet());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void modificarDetalleCredito(String codigo, CreditDetail DetalleCredito) throws Exception{
        CallableStatement statement=connection.prepareCall("{call 001_SP_U_DetalleCredito(?,?,?,?,?,?)}");
        statement.setString("pidcredito",codigo);
        statement.setString("pidproducto",DetalleCredito.getStrIdProducto());
        statement.setString("pcantidad",DetalleCredito.getStrCantidadDet());
        statement.setString("pcosto",DetalleCredito.getStrCostoDet());
        statement.setString("pprecio",DetalleCredito.getStrPrecioDet());
        statement.setString("ptotal",DetalleCredito.getStrTotalDet());
        statement.executeUpdate();
    }
    
    public ArrayList<ProductDetailItem> findByCreditId(int creditId) throws Exception{
        CallableStatement statement = connection.prepareCall("{call 001_SP_S_DetalleCreditoProductoPorIdCredito(?)}");
        statement.setInt("pidcredito", creditId);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<ProductDetailItem> list = new ArrayList<>();
        while (resultSet.next()) {
            ProductDetailItem detailCreditItem = new ProductDetailItem();
            detailCreditItem = new ProductDetailItem();
            detailCreditItem.setProductId(resultSet.getInt("idproducto"));
            detailCreditItem.setProductCode(resultSet.getString("codigo"));
            detailCreditItem.setProductName(resultSet.getString("nombre"));
            detailCreditItem.setProductDescription(resultSet.getString("descripcion"));
            detailCreditItem.setAmount(resultSet.getInt("cantidad"));
            detailCreditItem.setUnitPrice(resultSet.getInt("precio"));
            detailCreditItem.setTotal(resultSet.getInt("total"));
            list.add(detailCreditItem);
        }
        return list;
    }
    
    public List<CreditDetailDto> listarDetalleCreditoPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call 001_SP_S_DetalleCreditoPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<CreditDetailDto> list = new ArrayList<>();
//        dc.IdCredito,p.IdProducto,p.Codigo,p.Nombre,p.Descripcion,dc.Cantidad,dc.Precio,dc.Total
        while(rs.next()){
            CreditDetailDto saleDetailDto = new CreditDetailDto();
            saleDetailDto.setCreditId(rs.getLong("idcredito"));
            saleDetailDto.setProductId(rs.getLong("idproducto"));
            saleDetailDto.setProductCode(rs.getString("codigo"));
            saleDetailDto.setProductName(rs.getString("nombre"));
            saleDetailDto.setProductDescription(rs.getString("nescripcion"));
            saleDetailDto.setAmount(rs.getDouble("cantidad"));
            saleDetailDto.setPrice(rs.getDouble("precio"));
            saleDetailDto.setTotal(rs.getDouble("total"));
            list.add(saleDetailDto);
        }
        return list;
    }  
}
