CREATE DEFINER=`root`@`localhost` PROCEDURE `addThucAn`(in ma int, in ten varchar(15), in gia int, in chay boolean)
BEGIN
	insert into thuc_an values(ma, ten, gia);    
   
	if (chay=true) then
		INSERT INTO thuc_an_chay 
		VALUES (ma);
	end if;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `countSC`(OUT c INT, IN sucChua INT)
BEGIN
	select count(*) into c
    from sanh_cuoi
    where SucChua >= sucChua;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `doanhThuTheoQuy`(in quy int, out doanhThu int)
BEGIN
	select sum(TongTien) into doanhThu from hoa_don 
    where (quy*3-2) <= month(NgayThue) && month(NgayThue) <= quy*3;
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `doanhThuTheoThang`(in thang int, out doanhThu int)
BEGIN
	select sum(TongTien) into doanhThu from hoa_don 
    where month(NgayThue)=thang;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `findScByName`(IN giaTriTim nvarchar(15))
BEGIN
	select * from sanh_cuoi where TenSC like giaTriTim or MaSC like giaTriTim COLLATE utf8_unicode_ci;
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `findThucAn`(IN giaTriTim nvarchar(45))
BEGIN
	select * from thuc_an where MaThucAn = giaTriTim or TenThucAn like concat('%',giaTriTim,'%') COLLATE utf8_unicode_ci;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `findThucAnChay`(IN giaTriTim nvarchar(45))
BEGIN
	select ta.*, if (MaThucAnChay is not null, true, false) as 'isChay'
	from thuc_an ta
	left join thuc_an_chay tac on ta.MaThucAn = tac.MaThucAnChay
	where MaThucAn = giaTriTim 
		or TenThucAn like concat('%',giaTriTim,'%') 
        COLLATE utf8_unicode_ci;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `findThucUong`(IN giaTriTim nvarchar(45))
BEGIN
	select * from thuc_uong where MaThucUong = giaTriTim or TenThucUong like concat('%',giaTriTim,'%') COLLATE utf8_unicode_ci;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDichVu`()
BEGIN
	select dv.*, cs.ThongTinCaSi , cs.SoLuongBaiHat
from dv
left join dv_ca_si cs on dv.MaDv = cs.MaDv;
    
    select dv.*, kara.KhoangThoiGianThue 
	from dv
	left join  dv_karaoke kara on dv.MaDv=kara.MaDv;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `getScById`(IN id varchar(5))
BEGIN
	select * from sanh_cuoi
    where MaSC = id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `getThucAn`()
BEGIN
    select ta.*, if (MaThucAnChay is not null, true, false) as 'isChay'
	from thuc_an ta
	left join thuc_an_chay tac on ta.MaThucAn = tac.MaThucAnChay;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `getThucAnTheoHoaDon`(in maHD int)
BEGIN
	select b2.SoLuong, b1.TenThucAn, b1.Gia,  if (b3.MaThucAnChay is not null, true, false) as 'isChay'
    from hoa_don_thuc_an b2
    left join thuc_an b1 on b2.MaThucAn = b2.MaThucAn
    left join thuc_an_chay b3 on b1.MaThucAn = b3.MaThucAnChay
    where b2.MaHD = maHD and b1.MaThucAn = b2.MaThucAn;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `getThucUongTheoHoaDon`(in maHD int)
BEGIN	
	select b1.SoLuong, b2.TenThucUong, b2.Gia, b2.HangSX
    from hoa_don_thuc_uong b1
    left join thuc_uong b2 on b1.MaThucUong = b2.MaThucUong
    where b1.MaHD = maHD;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateThucAn`(in ma int, in ten varchar(15), in gia int, in chay boolean)
BEGIN
	update thuc_an 
	set 
	TenThucAn = if(ten = '', TenThucAn, ten),
	Gia = if(gia=null, Gia, gia)
	where MaThucAn = ma;
   
	if (chay=true) then
		INSERT INTO thuc_an_chay 
		VALUES (ma)
		ON DUPLICATE KEY UPDATE
		MaThucAnChay=ma;
	end if;
	if (not chay=true) then
		delete from thuc_an_chay where MaThucAnChay = ma;
	end if;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `xoaHoaDon`(in dong int)
BEGIN
	delete from hoa_don
    where MaHoaDon = dong;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `xoaHoaDonTheoMa`(in maHD int)
BEGIN
    delete from hoa_don_thuc_an where hoa_don_thuc_an.MaHD = maHD;
	delete from hoa_don_dv where hoa_don_dv.MaHD = maHD ;
    delete from hoa_don_thuc_uong where hoa_don_thuc_uong.MaHD = maHD ;
    delete from hoa_don where hoa_don.MaHoaDon = maHD ;
END





























