CREATE DEFINER=`root`@`localhost` PROCEDURE `getScById`(IN id varchar(5))
BEGIN
	select * from sanh_cuoi
    where MaSC = id;
END