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
    
    public void readShow() throws SQLException{
        String sql = "select * from sanh_cuoi;";
        ResultSet kq = super.read(sql);
        showSC(kq);
    }
    public void addSC(SanhCuoi s) throws SQLException{
        String sql=s.toString();
        sql = "insert into sanh_cuoi values ("+ sql +");";
        if (super.writeOrDelete(sql)==1)
            System.out.print("Add successful.");
        else
            System.out.println("Can't add SC");
    }
    public void deleteSC(String MaSC) throws SQLException{
        String sql = "delete from sanh_cuoi where MaSC = '"+MaSC+"'";
        if (super.writeOrDelete(sql)==1)
            System.out.print("Delete successful.");
        else
            System.out.println("Can't delete SC");
    }

   
    protected void edit(String TenSC, int ViTriSC, int SucChua, int GiaThue) {
        try {
            pStm = conn.prepareStatement("update sanh_cuoi set " +
                    "TenSC = 'Cầu Vồng'," +
                    "    ViTriSC=?," +
                    "    SucChua=?," +
                    "    GiaThue = ?" +
                    "where MaSC= '?';");
            pStm.setString(1, dong);
            pStm.executeUpdate();
        } catch (Exception e) {
            pStm.close();
        }
    }
    
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
