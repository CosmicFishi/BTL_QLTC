Truncate dv_ca_si;
truncate dv_karaoke;
truncate hoa_don_dv;
delete from dv where MaDv = 1;
delete from dv where MaDv = 2;
truncate hoa_don;
truncate sanh_cuoi;
truncate thuc_an;

select  * from dv;
select *  from dv_karaoke;
select * from dv_ca_si;
select * from hoa_don_dv;

select * from hoa_don_thuc_an;
