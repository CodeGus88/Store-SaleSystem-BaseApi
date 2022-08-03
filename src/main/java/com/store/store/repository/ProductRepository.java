package com.store.store.repository;

import com.store.store.config.ClsConexion;
import com.store.store.entities.Product;
import com.store.store.entities.dtos.ProductDto;
import com.store.store.entities.dtos.ProductStockDto;
import com.store.store.entities.dtos.ProductValueKardex;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClsProducto
 */
public class ProductRepository {

    private Connection connection=new ClsConexion().getConection();

    public void agregarProducto(Product product) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_I_Producto(?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString("pcodigo",product.getCode());
        statement.setString("pnombre",product.getName());
        statement.setString("pdescripcion",product.getDescription());
        statement.setDouble("pstock",product.getStock());
        statement.setDouble("pstockmin",product.getMinStock());
        statement.setDouble("ppreciocosto",product.getCost());
        statement.setDouble("pprecioventa",product.getPrice());
        statement.setDouble("putilidad",product.getUtility());
        statement.setString("pestado",product.getState());
        statement.setLong("pidcategoria",product.getCategoryId());
        statement.setString("pimagen",product.getImage());
        if(product.getExpirationDate() != null)
            statement.setDate("pfechavencimiento",new java.sql.Date(product.getExpirationDate().getTime()));
        else
            statement.setDate("pfechavencimiento", null);
        statement.execute();
    }

    public void modificarProducto(Long id, Product product) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_Producto(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setLong("pidproducto", id);
        statement.setString("pcodigo", product.getCode());
        statement.setString("pnombre", product.getName());
        statement.setString("pdescripcion", product.getDescription());
        statement.setDouble("pstock", product.getStock());
        statement.setDouble("pstockmin", product.getMinStock());
        statement.setDouble("ppreciocosto", product.getCost());
        statement.setDouble("pprecioventa", product.getPrice());
        statement.setDouble("putilidad", product.getUtility());
        statement.setString("pestado", product.getState());
        statement.setLong("pidcategoria", product.getCategoryId());
        statement.setString("pimagen", product.getImage());
        if (product.getExpirationDate() != null)
            statement.setDate("pfechavencimiento", new java.sql.Date(product.getExpirationDate().getTime()));
        else
            statement.setDate("pfechavencimiento", null);
        statement.executeUpdate();
    }

    public void actualizarProductoStock(String codigo, ProductDto producto) throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_U_ActualizarProductoStock(?,?)}");
        statement.setString("pidproducto",codigo);
        statement.setString("pstock", producto.getStrStockProducto());
        statement.executeUpdate();
    }

    public List<ProductDto> listarProducto() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_Producto}");
        ResultSet rs = statement.executeQuery();
        List<ProductDto> productos = new ArrayList<>();
        return productDtoList(rs);
    }

    public List<ProductDto> listarProductoActivo() throws Exception{
        CallableStatement statement=connection.prepareCall("{call SP_S_ProductoActivo}");
        ResultSet rs = statement.executeQuery();
        return productDtoList(rs);
    }

    public List<ProductDto> listarProductoPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ProductoPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        return productDtoList(rs);
    }

    public List<ProductDto> listarProductoActivoPorParametro(String criterio, String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ProductoActivoPorParametro(?,?)}");
        statement.setString("pcriterio", criterio);
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        return productDtoList(rs);
    }
    
    public List<ProductStockDto> consultaStock() throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ConsultaStock()}");
        ResultSet rs = statement.executeQuery();
        List<ProductStockDto> list = new ArrayList<>();
        while(rs.next()){
            ProductStockDto productStockDto = new ProductStockDto();
            productStockDto.setId(rs.getLong("idproducto"));
            productStockDto.setCode(rs.getString("codigo"));
            productStockDto.setName(rs.getString("nombre"));
            productStockDto.setCategory(rs.getString("categoria"));
            productStockDto.setStock(rs.getDouble("stock"));
            productStockDto.setMinStock(rs.getDouble("stockmin"));
            productStockDto.setCost(rs.getDouble("preciocosto"));
            productStockDto.setPrice(rs.getDouble("precioventa"));
            productStockDto.setUtility(rs.getDouble("utilidad"));
        }
        return list;
    }

     public List<ProductValueKardex> kardexValorizado() throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_KardexValorizado()}");
        ResultSet rs = statement.executeQuery();
         List<ProductValueKardex> list = new ArrayList<>();
         while(rs.next()){
            ProductValueKardex productValueKardex = new ProductValueKardex();
            productValueKardex.setId(rs.getLong("idproducto"));
            productValueKardex.setCode(rs.getString("codigo"));
            productValueKardex.setName(rs.getString("nombre"));
            productValueKardex.setCategory(rs.getString("categoria"));
            productValueKardex.setIncomeAmount(rs.getDouble("cantidadingreso"));
            productValueKardex.setPriceIncome(rs.getDouble("precioingreso"));
            productValueKardex.setExpenseIncome(rs.getDouble("gastoingreso"));
            productValueKardex.setQualitySale(rs.getDouble("cantidadventa"));
            productValueKardex.setSalePrice(rs.getDouble("precioventa"));
            productValueKardex.setExpenseSale(rs.getDouble("gastoventa"));
            productValueKardex.setStock(rs.getDouble("stock"));
            productValueKardex.setPricePurchase(rs.getDouble("preciocompra"));
            productValueKardex.setTotalValued(rs.getDouble("totalvalorizado"));
            list.add(productValueKardex);
        }
        return list;
    }

    public List<ProductDto> verificarCodigoBar(String busqueda) throws Exception{
        CallableStatement statement = connection.prepareCall("{call SP_S_ProductoVerificarCodigoBar(?)}");
        statement.setString("pbusqueda", busqueda);
        ResultSet rs = statement.executeQuery();
        return productDtoList(rs);
    }

    private List<ProductDto> productDtoList(ResultSet rs) throws Exception{
        List<ProductDto> list =  new ArrayList<>();
        while(rs.next()){
            ProductDto productDto = new ProductDto();
            productDto.setStrIdProducto(rs.getLong("idproducto"));
            productDto.setStrCodigoProducto(rs.getString("codigo"));
            productDto.setStrNombreProducto(rs.getString("nombre"));
            productDto.setStrDescripcionProducto(rs.getString("descripcion"));
            productDto.setStrStockProducto(rs.getString("stock"));
            productDto.setStrStockMinProducto(rs.getString("stockmin"));
            productDto.setStrPrecioCostoProducto(rs.getString("precioCosto"));
            productDto.setStrPrecioVentaProducto(rs.getString("precioVenta"));
            productDto.setStrUtilidadProducto(rs.getString("utilidad"));
            productDto.setStrEstadoProducto(rs.getString("estado"));
            productDto.setStrDescripcionCategoria(rs.getString("categoria"));
            productDto.setStrImagen(rs.getString("imagen"));
            productDto.setFechaVencimiento(rs.getDate("fechavencimiento"));
        }
        return list;
    }
}
