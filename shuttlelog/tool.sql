SELECT * FROM yliu12.log;

create table yliu12.backuplog as
SELECT * FROM yliu12.uniqueLog1;

insert into uniqueLog(record_time, Loop_Name,Stop_Short,driver_username,number_boarded ,number_Left, Bus_ID, position)
select  distinct CONCAT(record_time,Bus_ID) as dupkey, id, record_time, Loop_Name,Stop_Short,driver_username,number_boarded ,number_Left, Bus_ID, position from yliu12.backuplog;


insert into yliu12.uniqueLog1(ID, record_time, Loop_Name,Stop_Short,driver_username,number_boarded ,number_Left, Bus_ID, position)

select  distinct CONCAT(UNIX_TIMESTAMP(record_time),Bus_ID) as dupkey, record_time, Loop_Name,Stop_Short,driver_username,number_boarded ,number_Left, Bus_ID, position from yliu12.backuplog;



drop table yliu12.uniqueLog1;
CREATE TABLE yliu12.uniqueLog1(
	ID varchar(32) NOT NULL,
    RECORD_time datetime UNIQUE,
    LOOP_NAME varchar(32),
    STOP_SHORT varchar(32),
    DRIVER_USERNAME varchar(32),
    NUMBER_BOARDED varchar(32),
    NUMBER_LEFT varchar(32),
    BUS_ID varchar(32),
    Position varchar(32),
	PRIMARY KEY (ID)
);

