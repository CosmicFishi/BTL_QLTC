/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import com.mycompany.baitaplon.DvKaraoke;
import com.mycompany.baitaplon.DvThueCS;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DVKaraokeApi extends DVApi{

    @Override
    public void readShow() throws SQLException {
        String sql = "select * from dv_karaoke";
        super.read(sql);
        showDV();
    }
    
    /**
     * Thêm dịch vụ karaoke
     * @param d: dịch vụ karaoke
     * @throws SQLException 
     */
    @Override
    public void addDV(DichVu d) throws SQLException {
        String sql = d.toString();
        sql = "insert into dv_karaoke values(" +  sql + ")";
        super.writeOrDelete(sql, "add");
    }

    @Override
    public void deleteDV() throws SQLException {
        String sql = "delete from dv_karaoke where MaHD = " + getMaHoaDon() + ";";
        super.writeOrDelete(sql, "delete");
    }
   
    @Override
    protected void showDV() throws SQLException {
        System.out.println("Ma hoa don        | Khoang thoi gian thue    |Gia dich vu \n");
        System.out.println("+----------------+|+------------------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-18d| %-25s| %-12d|\n",
                    rs.getInt("MaHD"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
        }
    }
//Thiếu hàm edit
    @Override
    protected void showDV(int i) throws SQLException {
        if(rs.next()) {
            System.out.println("Ma hoa don        | Khoang thoi gian thue    |Gia dich vu \n");
            System.out.println("+----------------+|+------------------------+|+-----------+\n");
            System.out.printf("|%-18d| %-25s| %-12d|\n",
                    rs.getInt("MaHD"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
            DVKaraokeApi.setMaHoaDon(rs.getString("MaHD"));
        }
    }
    
    
    
    
    
}
