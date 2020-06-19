/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.QLMenu;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MenuApi extends Api{
    public void layDsMonUongTheoHoaDonSQL(int maHD) {
        try {
            cStm = conn.prepareCall("{call getThucUongTheoHoaDon(?)}");
            cStm.setInt(1, maHD);
            rs = cStm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QLMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void layDsMonAnTheoHoaDonSQL(int maHD) {
        try {
            cStm = conn.prepareCall("{call getThucAnTheoHoaDon(?)}");
            cStm.setInt(1, maHD);
            rs = cStm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QLMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Dùng để show những thức ăn đã luu trong qlmenu
     *
     * @throws SQLException
     */
    public void showThucAnDaLuu() throws SQLException {
        int tongTien = 0;
        System.out.format("\n+-----------+---------------------------------------------+--------------+-------|\n");
        System.out.format("| So Luong  | Ten Thuc An                                 |  Gia         | isChay|\n");
        System.out.format("+-----------+---------------------------------------------+--------------+-------|\n");
        while (rs.next()) {
            System.out.printf("| %-10d|  %-43s| %,-13d| %-6s|\n",
                    rs.getInt("SoLuong"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"),
                    rs.getBoolean("isChay"));
            tongTien += rs.getInt("SoLuong") * rs.getInt("Gia");
        }
        System.out.printf(" Tong don gia thuc an: %,d\n", tongTien);
        System.out.format("+-----------+---------------------------------------------+--------------+-------+\n");
    }
    /**
     * Dùng để show những thức uóng đã luu trong qlmenu
     *
     * @throws SQLException
     */
    public void showThucUongDaLuu() throws SQLException {
        int tongTien = 0;
        System.out.format("\n+----------+---------------------------------------------+--------------+-------------+\n");
        System.out.format("| So Luong | Ten Thuc Uong                               |  Gia         | Hang SX     |\n");
        System.out.format("+----------+---------------------------------------------+--------------+-------------|\n");
        while (rs.next()) {
            System.out.printf("| %-9d|  %-43s| %,-13d| %-12s|\n",
                    rs.getInt("SoLuong"),
                    rs.getString("TenThucUong"),
                    rs.getInt("Gia"),
                    rs.getString("HangSX"));
            tongTien += rs.getInt("SoLuong") * rs.getInt("Gia");
        }
        System.out.printf(" Tong don gia thuc uong: %,d\n", tongTien);
        System.out.format("+----------+---------------------------------------------+--------------+-------------+\n");
    }
}
