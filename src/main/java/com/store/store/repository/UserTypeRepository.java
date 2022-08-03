package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.UserType;
import com.store.store.entities.dtos.UserTypeLoginDataDto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsTipoUsuario
 */
public class UserTypeRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarTipoUsuario(UserType userType) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pdescripcion",userType.getDescription());
        statement.setBoolean("pp_venta",userType.isP_Venta());
        statement.setBoolean("pp_compra",userType.isP_Compra());
        statement.setBoolean("pp_producto",userType.isP_Producto());
        statement.setBoolean("pp_proveedor",userType.isP_Proveedor());
        statement.setBoolean("pp_empleado",userType.isP_Empleado());
        statement.setBoolean("pp_cliente",userType.isP_Cliente());
        statement.setBoolean("pp_categoria",userType.isP_Categoria());
        statement.setBoolean("pp_tipodoc",userType.isP_Tipodoc());
        statement.setBoolean("pp_tipouser",userType.isP_Tipouser());
        statement.setBoolean("pp_anularv",userType.isP_Anularv());
        statement.setBoolean("pp_anularc",userType.isP_Anularc());
        statement.setBoolean("pp_estadoprod",userType.isP_Estadoprod());
        statement.setBoolean("pp_ventare",userType.isP_Ventare());
        statement.setBoolean("pp_ventade",userType.isP_Ventade());
        statement.setBoolean("pp_estadistica",userType.isP_Estadistica());
        statement.setBoolean("pp_comprare",userType.isP_Comprare());
        statement.setBoolean("pp_comprade",userType.isP_Comprade());
        statement.setBoolean("pp_pass",userType.isP_Pass());
        statement.setBoolean("pp_respaldar",userType.isP_Respaldar());
        statement.setBoolean("pp_restaurar",userType.isP_Restaurar());
        statement.setBoolean("pp_caja",userType.isP_Caja());
        statement.execute();
    }
    public void modificarTipoUsuario(String codigo, UserType userType) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pidtipousuario",codigo);
        statement.setString("pdescripcion",userType.getDescription());
        statement.setBoolean("pp_venta",userType.isP_Venta());
        statement.setBoolean("pp_compra",userType.isP_Compra());
        statement.setBoolean("pp_producto",userType.isP_Producto());
        statement.setBoolean("pp_proveedor",userType.isP_Proveedor());
        statement.setBoolean("pp_empleado",userType.isP_Empleado());
        statement.setBoolean("pp_cliente",userType.isP_Cliente());
        statement.setBoolean("pp_categoria",userType.isP_Categoria());
        statement.setBoolean("pp_tipodoc",userType.isP_Tipodoc());
        statement.setBoolean("pp_tipouser",userType.isP_Tipouser());
        statement.setBoolean("pp_anularv",userType.isP_Anularv());
        statement.setBoolean("pp_anularc",userType.isP_Anularc());
        statement.setBoolean("pp_estadoprod",userType.isP_Estadoprod());
        statement.setBoolean("pp_ventare",userType.isP_Ventare());
        statement.setBoolean("pp_ventade",userType.isP_Ventade());
        statement.setBoolean("pp_estadistica",userType.isP_Estadistica());
        statement.setBoolean("pp_comprare",userType.isP_Comprare());
        statement.setBoolean("pp_comprade",userType.isP_Comprade());
        statement.setBoolean("pp_pass",userType.isP_Pass());
        statement.setBoolean("pp_respaldar",userType.isP_Respaldar());
        statement.setBoolean("pp_restaurar",userType.isP_Restaurar());
        statement.setBoolean("pp_caja",userType.isP_Caja());
        statement.executeUpdate();
    }
    public List<UserType> listarTipoUsuario() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_TipoUsuario}");
        ResultSet rs = statement.executeQuery();
        return userTypeList(rs);
    }
    
    public List<UserType> listarTipoUsuarioPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_TipoUsuarioPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        /*
        IdTipoUsuario,Descripcion,p_venta,p_compra,p_producto,p_proveedor,p_empleado,p_cliente
        p_categoria,p_tipodoc,p_tipouser,p_anularv,p_anularc,p_estadoprod,p_ventare,p_ventade
        p_estadistica,p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja
        */

        return userTypeList(rs);
    }
    public UserTypeLoginDataDto consultarLoginPermisos(String nomuser, String tipouser) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_LoginPermisos(?,?)}");
        statement.setString("pnombre_usuario", nomuser);
        statement.setString("pdescripcion_tipousuario", tipouser);
        ResultSet rs = statement.executeQuery();
