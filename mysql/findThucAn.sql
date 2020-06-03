CREATE DEFINER=`root`@`localhost` PROCEDURE `findThucAn`(IN giaTriTim nvarchar(45))
BEGIN
	select * from thuc_an where MaThucAn = giaTriTim or TenThucAn like concat('%',giaTriTim,'%') COLLATE utf8_unicode_ci;
END