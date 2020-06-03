CREATE DEFINER=`root`@`localhost` PROCEDURE `doanhThuTheoThang`(in thang int, out doanhThu int)
BEGIN
	select sum(TongTien) into doanhThu from hoa_don 
    where month(NgayThue)=thang;
END