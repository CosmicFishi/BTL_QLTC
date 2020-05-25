/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DoAnUong;
import com.mycompany.baitaplon.SanhCuoi;
import static com.mycompany.baitaplon.api.Api.conn;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ThucAnUongApi extends Api{
    protected static String selected;

    public void readShow(String tenBang) throws SQLException {
        String sql = "select * from "+tenBang+";";
        super.read(sql);
        showSC();
    }//ok

    public void addSC(DoAnUong s, String tenBang) throws SQLException {
        String sql = s.toString();
        sql = "insert into "+tenBang+" values (" + sql + ");";
        super.writeOrDelete(sql, "add");
    }

    public void deleteSC() throws SQLException {
        String sql = "delete from sanh_cuoi where MaSC ='" + selected + "';";
        super.writeOrDelete(sql, "delete");
    }

    public void findSC(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findScByName(?)}");
        cStm.setString(1, "%" + tenHoacMa + "%");
        rs = cStm.executeQuery();
    }

    protected boolean isNullRs() throws SQLException {
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay SC nhu yeu cau.");
            return true;
        }
        return false;
    }

    protected void edit(SanhCuoi sc) throws SQLException {
        try {
            pStm = conn.prepareStatement("update sanh_cuoi set "
                    + "TenSC = ?,"
                    + "ViTriSC=?,"
                    + "SucChua=?,"
                    + "GiaThue = ?"
                    + " where MaSC = ?;");
            pStm.setString(1, sc.getTenSanh());
            pStm.setInt(2, sc.getViTriSanh());
            pStm.setInt(3, sc.getSucChua());
            pStm.setInt(4, sc.getGiaThue());
            pStm.setString(5, this.selected);
            int kq = pStm.executeUpdate();
            if (kq == 1) {
                System.out.println("Edit success");
            } else {
                System.out.println("Edit fail");
            }
        } catch (SQLException e) {
            System.err.println("Edit fail.");
        } finally {
            pStm.close();
        }

    }

    protected void showSC() throws SQLException {
        //this.setSelected(rs.getString("MaSC"));
        System.out.format("  Ten sanh          | vi tri |suc chua | gia thue    \n");
        System.out.format("+-------------------+--------+---------+-------------+%n");
        while (rs.next()) {
            System.out.printf("| %-18s|  %-6d| %-8d| %-12d|\n",
                    rs.getString("TenSC"),
                    rs.getInt("ViTriSC"),
                    rs.getInt("SucChua"),
                    rs.getInt("GiaThue"));
        }

    }

    protected void showSC(int limit) throws SQLException {
        if (rs.next()) {
            System.out.format("  Ten sanh          | vi tri |suc chua | gia thue    \n");
            System.out.format("+-------------------+--------+---------+-------------+%n");
            System.out.printf("| %-18s|  %-6d| %-8d| %-12d|\n",
                    rs.getString("TenSC"),
                    rs.getInt("ViTriSC"),
                    rs.getInt("SucChua"),
                    rs.getInt("GiaThue"));
            this.setSelected(rs.getString("MaSC"));
        }
    }

    public static String getSelected() {
        return selected;
    }

    public static void setSelected(String aSelected) {
        selected = aSelected;
    }
}
