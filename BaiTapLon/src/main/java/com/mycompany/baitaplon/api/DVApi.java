/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DVApi extends Api{
    
    private static String selected;
    private static String maHoaDon;
    
    /**
     * Ham xuat ra danh sach
     * @throws SQLException 
     */
    public void readShow() throws SQLException {
        String sql = "select * from dv";
        super.read(sql);
        showDV();
    }
    /**
     * Ham them mot dich vu
     * @param d
     * @throws SQLException 
     */
    public void addDV(DichVu d) throws SQLException {
        String sql = d.toString();
        sql = "insert into dv values("+ sql +")";
        super.writeOrDelete(sql, "add");
    }
    //ham xoa mot dich vu 
    public void deleteDV() throws SQLException {
        String sql  = "delete from dv where maDv= '" + getSelected() + "';";
        super.writeOrDelete(sql, "delete");
    }
    //thiếu hàm tìm dịch vụ bằng mã hóa đơn (overloading)
    public void findDV(int MaHD) throws SQLException {
        cStm = conn.prepareCall("{call findDvByMaHoaDon(?)}"); // phải thêm một hàm trong mysql
        cStm.setInt(1,MaHD);
        rs = cStm.executeQuery();
    }
    //ham tim kiem dich vu bang ma dich vu
    public void findDV(String maDV) throws SQLException {
        cStm = conn.prepareCall("{call findDvByName(?)}"); //Phải thêm một hàm trong mysql
        cStm.setString(1, "%" + maDV+ "%");
        rs = cStm.executeQuery();
    }
    /**
     * ham kiem tra resultset tra ve co null hay khong
     * @return
     * @throws SQLException 
     */
    public boolean isNullRS() throws SQLException {
        if(rs.isBeforeFirst() == false) {
            System.out.println("khong tm thay dich vu yeu cau");
            return true;
        }
        return false;
    }
    
    /**
     * ham update cho mot doi tuong dich vu 
     * @param d
     * @throws SQLException 
     */
    protected void edit(DichVu d) throws SQLException {
        try {
            pStm = conn.prepareStatement("update dv set" + 
                    "MaDv = ?," +
                    "TenDv = ?,"+
                    "GiaDichVu = ?" + 
                    "where MaDv = ?");
            pStm.setString(1, d.getMaDV());
            pStm.setString(2, d.getTenDV());
            pStm.setInt(3, d.getGiaDV());
            pStm.setString(4, DVApi.getSelected());
            int kq = pStm.executeUpdate();
            if(kq == 1)
                System.out.println("edit successful");
            else
                System.out.println("Edit failed");
        } catch (SQLException e) {
            System.out.println("edit fail");
        } finally {
            pStm.close();
        }
    }
    protected void showDV() throws SQLException {
        System.out.println("Ma dich vu        | Ten dich vu              |Gia dich vu \n");
        System.out.println("+----------------+|+------------------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-18s| %-25s| %-12d|\n",
                    rs.getString("MaDv"),
                    rs.getString("TenDv"),
                    rs.getInt("giaDichVu"));
        }
    }
    /**
     * ham xuat ra mot dich vu 
     * @param i
     * @throws SQLException 
     */
    protected void showDV(int i) throws SQLException {
        if(rs.next())
        System.out.format("Ma dich vu        | Ten dich vu              |Gia dich vu \n");
        System.out.format("+----------------+|+------------------------+|+-----------+\n");
        System.out.printf("|%-18s| %-25s| %-12d|\n",
                rs.getString("MaDv"),
                rs.getString("TenDv"),
                rs.getInt("giaDichVu"));
        DVApi.setSelected(rs.getString("MaDv"));
    }
    
    //tạo ra thêm một lớp API cho Dv ca sĩ với Dv karaoke

    /**
     * @return the selected
     */
    public static String getSelected() {
        return selected;
    }

    /**
     * @param aSelected the selected to set
     */
    public static void setSelected(String aSelected) {
        selected = aSelected;
    }

    /**
     * @return the maHoaDon
     */
    public static String getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param aMaHoaDon the maHoaDon to set
     */
    public static void setMaHoaDon(String aMaHoaDon) {
        maHoaDon = aMaHoaDon;
    }
}
