package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.dtos.employee.EmployeeDto;
import com.store.store.entities.dtos.employee.EmployeeDtoX;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsEmpleado
 */
public class EmployeeRepository {

    private Connection connection = new ClsConexion().getConection();
    
    public void agregarEmpleado (EmployeeDtoX empleado) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pnombre", empleado.getStrNombreEmpleado());
        statement.setString("papellido", empleado.getStrApellidoEmpleado());
        statement.setString("psexo", empleado.getStrSexoEmpleado());
        statement.setDate("pfechanac", new java.sql.Date(empleado.getStrFechaNacEmpleado().getTime()));
        statement.setString("pdireccion", empleado.getStrDireccionEmpleado());
        statement.setString("ptelefono", empleado.getStrTelefonoEmpleado());
        statement.setString("pcelular", empleado.getStrCelularEmpleado());
        statement.setString("pemail", empleado.getStrEmailEmpleado());
        statement.setString("pdni", empleado.getStrDniEmpleado());
        statement.setDate("pfechaing", new java.sql.Date(empleado.getStrFechaIngEmpleado().getTime()));
        statement.setString("psueldo", empleado.getStrSueldoEmpleado());
        statement.setString("pestado", empleado.getStrEstadoEmpleado());
        statement.setString("pusuario", empleado.getStrUsuarioEmpleado());
        statement.setString("pcontrasenia", empleado.getStrContraseniaEmpleado());
        statement.setLong("pidtipousuario", empleado.getStrIdTipoUsuario());
        statement.execute();
    }
    
    public EmployeeDtoX findById(int employeeId) throws Exception{
        CallableStatement statement = connection.prepareCall("{call 003_SP_S_EmpleadoPorId(?)}");
        statement.setInt("pidempleado", employeeId);
        ResultSet resultSet = statement.executeQuery();
        EmployeeDtoX employee= null;
        while (resultSet.next()) {
            employee = new EmployeeDtoX();
            employee.setStrIdEmpleado(resultSet.getLong("IdEmpleado"));
            employee.setStrNombreEmpleado(resultSet.getString("Nombre"));
            employee.setStrApellidoEmpleado(resultSet.getString("Apellido"));
            employee.setStrSexoEmpleado(resultSet.getString("Sexo"));
            employee.setStrFechaNacEmpleado(resultSet.getDate("FechaNac"));
            employee.setStrDireccionEmpleado(resultSet.getString("Direccion"));
            employee.setStrTelefonoEmpleado(resultSet.getString("Telefono"));
            employee.setStrCelularEmpleado(resultSet.getString("Celular"));
            employee.setStrEmailEmpleado(resultSet.getString("Email"));
            employee.setStrDniEmpleado(resultSet.getString("Dni"));
            employee.setStrFechaIngEmpleado(resultSet.getDate("FechaIng"));
            employee.setStrSueldoEmpleado(resultSet.getString("Sueldo"));
            employee.setStrEstadoEmpleado(resultSet.getString("Estado"));
            employee.setStrUsuarioEmpleado(resultSet.getString("Usuario"));
            employee.setStrContraseniaEmpleado(resultSet.getString("Contrasenia"));
            employee.setStrIdTipoUsuario(resultSet.getLong("idTipoUsuario"));
        }
        return employee;
    }
    
    public void modificarEmpleado (String codigo, EmployeeDtoX empleado) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pnombre", empleado.getStrNombreEmpleado());
        statement.setString("papellido", empleado.getStrApellidoEmpleado());
        statement.setString("psexo", empleado.getStrSexoEmpleado());
        statement.setDate("pfechanac", new java.sql.Date(empleado.getStrFechaNacEmpleado().getTime()));
        statement.setString("pdireccion", empleado.getStrDireccionEmpleado());
        statement.setString("ptelefono", empleado.getStrTelefonoEmpleado());
        statement.setString("pcelular", empleado.getStrCelularEmpleado());
        statement.setString("pemail", empleado.getStrEmailEmpleado());
        statement.setString("pdni", empleado.getStrDniEmpleado());
        statement.setDate("pfechaing", new java.sql.Date(empleado.getStrFechaIngEmpleado().getTime()));
        statement.setString("psueldo", empleado.getStrSueldoEmpleado());
        statement.setString("pestado", empleado.getStrEstadoEmpleado());
        statement.setString("pusuario", empleado.getStrUsuarioEmpleado());
        statement.setString("pcontrasenia", empleado.getStrContraseniaEmpleado());
        statement.setLong("pidtipousuario", empleado.getStrIdTipoUsuario());
        statement.setString("pidempleado",codigo);
        statement.execute();
    }
    
    public List<EmployeeDtoX> listarEmpleado() throws Exception{
        List<EmployeeDtoX> empleados=new ArrayList<EmployeeDtoX>();
        CallableStatement statement=connection.prepareCall("{call SP_S_Empleado}");
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            EmployeeDtoX empleado=new EmployeeDtoX();
            empleado.setStrIdEmpleado(resultSet.getLong("IdEmpleado"));
            empleado.setStrNombreEmpleado(resultSet.getString("Nombre"));
            empleado.setStrApellidoEmpleado(resultSet.getString("Apellido"));
            empleado.setStrSexoEmpleado(resultSet.getString("Sexo"));
            empleado.setStrFechaNacEmpleado(resultSet.getDate("FechaNac"));
            empleado.setStrDireccionEmpleado(resultSet.getString("Direccion"));
            empleado.setStrTelefonoEmpleado(resultSet.getString("Telefono"));
            empleado.setStrCelularEmpleado(resultSet.getString("Celular"));
            empleado.setStrEmailEmpleado(resultSet.getString("Email"));
            empleado.setStrDniEmpleado(resultSet.getString("Dni"));
            empleado.setStrFechaIngEmpleado(resultSet.getDate("FechaIng"));
            empleado.setStrSueldoEmpleado(resultSet.getString("Sueldo"));
            empleado.setStrEstadoEmpleado(resultSet.getString("Estado"));
            empleado.setStrUsuarioEmpleado(resultSet.getString("Usuario"));
            empleado.setStrContraseniaEmpleado(resultSet.getString("Contrasenia"));
            empleado.setStrTipoUsuario(resultSet.getString("TipoUsuario"));
            empleados.add(empleado);
        }
        return empleados;
    }

    public EmployeeDto LoginEmpleados(String usu, String contra, String desc) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Login(?,?,?)}");
        statement.setString("pusuario", usu);
        statement.setString("pcontrasenia", contra);
        statement.setString("pdescripcion", desc);
