/*
Navicat MySQL Data Transfer

Source Server         : 333
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : 1

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2017-11-19 18:55:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `money` double(10,2) NOT NULL,
  `type` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
