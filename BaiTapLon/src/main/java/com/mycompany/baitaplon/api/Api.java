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
    private static final String password = "haungo230899";

    protected Statement stm = null;
    protected PreparedStatement pStm = null;
    protected ResultSet rs = null; //biến  trả về 
    protected CallableStatement cStm = null; //dùng trong store procedures//dùng trong store procedures

    public static void connectSql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Accessing mysql...");
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Accessed");
        System.out.println("===================================================");
    }

    public static void disconnectSql() throws SQLException {;
        System.out.println("===================================================");
        conn.close();
        System.out.println("Disconnected to mysql database.");
    }

    protected void read(String sql) throws SQLException {
        try {
            this.stm = conn.createStatement();
            this.rs = stm.executeQuery(sql);
        } catch (Exception e) {
            throw new Error("can't read query");
        }
    }

    protected void writeOrDelete(String sql, String action) throws SQLException {
        try {
            stm = conn.createStatement();
            int kq = stm.executeUpdate(sql);
            if (kq == 1) {
                System.out.println(action + " success.");
            } else {
                System.out.println(action + " khong co.");
            }
        } catch (Exception e) {
            throw new Error("can't update or delete");
        }
    }
}
