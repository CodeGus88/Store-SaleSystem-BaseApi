package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.Credit;
import com.store.store.entities.dtos.CreditDto;
import com.store.store.entities.dtos.MonthlyStatistics;
import com.store.store.entities.dtos.BaseDetailDto;
import com.store.store.entities.dtos.accountsreceivable.AccountsReceivableItem;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * ClsCredito
 */
public class CreditRepository {

    private Connection connection = new ClsConexion().getConection();

    public boolean agregarCredito(Credit credit) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_I_Credito(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setLong("pidtipodocumento", credit.getDocumentTypeId()); // statement.setString("pidtipodocumento", credit.getStrIdTipoDocumento());
        statement.setLong("pidcliente", credit.getClientId()); //statement.setString("pidcliente", credit.getStrIdCliente());
        statement.setLong("pidempleado", credit.getEmployeeId()); // statement.setString("pidempleado", credit.getStrIdEmpleado());
        statement.setString("pserie", credit.getSerie()); // statement.setString("pserie", credit.getStrSerieCredito());
        statement.setString("pnumero", credit.getNumber()); // statement.setString("pnumero", credit.getStrNumeroCredito());
        statement.setDate("pfecha", new java.sql.Date(credit.getDate().getTime())); // statement.setDate("pfecha", new java.sql.Date(credit.getStrFechaCredito().getTime()));
        statement.setDouble("ptotalcredito", credit.getTotalCredit()); // statement.setString("ptotalcredito", credit.getStrTotalCredito());
        statement.setDouble("pdescuento", credit.getDiscount()); // statement.setString("pdescuento", credit.getStrDescuentoCredito());
        statement.setDouble("psubtotal", credit.getSubtotal()); // statement.setString("psubtotal", credit.getStrSubTotalCredito());
        statement.setDouble("pigv", credit.getIgv()); // statement.setString("pigv", credit.getStrIgvCredito());
        statement.setDouble("ptotalpagar", credit.getTotalPayable()); // statement.setString("ptotalpagar", credit.getStrTotalPagarCredito());
        statement.setString("pestado", credit.getState()); // statement.setString("pestado", credit.getStrEstadoCredito());
        statement.setBoolean("pporcobrar", credit.isReceivable()); // statement.setBoolean("pporcobrar", credit.isPorCobrar());
        statement.execute();
        return true;
    }

    public boolean modificarCredito(String codigo, Credit credit) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_U_Credito(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pidcredito", codigo); // statement.setString("pidcredito", codigo);
        statement.setLong("pidtipodocumento", credit.getDocumentTypeId()); // statement.setString("pidtipodocumento", credit.getStrIdTipoDocumento());
        statement.setLong("pidcliente", credit.getClientId()); // statement.setString("pidcliente", credit.getStrIdCliente());
        statement.setLong("pidempleado", credit.getEmployeeId()); // statement.setString("pidempleado", credit.getStrIdEmpleado());
        statement.setString("pserie", credit.getSerie()); // statement.setString("pserie", credit.getStrSerieCredito());
        statement.setString("pnumero", credit.getNumber()); // statement.setString("pnumero", credit.getStrNumeroCredito());
        statement.setDate("pfecha", new java.sql.Date(credit.getDate().getTime())); // statement.setDate("pfecha", new java.sql.Date(credit.getStrFechaCredito().getTime()));
        statement.setDouble("ptotalcredito", credit.getTotalCredit()); // statement.setString("ptotalcredito", credit.getStrTotalCredito());
        statement.setDouble("pdescuento", credit.getDiscount()); // statement.setString("pdescuento", credit.getStrDescuentoCredito());
        statement.setDouble("psubtotal", credit.getSubtotal()); // statement.setString("psubtotal", credit.getStrSubTotalCredito());
        statement.setDouble("pigv", credit.getIgv()); // statement.setString("pigv", credit.getStrIgvCredito());
        statement.setDouble("ptotalpagar", credit.getTotalPayable()); // statement.setString("ptotalpagar", credit.getStrTotalPagarCredito());
        statement.setString("pestado", credit.getState()); // statement.setString("pestado", credit.getStrEstadoCredito());
        statement.setBoolean("pporcobrar", credit.isReceivable()); // statement.setBoolean("pporcobrar", credit.isPorCobrar());
        statement.executeUpdate();
        return true;
    }
    
