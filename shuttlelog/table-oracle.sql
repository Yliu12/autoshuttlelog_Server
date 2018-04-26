
drop table log;


CREATE TABLE log(
	ID number(19) NOT NULL,
    RECORD_time timestamp(0),
    LOOP_NAME varchar2(32),
    STOP_SHORT varchar2(32),
    DRIVER_USERNAME varchar2(32),
    NUMBER_BOARDED varchar2(32),
    NUMBER_LEFT varchar2(32),
    BUS_ID varchar2(32),
    Position varchar2(32),
	PRIMARY KEY (ID)
);



CREATE SEQUENCE log_seq START WITH 1300 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER log_seq_tr
 BEFORE INSERT ON log FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT log_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;

---==============================================================SL_USER==============================================================

drop table sl_USER

CREATE TABLE SL_USER(
	ID NUMBER(10) NOT NULL,
    Create_time timestamp(0),
    last_update_time timestamp(0),
    First_NAME varchar2(32),
    Last_NAME varchar2(32),
    Password varchar2(32),
    USERNAME varchar2(32) UNIQUE,
    ROLE varchar2(32),
    STATUS_CODE varchar2(32),
	PRIMARY KEY (ID)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE SL_USER_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER SL_USER_seq_tr
 BEFORE INSERT ON SL_USER FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT SL_USER_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/


insert into sl_user(
	Create_time ,
    First_NAME,
    Last_NAME,
    Password ,
    USERNAME,
    ROLE,
    STATUS_CODE
    )values (SYSDATE,'Ying','Liu','81dc9bdb52d04dc20036dbd8313ed055','testdriver','DRIVER','1');
    
    
    
    
    
drop TABLE BSULoop;
CREATE TABLE BSULoop(
	ID NUMBER(10) NOT NULL,
    Create_time timestamp(0),
    last_update_time timestamp(0),
    LOOP_NAME varchar2(32) UNIQUE,
    STOPS CLOB,
    STATUS_CODE varchar2(32),
	PRIMARY KEY (ID)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE BSULoop_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER BSULoop_seq_tr
 BEFORE INSERT ON BSULoop FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT BSULoop_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/


---PRESET LOOP DATA

INSERT INTO bsuloop (ID,Create_time,last_update_time,LOOP_NAME,STOPS,STATUS_CODE) VALUES (1,sysdate,sysdate,'Green Loop','["North Shelter - N", "Anthony - N", "Alumni", "Stadium","Scheidler 1","Scheidler 2","Scheidler 3", "Baseball Field", "Anthony - S","North Shelter - S","Lafollette - S","Shafer Tower -S ", "AJ", "Burkhardt", "South Shelter", "Ashland", "MU","Shafer Tower -N ","Lafollette - N"]','1');
INSERT INTO bsuloop (ID,Create_time,last_update_time,LOOP_NAME,STOPS,STATUS_CODE) VALUES (2,sysdate,sysdate,'Blue Loop','["Stu West Stop", "Linden & Neely Streets",  "Linden St & Wayne St", "Linden St  & Riverside Avenue", "Riverside & New York Avenue", "Emens Stop", "MU", "Shafer Tower"]','1');
INSERT INTO bsuloop (ID,Create_time,last_update_time,LOOP_NAME,STOPS,STATUS_CODE) VALUES (13,sysdate,sysdate,'Red Loop','["North Shelter - N",  "Anthony - N", "Oakwood", "Anthony - S", "North Shelter-S","Lafollette - S","Shafer Tower -S ", "AJ", "Burkhardt", "South Shelter", "Ashland", "MU","Shafer Tower -N ","Lafollette - N"]n','1');

commit;
 select * from BSULoop;
	
	
	
	
	
	
	
	
---==============================Report_Stop================================

drop table DAILYREPORT_stop;
CREATE TABLE DAILYREPORT_stop(
    ID number(19) NOT NULL,
    Record_date timestamp(0),
    LOOP_NAME varchar2(32),
    STOP_NAME varchar2(32),
    NUMBER_BOARDED number(19),
    NUMBER_LEFT number(19),
	PRIMARY KEY (ID)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE DAILYREPORT_stop_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER DAILYREPORT_stop_seq_tr
 BEFORE INSERT ON DAILYREPORT_stop FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT DAILYREPORT_stop_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/


-- timed task 5am 
INSERT INTO dailyreport_stop 
            (record_date, 
             stop_name, 
             loop_name, 
             number_boarded, 
             number_left) 
SELECT record_date, 
       stop_name, 
       loop_name, 
       SUM(number_boarded) AS NUMBER_BOARDED, 
       SUM(number_left)    AS NUMBER_LEFT 
FROM   (SELECT DISTINCT Concat(record_time, bus_id) AS Hash, 
                        Trunc(record_time)          AS Record_date, 
                        stop_short                  AS STOP_NAME, 
                        loop_name, 
                        number_boarded, 
                        number_left 
        FROM   log log where TRUNC(record_time) = TRUNC(SYSDATE) - 1) 
GROUP  BY stop_name, 
          loop_name, 
          record_date; 
commit;

		  
		  
---==============================Report_Hour================================


drop table DAILYREPORT_hour;
CREATE TABLE DAILYREPORT_hour(
    ID number(19) NOT NULL,
    Record_date timestamp(0),
    LOOP_NAME varchar2(32),
    hour number(2),
    NUMBER_BOARDED number(19),
    NUMBER_LEFT number(19),
	PRIMARY KEY (ID)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE DAILYREPORT_hour_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER DAILYREPORT_hour_seq_tr
 BEFORE INSERT ON DAILYREPORT_hour FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT DAILYREPORT_hour_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/


insert into  DAILYREPORT_hour(
    Record_date,
    hour,
    LOOP_NAME,
    NUMBER_BOARDED,
    NUMBER_LEFT
)
SELECT record_date,
       HOUR,
       loop_name,
       SUM(number_boarded) AS NUMBER_BOARDED,
       SUM(number_left)    AS NUMBER_LEFT
FROM   (SELECT DISTINCT Concat(record_time, bus_id) AS Hash,
                         TRUNC(record_time)   AS Record_date,
                        EXTRACT(HOUR from record_time)  AS HOUR,
                        loop_name,
                        number_boarded,
                        number_left
        FROM   log where TRUNC(record_time) = TRUNC(SYSDATE) - 1)
GROUP  BY HOUR,
          loop_name,
          record_date;

		  
    