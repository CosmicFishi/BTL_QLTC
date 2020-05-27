/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.ThucAnApi;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DanhSachThucAn extends ThucAnApi {
    public void xuat() throws SQLException{
        super.readShow();
    }
    public void them(ThucAn ta) throws SQLException{
        super.addThucAn(ta);
    }
    public void them(Scanner scanner) throws SQLException{
        ThucAn ta = new ThucAn();
        ta.nhap(scanner);
        super.addThucAn(ta);
    }
    public void xoa(Scanner scanner) throws SQLException{
        System.out.println("Nhap ma Thuc An hoac Ten can xoa: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa) == false)
            return;
        super.showThucAn(1);
        System.out.println("Ban muon xoa sanh tren: (y,n): ");
        if (scanner.nextLine().equals("y")) {
            System.out.println(selected);
            super.deleteThucAn();
        } else {
            System.out.println("Da huy xoa.");
        }
    }
    public void updateThucAn(Scanner scanner) throws SQLException{
        System.out.println("Nhap ten hoac ma ThucAn can cap nhat: ");
        String tenHoacMa = scanner.nextLine();
        if (findThucAn(tenHoacMa)==false) return;
        super.showThucAn(1);
        System.out.println("Ban muon cap nhat ThucAn tren(y/n): ");
        if (scanner.nextLine().equals("y")) {
            ThucAn ta = new ThucAn();
            ta.nhap(scanner);
            super.edit(ta);
        } else {
            System.out.println("Da huy bo cap nhat.");
        }
    }
    
}
