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
  `mdn` varchar(20) DEFAULT '0',
  `vmdn` varchar(20) NOT NULL,
  `base_type_model_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vmdn` (`vmdn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_base` */

/*Table structure for table `tbl_base_type` */

DROP TABLE IF EXISTS `tbl_base_type`;

CREATE TABLE `tbl_base_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_base_type` */

/*Table structure for table `tbl_channel` */

DROP TABLE IF EXISTS `tbl_channel`;

CREATE TABLE `tbl_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `title` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_channel` */

/*Table structure for table `tbl_charge_mode` */

DROP TABLE IF EXISTS `tbl_charge_mode`;

CREATE TABLE `tbl_charge_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_charge_mode` */

/*Table structure for table `tbl_expiration_mode` */

DROP TABLE IF EXISTS `tbl_expiration_mode`;

CREATE TABLE `tbl_expiration_mode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_expiration_mode` */

/*Table structure for table `tbl_header` */

DROP TABLE IF EXISTS `tbl_header`;

CREATE TABLE `tbl_header` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_header` */

insert  into `tbl_header`(`id`,`key`,`value`,`created_at`,`updated_at`) values 
(1,'spId','000201','2017-11-21 11:29:07','2017-11-21 11:29:07'),
(2,'spPassword','e6434ef249df55c7a21a0b45758a39bb','2017-11-21 11:29:27','2017-11-21 11:29:27'),
(3,'spRevId','sdp','2017-11-21 11:31:05','2017-11-21 11:36:00'),
(4,'spRevpassword','206D88BB7F3D154B130DD6E1E0B8828B','2017-11-21 11:31:21','2017-11-21 11:36:02');

/*Table structure for table `tbl_language` */

DROP TABLE IF EXISTS `tbl_language`;

CREATE TABLE `tbl_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_language` */

/*Table structure for table `tbl_notify_sms` */

DROP TABLE IF EXISTS `tbl_notify_sms`;

CREATE TABLE `tbl_notify_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correlator` varchar(20) DEFAULT NULL,
  `message` varchar(20) DEFAULT NULL,
  `sender_address` varchar(20) DEFAULT NULL,
  `sms_service_activation_number` varchar(20) DEFAULT NULL,
  `date_time` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_notify_sms` */

/*Table structure for table `tbl_object_type` */

DROP TABLE IF EXISTS `tbl_object_type`;

CREATE TABLE `tbl_object_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_object_type` */

/*Table structure for table `tbl_sms_delivery` */

DROP TABLE IF EXISTS `tbl_sms_delivery`;

CREATE TABLE `tbl_sms_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correlator` varchar(100) DEFAULT NULL,
  `address` varchar(20000) DEFAULT NULL,
  `delivery_status` varchar(30) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_sms_delivery` */

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
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_reason` bigint(20) DEFAULT NULL COMMENT 'updateReason',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_sync_relation` */

/*Table structure for table `tbl_update_type` */

DROP TABLE IF EXISTS `tbl_update_type`;

CREATE TABLE `tbl_update_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_update_type` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
