/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import com.mycompany.baitaplon.DvKaraoke;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DVKaraokeApi extends DVApi{

    @Override
    public void readShow() throws SQLException {
        super.readShow();
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
        super.addDV(d);
        String sql2 = d.xuat();
        sql2 ="insert into dv_karaoke values(" + sql2 + ")";
        super.writeOrDelete(sql2, "add"); // thêm mã và khoảng thời gian thuê vào bảng karaoke
    }

    @Override
    public void deleteDV() throws SQLException {
        super.deleteDV();
        String sql2  =  "delete from dv_karaoke where MaDV= " + getSelected()  + ";";
        super.writeOrDelete(sql2, "delete");
    }

    public void editKa(DvKaraoke d) {
        try{
            pStm = conn.prepareCall("update dv_karaoke set"
                    + "KhoangThoiGianThue = ?"
                    + "where MaDV = ?");
            pStm.setString(1, d.getKhoangTG());
            pStm.setString(2, d.getMaDV());
        } catch(SQLException e) {
            System.err.println("error");
        }
    }
   
    @Override
    protected void showDV() throws SQLException {
        System.out.println("Ma dich vu       | Khoang thoi gian thue    |Gia dich vu \n");
        System.out.println("+---------------+|+------------------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-17d| %-25s| %-12d|\n",
                    rs.getInt("MaDV"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
        }
    }
//Thiếu hàm edit
    @Override
    protected void showDV(int i) throws SQLException {
        if(rs.next()) {
            System.out.println("Ma dich vu        | Khoang thoi gian thue    |Gia dich vu \n");
            System.out.println("+----------------+|+------------------------+|+-----------+\n");
            System.out.printf("|%-17d| %-25s| %-12d|\n",
                    rs.getInt("MaDV"),
                    rs.getString("KhoangThoiGianThue"),
                    rs.getInt("giaDichVu"));
            DVKaraokeApi.setSelected(rs.getString("MaHD"));
        }
    }
    
    
    
    
    
}
