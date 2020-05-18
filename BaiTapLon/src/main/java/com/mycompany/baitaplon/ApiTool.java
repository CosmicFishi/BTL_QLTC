/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class ApiTool {
    public ApiTool() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public void connectSql() throws SQLException{
        Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quanlytieccuoi", "root", "haungo230899");
        Statement stm = conn.createStatement();
        //int kq= stm.executeUpdate("insert into sanh_cuoi values ('S002', 1, 'Sảnh đường hoa', 1, 500, 1200000);");
        //System.out.println("Ket Qua Chen: "+ kq);
        ResultSet rs = stm.executeQuery("select * from sanh_cuoi");
        
        while(rs.next()){
            String maSC = rs.getString("MaSC");
            int maHD = rs.getInt("MaHD");
            String tenSC = rs.getString("TenSC");
            int vtSC = rs.getInt("ViTriSC");
            int sucChua = rs.getInt("SucChua");
            int giaThue = rs.getInt("GiaThue");
            
            System.out.printf("%s \t %d \n %s \n %d\t%d\t%d\n", maSC, maHD, tenSC, vtSC, sucChua, giaThue);
        }
        
        
        stm.close();
        conn.close();
    }
}
