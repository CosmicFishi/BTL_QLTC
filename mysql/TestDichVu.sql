Truncate dv_ca_si;
truncate dv_karaoke;
truncate hoa_don_dv;
delete from dv where MaDv = 1;
delete from dv where MaDv = 2;


select  * from dv;
select *  from dv_karaoke;
select * from dv_ca_si;
select * from hoa_don_dv