//        IdEmpleado, Nombre, Apellido, Sexo, FechaNac, Direccion, Telefono, Celular,
//        Email, Dni, FechaIng, Sueldo, Estado, Usuario, Contrasenia, Descripcion
        ResultSet rs = statement.executeQuery();
        EmployeeDto employeeDto = null;
        while(rs.next()){
            employeeDto = new EmployeeDto();
            employeeDto.setId(rs.getLong("idempleado"));
            employeeDto.setName(rs.getString("nombre"));
            employeeDto.setLastName(rs.getString("apellido"));
            String gender = rs.getString("sexo");
            employeeDto.setGender(gender!=null?gender.charAt(0):' ');
            employeeDto.setDateOfBirth(rs.getDate("FechaNac"));
            employeeDto.setAddress(rs.getString("Direccion"));
            employeeDto.setPhone(rs.getString("Telefono"));
            employeeDto.setCellPhone(rs.getString("Celular"));
            employeeDto.setEmail(rs.getString("Email"));
            employeeDto.setDni(rs.getString("Dni"));
            employeeDto.setDateOfAdmission(rs.getDate("FechaIng"));
            employeeDto.setSalary(rs.getDouble("Sueldo"));
            employeeDto.setState(rs.getString("Estado"));
            employeeDto.setUser(rs.getString("Usuario"));
            employeeDto.setPassword(rs.getString("Contrasenia"));
            employeeDto.setDescription(rs.getString("Descripcion"));
        }
        return employeeDto;
    }

    public List<EmployeeDto> listarEmpleadoPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_EmpleadoPorParametro(?,?)}");
//        IdEmpleado, Nombre, Apellido, Sexo, FechaNac, Direccion, Telefono, Celular,
//        Email, Dni, FechaIng, Sueldo, Estado, Usuario, Contrasenia, Descripcion
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<EmployeeDto> list = new ArrayList<>();
        while(rs.next()){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(rs.getLong("idempleado"));
            employeeDto.setName(rs.getString("nombre"));
            employeeDto.setLastName(rs.getString("apellido"));
            String gender = rs.getString("sexo");
            employeeDto.setGender(gender!=null?gender.charAt(0):' ');
            employeeDto.setDateOfBirth(rs.getDate("FechaNac"));
            employeeDto.setAddress(rs.getString("Direccion"));
            employeeDto.setPhone(rs.getString("Telefono"));
            employeeDto.setCellPhone(rs.getString("Celular"));
            employeeDto.setEmail(rs.getString("Email"));
            employeeDto.setDni(rs.getString("Dni"));
            employeeDto.setDateOfAdmission(rs.getDate("FechaIng"));
            employeeDto.setSalary(rs.getDouble("Sueldo"));
            employeeDto.setState(rs.getString("Estado"));
            employeeDto.setUser(rs.getString("Usuario"));
            employeeDto.setPassword(rs.getString("Contrasenia"));
            employeeDto.setDescription(rs.getString("Descripcion"));
            list.add(employeeDto);
        }
        return list;
    }

    public void cambiarContrasenia(String codigo, EmployeeDtoX Empleado) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_CambiarPass(?,?)}");
        statement.setString("pidempleado",codigo);
        statement.setString("pcontrasenia",Empleado.getStrContraseniaEmpleado());
        statement.executeUpdate();
    }

}
