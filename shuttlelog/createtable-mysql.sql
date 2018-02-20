CREATE TABLE yliu12.logs(
	ID MEDIUMINT NOT NULL AUTO_INCREMENT,
    RECORD_time datetime,
    LOOP_NAME varchar(32),
    STOP_SHORT varchar(32),
    DRIVER_USERNAME varchar(32),
    NUMBER_BOARDED varchar(32),
    NUMBER_LEFT varchar(32),
    BUS_ID varchar(32),
	PRIMARY KEY (ID)
);

insert into yliu12.logs(
	
    RECORD_time,
    LOOP_NAME ,
    STOP_SHORT ,
    DRIVER_USERNAME ,
    NUMBER_BOARDED,
    NUMBER_LEFT ,
    BUS_ID 
)values ("2017-10-05 00:00:00","Sunday Loop","sc_N","yliu12","1","0","test_BUS");

select * from yliu12.logs
