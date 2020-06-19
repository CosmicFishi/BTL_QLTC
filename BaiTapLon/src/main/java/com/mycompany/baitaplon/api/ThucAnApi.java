/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

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
            ThucAn ta = new ThucAn(rs.getInt("MaThucAn"),rs.getString("TenThucAn"),
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
        showThucAn(false);
        cStm.close();
    }

    /**
     *Hàm để trả về một giá trị max(mã) của thức ăn trong sql
     * @return Integer
     * @throws SQLException
     * @throws Exception
     */
    public int getMaxMaThucAnSQL() throws SQLException, Exception {
        try {
            String sql = "select max(MaThucAn) as 'Max' from thuc_an;";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Max");
            }else
                throw new Exception("Loi khong lay dc id thuc an max.");
        } catch (SQLException ex) {
            throw new Exception("Loi khong lay dc id thuc an max.");
        } finally{
            stm.close();
        }
    }

    /**
     *Thêm thức ăn vào SQL
     * @param s
     * @throws SQLException
     */
    public void addThucAn(ThucAn s) throws SQLException {
        try {
            cStm = conn.prepareCall("{call addThucAn(?,?,?,?)}");
            cStm.setInt(1, s.getMa());
            cStm.setString(2, s.getTen());
            cStm.setInt(3, s.getGia());
            cStm.setBoolean(4, s.isIsAnChay());
            cStm.execute();
            System.out.println("Add Thuc An thanh cong.");
        } catch (SQLException e) {
            System.err.println("Add Thuc An that bai");
        } finally {
            cStm.close();
        }
    }

    /**
     *Hàm này chỉ dùng được khi trc đó đã dùng hàm showThucAn;
     * @throws SQLException
     */
    public void deleteThucAn() throws SQLException {
        String sql1 = "delete from thuc_an_chay where MaThucAnChay =" + selected + ";";
        super.writeOrDelete(sql1, "delete in ThucAnChay table");

        String sql = "delete from thuc_an where MaThucAn =" + selected + ";";
        super.writeOrDelete(sql, "delete in ThucAn table");
    }

    /**
     *Tìm thức ăn qua tên hoặc mã trong SQL
     * @param tenHoacMa
     * @return true nếu tìm thấy, false và in ra k tìm thấy nếu k tìm thấy
     * @throws SQLException
     */
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

    /**
     *Truyền vào thức ăn có MÃ trùng với thức ăn cần sửa trong SQL, nếu k trùng
     * dữ liệu sẽ được tạo mới
     * @param an ThucAn
     * @throws SQLException
     */
    protected void edit(ThucAn an) throws SQLException {
        try {
            cStm = conn.prepareCall("{call updateThucAn(?,?,?,?)}");
            cStm.setInt(1, an.getMa());
            cStm.setString(2, an.getTen());
            cStm.setInt(3, an.getGia());
            cStm.setBoolean(4, an.isIsAnChay());
            cStm.executeUpdate();
            System.out.println("Cap nhat thuc an thanh cong.");
        } catch (SQLException e) {
            System.err.println("Edit err fail.");
        } finally {
            cStm.close();
        }

    }

    /**
     *Hiển thị trong console danh sách thứ ăn từ SQL
     * @param nhap
     * @throws SQLException
     */
    protected void showThucAn(boolean nhap) throws SQLException {
        System.out.println("+------------+---------------------------------------------+--------------+--------+");
        System.out.println("| Ma Thuc An | Ten Thuc An                                 |  Gia         | isChay |");
        System.out.println("+------------+---------------------------------------------+--------------+--------+");
        while (rs.next()) {
            System.out.printf("| %-11d|  %-43s| %,-13d| %-6s |\n",
                    rs.getInt("MaThucAn"),
                    rs.getString("TenThucAn"),
                    rs.getInt("Gia"),
                    rs.getBoolean("isChay"));
            ThucAnApi.setSelected(rs.getInt("MaThucAn"));
            if(nhap) break;
        }
        System.out.println("+------------+---------------------------------------------+--------------+--------+");
    }

    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int aSelected) {
        selected = aSelected;
    }
}
