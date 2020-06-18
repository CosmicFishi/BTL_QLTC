/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon.api;

import com.mycompany.baitaplon.DichVu;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DVApi extends Api{
    
    private static String selected;
    
    /**
     * Ham xuat ra danh sach
     * @throws SQLException 
     */
    public void readShow() throws SQLException {
        String sql = "select * from dv";
        super.read(sql);
        showDV(false);
    }
    /**
     * Ham them mot dich vu
     * @param d
     * @throws SQLException
     */
    public void addDV(DichVu d) throws SQLException {
        String sql = d.toString();
        sql = String.format("insert into dv values(%d, '%s', %d)", 
                d.getMaDV(), d.getTenDV(), d.getGiaDV());
        super.writeOrDelete(sql, "luu dich vu "); //Nhập mã, tên và giá dịch vụ vào bảng dịch vụ
    }
    //ham xoa mot dich vu
    public void deleteDV() throws SQLException {
        String sql  = "delete from dv where maDV= '" + selected + "';";
        super.writeOrDelete(sql, "delete dich vu ");
    }
    public boolean findDV(int MaDV) throws SQLException {
        cStm = conn.prepareCall("{call findDvByMaDichVu(?)}"); // phải thêm một hàm trong mysql
        cStm.setInt(1,MaDV);
        rs = cStm.executeQuery();
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay DV nhu yeu cau.");
            return false;
        }
        return true;
    }
    //ham tim kiem dich vu bang ma dich vu
    public boolean findDV(String maDV) throws SQLException {
        cStm = conn.prepareCall("{call findDvByName(?)}"); //Phải thêm một hàm trong mysql
        cStm.setString(1, "%" + maDV+ "%");
        rs = cStm.executeQuery();
        if (rs.isBeforeFirst() == false) {
            System.out.println("Khong tim thay DV nhu yeu cau.");
            return false;
        }
        return true;
    }
  
    /**
     * ham update cho mot doi tuong dich vu 
     * @param d
     * @throws SQLException 
     */
    public void edit(DichVu d) throws SQLException {
        Scanner s = new Scanner(System.in);
        try {
            pStm = conn.prepareStatement("update dv set" + 
                    "TenDv = ?,"+
                    "GiaDichVu = ?" + 
                    "where MaDV = ?");
            System.out.println("Nhap vao ten dich vu: ");
            pStm.setString(1, s.nextLine());
            System.out.println("Nhap vao gia dich vu: ");
            s.nextLine();
            pStm.setInt(2, s.nextInt());
            pStm.setInt(3, d.getMaDV());
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
    public void showDV(boolean nhap) throws SQLException {
        System.out.println("Ma dich vu        | Ten dich vu              |Gia dich vu \n");
        System.out.println("+----------------+|+------------------------+|+-----------+\n");
        while(rs.next()) {
            System.out.printf("|%-18s| %-25s| %-12d|\n",
                    rs.getString("MaDv"),
                    rs.getString("TenDv"),
                    rs.getInt("giaDichVu"));
            DVApi.setSelected(rs.getString("MaDv"));
            if(nhap == true) break;
        }
    } 
    
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
}
