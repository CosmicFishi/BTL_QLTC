/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DoAnUong;
import com.mycompany.baitaplon.ThucUong;
import static com.mycompany.baitaplon.api.Api.conn;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ThucUongApi extends Api {

    protected static int selected;

    public ThucUong get1ThucUong(int ma) throws SQLException {
        String sql = "select * from thuc_uong where MaThucUong = " + ma + ";";
        super.read(sql);
        if (rs.next()) {
            return new ThucUong(rs.getInt("MaThucUong"), rs.getString("TenThucUong"),
                    rs.getInt("Gia"),
                    rs.getString("HangSX"));
        }
        return new ThucUong();
    }
    public int getMaxMaThucUongSQL() throws SQLException, Exception {
        try {
            String sql = "select max(MaThucUong) as 'Max' from thuc_uong;";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Max");
            }else
                throw new Exception("Loi khong lay dc id thuc uong max.");
        } catch (SQLException ex) {
            throw new Exception("Loi khong lay dc id thuc uong max.");
        }
    }
    public void readShow(String tenBang) throws SQLException {
        String sql = "select * from " + tenBang + ";";
        super.read(sql);
        showThucUong(false);
        stm.close();
    }

    public void addThucUong(DoAnUong s) throws SQLException {
        String sql = String.format("insert into thuc_uong values (%s);", s.toString());
        super.writeOrDelete(sql, "Luu thuc uong ");
    }

    public void deleteThucUong() throws SQLException {
        String sql = "delete from thuc_uong where MaThucUong =" + selected + ";";
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
                    + "HangSX = ?"
                    + " where MaThucUong = ?;");
            pStm.setString(1, tu.getTen());
            pStm.setInt(2, tu.getGia());
            pStm.setString(3, tu.getHangSX());
            pStm.setInt(4, ThucUongApi.selected);
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
            System.out.printf("| %-11d|  %-43s| %-13d| %-13s|\n",
                    rs.getInt("MaThucUong"),
                    rs.getString("TenThucUong"),
                    rs.getInt("Gia"),
                    rs.getString("HangSX"));
            ThucUongApi.setSelected(rs.getInt("MaThucUong"));
            if (nhap) {
                break;
            }
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
