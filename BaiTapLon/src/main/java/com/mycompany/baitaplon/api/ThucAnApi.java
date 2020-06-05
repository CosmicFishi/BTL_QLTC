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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThucAnApi extends Api {

    protected static int selected;

    // unusesual
    public List<ThucAn> getList() throws SQLException {
        String sql = "select ta.*, if (MaThucAnChay is not null, true, false) as 'isChay'"
                + "from thuc_an ta"
                + "left join thuc_an_chay tac on ta.MaThucAn = tac.MaThucAnChay;";
        super.read(sql);

        List<ThucAn> kq = new ArrayList<>();
        while (rs.next()) {
            ThucAn ta = new ThucAn(rs.getString("TenThucAn"),
                    rs.getInt("Gia"), rs.getBoolean("isChay"));
        }
        return kq;
    }

    /**
     * Lấy một dòng trong csdl trả về lưu dưới dạng new ThucAn()
     * 
     * @param ma Mã thức ăn trong csdl
     * @return về một Thức Ăn trong sơ sở dữ liệu thành new ThucAn
     * @throws SQLException
     */
    public ThucAn get1ThucAn(int ma) throws SQLException {
        String sql = "select ta.*, if (MaThucAnChay is not null, true, false) as 'isChay' "
                + "from thuc_an ta "
                + "left join thuc_an_chay tac on ta.MaThucAn = tac.MaThucAnChay "
                + "where ta.MaThucAn = " + ma + ";";
        super.read(sql);
        if (rs.next()) {
            ThucAn ta = new ThucAn(rs.getString("TenThucAn"),
                    rs.getInt("Gia"), rs.getBoolean("isChay"));
            return ta;
        }
        return new ThucAn();
    }

    /**
     * In ra console tất cả dữ liệu thức ăn đã lưu trong Mysql.
     *
     * @throws SQLException
     */
    public void readShow() throws SQLException {
        cStm = conn.prepareCall("{call getThucAn()}");
        rs = cStm.executeQuery();
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
        cStm = conn.prepareCall("{call findThucAnChay(?)}");
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
            cStm.setInt(1, an.getMa());
            cStm.setString(2, an.getTen());
            cStm.setInt(3, an.getGia());
            cStm.setBoolean(4, an.isIsAnChay());
            if (cStm.executeUpdate() == 1) {
                System.out.println("Update thanh cong.");
            } else {
                System.out.println("Update that bai");
            }
        } catch (SQLException e) {
            System.err.println("Edit err fail.");
        } finally {
            cStm.close();
        }

    }

    protected void showThucAn() throws SQLException {
        //this.setSelected(rs.getString("MaSC"));
        System.out.format("  Ma Thuc An | Ten Thuc An                                 |  Gia         | isChay\n");
        System.out.format("+------------+---------------------------------------------+--------------+-------%n");
        while (rs.next()) {
            System.out.printf("| %-11d|  %-43s| %-13d| %-6s\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"),
                    rs.getBoolean("isChay"));
        }

    }

    protected void showThucAn(int limit) throws SQLException {
        if (rs.next()) {
            System.out.format("  Ma Thuc An | Ten Thuc An                                 |  Gia         | isChay\n");
            System.out.format("+------------+---------------------------------------------+--------------+-------%n");
            System.out.printf("| %-11d|  %-43s| %-13d| %-6s\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"),
                    rs.getBoolean("isChay"));

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
