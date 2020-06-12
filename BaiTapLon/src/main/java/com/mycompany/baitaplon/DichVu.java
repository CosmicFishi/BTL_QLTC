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
    public String xuat() {
        return String.format("%d,\'%s\', %d", this.maDV, this.tenDV, this.giaDV);
    };
    @Override
    public String toString() {
        return String.format("Ma dich vu: %d\nTen dich vu: %s\nGia dich vu: %d\n", this.maDV, this.tenDV, this.giaDV);
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
        String sql = this.xuat();
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
    public void editSQL(Scanner scanner) {
        try {
            pStm = conn.prepareStatement("update dv set " + 
                    "TenDv = ? ,"+
                    "GiaDichVu = ? " + 
                    "where MaDv = ? ");
            System.out.println("Nhap vao ten dich vu: ");
            pStm.setString(1, scanner.nextLine());
            System.out.println("Nhap vao gia dich vu: ");
            //s.nextLine();
            pStm.setInt(2, scanner.nextInt());
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
    protected void showDV(int i) throws SQLException {
        if(rs.next())
        System.out.format("Ma dich vu        | Ten dich vu              |Gia dich vu \n");
        System.out.format("+----------------+|+------------------------+|+-----------+\n");
        System.out.printf("|%-18s| %-25s| %-12d|\n",
                rs.getString("MaDv"),
                rs.getString("TenDv"),
                rs.getInt("giaDichVu"));
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
