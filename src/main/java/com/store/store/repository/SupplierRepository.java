package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.Supplier;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsProveedor
 */
public class SupplierRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarProveedor(Supplier Proveedor) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Proveedor(?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pnombre",Proveedor.getName());
        statement.setString("pruc",Proveedor.getRuc());
        statement.setString("pdni",Proveedor.getDni());
        statement.setString("pdireccion",Proveedor.getAddress());
        statement.setString("ptelefono",Proveedor.getPhone());
        statement.setString("pcelular",Proveedor.getCellPhone());
        statement.setString("pemail",Proveedor.getEmail());
        statement.setString("pcuenta1",Proveedor.getAccount1());
        statement.setString("pcuenta2",Proveedor.getAccount2());
        statement.setString("pestado",Proveedor.getState());
        statement.setString("pobsv",Proveedor.getObservation());
        statement.execute();
    }

    public void modificarProveedor(String codigo, Supplier Proveedor) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_Proveedor(?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pidproveedor",codigo);
        statement.setString("pnombre",Proveedor.getName());
        statement.setString("pruc",Proveedor.getRuc());
        statement.setString("pdni",Proveedor.getDni());
        statement.setString("pdireccion",Proveedor.getAddress());
        statement.setString("ptelefono",Proveedor.getPhone());
        statement.setString("pcelular",Proveedor.getCellPhone());
        statement.setString("pemail",Proveedor.getEmail());
        statement.setString("pcuenta1",Proveedor.getAccount1());
        statement.setString("pcuenta2",Proveedor.getAccount2());
        statement.setString("pestado",Proveedor.getState());
        statement.setString("pobsv",Proveedor.getObservation());
        statement.executeUpdate();
    }

    public List<Supplier> listarProveedor() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Proveedor}");
        ResultSet rs = statement.executeQuery();
        return supplierList(rs);
    }

    public List<Supplier> listarProveedorPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ProveedorPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
//        p.IdProveedor,p.Nombre,p.Ruc,p.Dni,p.Direccion,p.Telefono,p.Celular,p.Email,p.Cuenta1,p.Cuenta2,p.Estado,p.Obsv
        return supplierList(rs);
    }

    private List<Supplier> supplierList(ResultSet rs) throws Exception{
        List<Supplier> proveedores=new ArrayList<>();
        while (rs.next()){
            Supplier proveedor=new Supplier();
            proveedor.setId(rs.getLong("idproveedor"));
            proveedor.setName(rs.getString("nombre"));
            proveedor.setRuc(rs.getString("ruc"));
            proveedor.setDni(rs.getString("dni"));
            proveedor.setAddress(rs.getString("direccion"));
            proveedor.setPhone(rs.getString("telefono"));
            proveedor.setCellPhone(rs.getString("celular"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setAccount1(rs.getString("cuenta1"));
            proveedor.setAccount2(rs.getString("cuenta2"));
            proveedor.setState(rs.getString("estado"));
            proveedor.setObservation(rs.getString("obsv"));
            proveedores.add(proveedor);
        }
        return proveedores;
    }
}
