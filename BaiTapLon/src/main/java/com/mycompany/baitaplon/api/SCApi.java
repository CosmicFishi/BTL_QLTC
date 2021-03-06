package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.SanhCuoi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SCApi extends Api {

    protected static String selected;

    public void readShow() throws SQLException {
        String sql = "select * from sanh_cuoi;";
        super.read(sql);
        showSC(false);
        stm.close();
    }

    public void addSC(SanhCuoi s) {
        String sql = String.format("insert into sanh_cuoi values (%s);", s);
        super.writeOrDelete(sql, "Luu sanh cuoi ");
    }

    public SanhCuoi get1SC(String ma) {
        String sql = "select * "
                + "from sanh_cuoi "
                + "where MaSC = '" + ma + "';";
        super.read(sql);
        try {
            if (rs.next()) {
                return new SanhCuoi(rs.getString("MaSC"),
                        rs.getString("TenSC"),
                        rs.getInt("ViTriSC"),
                        rs.getInt("SucChua"),
                        rs.getInt("GiaThue"));
            }
        } catch (SQLException ex) {
            throw new Error("Error when create a new SanhCuoi() from sql; SCApi/get1SC(String ma)");
        }
        return new SanhCuoi();
    }

    public void deleteSC() {
        String sql = "delete from sanh_cuoi where MaSC ='" + selected + "';";
        super.writeOrDelete(sql, "delete");
    }

    public boolean findSC(String tenHoacMa) {
        try {
            cStm = conn.prepareCall("{call findScByName(?)}");
            cStm.setString(1, "%" + tenHoacMa + "%"); //% là để sài cho hàm tìm kiếm từ khóa chứa ký tự 
            rs = cStm.executeQuery();
            if (rs.isBeforeFirst() == false) {
                System.out.println("Khong tim thay SC nhu yeu cau.");
                return false;
            }
            return true;
        } catch (SQLException ex) {
            throw new Error("Loi find SC");
        }
    }

    /**
     * Dùng để tìm kiếm theo Sức Chứa hoặc Vị Trí Sảnh
     *
     * @param nhap
     */
    public void findSCShow(int nhap) throws SQLException {
        String sql = "select * from sanh_cuoi where SucChua >= " + nhap + " ORDER BY SucChua ASC;";
        if (nhap < 10) {
            sql = "select * from sanh_cuoi where ViTriSC= " + nhap + " ;";
        }
        super.read(sql);
        showSC(false);
        stm.close();
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
            pStm.setString(5, SCApi.selected);
            int kq = pStm.executeUpdate();
            if (kq == 1) {
                System.out.println("Edit success");
            } else {
                System.out.println("Edit fail");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi không edit lên sql đc");
        } finally{
            pStm.close();
        }
    }
    protected String getMaxMaSCSQL() throws SQLException, Exception {
        try {
            String sql = "select max(MaSC) as 'Max' from sanh_cuoi;";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("Max");
            }else
                throw new Exception("Loi khong lay dc id SC max.");
        } catch (SQLException ex) {
            throw new Exception("Loi khong lay dc id SC max.");
        }
    }

    /**
     * Dùng để show sảnh cưới trong Sql, nếu isOne = true thì chỉ xuất 1 dòng
     * nếu bằng false thì xuất tất cả
     *
     * @param isOne
     * @throws SQLException
     */
    public void showSC(boolean isOne) {
        System.out.format("\n+-------+-------------------+--------+---------+-------------+\n");
        System.out.format("|  MaSC |  Ten sanh         | vi tri |suc chua | gia thue    |\n");
        System.out.format("+-------+-------------------+--------+---------+-------------+\n");
        try {
            while (rs.next()) {
                System.out.printf("|%-7s| %-18s|  %-6d| %-8d| %,-12d|\n",
                        rs.getString("MaSC"),
                        rs.getString("TenSC"),
                        rs.getInt("ViTriSC"),
                        rs.getInt("SucChua"),
                        rs.getInt("GiaThue"));
                SCApi.setSelected(rs.getString("MaSC"));
                if (isOne == true) {
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.format("+-------+-------------------+--------+---------+-------------+\n");
    }

    public static String getSelected() {
        return selected;
    }

    public static void setSelected(String Selected) {
        SCApi.selected = Selected;
    }
}