    public boolean payCredit(int creditId, boolean payable, java.sql.Date date) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 001_SP_U_PagarCredito(?,?,?)}");
        statement.setInt("pidcredito", creditId);
        statement.setDate("pfechacobro", date);
        statement.setBoolean("pporcobrar", payable);
        statement.executeUpdate();
        return true;
    }
    
    public Credit findById(int creditId) throws Exception{
        CallableStatement statement = connection.prepareCall("{call 000_CreditoPorId(?)}");
        statement.setInt("pidcredito", creditId);
        ResultSet resultSet = statement.executeQuery();
        Credit credit = null;
        while (resultSet.next()) {
            credit = new Credit();
            credit.setId(resultSet.getLong("idcredito")); //credit.setStrIdCredito(resultSet.getString("idcredito"));
            credit.setDocumentTypeId(resultSet.getLong("idtipodocumento")); // credit.setStrIdTipoDocumento(resultSet.getString("idtipodocumento"));
            credit.setClientId(resultSet.getLong("idcliente"));
            credit.setEmployeeId(resultSet.getLong("idempleado"));
            credit.setSerie(resultSet.getString("serie"));
            credit.setNumber(resultSet.getString("numero"));
            credit.setDate(resultSet.getDate("fecha")); // credit.setStrFechaCredito(resultSet.getDate("fecha"));
            credit.setTotalCredit(resultSet.getDouble("totalcredito"));
            credit.setDiscount(resultSet.getDouble("descuento"));
            credit.setSubtotal(resultSet.getDouble("subtotal"));
            credit.setIgv(resultSet.getDouble("igv"));
            credit.setTotalPayable(resultSet.getDouble("totalpagar"));
            credit.setState(resultSet.getString("estado"));
            credit.setPaymentDate(resultSet.getDate("fechacobro"));
            credit.setReceivable(resultSet.getBoolean("porcobrar"));
        }
        return credit;
    }

    public ArrayList<CreditDto> listarCredito() throws Exception {
        ArrayList<CreditDto> creditos = new ArrayList<CreditDto>();
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_Credito}");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CreditDto credito = new CreditDto();
            credito.setStrIdCredito(resultSet.getString("IdCredito"));
            credito.setStrTipoDocumento(resultSet.getString("TipoDocumento"));
            credito.setStrCliente(resultSet.getString("Cliente"));
            credito.setStrEmpleado(resultSet.getString("Empleado"));
            credito.setStrSerieCredito(resultSet.getString("Serie"));
            credito.setStrNumeroCredito(resultSet.getString("Numero"));
            credito.setStrFechaCredito(resultSet.getDate("Fecha"));
            credito.setStrTotalCredito(resultSet.getString("TotalCredito"));
            credito.setStrDescuentoCredito(resultSet.getString("Descuento"));
            credito.setStrSubTotalCredito(resultSet.getString("SubTotal"));
            credito.setStrIgvCredito(resultSet.getString("Igv"));
            credito.setStrTotalPagarCredito(resultSet.getString("TotalPagar"));
            credito.setStrEstadoCredito(resultSet.getString("Estado"));
            creditos.add(credito);
        }
        return creditos;
    }
    
    /**
     * Lista todos los dréditos
     * @return listaCreditos
     */
    public ArrayList<AccountsReceivableItem> listarCreditoPagable(boolean porCobrar) throws Exception {
        ArrayList<AccountsReceivableItem> items = new ArrayList<AccountsReceivableItem>();
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_CreditoPagable(?)}");
        statement.setBoolean("pporcobrar", porCobrar);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            AccountsReceivableItem receivableItem = new AccountsReceivableItem();
            receivableItem.setId(resultSet.getInt("idcredito"));
            receivableItem.setClientName(resultSet.getString("nombrecliente"));
            receivableItem.setClientDni(resultSet.getString("dnicliente"));
            receivableItem.setClientRuc(resultSet.getString("ruccliente"));
            receivableItem.setEmployeeName(resultSet.getString("nombreempleado"));
            receivableItem.setIgv(resultSet.getFloat("igv"));
            receivableItem.setSubtotal(resultSet.getFloat("subtotal"));
            receivableItem.setTotalCredit(resultSet.getFloat("totalcredito"));
            receivableItem.setDiscount(resultSet.getFloat("descuento"));
            receivableItem.setTotal(resultSet.getFloat("totalpagar"));
            receivableItem.setDate(resultSet.getDate("fecha"));
            items.add(receivableItem);
        }
        return items;
    }

    public List<CreditDto> listarCreditoPorParametro(String criterio, String busqueda) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_CreditoPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<CreditDto> list = new ArrayList<>();
        while(rs.next()){
            CreditDto creditDto = new CreditDto();
            creditDto.setStrIdCredito(rs.getString("idcredito"));
            creditDto.setStrTipoDocumento(rs.getString("tipodocumento"));
            creditDto.setStrCliente(rs.getString("cliente"));
            creditDto.setStrEmpleado(rs.getString("empleado"));
            creditDto.setStrSerieCredito(rs.getString("serie"));
            creditDto.setStrNumeroCredito(rs.getString("numero"));
            creditDto.setStrFechaCredito(rs.getDate("fecha"));
            creditDto.setStrTotalCredito(rs.getString("totalcredito"));
            creditDto.setStrDescuentoCredito(rs.getString("descuento"));
            creditDto.setStrSubTotalCredito(rs.getString("subtotal"));
            creditDto.setStrIgvCredito(rs.getString("igv"));
            creditDto.setStrTotalPagarCredito(rs.getString("totalpagar"));
            creditDto.setStrEstadoCredito(rs.getString("estado"));
            list.add(creditDto);
        }
        return list;
    }

    public long obtenerUltimoIdCredito() throws Exception {
        ResultSet rs = null;
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_UltimoIdCredito()}");
        rs = statement.executeQuery();
        long ultimoIdCredito = -1;
        while(rs.next()){
            ultimoIdCredito = rs.getLong("id");
        }
        return ultimoIdCredito;
    }

    public List<CreditDto> listarCreditoPorFecha(String criterio, Date fechaini, Date fechafin, String doc) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_CreditoPorFecha(?,?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setDate("pfechaini", new java.sql.Date(fechaini.getTime()));
        statement.setDate("pfechafin", new java.sql.Date(fechafin.getTime()));
        statement.setString("pdocumento", doc);
        ResultSet rs = statement.executeQuery();
        List<CreditDto> list = new ArrayList<>();
        while(rs.next()){
            CreditDto creditDto = new CreditDto();
            creditDto.setStrIdCredito(rs.getString("idcredito"));
            creditDto.setStrCliente(rs.getString("cliente"));
            creditDto.setStrFechaCredito(rs.getDate("fecha"));
            creditDto.setStrEmpleado(rs.getString("empleado"));
            creditDto.setStrTipoDocumento(rs.getString("tipodocumento"));
            creditDto.setStrSerieCredito(rs.getString("serie"));
            creditDto.setStrNumeroCredito(rs.getString("numero"));
            creditDto.setStrEstadoCredito(rs.getString("estado"));
            creditDto.setStrTotalPagarCredito(rs.getString("totalpagar"));
            creditDto.setStrTotalCredito(rs.getString("totalcredito"));
            creditDto.setStrDescuentoCredito(rs.getString("descuento"));
            creditDto.setStrSubTotalCredito(rs.getString("subtotal"));
            creditDto.setStrIgvCredito(rs.getString("igv"));
            list.add(creditDto);
        }
        return list;
    }

    public boolean actualizarCreditoEstado(String codigo, String state) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_U_ActualizarCreditoEstado(?,?)}");
        statement.setString("pidcredito", codigo);
        statement.setString("pestado", state);
        statement.executeUpdate();
        return true;
    }

    public List<BaseDetailDto> listarCreditoPorDetalle(String criterio, Date fechaini, Date fechafin) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_CreditoPorDetalle(?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setDate("pfechaini", new java.sql.Date(fechaini.getTime()));
        statement.setDate("pfechafin", new java.sql.Date(fechafin.getTime()));
        ResultSet rs = statement.executeQuery();
        List<BaseDetailDto> list = new ArrayList<>();
        while(rs.next()){
            BaseDetailDto baseDetailDto = new BaseDetailDto();
            baseDetailDto.setCode(rs.getString("Codigo"));
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

    public List<MonthlyStatistics> listarCreditoMensual(String criterio, String fecha_ini, String fecha_fin) throws Exception {
        CallableStatement statement = connection.prepareCall("{call 000_SP_S_CreditoMensual(?,?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pfecha_ini", fecha_ini);
        statement.setString("pfecha_fin", fecha_fin);
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
