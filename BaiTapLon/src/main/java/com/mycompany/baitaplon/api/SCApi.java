/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.SanhCuoi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class SCApi extends Api{
    
    public void read() throws SQLException{
        String sql = "select * from sanh_cuoi;";
        ResultSet kq = super.read(sql);
        showSC(kq);
    }
//    public int add(SanhCuoi s){
//        String sql=s.toString();
//        sql = "insert into sanh_cuoi values ("+ sql +");";
//        write(sql);
//    }
    private void showSC(ResultSet rs) throws SQLException{
        System.out.format("  Ten sanh          | vi tri |suc chua | gia thue    \n");
        System.out.format("+-------------------+--------+---------+-------------+%n");
        while(rs.next()){
            System.out.printf("%-20s|  %-6d| %-8d| %-12d|\n",
                    rs.getString("TenSC"),
                    rs.getInt("ViTriSC"),
                    rs.getInt("SucChua"),
                    rs.getInt("GiaThue"));
        }
    }
}
