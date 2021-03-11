/*
SQLyog Community v12.4.3 (64 bit)
MySQL - 10.1.25-MariaDB : Database - robisdp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`robisdp` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `robisdp`;

/*Table structure for table `tbl_base` */

DROP TABLE IF EXISTS `tbl_base`;

CREATE TABLE `tbl_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mdn` varchar(20) DEFAULT NULL,
  `vmdn` varchar(20) NOT NULL,
  `base_type_model_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mdn` (`mdn`,`vmdn`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_base` */

insert  into `tbl_base`(`id`,`mdn`,`vmdn`,`base_type_model_id`,`created_at`,`updated_at`) values 
(8,NULL,'8619800000001',1,'2017-11-02 14:42:00','2017-11-02 14:42:00'),
(27,NULL,'8619800000002',1,'2017-11-02 15:28:21','2017-11-02 15:28:21'),
(36,NULL,'8619800000004',1,'2017-11-02 15:50:12','2017-11-02 15:50:12'),
(40,NULL,'8619800000005',1,'2017-11-02 16:14:34','2017-11-02 16:14:34'),
(44,NULL,'8619800000006',1,'2017-11-02 16:26:04','2017-11-02 16:26:04'),
(46,NULL,'8619800000007',1,'2017-11-02 16:27:58','2017-11-02 16:27:58'),
(47,NULL,'8619800000009',1,'2017-11-05 12:59:17','2017-11-05 12:59:17'),
(48,NULL,'8619800000010',1,'2017-11-08 12:42:22','2017-11-08 12:42:22'),
(49,NULL,'86198000000010',1,'2017-11-08 12:44:50','2017-11-08 12:44:50');

/*Table structure for table `tbl_base_type` */

DROP TABLE IF EXISTS `tbl_base_type`;

CREATE TABLE `tbl_base_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_base_type` */

insert  into `tbl_base_type`(`id`,`code`,`title`,`created_at`,`updated_at`) values 
(1,0,'Mobile Phone User','2017-10-31 15:12:24','2017-10-31 15:12:24'),
(2,10,'User Fake ID','2017-10-31 15:12:36','2017-10-31 15:12:36'),
(3,11,'Email User','2017-10-31 15:12:49','2017-10-31 15:12:49'),
(4,18,'Internet User','2017-10-31 15:12:57','2017-10-31 15:12:57');

/*Table structure for table `tbl_channel` */

DROP TABLE IF EXISTS `tbl_channel`;

CREATE TABLE `tbl_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `title` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_channel` */

insert  into `tbl_channel`(`id`,`code`,`title`,`created_at`,`updated_at`) values 
(1,'1','web','2017-10-31 15:29:06','2017-10-31 15:29:06'),
(2,'2','sms','2017-10-31 15:29:11','2017-10-31 15:29:11'),
(3,'3','ussd','2017-10-31 15:29:16','2017-10-31 15:29:16'),
(4,'4','ivr','2017-10-31 15:29:23','2017-10-31 15:29:23'),
(5,'5','cc (crm)','2017-10-31 15:29:32','2017-10-31 15:29:32'),
(6,'6','pda','2017-10-31 15:29:39','2017-10-31 15:29:39'),
(7,'7','XHTML (WAP 2)','2017-10-31 15:30:18','2017-10-31 15:30:18'),
(8,'10','Promotion','2017-10-31 15:30:31','2017-10-31 15:30:31'),
(9,'21','ODP','2017-10-31 15:31:10','2017-10-31 15:31:10'),
(10,'99','Other','2017-10-31 15:31:16','2017-10-31 15:31:16'),
(11,'100','SP Website','2017-10-31 15:31:25','2017-10-31 15:31:25'),
(12,'101','UE (User Equipment)','2017-10-31 15:31:47','2017-10-31 15:31:47'),
(13,'102','MDSP CC Portal','2017-10-31 15:32:02','2017-10-31 15:32:02'),
(14,'105','PC Client','2017-10-31 15:32:28','2017-10-31 15:32:28'),
(15,'110','IVR Call Out','2017-10-31 15:32:41','2017-10-31 15:32:41'),
(16,'111','SDP Web Tool (CS)','2017-10-31 15:32:53','2017-10-31 15:33:07'),
(17,'112','SDP Web Tool (Admin)','2017-10-31 15:33:28','2017-10-31 15:33:28'),
(18,'113','SDP Web Tool (CP)','2017-10-31 15:33:42','2017-10-31 15:33:51'),
(19,'115','MMS','2017-10-31 15:33:59','2017-10-31 15:33:59'),
(20,'120','channel of unsubscription caused by user deregistr','2017-10-31 15:34:15','2017-10-31 15:34:15'),
(21,'124','channel of unsubscription caused by the blacklist','2017-10-31 15:34:33','2017-10-31 15:34:33'),
(22,'125','SP website (added channel type in the MTN project,','2017-10-31 15:34:44','2017-10-31 15:34:44');

/*Table structure for table `tbl_charge_mode` */

DROP TABLE IF EXISTS `tbl_charge_mode`;

CREATE TABLE `tbl_charge_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_charge_mode` */

insert  into `tbl_charge_mode`(`id`,`code`,`title`,`created_at`,`updated_at`) values 
(1,'0','month','2017-10-31 15:15:19','2017-10-31 15:15:19'),
(2,'10','multiday','2017-10-31 15:15:30','2017-10-31 15:15:30'),
(3,'13','multimonth','2017-10-31 15:15:38','2017-10-31 15:15:38'),
(4,'18','day','2017-10-31 15:15:45','2017-10-31 15:15:45'),
(5,'19','week','2017-10-31 15:15:52','2017-10-31 15:15:52'),
(6,'20','multiweek','2017-10-31 15:15:58','2017-10-31 15:15:58');

/*Table structure for table `tbl_expiration_mode` */

DROP TABLE IF EXISTS `tbl_expiration_mode`;

CREATE TABLE `tbl_expiration_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_expiration_mode` */

insert  into `tbl_expiration_mode`(`id`,`code`,`title`,`created_at`,`updated_at`) values 
(1,'1','expires immediately','2017-10-31 15:18:18','2017-10-31 15:18:18'),
(2,'2','expires next week','2017-10-31 15:18:28','2017-10-31 15:18:28');

/*Table structure for table `tbl_language` */

DROP TABLE IF EXISTS `tbl_language`;

CREATE TABLE `tbl_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_language` */

insert  into `tbl_language`(`id`,`title`,`created_at`,`updated_at`) values 
(1,'zh','2017-10-31 15:36:19','2017-10-31 15:36:19'),
(2,'en','2017-10-31 15:36:21','2017-10-31 15:36:21');

/*Table structure for table `tbl_notify_sms` */

DROP TABLE IF EXISTS `tbl_notify_sms`;

CREATE TABLE `tbl_notify_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correlator` varchar(20) DEFAULT NULL,
  `message` varchar(20) DEFAULT NULL,
  `sender_address` varchar(20) DEFAULT NULL,
  `sms_service_activation_number` varchar(20) DEFAULT NULL,
  `date_time` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_notify_sms` */

insert  into `tbl_notify_sms`(`id`,`correlator`,`message`,`sender_address`,`sms_service_activation_number`,`date_time`,`created_at`,`updated_at`) values 
(14,'00001','Hello world','tel:8612312345678','tel:1234501','2010-08-09T00:00:00.000+08:00',NULL,NULL),
(15,'00001','Hello world','tel:8612312345678','tel:1234501','2010-08-09T00:00:00.000+08:00',NULL,NULL),
(16,'00001','Hello world','tel:8612312345678','tel:1234501','2010-08-09T00:00:00.000+08:00',NULL,NULL);

/*Table structure for table `tbl_object_type` */

DROP TABLE IF EXISTS `tbl_object_type`;

CREATE TABLE `tbl_object_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_object_type` */

insert  into `tbl_object_type`(`id`,`code`,`title`,`created_at`,`updated_at`) values 
(1,'1','service','2017-10-31 15:21:18','2017-10-31 15:21:18'),
(2,'2','content','2017-10-31 15:21:25','2017-10-31 15:21:25'),
(3,'3','category','2017-10-31 15:21:30','2017-10-31 15:21:30'),
(4,'4','package','2017-10-31 15:21:39','2017-10-31 15:21:39');

/*Table structure for table `tbl_sms_delivery` */

DROP TABLE IF EXISTS `tbl_sms_delivery`;

CREATE TABLE `tbl_sms_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correlator` varchar(100) DEFAULT NULL,
  `address` varchar(20000) DEFAULT NULL,
  `delivery_status` varchar(30) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_sms_delivery` */

insert  into `tbl_sms_delivery`(`id`,`correlator`,`address`,`delivery_status`,`created_at`,`updated_at`) values 
(1,'dfd','wewe','ghhghg','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(2,'00001',NULL,' \n         ','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(3,'00001',NULL,' \n         ','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(4,'00001',NULL,' \n         ','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(6,'00001',NULL,' \n         ','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(7,'00001',NULL,' \n         ','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(8,NULL,NULL,NULL,'2017-11-08 11:45:07','2017-11-08 11:45:07'),
(9,NULL,NULL,'null','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(10,'00001','tel:8612312345678','DeliveredToTerminal','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(11,'00001','tel:8612312345678','DeliveredToTerminal','2017-11-08 11:45:07','2017-11-08 11:45:07'),
(12,'00001','tel:8612312345678','DeliveredToTerminal','0000-00-00 00:00:00','0000-00-00 00:00:00'),
(13,'00001','tel:8612312345678','DeliveredToTerminal','0000-00-00 00:00:00','0000-00-00 00:00:00'),
(14,'00001','tel:8612312345678','DeliveredToTerminal','1970-01-20 00:00:00','2017-11-08 12:33:47'),
(15,'00001','tel:8612312345678','DeliveredToTerminal',NULL,NULL),
(16,'00005','tel:8612312345678','DeliveredToTerminal',NULL,NULL);

/*Table structure for table `tbl_sync_relation` */

DROP TABLE IF EXISTS `tbl_sync_relation`;

CREATE TABLE `tbl_sync_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_model_id` bigint(20) NOT NULL COMMENT 'userID',
  `partner_id` varchar(25) DEFAULT NULL COMMENT 'spID',
  `product_id` varchar(25) DEFAULT NULL COMMENT 'productID',
  `service_id` varchar(25) DEFAULT NULL COMMENT 'serviceID',
  `service_list` text COMMENT 'serviceList',
  `update_type` int(1) DEFAULT NULL COMMENT '1=ADD; 2=Delete; 3=Update',
  `update_time` varchar(15) DEFAULT NULL COMMENT 'updateTime',
  `update_desc` text COMMENT 'updateDesc',
  `effective_time` varchar(15) DEFAULT NULL COMMENT 'effectiveTime',
  `expiry_time` varchar(15) DEFAULT NULL COMMENT 'expiryTime',
  `access_code` varchar(30) DEFAULT NULL COMMENT 'accessCode',
  `charge_mode_model_id` bigint(20) DEFAULT NULL COMMENT 'chargeMode',
  `expiration_mode_model_id` bigint(20) DEFAULT NULL COMMENT 'MDSPSUBEXPMODE',
  `object_type_model_id` bigint(20) DEFAULT NULL COMMENT 'objectType',
  `free_period` tinyint(1) DEFAULT NULL COMMENT 'isFreePeriod',
  `pay_type` int(11) DEFAULT NULL COMMENT 'payType',
  `transaction_id` varchar(100) DEFAULT NULL COMMENT 'transactionID',
  `order_key` varchar(20) DEFAULT NULL COMMENT 'orderKey',
  `keyword` varchar(100) DEFAULT NULL COMMENT 'keyword',
  `cycle_end_time` varchar(15) DEFAULT NULL COMMENT 'cycleEndTime',
  `duration_of_grace` varchar(4) DEFAULT NULL COMMENT 'durationOfGracePeriod',
  `update_type_model_id` bigint(20) DEFAULT NULL COMMENT 'updateReason',
  `service_availbility` varchar(10) DEFAULT NULL COMMENT 'serviceAvailability',
  `channel_model_id` bigint(20) DEFAULT NULL COMMENT 'channelID',
  `trace_id` varchar(30) DEFAULT NULL COMMENT 'TraceUniqueID',
  `language_model_id` bigint(20) DEFAULT NULL COMMENT 'operCode',
  `rent_success` tinyint(1) DEFAULT NULL COMMENT 'rentSuccess',
  `try` tinyint(1) DEFAULT NULL COMMENT 'try',
  `short_message` text COMMENT 'shortMessage',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_reason` bigint(20) DEFAULT NULL COMMENT 'updateReason',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_sync_relation` */

insert  into `tbl_sync_relation`(`id`,`base_model_id`,`partner_id`,`product_id`,`service_id`,`service_list`,`update_type`,`update_time`,`update_desc`,`effective_time`,`expiry_time`,`access_code`,`charge_mode_model_id`,`expiration_mode_model_id`,`object_type_model_id`,`free_period`,`pay_type`,`transaction_id`,`order_key`,`keyword`,`cycle_end_time`,`duration_of_grace`,`update_type_model_id`,`service_availbility`,`channel_model_id`,`trace_id`,`language_model_id`,`rent_success`,`try`,`short_message`,`created_at`,`updated_at`,`update_reason`) values 
(33,27,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(35,36,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(39,40,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(40,40,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(41,40,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(42,44,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(43,44,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(44,46,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(45,46,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(46,8,'001100','1000000423','0011002000001100','0011002000001100',2,'20130723094953','Deletion','20130723082551','20130723094952','20086',1,1,1,0,0,'504016000001307231748315161003','999000000000000194','unsub',NULL,NULL,NULL,NULL,1,'504016000001307231748315161006',NULL,1,0,'Hello world.',NULL,NULL,1),
(47,8,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(48,40,'001100','1000000423','0011002000001100','0011002000001100',2,'20130723094953','Deletion','20130723082551','20130723094952','20086',1,1,1,0,0,'504016000001307231748315161003','999000000000000194','unsub',NULL,NULL,1,NULL,1,'504016000001307231748315161006',NULL,1,0,'Hello world.',NULL,NULL,NULL),
(49,47,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(50,8,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(51,8,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(52,8,'001100','1000000423','0011002000001100','0011002000001100',2,'20130723094953','Deletion','20130723082551','20130723094952','20086',1,1,1,0,0,'504016000001307231748315161003','999000000000000194','unsub',NULL,NULL,1,NULL,1,'504016000001307231748315161006',NULL,1,0,'Hello world.',NULL,NULL,NULL),
(53,48,'001100','1000000423','0011002000001100','0011002000001100',1,'20130723082551','Addition','20130723082551','20361231160000','20086',1,1,1,0,0,'504016000001307231624304170004','999000000000000194','sub','20130822160000','-1',NULL,'0',1,'504016000001307231624304170005',1,1,0,'Hello world.',NULL,NULL,NULL),
(54,49,'001100','1000000423','0011002000001100','0011002000001100',2,'20130723094953','Deletion','20130723082551','20130723094952','20086',1,1,1,0,0,'504016000001307231748315161003','999000000000000194','unsub',NULL,NULL,1,NULL,1,'504016000001307231748315161006',NULL,1,0,'Hello world.',NULL,NULL,NULL);

/*Table structure for table `tbl_update_type` */

DROP TABLE IF EXISTS `tbl_update_type`;

CREATE TABLE `tbl_update_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_update_type` */

insert  into `tbl_update_type`(`id`,`code`,`description`,`created_at`,`updated_at`) values 
(1,'1','A user unsubscribes from a product','2017-10-31 15:25:19','2017-10-31 15:25:19'),
(2,'2','Rental fails to be collected upon subscription renewal','2017-10-31 15:25:27','2017-10-31 15:25:27'),
(3,'3','Unsubscription is triggered when the charging fails upon the first subscription','2017-10-31 15:25:36','2017-10-31 15:25:36'),
(4,'4','Unsubscription is triggered when the service status is abnormal','2017-10-31 15:25:54','2017-10-31 15:25:54'),
(5,'5','Unsubscription is triggered when the service status is abnormal','2017-10-31 15:26:06','2017-10-31 15:26:06');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int(3) NOT NULL,
  `height` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `test` */

insert  into `test`(`id`,`name`,`age`,`height`) values 
(1,'Saikat',0,5.09),
(2,'Atiq',30,6),
(3,'Saikat',0,5.09),
(4,'Saikat',0,5.09);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
