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
    protected DanhSachThucAn dsAn = new DanhSachThucAn();
    protected DanhSachThucUong dsUong = new DanhSachThucUong();
    private int slMenu = 0;
    
    public Menu() {
    }
    
    public Menu(DanhSachThucAn dsA, DanhSachThucUong dsU) {
        this.dsAn = dsA;
        this.dsUong = dsU;
    }
    
    public void nhap(Scanner scanner) throws SQLException {
        this.dsAn.themTuSql(scanner);
        this.dsUong.themTuSql(scanner);
        System.out.print("Nhap sl menu: ");
        this.setSlMenu(Integer.parseInt(scanner.nextLine()));
    }

    @Override
    public String toString() {
        StringBuilder kq = new StringBuilder("");
        kq.append("Ds Thuc An: \n").append(this.dsAn).append("\n")
                .append("Ds Thuc Uong: \n").append(this.dsUong)
                .append("Sl menu: ").append(this.slMenu).append("\n");
        return kq.toString();
    }

    public int getSlMenu() {
        return slMenu;
    }

    public void setSlMenu(int slMenu) {
        this.slMenu = slMenu;
    }
    
    
}
