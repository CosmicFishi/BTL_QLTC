CREATE DEFINER=`root`@`localhost` PROCEDURE `xoaHoaDon`(in dong int)
BEGIN
	delete from hoa_don
    where MaHoaDon = dong;
END