package com.store.store.repository;


import com.store.store.config.ClsConexion;
import com.store.store.entities.Client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsCliente
 */
public class ClientRepository {

    private Connection connection=new ClsConexion().getConection();

    /**
     * agregarCliente
     * @param Cliente
     * @throws Exception
     */
    public void save(Client Cliente) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Cliente(?,?,?,?,?,?)}");
        statement.setString("pnombre",Cliente.getName());
        statement.setString("pruc",Cliente.getNit());
        statement.setString("pdni",Cliente.getDni());
        statement.setString("pdireccion",Cliente.getAddress());
        statement.setString("ptelefono",Cliente.getPhone());
        statement.setString("pobsv",Cliente.getObservation());
        statement.execute();
    }
    
    /**
     * agregarClienteObtenerIdAsignado
     * Agrega un nuevo cliente y retorna el cliente con el id que se le asignó
     * @param cliente
     * @return cliente
     */
     public Client agregarClienteObtenerIdAsignado(Client cliente) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Cliente(?,?,?,?,?,?)}");
        statement.setString("pnombre",cliente.getName());
        statement.setString("pruc",cliente.getNit());
        statement.setString("pdni",cliente.getDni());
        statement.setString("pdireccion",cliente.getAddress());
        statement.setString("ptelefono",cliente.getPhone());
        statement.setString("pobsv",cliente.getObservation());
        ResultSet resultSet = statement.executeQuery();
        cliente.setId(0);
        while (resultSet.next()){
            cliente.setId(resultSet.getLong("clienteId"));
        }
        return cliente;
    }

    public void modificarCliente(String codigo, Client Cliente) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_Cliente(?,?,?,?,?,?,?)}");
        statement.setString("pidcliente",codigo);
        statement.setString("pnombre",Cliente.getName());
        statement.setString("pruc",Cliente.getNit());
        statement.setString("pdni",Cliente.getDni());
        statement.setString("pdireccion",Cliente.getAddress());
        statement.setString("ptelefono",Cliente.getPhone());
        statement.setString("pobsv",Cliente.getObservation());
        statement.executeUpdate();
    }

    public Client findById(long id) throws Exception{
        CallableStatement statement = connection.prepareCall("{call 002_SP_S_ClientePorId(?)}");
        statement.setLong("pidcliente", id);
        ResultSet resultSet = statement.executeQuery();
        Client client = null;
        while (resultSet.next()) {
            client = new Client();
            client.setId(resultSet.getLong("idcliente"));
            client.setName(resultSet.getString("nombre"));
            client.setNit(resultSet.getString("ruc"));
            client.setDni(resultSet.getString("dni"));
            client.setAddress(resultSet.getString("direccion"));
            client.setPhone(resultSet.getString("telefono"));
            client.setObservation(resultSet.getString("obsv"));
        }
        return client;
    }
    
    public List<Client> listarCliente() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Cliente}");
        ResultSet resultSet = statement.executeQuery();
        return clientList(resultSet);
    }

    public List<Client> listarClientePorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ClientePorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        return clientList(rs);
    }

    private List<Client> clientList(ResultSet rs) throws Exception{
        List<Client> list = new ArrayList<>();
        while(rs.next()){
            Client client = new Client();
            client.setId(rs.getLong("idcliente"));
            client.setName(rs.getString("nombre"));
            client.setNit(rs.getString("ruc"));
            client.setDni(rs.getString("dni"));
            client.setAddress(rs.getString("direccion"));
            client.setPhone(rs.getString("telefono"));
            client.setObservation(rs.getString("obsv"));
            list.add(client);
        }
        return list;
    }
    
}
