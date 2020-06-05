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