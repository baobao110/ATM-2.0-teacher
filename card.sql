/*
Navicat MySQL Data Transfer

Source Server         : 333
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : 1

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2017-11-19 18:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `money` double(10,2) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `modifytime` datetime NOT NULL,
  `version` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', '800529', '111111', '800.00', '2017-11-19 14:13:41', '2017-11-19 17:21:17', null);
INSERT INTO `card` VALUES ('2', '800523', '222222', '700.00', '2017-11-19 15:33:42', '2017-11-19 17:45:33', null);
INSERT INTO `card` VALUES ('3', '945246', '223333333', '300.00', '2017-11-19 17:37:17', '2017-11-19 18:52:35', null);
