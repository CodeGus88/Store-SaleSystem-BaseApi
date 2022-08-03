package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.Category;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsCategoria
 */
public class CategoryRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarCategoria(Category Categoria) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Categoria(?)}");
        statement.setString("pdescripcion",Categoria.getCategory());
        statement.execute();
    }

    public void modificarCategoria(String codigo, Category Categoria) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_Categoria(?,?)}");
        statement.setString("pidcategoria",codigo);
        statement.setString("pdescripcion",Categoria.getCategory());
        statement.executeUpdate();
    }

    public List<Category> listarCategoria()throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Categoria}");
        ResultSet resultSet=statement.executeQuery();
        List<Category> categorias = new ArrayList<>();
        while (resultSet.next()){
            Category categoria=new Category();
            categoria.setId(resultSet.getLong("idcategoria"));
            categoria.setCategory(resultSet.getString("descripcion"));
            categorias.add(categoria);
        }
        return categorias;
    }

    public List<Category> listarCategoriaPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_CategoriaPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        List<Category> list = new ArrayList<>();
        while(rs.next()){
            Category category = new Category();
            category.setId(rs.getLong("idcategoria"));
            category.setCategory(rs.getString("description"));
            list.add(category);
        }
        return list;
    }
}
