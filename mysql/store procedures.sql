CREATE PROCEDURE `countSC` (OUT c INT, IN sucChua INT)
BEGIN
	select count(*) into c
    from sanh_cuoi as sc
    where sc.SucChua > = sucChua;
END