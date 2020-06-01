/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */

// executeQuery for select
//executeUpdate for INSERT, UPDATE and DELETE stm
public class Api {
    protected static Connection conn = null;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/quanlytieccuoi";
    private static final String user = "root";
    private static final String password = "12345678";
    
    protected Statement stm = null;
    protected PreparedStatement pStm = null; 
    protected ResultSet rs = null; //biến  trả về 
    protected CallableStatement cStm = null; //dùng trong store procedures
    
    public static void connectSql() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
    }
    public static void disconnectSql() throws SQLException{
        conn.close();
    }
    protected void read(String sql) throws SQLException{
        try {
            this.stm = conn.createStatement();
            this.rs = stm.executeQuery(sql);
            //return rs;
        } catch (Exception e) {
            throw new Error("can't readSC");
        } finally{
            //stm.close();
        }
    }
    protected void writeOrDelete(String sql, String action) throws SQLException{
        try {
            stm = conn.createStatement();
            int kq = stm.executeUpdate(sql);
            if (kq==1)
                System.out.println(action+" success.");
            else
                System.out.println(action+" fail.");
        } catch (Exception e) {
            throw new Error("can't writeSC");
        } finally{
            stm.close();
        }
    }
//    protected abstract void edit(String sql);
//    private void delete(String dong) throws SQLException{
//        try {
//            pStm = conn.prepareStatement("delete from sanh_cuoi where MaSC = '?'");
//            pStm.setString(1, dong);
//            pStm.executeUpdate();
//        } catch (Exception e) {
//            pStm.close();
//        }
//    }
//    public void showSC(ResultSet rs) throws SQLException{
//        while(rs.next()){
//            System.out.printf("%d\n%s\n%s\n%s\n%s\n%d\n_________________", 
//                rs.getInt("MaHoaDon"),
//                rs.getString("ThoiDiem"),
//                rs.getString("NgayThue"),
//                rs.getString("TenBuoiTiec"),
//                rs.getString("MaSC"),
//                rs.getInt("TongTien")
//                );
//        }
//    }
}
