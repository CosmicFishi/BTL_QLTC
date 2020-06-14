/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu {
    private DanhSachThucAn dsAn = new DanhSachThucAn();
    private DanhSachThucUong dsUong = new DanhSachThucUong();
    private int slMenu = 0;
    
    public Menu() {
    }
    
    public Menu(DanhSachThucAn dsA, DanhSachThucUong dsU) {
        this.dsAn = dsA;
        this.dsUong = dsU;
    }
    public int tinhGiaDs(){
        return ( this.getDsAn().tinhGiaDs() + this.getDsUong().tinhGiaDs() )*this.slMenu ;
    }
    public void nhap(Scanner scanner) throws SQLException {
        this.getDsAn().themTuSql(scanner);
        this.getDsUong().themTuSql(scanner);
        System.out.print("Nhap sl menu: ");
        this.setSlMenu(Integer.parseInt(scanner.nextLine()));
    }

    @Override
    public String toString() {
        StringBuilder kq = new StringBuilder("");
        kq.append("Ds Thuc An: \n").append(this.getDsAn()).append("\n")
                .append("Ds Thuc Uong: \n").append(this.getDsUong())
                .append("Sl menu: ").append(this.slMenu).append("\n");
        return kq.toString();
    }

    public int getSlMenu() {
        return slMenu;
    }

    public void setSlMenu(int slMenu) {
        this.slMenu = slMenu;
    }

    public DanhSachThucAn getDsAn() {
        return dsAn;
    }

    public DanhSachThucUong getDsUong() {
        return dsUong;
    }
    
    
}
