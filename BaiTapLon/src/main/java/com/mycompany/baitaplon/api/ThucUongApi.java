/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DoAnUong;
import com.mycompany.baitaplon.SanhCuoi;
import com.mycompany.baitaplon.ThucAn;
import com.mycompany.baitaplon.ThucUong;
import static com.mycompany.baitaplon.api.Api.conn;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ThucUongApi extends Api {

    protected static int selected;

    public ThucUong getThucUong(int ma) throws SQLException {
        String sql = "select * "
                + "from thuc_uong "
                + "where MaThucUong = " + ma + ";";
        super.read(sql);
        if(rs.next()){
            String ten = rs.getString("TenThucUong");
            int gia = rs.getInt("Gia");
            String nhaSX = rs.getString("HangSX");
            ThucUong tu = new ThucUong(ten, gia, nhaSX);
            return tu;
        }
        return new ThucUong();
    }

    public void readShow(String tenBang) throws SQLException {
        String sql = "select * from " + tenBang + ";";
        super.read(sql);
        showThucUong(false);
    }//ok

    public void addThucUong(DoAnUong s, String tenBang) throws SQLException {
        String sql = s.toString();
        sql = "insert into " + tenBang + " values (" + sql + ");";
        super.writeOrDelete(sql, "add");
    }

    public void deleteThucUong() throws SQLException {
        String sql = "delete from sanh_cuoi where MaThucUong ='" + selected + "';";
        super.writeOrDelete(sql, "delete");
    }

    public boolean findThucUong(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findThucUong(?)}");
        cStm.setString(1, tenHoacMa);
        rs = cStm.executeQuery();
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay thuc an nhu yeu cau.");
            return false;
        }
        return true;
    }

    protected void edit(ThucUong tu) throws SQLException {
        try {
            pStm = conn.prepareStatement("update thuc_uong set "
                    + "TenThucUong=?,"
                    + "Gia = ?,"
                    + "HangSX = ?,"
                    + " where MaThucUong = ?;");
            pStm.setString(1, tu.getTen());
            pStm.setInt(2, tu.getGia());
            pStm.setString(3, tu.getHangSX());
            pStm.setInt(4, this.selected);
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

    protected void showThucUong(boolean nhap) throws SQLException {
        System.out.println("+------------+---------------------------------------------+--------------+--------------+");
        System.out.println("|Ma Thuc Uong| Ten Thuc Uong                               |  Gia         | Hang SX      |");
        System.out.println("+------------+---------------------------------------------+--------------+--------------+");
        while (rs.next()) {
            System.out.printf("| %-11d|  %-43s| %-13d| %-12s |\n",
                    rs.getInt("MaThucUong"),
                    rs.getString("TenThucUong"),
                    rs.getInt("Gia"),
                    rs.getString("HangSX"));
            this.setSelected(rs.getInt("MaThucUong"));
            if(nhap) break;
        }
        System.out.println("+------------+---------------------------------------------+--------------+--------------+");
    }

    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int Selected) {
        selected = Selected;
    }
}
