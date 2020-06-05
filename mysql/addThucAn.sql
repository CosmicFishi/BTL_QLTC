CREATE DEFINER=`root`@`localhost` PROCEDURE `addThucAn`(in ma int, in ten varchar(15), in gia int, in chay boolean)
BEGIN
	insert into thuc_an values(ma, ten, gia);    
   
	if (chay=true) then
		INSERT INTO thuc_an_chay 
		VALUES (ma);
	end if;
END