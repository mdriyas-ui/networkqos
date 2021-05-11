/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.22-community-nt : Database - psjav05
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`psjav05` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `psjav05`;

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `sno` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `bandwidth` varchar(50) NOT NULL default '',
  `phonetype` varchar(50) NOT NULL,
  `networkname` varchar(50) NOT NULL,
  `simstate` varchar(50) NOT NULL,
  `networktype` varchar(50) NOT NULL,
  `osversion` varchar(50) NOT NULL,
  PRIMARY KEY  (`username`),
  UNIQUE KEY `sno` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`sno`,`username`,`password`,`email`,`bandwidth`,`phonetype`,`networkname`,`simstate`,`networktype`,`osversion`) values (1,'s','s','s','34','ds','d','ds','ds','ds'),(2,'san','san','san','46','GSM','Android','89014103211118510720','UMTS (3G)','4.2.2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
