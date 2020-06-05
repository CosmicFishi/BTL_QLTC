/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DoAnUong {

    protected int ma;
    protected String ten;
    protected int gia;

    public DoAnUong() {
        this.ma = 0;
        this.ten = "";
        this.gia = 0;
    }

    public DoAnUong(int ma, String ten, int gia) {
        this.ten = ten;
        this.gia = gia;
        this.ma = ma;
    }

    public void nhap(Scanner scanner) {
        //dùng nextInt xong sẽ bị bỏ qua cái nextLine tiếp theo 
        // nên chuyển về dùng như này
        System.out.print("Ten: ");
        this.ten=scanner.nextLine();
        System.out.print("Gia: ");
        this.gia = (Integer.parseInt(scanner.nextLine()));
    }

    @Override
    public String toString() {
        return String.format("%d, '%s', %d", this.getMa(), this.getTen(), this.getGia());
    }
    // <editor-fold defaultstate="collapsed" desc="Get set">
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    // </editor-fold>
}
