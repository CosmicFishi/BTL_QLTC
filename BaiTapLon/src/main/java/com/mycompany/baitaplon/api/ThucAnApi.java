/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.SanhCuoi;
import com.mycompany.baitaplon.ThucAn;
import static com.mycompany.baitaplon.api.Api.conn;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ThucAnApi extends Api {

    protected static int selected;

    public void readShow() throws SQLException {
        String sql = "select * from thuc_an;";
        super.read(sql);
        showThucAn();
    }

    public void addThucAn(ThucAn s) throws SQLException {
        try {
            cStm = conn.prepareCall("{call addThucAn(?,?,?,?)}");
            cStm.setInt(1, s.getMa());
            cStm.setString(2, s.getTen());
            cStm.setInt(3, s.getGia());
            cStm.setBoolean(4, s.isIsAnChay());
            cStm.execute();
        } catch (SQLException e) {
            System.err.println("Add Thuc An that bai");
        } finally {
            System.out.println("Add Thuc An thanh cong");
            cStm.close();
        }
    }

    public void deleteThucAn() throws SQLException {
        String sql1 = "delete from thuc_an_chay where MaThucAnChay =" + selected + ";";
        super.writeOrDelete(sql1, "delete in ThucAnChay table");

        String sql = "delete from thuc_an where MaThucAn =" + selected + ";";
        super.writeOrDelete(sql, "delete in ThucAn table");
    }

    public boolean findThucAn(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findThucAn(?)}");
        cStm.setString(1, tenHoacMa);
        rs = cStm.executeQuery();
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay thuc an nhu yeu cau.");
            return false;
        }
        return true;
    }

    protected void edit(ThucAn an) throws SQLException {
        try {
            cStm = conn.prepareCall("{call updateThucAn(?,?,?,?)}");
            cStm.setInt("MaThucAn", an.getMa());
            cStm.setString("TenThucAn", an.getTen());
            cStm.setInt("Gia", an.getGia());
            cStm.setBoolean("chay", an.isIsAnChay());
            cStm.execute();
        } catch (SQLException e) {
            System.err.println("Edit err fail.");
        } finally {
            cStm.close();
        }

    }

    protected void showThucAn() throws SQLException {
        //this.setSelected(rs.getString("MaSC"));
        System.out.format("  Ma Thuc An | Ten Thuc An                                 |  Gia         |\n");
        System.out.format("+------------+---------------------------------------------+--------------+%n");
        while (rs.next()) {
            System.out.printf("| %-11d|  %-43s| %-13d|\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"));
        }

    }

    protected void showThucAn(int limit) throws SQLException {
        if (rs.next()) {
            System.out.format("  Ma Thuc An | Ten Thuc An                                 |  Gia         |\n");
            System.out.format("+------------+---------------------------------------------+--------------+%n");
            System.out.printf("| %-11d|  %-43s| %-13d|\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"));

            this.setSelected(rs.getInt("MaThucAn"));
        }
    }

    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int aSelected) {
        selected = aSelected;
    }
}
