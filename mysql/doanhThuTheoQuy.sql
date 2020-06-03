CREATE DEFINER=`root`@`localhost` PROCEDURE `doanhThuTheoQuy`(in quy int, out doanhThu int)
BEGIN
	select sum(TongTien) into doanhThu from hoa_don 
    where (quy*3-2) <= month(NgayThue) && month(NgayThue) <= quy*3;
END