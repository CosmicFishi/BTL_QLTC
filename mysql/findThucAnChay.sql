CREATE DEFINER=`root`@`localhost` PROCEDURE `findThucAnChay`(IN giaTriTim nvarchar(45))
BEGIN
	select ta.*, if (MaThucAnChay is not null, true, false) as 'isChay'
	from thuc_an ta
	left join thuc_an_chay tac on ta.MaThucAn = tac.MaThucAnChay
	where MaThucAn = giaTriTim 
		or TenThucAn like concat('%',giaTriTim,'%') 
        COLLATE utf8_unicode_ci;
END