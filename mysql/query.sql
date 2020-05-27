

insert into thuc_an values (1, 'Gà ta hấp hành gừng', 600000);
insert into thuc_an values (2, 'Bò hầm quế chi', 600000);
insert into thuc_an values (3, 'Tôm sú sốt me', 600000);
insert into thuc_an values (4, 'Cá điêu hồng hấp khế', 600000);
insert into thuc_an values (5, 'Cơm tám', 600000);
insert into thuc_an values (6, 'Xôi vò hạt sen', 600000);
insert into thuc_an values (7, 'Củ quả luộc thập cẩm & kho quẹt', 600000);
insert into thuc_an values (8, 'Xôi vò gấc', 600000);
insert into thuc_an values (9, 'Rau bí xào tỏi', 600000);
insert into thuc_an values (10, 'Canh mọc nấu nấm hương', 600000);
insert into thuc_an values (11, 'Chè khoai môn', 600000);


-- ----------------------------------------------------------HOA DON
# thêm hóa đơn
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (7, "sang", '2020-1-18', "ABC", 'S001', 1000000);
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (8, "sang", '2020-3-18', "ABC", 'S001', 2000000);
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (9, "sang", '2020-5-18', "ABC", 'S001', 3000000);
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (4, "sang", '2020-6-18', "ABC", 'S001', 1000000);
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (5, "sang", '2020-7-18', "ABC", 'S001', 2000000);
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec, MaSC, TongTien) values (6, "sang", '2020-10-18', "ABC", 'S001', 3000000);

#sửa hóa đơn trong 1 row
update hoa_don 
set MaHoaDon = 1,
	ThoiDiem = 'sang',
	NgayThue ='2020-5-20',
    TenBuoiTiec = 'GAO',
    MaSC= 'S001',
    TongTien = 1000000
where MaHoaDon = 1;
    
#sửa 1 giá tri trong 1 cột thuộc 1 dòng nào đó
update hoa_don
set ThoiDiem = '2020-5-19'
where MaHoaDon = 1;

#xóa hóa đơn
delete from hoa_don where MaHoaDon = 5;

#lấy hóa đơn
select *
from hoa_don;

call xoaRow(3);
-- ------------------------------------------------SẢNH CƯỚI 
# thêm sanh cuoi
insert into sanh_cuoi values ("S001", "Thien Duong", 1, 500, 50000000);
insert into sanh_cuoi values ("S002", "Mộng lý", 1, 500, 50000000);
insert into sanh_cuoi values ("S003", "Thien Duong", 1, 500, 50000000);
insert into sanh_cuoi values ("S004", "Thien Duong", 1, 500, 50000000);
insert into sanh_cuoi values ('S005','Sảnh đường hoa', 1, 500, 45000000);
insert into sanh_cuoi values ('S006','Sảnh thiên đường', 1, 400, 50000000);
insert into sanh_cuoi values ('S007','Sảnh muôn màu', 1, 550, 50000000);
insert into sanh_cuoi values ('S008','Sảnh hạnh phúc', 2, 600, 45000000);
insert into sanh_cuoi values ('S009','Sảnh cầu vồng', 2, 700, 40000000);
insert into sanh_cuoi values ('S010','Sảnh chia li', 2, 400, 45000000);
insert into sanh_cuoi values ('S010','Sảnh hoang vu', 2, 450, 45000000);

#sửa sảnh cưới trong 1 row
update sanh_cuoi 
set MaSC = 'S001', 
	TenSC = 'Cầu Vồng', 
    ViTriSC=1,
    SucChua=550, 
    GiaThue = 60000000
where MaSC= 'S001';
    
#sửa 1 giá tri trong 1 cột thuộc 1 dòng nào đó
update sanh_cuoi
set 
TenSC = 'hoang vu',
SucChua = 600
where MaSC = 's010';

#xóa sảnh cưới
delete from sanh_cuoi where MaSC = 'S011';

delete from sanh_cuoi where MaSC ='s011';

#xóa hết data
delete from sanh_cuoi where MaSC like 'hoang vu' or TenSC like '%hoang vu%' limit 1;

#lấy thuc_an

#lấy sảnh cưới
select *
from sanh_cuoi;

#tra cứu theo tên sức chứa vị trí sảnh
select * 
from sanh_cuoi
where TenSC like '%Thiên%';

call findScByName('%hoang vu%');
-- ---------------------------------------------------Dich vu
insert into dv values (1,'karaoke', 4000000);
insert into dv values (2,'trang tri', 1000000);
insert into dv values (3,'ca si', 200000);

#sửa dich vu trong 1 row
update dv
set MaDv = 3, 
	TenDv = 'ca si', 
    GiaDichVu = 1000000
where MaDv= 3;
    
#sửa 1 giá tri trong 1 cột thuộc 1 dòng nào đó
update dv
set TenDv = 'casi'
where MaDv = 3;

#xóa dich vu
delete from dv where TenDv = '%kara%';

#xóa hết data
delete from dv where MaDv like 'S___';

#lấy sảnh cưới
select *
from sanh_cuoi;

#tra cứu theo tên sức chứa vị trí sảnh
select * 
from sanh_cuoi
where TenSC like '%Thiên%';

-- ---------------------------------------------------------THUWcs Awn

select * from thuc_an;
select * from thuc_an_chay;

insert into thuc_an_chay value (5);

#thêm giá hóa đơn
insert into thuc_an values (3, "toi", "2020-5-29", "TOMYS");
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec) values (4, "trua", "2020-6-7", "ATOM");
insert into hoa_don(MaHoaDon, ThoiDiem, NgayThue, TenBuoiTiec) values (5, "sang", "2020-6-2", "LEGEN");




call findScByName("s001");

call countSC(@c, 400);
select @c;

call doanhThuTheoThang(5, @a);
select @a;
 
 call doanhThuTheoQuy(3, @b);
 select @b;
 
 call getScById('S002');
 
select *
from hoa_don;
