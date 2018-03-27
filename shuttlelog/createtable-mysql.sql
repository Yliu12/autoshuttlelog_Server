CREATE TABLE yliu12.log(
	ID BIGINT NOT NULL AUTO_INCREMENT,
    RECORD_time datetime,
    LOOP_NAME varchar(32),
    STOP_SHORT varchar(32),
    DRIVER_USERNAME varchar(32),
    NUMBER_BOARDED varchar(32),
    NUMBER_LEFT varchar(32),
    BUS_ID varchar(32),
    	Position varchar(32),
	PRIMARY KEY (ID)
);

insert into yliu12.log(
	
    RECORD_time,
    LOOP_NAME ,
    STOP_SHORT ,
    DRIVER_USERNAME ,
    NUMBER_BOARDED,
    NUMBER_LEFT ,
    BUS_ID 
)values ("2017-10-05 00:00:00","Sunday Loop","sc_N","yliu12","1","0","test_BUS");

select * from yliu12.log


#==================================================USER==================================================

drop table yliu12.User;
CREATE TABLE yliu12.User(
	ID INT NOT NULL AUTO_INCREMENT,
    Create_time datetime,
    last_update_time datetime,
    First_NAME varchar(32),
    Last_NAME varchar(32),
    Password varchar(32),
    USERNAME varchar(32) UNIQUE,
    ROLE varchar(32),
    STATUS_CODE varchar(32),
	PRIMARY KEY (ID)
);

insert into yliu12.User(
	Create_time ,
    First_NAME,
    Last_NAME,
    Password ,
    USERNAME,
    ROLE,
    STATUS_CODE
    )values ("2017-10-05 00:00:00","Ying","Liu","81dc9bdb52d04dc20036dbd8313ed055","yliu12","DRIVER","1");

select * from yliu12.User;

#==================================================LOOP==================================================

drop TABLE yliu12.BSULoop;
CREATE TABLE yliu12.BSULoop(
	ID INT NOT NULL AUTO_INCREMENT,
    Create_time datetime,
    last_update_time datetime,
    LOOP_NAME varchar(32) UNIQUE,
    STOPS TEXT,
    STATUS_CODE varchar(32),
	PRIMARY KEY (ID)
);
commit;
insert into yliu12.BSULoop(
	Create_time ,
    last_update_time,
    LOOP_NAME,
    STOPS ,
    STATUS_CODE
    )values ("2017-10-05 00:00:00","2017-10-05 00:00:00","Green Loop","['North Shelter - N', 'Anthony - N', 'Alumni', 'Stadium','Scheidler 1','Scheidler 2','Scheidler 3', 'Baseball Field', 'Anthony - S','North Shelter - S','Lafollette - S','Shafer Tower -S ', 'AJ', 'Burkhardt', 'South Shelter', 'Ashland', 'MU','Shafer Tower -N ','Lafollette - N']","1");

    select * from yliu12.BSULoop;

#	Green Loop Stops 
#   ['North Shelter - N', 'Anthony - N', 'Alumni', 'Stadium','Scheidler 1','Scheidler 2','Scheidler 3', 'Baseball Field', 'Anthony - S','North Shelter - S','Lafollette - S','Shafer Tower -S ', 'AJ', 'Burkhardt', 'South Shelter', 'Ashland', 'MU','Shafer Tower -N ','Lafollette - N']
