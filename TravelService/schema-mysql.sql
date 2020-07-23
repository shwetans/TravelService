drop table weatherinfo;
drop table location;

create table location (
	id INT AUTO_INCREMENT PRIMARY KEY,
    lat DECIMAL(10,8) NOT NULL,
    lon DECIMAL(11,8) NOT NULL,
    city VARCHAR(45) NOT NULL,
    state VARCHAR(45) NOT NULL
);

create table weatherinfo(
	id INT AUTO_INCREMENT PRIMARY KEY,
    wdate DATE,
    temp BLOB,
    locId INT,
    CONSTRAINT fk_key FOREIGN KEY (locId )REFERENCES location(id));
    
insert into travel.location (lat,lon,city,state) values(36.1189, -86.6892,"katy","TX");

insert into travel.weatherinfo values (1,'07/07/20','57.1 65 67 54 55 56 89 45 34 56.1 54.2 57.8 23.4 45.7 55.0 76.8 44 67.9 89.0 99.8 100.5 104.4 100.4 99.6',1);

