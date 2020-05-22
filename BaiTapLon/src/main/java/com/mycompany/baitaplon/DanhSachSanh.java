/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DanhSachSanh extends Api{
    private List<SanhCuoi> dsSanh =  new ArrayList<>();
    
    public void them(SanhCuoi s) throws SQLException {
        String sql=s.toString();
        addBySql(sql);
    }
    
    public void show() throws SQLException{
        String sql="select * from sanh_cuoi;";
        try {
            ResultSet rs = showBySql(sql);
            System.out.format("  Ten sanh          | vi tri |suc chua | gia thue    \n");
            System.out.format("+-------------------+--------+---------+-------------+%n");
            while(rs.next()){
                System.out.printf("%-20s|  %-6d| %-8d| %-12d|\n",
                        rs.getString("TenSC"),
                        rs.getInt("ViTriSC"),
                        rs.getInt("SucChua"),
                        rs.getInt("GiaThue"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachSanh.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConn();
    }
    
    public void xuat() {
        this.dsSanh.forEach((SanhCuoi s) -> {
            System.out.println(s);
        });
        
    }
    public void capNhat(SanhCuoi s) {
        Scanner scanner = new Scanner(System.in);
        s.nhap(scanner);
    }
    public void xoa(SanhCuoi s) {
        this.dsSanh.remove(s);
    }
    
    public ArrayList<SanhCuoi> traCuu(String kw) {
        kw = kw.toLowerCase();
        ArrayList<SanhCuoi> kq = new ArrayList<>();
        for(SanhCuoi s: this.dsSanh) 
            if(s.getTenSanh().toLowerCase().contains(kw) == true)
                kq.add(s);
        return kq;
    }
    public ArrayList<SanhCuoi> traCuu(int t) {
        ArrayList<SanhCuoi> kq = new ArrayList<>();
        this.dsSanh.stream().filter((s) -> (s.getTenSanh().toLowerCase().equals(t) == true)).forEachOrdered((s) -> {
            kq.add(s);
        });
        return kq;
    }
}
