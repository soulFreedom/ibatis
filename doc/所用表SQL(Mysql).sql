--用户表
CREATE 
    TABLE t_user 
    ( 
        id int(20) NOT NULL AUTO_INCREMENT, 
        name varchar(100) NOT NULL, 
        sex int(2) NOT NULL, 
        age int(3) NOT NULL, 
        title varchar(20) NOT NULL, 
        phone varchar(22), 
        address varchar(50), 
        UNIQUE USING BTREE (id) 
    ) 
ENGINE= InnoDB


--存储过程
CREATE PROCEDURE `age_from_user`(IN user_id int, OUT user_age int)
BEGIN
		SELECT age into user_age
		from t_user
		where id = user_id;
END



