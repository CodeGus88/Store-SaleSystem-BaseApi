package com.store.store.config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;

public class ClsConexion {
    
    private static Connection conection=null;

    public Connection getConection(){
        try{
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setDatabaseName("bd_tiendastec_base");
            conection=ds.getConnection("root","");
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return conection;
    }
    
}
