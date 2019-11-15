#User -------------- Start---------------------
create table USER (
ID INTEGER primary key auto_increment,
NAME varchar(50) not null,
ADDRESS varchar(100) not null,
PHONE varchar(50),
POSITION varchar(50), 
PROFILE_PHOTO varchar(250),
COVER_PHOTO varchar(250),
BIO varchar(250),
CHOSEN_TIME TIMESTAMP
);
#User --------------End---------------------

#TEAM---------------Start-------------------
create table TEAM (
ID INTEGER primary key auto_increment,
NAME varchar(50) not null,
ADDRESS varchar(100) not null,
PROFILE_PHOTO varchar(250),
COVER_PHOTO varchar(250),
BIO varchar(250)
);
#TEAM---------------End---------------------

#USER_TEAM----------Start-------------------
create table USER_TEAM (
USER_ID INTEGER,
TEAM_ID INTEGER,
primary key (USER_ID, TEAM_ID),
constraint USER_TEAM_IBFK1 
foreign key (USER_ID) REFERENCES USER (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint USER_TEAM_IBFK2
foreign key (TEAM_ID) REFERENCES TEAM (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#USER_TEAM----------End--------------------------------

#PLAYGROUND---------Start------------------------------
create table PLAYGROUND(
ID INTEGER primary key auto_increment,
NAME varchar(50) not null,
ADDRESS varchar(100) not null,
PHONE varchar(50),
DESCRIPTION varchar(250),
AREA varchar(50),
AVAILABLE_TIME TIMESTAMP,
PRICE_PER_HOUR varchar(50) not null
);
#PLAYGROUND---------End---------------------------------

-- #USER_PLAYGROUND---------START--------------------------
-- create table USER_PLAYGROUND (
-- USER_ID INTEGER,
-- PLAYGROUND_ID INTEGER,
-- RESERVED_TIME timestamp not null,
-- HOURS_NUMBER integer (11) not null,
-- PLAYERS_NEEDED integer (11) not null,
-- primary key (USER_ID, PLAYGROUND_ID),
-- constraint USER_PLAYGROUND_IBFK1
-- foreign key (USER_ID) REFERENCES USER (ID)
-- ON DELETE CASCADE ON UPDATE CASCADE,
-- constraint USER_PLAYGROUND_IBFK2
-- foreign key (PLAYGROUND_ID) REFERENCES PLAYGROUND (ID)
-- ON DELETE CASCADE ON UPDATE CASCADE
-- );
-- #USER_PLAYGROUND---------END--------------------------

#STORE-------------------START------------------------
create table STORE (
ID INTEGER primary key auto_increment,
NAME varchar(50) not null,
DESCREPTION varchar(250), 
PRICE varchar(50),
QUANTITY INTEGER (11)
);
#STORE-------------------END--------------------------

#USER_STORE-------------------START------------------------
create table USER_STORE (
USER_ID INTEGER,
STORE_ID INTEGER,
QUANTITY INTEGER(11), 
primary key (USER_ID, STORE_ID),
constraint USER_STORE_IBFK1 
foreign key (USER_ID) REFERENCES USER (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint USER_STORE_IBFK2
foreign key (STORE_ID) REFERENCES STORE (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#USER_STORE-------------------END--------------------------

#PHOTO------------------------START------------------------
create table PHOTO (
ID INTEGER primary key auto_increment,
NAME varchar(50),
LINK varchar(250) not null
);
#PHOTO------------------------END--------------------------

#USER_PHOTO-------------------START------------------------
create table USER_PHOTO (
USER_ID INTEGER,
PHOTO_ID INTEGER,
primary key (USER_ID, PHOTO_ID),
constraint USER_PHOTO_IBFK1 
foreign key (USER_ID) REFERENCES USER (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint USER_PHOTO_IBFK2
foreign key (PHOTO_ID) REFERENCES PHOTO (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#USER_PHOTO-------------------END--------------------------

#PLAYGROUND_PHOTO-------------------START------------------------
create table PLAYGROUND_PHOTO (
PLAYGROUND_ID INTEGER,
PHOTO_ID INTEGER,
primary key (PLAYGROUND_ID, PHOTO_ID),
constraint PLAYGROUND_PHOTO_IBFK1 
foreign key (PLAYGROUND_ID) REFERENCES PLAYGROUND (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint PLAYGROUND_PHOTO_IBFK2
foreign key (PHOTO_ID) REFERENCES PHOTO (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#PLAYGROUND_PHOTO-------------------END--------------------------

#TEAM_PHOTO-------------------START------------------------
create table TEAM_PHOTO (
TEAM_ID INTEGER,
PHOTO_ID INTEGER,
primary key (TEAM_ID, PHOTO_ID),
constraint TEAM_PHOTO_IBFK1 
foreign key (TEAM_ID) REFERENCES TEAM (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint TEAM_PHOTO_IBFK2
foreign key (PHOTO_ID) REFERENCES PHOTO (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#TEAM_PHOTO-------------------END--------------------------

#STORE_PHOTO-------------------START------------------------
create table STORE_PHOTO (
STORE_ID INTEGER,
PHOTO_ID INTEGER,
primary key (STORE_ID, PHOTO_ID),
constraint STORE_PHOTO_IBFK1 
foreign key (STORE_ID) REFERENCES STORE (ID) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint STORE_PHOTO_IBFK2
foreign key (PHOTO_ID) REFERENCES PHOTO (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#STORE_PHOTO-------------------END--------------------------

#RESERVATION-------------------START------------------------
create table RESERVATION (
ID INTEGER primary key auto_increment,
USER_ID INTEGER,
PLAYGROUND_ID INTEGER,
RESERVED_TIME timestamp not null,
HOURS_NUMBER integer (11) not null,
PLAYERS_NEEDED integer (11) not null,
constraint USER_PLAYGROUND_IBFK1
foreign key (USER_ID) REFERENCES USER (ID)
ON DELETE CASCADE ON UPDATE CASCADE,
constraint USER_PLAYGROUND_IBFK2
foreign key (PLAYGROUND_ID) REFERENCES PLAYGROUND (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#RESERVATION-------------------END--------------------------

#TEAM_RESERVATION-------------------START------------------------
create table RESERVATION_TEAM (
TEAM_ID INTEGER,
RESERVATION_ID INTEGER,
primary key (TEAM_ID, RESERVATION_ID),
constraint RESERVATION_TEAM_IBFK1
foreign key (TEAM_ID) REFERENCES TEAM (ID)
ON DELETE CASCADE ON UPDATE CASCADE,
constraint RESERVATION_TEAM_IBFK2
foreign key (RESERVATION_ID) REFERENCES RESERVATION (ID)
ON DELETE CASCADE ON UPDATE CASCADE
);
#TEAM_RESERVATION-------------------END--------------------------
