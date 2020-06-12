CREATE DEFINER=`root`@`localhost` PROCEDURE `getDichVu`()
BEGIN
	select dv.*, cs.ThongTinCaSi , cs.SoLuongBaiHat, kara.KhoangThoiGianThue
	from dv
	left join dv_ca_si cs on dv.MaDv = cs.MaDv
	left join dv_karaoke kara on dv.MaDv = kara.MaDv;
END