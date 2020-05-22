/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author Admin
 */
public abstract class Api {
    protected final Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quanlytieccuoi", "root", "haungo230899");
    private String tenBang;
    
    public Api() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");        
    }
    public void addBySql(String str) throws SQLException{
        Statement stm = conn.createStatement();
        stm.executeUpdate(str); 
        stm.close();
    }
    private void xoa(int dong) throws SQLException{
        CallableStatement stm = conn.prepareCall("{call xoaHoaDon(?)}");
        stm.setInt(1, dong);
        stm.execute();
    }
    
    public ResultSet showBySql(String sql) throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        stm.close();
        return rs;
    }
    public void sua(String tenBang) throws SQLException{
        String sql="select * from ?;";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, tenBang);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            System.out.printf("%d\n%s\n%s\n%s\n%s\n%d\n_________________", 
                rs.getInt("MaHoaDon"),
                rs.getString("ThoiDiem"),
                rs.getString("NgayThue"),
                rs.getString("TenBuoiTiec"),
                rs.getString("MaSC"),
                rs.getInt("TongTien")
                );
        }
        
    }
    
    public void closeConn() throws SQLException{
        conn.close();
    }
}
