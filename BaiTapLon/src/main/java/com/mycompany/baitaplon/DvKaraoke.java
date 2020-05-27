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
public class DvKaraoke extends DichVu {
    private String KhoangTG;
    
    public DvKaraoke() {
        super();
        this.KhoangTG = "0";
    }
    /**
     * 
     * @param maDV: Mã dịch vụ
     * @param ten: Tên dịch vụ
     * @param gia: Giá dịch vụ
     * @param TG: Thời gian thuê
     */
    public DvKaraoke(String maDV, String ten, int gia, String TG ) {
        super(maDV,ten,gia);
        this.KhoangTG = TG;
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); 
        scanner.nextLine();
        System.out.println("Nhap vao khoang thoi gian: ");
        this.setKhoangTG(scanner.nextLine());
    }

    @Override
    public String toString() {
        return String.format("\'%s\', \'%s\', \'%d\', \'%s\'",
                this.getMaDV(), this.getTenDV(), this.getGiaDV(), this.KhoangTG);
    }

    
    /**
     * @return the KhoangTG
     */
    public String getKhoangTG() {
        return KhoangTG;
    }

    /**
     * @param KhoangTG the KhoangTG to set
     */
    public void setKhoangTG(String KhoangTG) {
        this.KhoangTG = KhoangTG;
    }
    
    
    
}
