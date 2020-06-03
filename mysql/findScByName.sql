CREATE DEFINER=`root`@`localhost` PROCEDURE `findScByName`(IN giaTriTim nvarchar(15))
BEGIN
	select * from sanh_cuoi where TenSC like giaTriTim or MaSC like giaTriTim COLLATE utf8_unicode_ci;
END