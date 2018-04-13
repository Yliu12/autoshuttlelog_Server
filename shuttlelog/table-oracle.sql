select * from bsu_yliu12.LOG

CREATE SEQUENCE log_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER log_seq_tr
 BEFORE INSERT ON log FOR EACH ROW
 WHEN (LOG.ID IS NULL)
BEGIN
 SELECT log_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;




-- Generate ID using sequence and trigger
CREATE SEQUENCE User_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER User_seq_tr
 BEFORE INSERT ON sl_user FOR EACH ROW
 WHEN (NEW.ID IS NULL)
BEGIN
 SELECT User_seq.NEXTVAL INTO :NEW.ID FROM DUAL;
END;


insert into sl_user(
	Create_time ,
    First_NAME,
    Last_NAME,
    Password ,
    USERNAME,
    ROLE,
    STATUS_CODE
    )values (SYSDATE,'Ying','Liu','81dc9bdb52d04dc20036dbd8313ed055','testdriver','DRIVER','1');
    
    
    commit;
    
    
    
    
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
commit;
insert into BSULoop(
	Create_time ,
    last_update_time,
    LOOP_NAME,
    STOPS ,
    STATUS_CODE
    )values (SYSDATE,SYSDATE,'Green Loop','['North Shelter - N', 'Anthony - N', 'Alumni', 'Stadium','Scheidler 1','Scheidler 2','Scheidler 3', 'Baseball Field', 'Anthony - S','North Shelter - S','Lafollette - S','Shafer Tower -S ', 'AJ', 'Burkhardt', 'South Shelter', 'Ashland', 'MU','Shafer Tower -N ','Lafollette - N']','1');

    select * from BSULoop;
    