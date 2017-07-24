CREATE DATABASE  IF NOT EXISTS vicloth;
USE 'vicloth';

DROP TABLE IF EXISTS body;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS pose;

CREATE TABLE user (
	Id int(10) NOT NULL AUTO_INCREMENT,
	Account varchar(255) ,
	Password varchar(255) ,
	Name varchar(255) ,
	Phone varchar(255)  ,
	Email varchar(255)  ,
	CreateTime varchar(255) ,
	PRIMARY KEY (`Id`)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE body (
	Id int(10),
	Height varchar(255) ,
	Weight varchar(255) ,
	ArmLength varchar(255) ,
	LegLength varchar(255) ,
	Waist varchar(255) ,
	Bust varchar(255) ,	
	EyeMidColor varchar(255) ,
	Face varchar(255) ,
	HairId varchar(255) ,
	Gender varchar(255),
	Primary Key (Id),
	CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `user` (`Id`) 
) ENGINE=InnoDB  CHARSET=utf8;

CREATE TABLE pose (
	Id int(10) Primary Key,
	RoleId varchar(255) NOT NULL DEFAULT ' ',
	Motion varchar(255) NOT NULL DEFAULT ' '
	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE hair (
	Id int(10) Primary Key,
	Url varchar(255) ,
	HairId varchar(255) ,
	Color varchar(255) ,	
	Gender varchar(255) ,
	Enable varchar(255) ,
	Priority int(10) 
	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE vicloth (
	Id int(10) Primary Key,
	Subject varchar(255) ,
	Url varchar(255) ,
	Role varchar(255) ,
	RoleId varchar(255) ,
	ThemeName varchar(255) ,	
	Priority int(10) ,
	Color varchar(255) ,
	Gender varchar(255) ,
	Assort int(10) ,
	Enable varchar(255) ,
	Hat varchar(255) 
	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;