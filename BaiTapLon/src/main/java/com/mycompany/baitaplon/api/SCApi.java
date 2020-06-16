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
    }

    public void addSC(SanhCuoi s) throws SQLException {
        String sql = s.toString();
        sql = "insert into sanh_cuoi values (" + sql + ");";
        super.writeOrDelete(sql, "add");
    }

    public SanhCuoi get1SC(String ma) throws SQLException {
        String sql = "select * "
                + "from sanh_cuoi "
                + "where MaSC = '" + ma + "';";
        super.read(sql);
        if (rs.next()) {
            SanhCuoi sc = new SanhCuoi(rs.getString("MaSC"),
                    rs.getString("TenSC"),
                    rs.getInt("ViTriSC"),
                    rs.getInt("SucChua"),
                    rs.getInt("GiaThue"));
            return sc;
        }
        return new SanhCuoi();
    }

    public void deleteSC() throws SQLException {
        String sql = "delete from sanh_cuoi where MaSC ='" + selected + "';";
        super.writeOrDelete(sql, "delete");
    }

    public void findSC(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findScByName(?)}");
        cStm.setString(1, "%" + tenHoacMa + "%"); //% là để sài cho hàm tìm kiếm từ khóa chứa ký tự 
        rs = cStm.executeQuery();
    }
    
    public void findSCShow(String tenHoacMa) throws SQLException {
        cStm = conn.prepareCall("{call findScByName(?)}");
        cStm.setString(1, "%" + tenHoacMa + "%"); //% là để sài cho hàm tìm kiếm từ khóa chứa ký tự 
        rs = cStm.executeQuery();
        showSC(false);
    }

    /**
     * Dùng để tìm kiếm theo Sức Chứa hoặc Vị Trí Sảnh
     *
     * @param nhap
     */
    public void findSCShow(int so) {
        try {
            String sql = "select * from sanh_cuoi where SucChua > " + so + " ;";
            if (so < 10) {
                sql = "select * from sanh_cuoi where ViTriSC= " + so + " ;";
            }
            super.read(sql);
            showSC(false);
        } catch (SQLException ex) {
            Logger.getLogger(SCApi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *Kiểm tra Rs có null hay không
     * @return
     * @throws SQLException
     */
    protected boolean isNullRs() throws SQLException {
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay SC nhu yeu cau.");
            return true;
        }
        return false;
    }

    protected void edit(SanhCuoi sc) throws SQLException{
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
        } finally {
            try {
                pStm.close();
            } catch (SQLException ex) {
                throw new SQLException("Lỗi không đóng đc prepare statment");
            }
        }

    }

    /**
     * Dùng để show sảnh cưới trong Sql, nếu isOne = true thì chỉ xuất 1 dòng
     * nếu bằng false thì xuất tất cả
     *
     * @param isOne
     * @throws SQLException
     */
    protected void showSC(boolean isOne) throws SQLException {
        System.out.format(" MaSC  |  Ten sanh          | vi tri |suc chua | gia thue    \n");
        System.out.format("-------+-------------------+--------+---------+-------------+%n");
        while (rs.next()) {
            System.out.printf("%-7s| %-18s|  %-6d| %-8d| %-12d|\n",
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
    }

    public static String getSelected() {
        return selected;
    }

    public static void setSelected(String Selected) {
        SCApi.selected = Selected;
    }
}
