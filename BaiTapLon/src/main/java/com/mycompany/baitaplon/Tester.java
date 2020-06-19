/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baitaplon;

import com.mycompany.baitaplon.api.*; //để sài gói API

import com.mycompany.baitaplon.api.Api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Tester {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //dòng cần thiết cho toàn bộ quá trình 
        Api api = new Api();
        Api.connectSql();
        Scanner scanner = new Scanner(System.in);

        String nhap = "";
        int luaChon = 0;

        QLHD qlhd = new QLHD();
        QLDV qldv = new QLDV();
        QLSanhCuoi ql = new QLSanhCuoi();
        DanhSachThucAn dsAn = new DanhSachThucAn();
        DanhSachThucUong dsUong = new DanhSachThucUong();

        System.out.println("Chao mung!! Hay chon cac lua chon sau: ");
        System.out.println("1.Giao dien cho User.");
        System.out.println("2.Giao dien cho Admin.");
        System.out.print("Chon: ");
        nhap = scanner.nextLine();
        if (nhap.equals("1")) //User interface in console
        {
            // <editor-fold defaultstate="collapsed" desc="Giao dien nguoi dung">
            do {
                System.out.println("Chao mung!! Hay chon cac lua chon sau: ");
                System.out.println("1. Xuat danh sach cac sanh cuoi co trong he thong");
                System.out.println("2. Xuat danh sach cac dich vu trong he thong");
                System.out.println("3. Xuat danh sach menu co trong he thong");
                System.out.println("4. Nhap hoa don");
                System.out.println("5. Xuat toan bo hoa don");
                System.out.println("6. Xuat mot hoa don");
                System.out.println("7. Xoa hoa don");
                System.out.println("8. Xuat doanh thu theo thang");
                System.out.println("9. Xuat doanh thu theo quy");
                System.out.print("Nhap vao -1 de thoat: ");
                nhap = scanner.nextLine();
                try {
                    luaChon = Integer.parseInt(nhap);
                } catch (NumberFormatException e) {
                    luaChon = 0;
                }
                switch (luaChon) {
                    case -1:
                        break;
                    case 1: {
                        ql.xuatSC();
                        break;
                    }
                    case 2: {
                        qldv.xuatDsSQL();
                        break;
                    }
                    case 3: {
                        dsAn.xuatThucAnSql();
                        dsUong.xuatThucUongSql();
                        break;
                    }
                    case 4: {
                        try {
                            qlhd.nhapHoaDon(scanner);
                            break;
                        } catch (Error | Exception e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    }
                    case 5: {
                        qlhd.xuatHoaDonSQL();
                        break;
                    }
                    case 6: {
                        System.out.print("Nhap ma hoa don can xuat: ");
                        qlhd.xuatHoaDonSQL(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    }

                    case 7: {
                        System.out.print("Nhap ma hoa don muon xoa: ");
                        qlhd.xoaHoaDonSQL(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    }

                    case 8: {
                        System.out.print("Nhap vao thang: ");
                        int thang = scanner.nextInt();
                        scanner.nextLine();
                        if (thang > 0 && thang <= 12) {
                            qlhd.xuatDoanhThuThang(thang);
                        } else {
                            System.out.println("Thang khong ton tai");
                        }
                        break;
                    }
                    case 9: {
                        System.out.print("Nhap vao quy: ");
                        int quy = scanner.nextInt();
                        scanner.nextLine();
                        if (quy > 0 && quy <= 4) {
                            qlhd.xuatDoanhThuQuy(quy);
                        } else {
                            System.out.println("Quy khong ton tai");
                        }
                        break;
                    }
                    default:
                        System.out.println("Lua chon khong ton tai");
                }
            } while (luaChon != -1);
            // </editor-fold>
        } else {
            System.out.println("Chao mung!! Hay chon cac lua chon sau: ");
            System.out.println("1. Sanh cuoi.");
            System.out.println("2. Thuc An.");
            System.out.println("3. Thuc Uong.");
            System.out.println("4. Dich Vu.");
            System.out.println("-1. Thoat.");
            System.out.print("Chon: ");
            nhap = scanner.nextLine();
            try {
                luaChon = Integer.parseInt(nhap);
            } catch (NumberFormatException e) {
                luaChon = 0;
            }
            switch (luaChon) {
                case -1:
                    break;
                case 1:
                    // <editor-fold defaultstate="collapsed" desc="ADMIN Sanh cuoi">
                    while (true) {
                        System.out.println("1. xuat tat ca SC");
                        System.out.println("2. them SC vao SQL");
                        System.out.println("3. xoa SC trong SQL");
                        System.out.println("4. chinh sua SC trong SQL");
                        System.out.println("5. tim SC theo TEN || MA trong SQL");
                        System.out.println("6. tim SC theo VT || Suc Chua trong SQL");
                        System.out.println("(-1). de thoat.");
                        System.out.print("Chon : ");

                        try {
                            luaChon = Integer.parseInt(nhap);
                        } catch (NumberFormatException e) {
                            luaChon = 0;
                        }
                        luaChon = Integer.parseInt(scanner.nextLine());
                        switch (luaChon) {
                            case -1:
                                break;
                            case 1:
                                ql.xuatSC();
                                break;
                            case 2:
                                ql.themSC(scanner);
                                break;
                            case 3:
                                ql.xoaSC(scanner);
                                break;
                            case 4:
                                ql.capNhatSC(scanner);
                                break;
                            case 5:
                                ql.timSCTenMa(scanner);
                                break;
                            case 6:
                                ql.timSCViChua(scanner);
                                break;
                            default:
                                System.out.println("Lua chon khong ton tai.");
                                break;
                        }
                        if (luaChon == -1) {
                            break;
                        }
                    }
                    // </editor-fold>
                    break;
                case 2:
                    // <editor-fold defaultstate="collapsed" desc="ADMIN Thuc an">
                    while (true) {
                        System.out.println("1. xuat tat ca ThucAn");
                        System.out.println("2. them ThucAn vao SQL");
                        System.out.println("3. xoa ThucAn trong SQL");
                        System.out.println("4. chinh sua ThucAn trong SQL");
                        System.out.println("5. tim ThucAn theo TEN || MA trong SQL");
                        System.out.println("(-1). de thoat.");
                        System.out.print("Chon : ");
                        luaChon = Integer.parseInt(scanner.nextLine());

                        switch (luaChon) {
                            case -1:
                                break;
                            case 1:
                                dsAn.xuatThucAnSql();
                                break;
                            case 2: {
                                try {
                                    dsAn.themThucAnSql(scanner);
                                } catch (Exception ex) {
                                    Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                            case 3:
                                dsAn.xoaThucAnSql(scanner);
                                break;
                            case 4:
                                dsAn.updateThucAnSql(scanner);
                                break;
                            case 5:
                                dsAn.timThucAnSql(scanner);
                                break;
                            default:
                                System.out.println("Chọn sai chọn lại");
                                break;
                        }
                        if (luaChon == -1) {
                            break;
                        }
                    }
                    // </editor-fold>
                    break;
                case 3:
                    // <editor-fold defaultstate="collapsed" desc="ADMIN Thuc Uong">
                    while (true) {
                        System.out.println("1. xuat tat ca Thuc Uong");
                        System.out.println("2. them Thuc Uong vao SQL");
                        System.out.println("3. xoa Thuc Uong trong SQL");
                        System.out.println("4. chinh sua Thuc Uong trong SQL");
                        System.out.println("5. tim Thuc Uong theo TEN || MA trong SQL");
                        System.out.println("(-1). de thoat.");
                        System.out.print("Chon : ");
                        luaChon = Integer.parseInt(scanner.nextLine());
                        switch (luaChon) {
                            case -1:
                                break;
                            case 1:
                                dsUong.xuatThucUongSql();
                                break;
                            case 2:
                                dsUong.themThucUongSql(scanner);
                                break;
                            case 3:
                                dsUong.xoaThucUongSql(scanner);
                                break;
                            case 4: {
                                try {
                                    dsUong.updateThucUongSql(scanner);
                                } catch (Exception ex) {
                                    Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                            case 5:
                                dsUong.timThucUongSql(scanner);
                                break;
                            default:
                                System.out.println("Chọn sai chọn lại");
                                break;
                        }
                        if (luaChon == -1) {
                            break;
                        }
                    }
                    // </editor-fold>
                    break;
                case 4:
                    // <editor-fold defaultstate="collapsed" desc="ADMIN dịch vụ">
                    while (true) {
                        System.out.println("1. xuat tat ca Dich Vu");
                        System.out.println("2. them DV vao SQL");
                        System.out.println("3. xoa DV trong SQL");
                        System.out.println("4. tim DV theo TEN trong SQL");
                        System.out.println("(-1). de thoat.");
                        System.out.print("Chon : ");
                        luaChon = Integer.parseInt(scanner.nextLine());
                        switch (luaChon) {
                            case -1:
                                break;
                            case 1:
                                qldv.xuatDsSQL();
                                break;
                            case 2: {
                                System.out.println("1. dich vu karaoke\n2. dich vu thue ca si");
                                System.out.print("Chon: ");
                                luaChon = Integer.parseInt(scanner.nextLine());
                                switch (luaChon) {
                                    case 1: {
                                        DichVu d = new DvKaraoke();
                                        d.nhap(scanner);
                                        d.addSQL();
                                    }
                                    break;
                                    case 2: {
                                        DichVu d = new DvThueCS();
                                        d.nhap(scanner);
                                        d.addSQL();
                                    }
                                    break;
                                    default:
                                        System.out.println("Lua chon khong ton tai");
                                }
                            }
                            break;
                            case 3:
                                qldv.xuatDsSQL();
                                System.out.print("Nhap ma dv xoa: ");
                                qldv.xoaSQL(Integer.parseInt(scanner.nextLine()));
                                break;
                            case 4:
                                System.out.println("Nhap ten can tim");
                                qldv.traCuuSQL(scanner.nextLine());
                                break;
                            default:
                                System.out.println("Chọn sai chọn lại");
                                break;
                        }
                        if (luaChon == -1) {
                            break;
                        }
                    }
                    // </editor-fold>
                    break;
                default:
                    System.out.println("Lua chon khong ton tai.");
                    break;
            }
        }
        Api.disconnectSql();
    }
}
