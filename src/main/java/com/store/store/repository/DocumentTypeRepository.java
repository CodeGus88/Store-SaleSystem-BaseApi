package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.DocumentType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsTipoDocumento
 */
public class DocumentTypeRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarTipoDocumento(DocumentType TipoDocumento){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_TipoDocumento(?)}");
            statement.setString("pdescripcion",TipoDocumento.getDocumentType());
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    public void modificarTipoDocumento(String codigo, DocumentType TipoDocumento) throws Exception{
            CallableStatement statement=connection.prepareCall("{call SP_U_TipoDocumento(?,?)}");
            statement.setString("pidtipodocumento",codigo);
            statement.setString("pdescripcion",TipoDocumento.getDocumentType());
            statement.executeUpdate();
    }
    
    public List<DocumentType> listarTipoDocumento() throws Exception{
        List<DocumentType> tipodocumentos=new ArrayList<>();
            CallableStatement statement=connection.prepareCall("{call SP_S_TipoDocumento}");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                DocumentType categoria=new DocumentType();
                categoria.setId(resultSet.getLong("IdTipoDocumento"));
                categoria.setDocumentType(resultSet.getString("Descripcion"));
                tipodocumentos.add(categoria);
            }
            return tipodocumentos;
    }
         
    public List<DocumentType> listarTipoDocumentoPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_TipoDocumentoPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<DocumentType> list = new ArrayList<>();
        while(rs.next()){
            DocumentType documentType = new DocumentType();
            documentType.setId(rs.getLong("idtipodocumento"));
            documentType.setDocumentType(rs.getString("descripcion"));
        }
        return list;
    }

}
