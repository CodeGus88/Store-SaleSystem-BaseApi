package com.store.store.repository;


import com.store.store.config.ClsConexion;
import com.store.store.entities.dtos.CashRegisterItem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


/**
 *  ClsCashRegister
 */
public class CashRegisterRepository {
    
    public ArrayList<CashRegisterItem> listCashRegisterItem(String tableName, Date date) throws Exception{
        Connection connection = new ClsConexion().getConection();
        ArrayList<CashRegisterItem> list = new ArrayList<>();
        CallableStatement statement = connection.prepareCall("{call 099_SP_S_CreditoVentaCajaPorFecha(?,?)}");
        statement.setString("pnombretabla", tableName);
        statement.setDate("pfecha", new java.sql.Date(date.getTime()));
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            CashRegisterItem cash = new CashRegisterItem();
            cash.setAmount(rs.getFloat("cantidad"));
            cash.setName(rs.getString("producto"));
            cash.setPrice(rs.getFloat("precio"));
            cash.setPrice(rs.getFloat("precio"));
            cash.setTotal(rs.getFloat("total"));
            cash.setUtility(rs.getFloat("ganancia"));
            cash.setDate(rs.getDate("fechacobro"));
            list.add(cash);
        }
        connection.close();
        return list;
    }
}
