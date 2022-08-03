package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.dtos.BaseDetailDto;
import com.store.store.entities.dtos.MonthlyStatistics;
import com.store.store.entities.dtos.SaleDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClsVenta
 */
public class SaleRepository {

    private Connection connection = new ClsConexion().getConection();

    public boolean agregarVenta(SaleDto venta) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_I_Venta(?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pidtipodocumento", venta.getStrIdTipoDocumento());
        statement.setString("pidcliente", venta.getStrIdCliente());
        statement.setString("pidempleado", venta.getStrIdEmpleado());
        statement.setString("pserie", venta.getStrSerieVenta());
        statement.setString("pnumero", venta.getStrNumeroVenta());
        statement.setDate("pfecha", new java.sql.Date(venta.getStrFechaVenta().getTime()));
        statement.setString("ptotalventa", venta.getStrTotalVenta());
        statement.setString("pdescuento", venta.getStrDescuentoVenta());
        statement.setString("psubtotal", venta.getStrSubTotalVenta());
        statement.setString("pigv", venta.getStrIgvVenta());
        statement.setString("ptotalpagar", venta.getStrTotalPagarVenta());
        statement.setString("pestado", venta.getStrEstadoVenta());
        statement.execute();
        return true;
    }

    public boolean modificarVenta(String codigo, SaleDto venta) throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_U_Venta(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pidventa", codigo);
        statement.setString("pidtipodocumento", venta.getStrIdTipoDocumento());
        statement.setString("pidcliente", venta.getStrIdCliente());
        statement.setString("pidempleado", venta.getStrIdEmpleado());
        statement.setString("pserie", venta.getStrSerieVenta());
        statement.setString("pnumero", venta.getStrNumeroVenta());
        statement.setDate("pfecha", new java.sql.Date(venta.getStrFechaVenta().getTime()));
        statement.setString("ptotalventa", venta.getStrTotalVenta());
        statement.setString("pdescuento", venta.getStrDescuentoVenta());
        statement.setString("psubtotal", venta.getStrSubTotalVenta());
        statement.setString("pigv", venta.getStrIgvVenta());
        statement.setString("ptotalpagar", venta.getStrTotalPagarVenta());
        statement.setString("pestado", venta.getStrEstadoVenta());
        statement.executeUpdate();
        return true;
    }

