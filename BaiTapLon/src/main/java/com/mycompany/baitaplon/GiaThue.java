/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

//import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class GiaThue {
    private int giaThue;
    private ThoiDiem thoiDiem;
    private NgayThue Ngay;
    private boolean isDipLe;
    
    public GiaThue() {
        this.giaThue = 0;
        this.thoiDiem = ThoiDiem.SANG;
        this.Ngay = null;
        this.isDipLe = false;
    }
    /**
     * @param thoiDiem : thời điểm thuê (Sáng, trưa, chiều, tối)
     * @param ngayThue : Ngày thuê trong tuần
     * @param isDip : có phải dịp lễ hay không
     */
    public GiaThue(ThoiDiem thoiDiem, NgayThue ngayThue, boolean isDip) {
        this.thoiDiem = thoiDiem;
        this.Ngay = ngayThue;
        this.isDipLe = isDip;
        tinhTien();
    }
    
    public void nhap(Scanner scanner) {
        System.out.println("Nhap vao thoi diem thue: (Sang, chieu, toi) ");
        if(null == scanner.nextLine())
            this.thoiDiem = null;
        else
            switch (scanner.nextLine()) {
            case "Sang":
                this.thoiDiem = ThoiDiem.SANG;
                break;
            case "Chieu":
                this.thoiDiem = ThoiDiem.CHIEU;
                break;
            default:
                this.thoiDiem = ThoiDiem.TOI;
                break;
        }
        System.out.println("Nhap vao ngay thue(1 | 2): Ngay thuong hay thu Bay + Chu nhat ");
        if(scanner.nextInt() == 1)
            this.Ngay = NgayThue.NgayThuong;
        else
            this.Ngay = NgayThue.Bay_ChuNhat;
        System.out.println("Dip le ? (0 | 1)");
        this.isDipLe = scanner.nextInt() == 1;
        tinhTien();
    }
    
    private void tinhTien()  {
        this.giaThue = 0;
        this.giaThue  = Ngay.layTien() +  thoiDiem.layTien();
        if  (this.isDipLe == true) 
            this.giaThue += 10000;
    }

    @Override
    public String toString() {
        return String.format("Gia thue: %d\nThoi diem: %s\nNgay thue: %s\nDip le: %b\n",
                this.giaThue,this.thoiDiem,this.Ngay,this.isDipLe);
    }
    

    /**
     * @return the giaThue
     */
    public int getGiaThue() {
        return giaThue;
    }

    /**
     * @param giaThue the giaThue to set
     */
    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    /**
     * @return the thoiDiem
     */
    public ThoiDiem getThoiDiem() {
        return thoiDiem;
    }

    /**
     * @param thoiDiem the thoiDiem to set
     */
    public void setThoiDiem(ThoiDiem thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    /**
     * @return the Ngay
     */
    public NgayThue getNgay() {
        return Ngay;
    }

    /**
     * @param Ngay the Ngay to set
     */
    public void setNgay(NgayThue Ngay) {
        this.Ngay = Ngay;
    }

    /**
     * @return the isDipLe
     */
    public boolean isIsDipLe() {
        return isDipLe;
    }

    /**
     * @param isDipLe the isDipLe to set
     */
    public void setIsDipLe(boolean isDipLe) {
        this.isDipLe = isDipLe;
    }
    
    
}
