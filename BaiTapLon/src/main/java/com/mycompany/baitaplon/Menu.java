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
public abstract class Menu {
    protected DanhSachThucAn dsAn;
    protected DanhSachThucUong dsUong;
    
    public Menu() {
    }
    
    public Menu(DanhSachThucAn dsA, DanhSachThucUong dsU) {
        this.dsAn = dsA;
        this.dsUong = dsU;
    }
    
    public void nhap(Scanner scanner) throws SQLException {
        this.dsAn.them(scanner);
    }

    @Override
    public String toString() {
        return String.format("");
    }
    
    
}