    public ArrayList<SaleDto> listarVenta() throws Exception {
        ArrayList<SaleDto> ventas = new ArrayList<SaleDto>();
        CallableStatement statement = connection.prepareCall("{call SP_S_Venta}");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            SaleDto venta = new SaleDto();
            venta.setStrIdVenta(resultSet.getString("IdVenta"));
            venta.setStrTipoDocumento(resultSet.getString("TipoDocumento"));
            venta.setStrCliente(resultSet.getString("Cliente"));
            venta.setStrEmpleado(resultSet.getString("Empleado"));
            venta.setStrSerieVenta(resultSet.getString("Serie"));
            venta.setStrNumeroVenta(resultSet.getString("Numero"));
            venta.setStrFechaVenta(resultSet.getDate("Fecha"));
            venta.setStrTotalVenta(resultSet.getString("TotalVenta"));
            venta.setStrDescuentoVenta(resultSet.getString("Descuento"));
            venta.setStrSubTotalVenta(resultSet.getString("SubTotal"));
            venta.setStrIgvVenta(resultSet.getString("Igv"));
            venta.setStrTotalPagarVenta(resultSet.getString("TotalPagar"));
            venta.setStrEstadoVenta(resultSet.getString("Estado"));
            ventas.add(venta);
        }
        return ventas;
    }

    public boolean actualizarVentaEstado(String codigo, SaleDto Venta) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_U_ActualizarVentaEstado(?,?)}");
        statement.setString("pidventa", codigo);
        statement.setString("pestado", Venta.getStrEstadoVenta());
        statement.executeUpdate();
        return true;
    }

    public List<SaleDto> listarVentaPorParametro(String criterio, String busqueda) throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<SaleDto> list = new ArrayList<>();
        while(rs.next()){
            SaleDto saleDto = new SaleDto();
            saleDto.setStrIdVenta(rs.getString("idventa"));
            saleDto.setStrTipoDocumento(rs.getString("tipodocumento"));
            saleDto.setStrCliente(rs.getString("cliente"));
            saleDto.setStrEmpleado(rs.getString("empleado"));
            saleDto.setStrSerieVenta(rs.getString("serie"));
            saleDto.setStrNumeroVenta(rs.getString("numero"));
            saleDto.setStrFechaVenta(rs.getDate("fecha"));
            saleDto.setStrTotalVenta(rs.getString("totalventa"));
            saleDto.setStrDescuentoVenta(rs.getString("descuento"));
            saleDto.setStrSubTotalVenta(rs.getString("subtotal"));
            saleDto.setStrIgvVenta(rs.getString("igv"));
            saleDto.setStrTotalPagarVenta(rs.getString("totalpagar"));
            saleDto.setStrEstadoVenta(rs.getString("estado"));
            list.add(saleDto);
        }
        return list;
    }

    public long obtenerUltimoIdVenta() throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_S_UltimoIdVenta()}");
        ResultSet rs = statement.executeQuery();
        long ultimoIdCredito = -1;
        while(rs.next()){
            ultimoIdCredito = rs.getLong("id");
        }
        return ultimoIdCredito;
    }

    public List<SaleDto> listarVentaPorFecha(String criterio, Date fechaIni, Date fechaFin, String doc) throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorFecha(?,?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setDate("pfechaini", new java.sql.Date(fechaIni.getTime()));
        statement.setDate("pfechafin", new java.sql.Date(fechaFin.getTime()));
        statement.setString("pdocumento", doc);
        ResultSet rs = statement.executeQuery();
        List<SaleDto> list = new ArrayList<>();
        while(rs.next()){
            SaleDto saleDto = new SaleDto();
            saleDto.setStrIdVenta(rs.getString("idventa"));
            saleDto.setStrCliente(rs.getString("cliente"));
            saleDto.setStrFechaVenta(rs.getDate("fecha"));
            saleDto.setStrEmpleado(rs.getString("empleado"));
            saleDto.setStrTipoDocumento(rs.getString("tipodocumento"));
            saleDto.setStrSerieVenta(rs.getString("serie"));
            saleDto.setStrNumeroVenta(rs.getString("numero"));
            saleDto.setStrEstadoVenta(rs.getString("estado"));
            saleDto.setStrTotalPagarVenta(rs.getString("totalpagar"));
            saleDto.setStrTotalVenta(rs.getString("totalventa"));
            saleDto.setStrDescuentoVenta(rs.getString("descuento"));
            saleDto.setStrSubTotalVenta(rs.getString("subtotal"));
            saleDto.setStrIgvVenta(rs.getString("igv"));
            list.add(saleDto);
        }
        return list;
    }

    public List<BaseDetailDto> listarVentaPorDetalle(String criterio, Date fechaini, Date fechafin) throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorDetalle(?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setDate("pfechaini", new java.sql.Date(fechaini.getTime()));
        statement.setDate("pfechafin", new java.sql.Date(fechafin.getTime()));
        ResultSet rs  = statement.executeQuery();
        List<BaseDetailDto> list = new ArrayList<>();
        while(rs.next()){
            BaseDetailDto baseDetailDto = new BaseDetailDto();
            baseDetailDto.setCode(rs.getString("codigo"));
            baseDetailDto.setProductName(rs.getString("producto"));
            baseDetailDto.setCategory(rs.getString("categoria"));
            baseDetailDto.setCost(rs.getDouble("costo"));
            baseDetailDto.setPrice(rs.getDouble("precio"));
            baseDetailDto.setAmount(rs.getDouble("cantidad"));
            baseDetailDto.setTotal(rs.getDouble("total"));
            baseDetailDto.setUtility(rs.getDouble("ganancia"));
            list.add(baseDetailDto);
        }
        return list;
    }

    public List<MonthlyStatistics> listarVentaMensual(String criterio, String fechaIni, String fechaFin) throws Exception {
        CallableStatement statement = connection.prepareCall("{call SP_S_VentaMensual(?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pfecha_ini", fechaIni);
        statement.setString("pfecha_fin", fechaFin);
        ResultSet rs = statement.executeQuery();
        List<MonthlyStatistics> list = new ArrayList<>();
        while(rs.next()){
            MonthlyStatistics monthlyStatistics = new MonthlyStatistics();
            monthlyStatistics.setDate(rs.getString("fecha"));
            monthlyStatistics.setTotal(rs.getDouble("total"));
            monthlyStatistics.setPercentage(rs.getFloat("porcentaje"));
            list.add(monthlyStatistics);
        }
        return list;
    }
}
