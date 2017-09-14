/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : co-change

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-09-14 10:53:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for change_operation
-- ----------------------------
DROP TABLE IF EXISTS `change_operation`;
CREATE TABLE `change_operation` (
  `change_operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `repository_id` int(11) DEFAULT NULL,
  `commit_id` varchar(1000) DEFAULT NULL,
  `file_name` varchar(1000) DEFAULT NULL,
  `root_entity_type` varchar(1000) DEFAULT NULL,
  `root_entity_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `parent_entity_type` varchar(1000) DEFAULT NULL,
  `parent_entity_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `change_type` varchar(1000) DEFAULT NULL,
  `significance_level` varchar(1000) DEFAULT NULL,
  `changed_entity_type` varchar(1000) DEFAULT NULL,
  `changed_entity_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `new_entity_content` longtext,
  PRIMARY KEY (`change_operation_id`),
  KEY `change_operation_fk1` (`repository_id`),
  KEY `change_operation_fk2` (`commit_id`),
  KEY `change_operation_fk3` (`file_name`),
  KEY `change_type` (`change_type`),
  KEY `changed_entity_type` (`changed_entity_type`),
  CONSTRAINT `change_operation_fk1` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`repository_id`),
  CONSTRAINT `change_operation_fk2` FOREIGN KEY (`commit_id`) REFERENCES `commit` (`commit_id`),
  CONSTRAINT `change_operation_fk3` FOREIGN KEY (`file_name`) REFERENCES `change_file` (`file_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2605414 DEFAULT CHARSET=utf8;