//        IdTipoUsuario,e.Usuario,Descripcion,p_venta,p_compra,p_producto,
//        p_proveedor,p_empleado,p_cliente,p_categoria,p_tipodoc,p_tipouser,
//        p_anularv,p_anularc,p_estadoprod,p_ventare,p_ventade,p_estadistica,
//        p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja
        UserTypeLoginDataDto userTypeLoginDataDto = null;
        while(rs.next()){
            userTypeLoginDataDto = new UserTypeLoginDataDto();
            userTypeLoginDataDto.setId(rs.getLong("IdTipoUsuario"));
            userTypeLoginDataDto.setUser(rs.getString("Usuario"));
            userTypeLoginDataDto.setDescription(rs.getString("Descripcion"));
            userTypeLoginDataDto.setP_Venta(rs.getBoolean("p_venta"));
            userTypeLoginDataDto.setP_Compra(rs.getBoolean("p_compra"));
            userTypeLoginDataDto.setP_Producto(rs.getBoolean("p_producto"));
            userTypeLoginDataDto.setP_Proveedor(rs.getBoolean("p_proveedor"));
            userTypeLoginDataDto.setP_Empleado(rs.getBoolean("p_empleado"));
            userTypeLoginDataDto.setP_Cliente(rs.getBoolean("p_cliente"));
            userTypeLoginDataDto.setP_Categoria(rs.getBoolean("p_categoria"));
            userTypeLoginDataDto.setP_Tipodoc(rs.getBoolean("p_tipodoc"));
            userTypeLoginDataDto.setP_Tipouser(rs.getBoolean("p_tipouser"));
            userTypeLoginDataDto.setP_Anularv(rs.getBoolean("p_anularv"));
            userTypeLoginDataDto.setP_Anularc(rs.getBoolean("p_anularc"));
            userTypeLoginDataDto.setP_Estadoprod(rs.getBoolean("p_estadoprod"));
            userTypeLoginDataDto.setP_Ventare(rs.getBoolean("p_ventare"));
            userTypeLoginDataDto.setP_Ventade(rs.getBoolean("p_ventade"));
            userTypeLoginDataDto.setP_Estadistica(rs.getBoolean("p_estadistica"));
            userTypeLoginDataDto.setP_Comprare(rs.getBoolean("p_comprare"));
            userTypeLoginDataDto.setP_Comprade(rs.getBoolean("p_comprade"));
            userTypeLoginDataDto.setP_Pass(rs.getBoolean("p_pass"));
            userTypeLoginDataDto.setP_Respaldar(rs.getBoolean("p_respaldar"));
            userTypeLoginDataDto.setP_Restaurar(rs.getBoolean("p_restaurar"));
            userTypeLoginDataDto.setP_Caja(rs.getBoolean("p_caja"));
        }
        return userTypeLoginDataDto;
    }

    private List<UserType> userTypeList(ResultSet rs) throws Exception{
        List<UserType> tipousuariousuarios=new ArrayList<UserType>();
        while (rs.next()){
            UserType tipousuario=new UserType();
            tipousuario.setId(rs.getLong("IdTipoUsuario"));
            tipousuario.setDescription(rs.getString("Descripcion"));
            tipousuario.setP_Venta(rs.getBoolean("p_venta"));
            tipousuario.setP_Compra(rs.getBoolean("p_compra"));
            tipousuario.setP_Producto(rs.getBoolean("p_producto"));
            tipousuario.setP_Proveedor(rs.getBoolean("p_proveedor"));
            tipousuario.setP_Empleado(rs.getBoolean("p_empleado"));
            tipousuario.setP_Cliente(rs.getBoolean("p_cliente"));
            tipousuario.setP_Categoria(rs.getBoolean("p_categoria"));
            tipousuario.setP_Tipodoc(rs.getBoolean("p_tipodoc"));
            tipousuario.setP_Tipouser(rs.getBoolean("p_tipouser"));
            tipousuario.setP_Anularv(rs.getBoolean("p_anularv"));
            tipousuario.setP_Anularc(rs.getBoolean("p_anularc"));
            tipousuario.setP_Estadoprod(rs.getBoolean("p_estadoprod"));
            tipousuario.setP_Ventare(rs.getBoolean("p_ventare"));
            tipousuario.setP_Ventade(rs.getBoolean("p_ventade"));
            tipousuario.setP_Estadistica(rs.getBoolean("p_estadistica"));
            tipousuario.setP_Comprare(rs.getBoolean("p_comprare"));
            tipousuario.setP_Comprade(rs.getBoolean("p_comprade"));
            tipousuario.setP_Pass(rs.getBoolean("p_pass"));
            tipousuario.setP_Respaldar(rs.getBoolean("p_respaldar"));
            tipousuario.setP_Restaurar(rs.getBoolean("p_restaurar"));
            tipousuario.setP_Caja(rs.getBoolean("p_caja"));
            tipousuariousuarios.add(tipousuario);
        }
        return tipousuariousuarios;
    }

}
