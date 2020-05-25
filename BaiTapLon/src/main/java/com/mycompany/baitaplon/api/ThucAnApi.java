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
        showSC();
    }

    public void addSC(ThucAn s) throws SQLException {
        String sql = s.toString();
        sql = "insert into thuc_an values (" + sql + ");";
        super.writeOrDelete(sql, "add");
    }

    public void deleteSC() throws SQLException {
        String sql = "delete from thuc_an where MaSC ='" + selected + "';";
        super.writeOrDelete(sql, "delete");
    }

    public void findSC(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findThucAn(?)}");
        cStm.setString(1, "%" + tenHoacMa + "%");
        rs = cStm.executeQuery();
    }//ok

    protected boolean isNullRs() throws SQLException {
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay thuc an nhu yeu cau.");
            return true;
        }
        return false;
    }

    protected void edit(ThucAn an) throws SQLException {
        try {
            pStm = conn.prepareStatement("update thuc_an set "
                    + "TenThucAn = ?,"
                    + "Gia=?,"
                    + " where MaThucAn = ?;");
            pStm.setString(1, an.getTen());
            pStm.setInt(2, an.getGia());
            pStm.setInt(3, this.selected);
            int kq = pStm.executeUpdate();
            if (kq == 1) {
                System.out.println("Edit success");
            } else {
                System.out.println("Edit fail");
            }
        } catch (SQLException e) {
            System.err.println("Edit err fail.");
        } finally {
            pStm.close();
        }

    }

    protected void showSC() throws SQLException {
        //this.setSelected(rs.getString("MaSC"));
        System.out.format("  Ma Thuc An | Ten Thuc An      |  Gia         |\n");
        System.out.format("+------------+------------------+--------------+%n");
        while (rs.next()) {
            System.out.printf("| %-11s|  %-16d| %-12d|\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"));
        }

    }

    protected void showSC(int limit) throws SQLException {
        if (rs.next()) {
            System.out.format("  Ma Thuc An | Ten Thuc An      |  Gia         |\n");
            System.out.format("+------------+------------------+--------------+%n");
            System.out.printf("| %-11s|  %-16d| %-12d|\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"));
            this.setSelected(rs.getInt("MaSC"));
        }
    }

    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int aSelected) {
        selected = aSelected;
    }
}
