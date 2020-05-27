/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DVCaSiApi extends DVApi {

    @Override
    public void addDV(DichVu d) throws SQLException {
        String sql = d.toString();
        sql = "insert into dv_ca_si values("  + sql + ")";
        super.writeOrDelete(sql, "add");
    }

    @Override
    public void readShow() throws SQLException {
        String sql = "select * from dv_ca_si";
        super.read(sql);
        showDV();
    }

    @Override
    protected void showDV() throws SQLException {
        System.out.println("Ma hoa don        | Thong tin ca si      | So luong bai hat  |Gia dich vu \n");
        System.out.println("+----------------+|+---------------------|+-----------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-18d| %-23s| %-19d|%-12d\n",
                    rs.getInt("MaHD"),
                    rs.getString("ThongTinCaSi"),
                    rs.getInt("SoLuongBaiHat"),
                    rs.getInt("GiaDv"));
        }
    }
//Thiếu hàm edit
    @Override
    protected void showDV(int i) throws SQLException {
        if(rs.next()) {
            System.out.println("Ma hoa don        | Thong tin ca si      | So luong bai hat  |Gia dich vu \n");
            System.out.println("+----------------+|+---------------------|+-----------------+|+-----------+\n");
            System.out.printf("|%-18d| %-23s| %-19d|%-12d\n",
                    rs.getInt("MaHD"),
                    rs.getString("ThongTinCaSi"),
                    rs.getInt("SoLuongBaiHat"),
                    rs.getInt("GiaDv"));
            DVCaSiApi.setMaHoaDon(rs.getString("MaHD")) ;
        }
        
    }
    
    
    
}
