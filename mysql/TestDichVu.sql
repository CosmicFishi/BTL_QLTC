Truncate dv_ca_si;
truncate dv_karaoke;
truncate hoa_don_dv;
delete from dv where MaDv = 1;
delete from dv where MaDv = 2;
truncate hoa_don;
truncate sanh_cuoi;
truncate hoa_don_thuc_an;
truncate thuc_an;
truncate thuc_uong;
truncate thuc_an_chay;

select  * from dv;
select *  from dv_karaoke;
select * from dv_ca_si;
select * from hoa_don_dv;

select * from hoa_don_thuc_an;
select max(MaSC) as 'Max' from sanh_cuoi;