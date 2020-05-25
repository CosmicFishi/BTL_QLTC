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
    private int ma;
    private String ten;
    private int gia;
    
    public DoAnUong() {
    }
    
    public DoAnUong(int ma, String ten, int gia) {
        this.ten = ten;
        this.gia = gia;
        this.ma = ma;
    }
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao ma: ");
        this.setMa(scanner.nextInt());
        System.out.println("Nhap vao ten: ");
        this.setTen(scanner.nextLine());
        System.out.println("Nhap vao gia: ");
        this.setGia(scanner.nextInt());
    }

    @Override
    public String toString() {
        return String.format("%d, '%s', %d", this.getMa(), this.getTen(), this.getGia());
    }

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
}
