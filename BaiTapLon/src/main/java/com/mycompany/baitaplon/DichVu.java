/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.Api;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public abstract class DichVu extends Api implements TuongTacSQL{
    
    private static String selected;
    
    private int maDV;
    private String tenDV;
    private int giaDV;

    public DichVu() {
        this.maDV = 0;
        this.tenDV = "ten";
        this.giaDV = 0;
    }
    public DichVu(int ma, String ten, int gia) {
        this.maDV = ma;
        this.tenDV = ten;
        this.giaDV = gia;
    }
    
    
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ma dich vu: ");
        this.setMaDV(scanner.nextInt());
        System.out.println("Nhap vao ten dich vu: ");
        this.setTenDV(scanner.nextLine());
        System.out.println("Nhap vao gia dich vu: ");
        this.setGiaDV(scanner.nextInt());
    }
    public abstract String xuat();
    @Override
    public String toString() {
        return String.format("%d,\'%s\', %d", this.maDV, this.tenDV, this.giaDV);
    }
    
//Phần tương tác với mysql
    // where MaDv =" + this.maDV + "
    @Override
    public void readSQLShow() {
        String sql = "select * from dv where MaDv =" + this.maDV + ";";
        try {
            super.read(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //this.showSQL();
        
    }
    @Override
    public void addSQL() {
        String sql = this.toString();
        sql = "insert into dv values (" + sql + ");";
        try {
            super.writeOrDelete(sql, "add");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void deleteSQL() {
        String sql = "delete from dv where MaDv =  " + this.maDV +";";
        try {
            super.writeOrDelete(sql, "delete");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void editSQL() {
        Scanner s = new Scanner(System.in);
        try {
            pStm = conn.prepareStatement("update dv set " + 
                    "TenDv = ? ,"+
                    "GiaDichVu = ? " + 
                    "where MaDv = ? ");
            System.out.println("Nhap vao ten dich vu: ");
            pStm.setString(1, s.nextLine());
            System.out.println("Nhap vao gia dich vu: ");
            //s.nextLine();
            pStm.setInt(2, s.nextInt());
            pStm.setInt(3, this.getMaDV());
            int kq = pStm.executeUpdate();
            if(kq == 1)
                System.out.println("edit successful");
            else
                System.out.println("Edit failed");
        } catch (SQLException e) {
            System.out.println("edit fail");
        } finally {
            try {
                pStm.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public void showSQL() {
        try {
            System.out.println("Ma dich vu        | Ten dich vu              | Gia dich vu ");
            System.out.println("+----------------+|+------------------------+|+-----------+");
            while(rs.next()) {
                System.out.printf("|%-17d| %-25s| %-12d|\n",
                        rs.getInt("MaDv"),
                        rs.getString("TenDv"),
                        rs.getInt("giaDichVu"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * (user)Ham xuat ra danh sach tu mysql
     * @throws SQLException 
     */
//    public void readSQLShow() throws SQLException {
//        String sql = "select * from dv";
//        super.read(sql);
//        showDV();
//    }
    
    //Ham can xoa
    /**
     * Ham them mot dich vu
     * @param d
     * @throws SQLException
     */
//    public void addDVSQL(DichVu d) throws SQLException {
//        String sql = d.toString();
//        sql = "insert into dv values("+ sql +")";
//        super.writeOrDelete(sql, "add"); //Nhập mã, tên và giá dịch vụ vào bảng dịch vụ
//    }  
//    //ham xoa mot dich vu
//    public void deleteDVSQL() throws SQLException {
//        String sql  = "delete from dv where maDV= '" + getSelected() + "';";
//        super.writeOrDelete(sql, "delete");
//    }
    //dời sang  qldv
    public void findDVSQL(int MaDV) throws SQLException {
        cStm = conn.prepareCall("{call findDvByMaDichVu(?)}"); // phải thêm một hàm trong mysql
        cStm.setInt(1,MaDV);
        rs = cStm.executeQuery();
    }
    //ham tim kiem dich vu bang ma dich vu
    public void findDVSQL(String maDV) throws SQLException {
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
//    public void editSQL(DichVu d) throws SQLException {
//        Scanner s = new Scanner(System.in);
//        try {
//            pStm = conn.prepareStatement("update dv set" + 
//                    "TenDv = ?,"+
//                    "GiaDichVu = ?" + 
//                    "where MaDV = ?");
//            System.out.println("Nhap vao ten dich vu: ");
//            pStm.setString(1, s.nextLine());
//            System.out.println("Nhap vao gia dich vu: ");
//            s.nextLine();
//            pStm.setInt(2, s.nextInt());
//            pStm.setInt(3, d.getMaDV());
//            int kq = pStm.executeUpdate();
//            if(kq == 1)
//                System.out.println("edit successful");
//            else
//                System.out.println("Edit failed");
//        } catch (SQLException e) {
//            System.out.println("edit fail");
//        } finally {
//            pStm.close();
//        }
//    }
//    public void showDV() throws SQLException {
//        System.out.println("Ma dich vu        | Ten dich vu              |Gia dich vu \n");
//        System.out.println("+----------------+|+------------------------+|+-----------+\n");
//        while(rs.next()) {
//            System.out.printf("|%-18s| %-25s| %-12d|\n",
//                    rs.getString("MaDv"),
//                    rs.getString("TenDv"),
//                    rs.getInt("giaDichVu"));
//        }
//    }
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
        //DVApi.setSelected(rs.getString("MaDv"));
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

    /**
     * @return the maDV
     */
    public int getMaDV() {
        return maDV;
    }

    /**
     * @param maDV the maDV to set
     */
    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    /**
     * @return the tenDV
     */
    public String getTenDV() {
        return tenDV;
    }

    /**
     * @param tenDV the tenDV to set
     */
    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    /**
     * @return the giaDV
     */
    public int getGiaDV() {
        return giaDV;
    }

    /**
     * @param giaDV the giaDV to set
     */
    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    
    
}
