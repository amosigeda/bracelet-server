/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : bracelet

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2017-09-21 21:56:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `device_info`
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `dev_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dv` varchar(256) NOT NULL COMMENT '设备固件版本号',
  `sd` varchar(256) NOT NULL COMMENT '软件版本号',
  `user_id` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dev_id`),
  UNIQUE KEY `unique_idx_dv` (`dv`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_info
-- ----------------------------

-- ----------------------------
-- Table structure for `heart_pressure`
-- ----------------------------
DROP TABLE IF EXISTS `heart_pressure`;
CREATE TABLE `heart_pressure` (
  `hp_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `max_heart_pressure` int(10) NOT NULL COMMENT '高压',
  `min_heart_pressure` int(10) NOT NULL COMMENT '低压',
  `user_id` int(11) unsigned NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of heart_pressure
-- ----------------------------
INSERT INTO `heart_pressure` VALUES ('1', '100', '120', '1', '2017-08-24 14:25:38');
INSERT INTO `heart_pressure` VALUES ('2', '22', '333', '1', '2017-08-31 23:44:13');
INSERT INTO `heart_pressure` VALUES ('3', '120', '78', '1', '2017-09-03 23:42:46');
INSERT INTO `heart_pressure` VALUES ('4', '121', '78', '1', '2017-09-03 23:43:08');
INSERT INTO `heart_pressure` VALUES ('5', '120', '78', '1', '2017-09-03 23:56:37');
INSERT INTO `heart_pressure` VALUES ('6', '120', '78', '1', '2017-09-03 23:57:12');
INSERT INTO `heart_pressure` VALUES ('7', '120', '78', '1', '2017-09-03 23:57:49');
INSERT INTO `heart_pressure` VALUES ('8', '127', '78', '1', '2017-09-03 23:58:10');
INSERT INTO `heart_pressure` VALUES ('9', '127', '78', '1', '2017-09-04 00:02:03');
INSERT INTO `heart_pressure` VALUES ('10', '127', '78', '1', '2017-09-04 00:10:07');
INSERT INTO `heart_pressure` VALUES ('11', '127', '78', '1', '2017-09-04 00:16:21');
INSERT INTO `heart_pressure` VALUES ('12', '127', '78', '1', '2017-09-04 00:18:50');
INSERT INTO `heart_pressure` VALUES ('13', '126', '78', '1', '2017-09-04 00:18:56');
INSERT INTO `heart_pressure` VALUES ('14', '126', '78', '1', '2017-09-04 00:26:53');
INSERT INTO `heart_pressure` VALUES ('15', '123', '67', '1', '2017-09-04 06:37:17');
INSERT INTO `heart_pressure` VALUES ('16', '123', '69', '1', '2017-09-04 06:40:06');
INSERT INTO `heart_pressure` VALUES ('17', '123', '69', '1', '2017-09-04 06:54:04');
INSERT INTO `heart_pressure` VALUES ('18', '123', '69', '1', '2017-09-04 07:02:45');
INSERT INTO `heart_pressure` VALUES ('19', '123', '69', '1', '2017-09-04 07:05:23');
INSERT INTO `heart_pressure` VALUES ('20', '123', '69', '1', '2017-09-04 11:08:47');
INSERT INTO `heart_pressure` VALUES ('21', '123', '69', '1', '2017-09-04 11:22:59');
INSERT INTO `heart_pressure` VALUES ('22', '123', '69', '1', '2017-09-04 11:24:43');
INSERT INTO `heart_pressure` VALUES ('23', '123', '90', '1', '2017-09-04 11:24:51');
INSERT INTO `heart_pressure` VALUES ('24', '123', '69', '1', '2017-09-04 12:20:53');
INSERT INTO `heart_pressure` VALUES ('25', '123', '69', '1', '2017-09-04 12:21:27');
INSERT INTO `heart_pressure` VALUES ('26', '112', '75', '1', '2017-09-04 13:23:48');
INSERT INTO `heart_pressure` VALUES ('27', '0', '0', '1', '2017-09-04 13:28:04');
INSERT INTO `heart_pressure` VALUES ('28', '0', '0', '1', '2017-09-04 13:29:04');
INSERT INTO `heart_pressure` VALUES ('29', '0', '0', '1', '2017-09-04 13:29:44');
INSERT INTO `heart_pressure` VALUES ('30', '0', '0', '1', '2017-09-04 13:29:48');
INSERT INTO `heart_pressure` VALUES ('31', '0', '0', '1', '2017-09-04 13:30:37');
INSERT INTO `heart_pressure` VALUES ('32', '0', '0', '1', '2017-09-04 13:32:38');
INSERT INTO `heart_pressure` VALUES ('33', '0', '0', '1', '2017-09-04 13:33:57');
INSERT INTO `heart_pressure` VALUES ('34', '120', '80', '1', '2017-09-04 13:35:34');
INSERT INTO `heart_pressure` VALUES ('35', '120', '80', '1', '2017-09-04 13:35:55');
INSERT INTO `heart_pressure` VALUES ('36', '125', '87', '1', '2017-09-04 20:35:06');
INSERT INTO `heart_pressure` VALUES ('37', '130', '76', '1', '2017-09-04 20:36:40');
INSERT INTO `heart_pressure` VALUES ('38', '200', '100', '1', '2017-09-05 17:09:26');
INSERT INTO `heart_pressure` VALUES ('39', '200', '100', '1', '2017-09-05 20:49:02');
INSERT INTO `heart_pressure` VALUES ('40', '200', '100', '1', '2017-09-05 20:53:41');
INSERT INTO `heart_pressure` VALUES ('41', '200', '100', '1', '2017-09-05 21:07:15');
INSERT INTO `heart_pressure` VALUES ('42', '200', '100', '1', '2017-09-05 21:23:19');
INSERT INTO `heart_pressure` VALUES ('43', '200', '100', '1', '2017-09-05 21:30:54');
INSERT INTO `heart_pressure` VALUES ('44', '200', '100', '1', '2017-09-05 22:28:57');
INSERT INTO `heart_pressure` VALUES ('45', '200', '100', '1', '2017-09-05 22:31:36');
INSERT INTO `heart_pressure` VALUES ('46', '200', '100', '1', '2017-09-05 22:33:37');
INSERT INTO `heart_pressure` VALUES ('47', '200', '100', '1', '2017-09-05 22:42:28');
INSERT INTO `heart_pressure` VALUES ('48', '200', '100', '1', '2017-09-05 22:49:20');
INSERT INTO `heart_pressure` VALUES ('49', '200', '100', '1', '2017-09-05 22:57:45');
INSERT INTO `heart_pressure` VALUES ('50', '200', '100', '1', '2017-09-06 09:14:14');
INSERT INTO `heart_pressure` VALUES ('51', '200', '100', '1', '2017-09-06 10:01:23');
INSERT INTO `heart_pressure` VALUES ('52', '200', '100', '1', '2017-09-06 10:06:15');
INSERT INTO `heart_pressure` VALUES ('53', '200', '100', '1', '2017-09-06 10:21:35');
INSERT INTO `heart_pressure` VALUES ('54', '200', '100', '1', '2017-09-06 10:34:20');
INSERT INTO `heart_pressure` VALUES ('55', '200', '100', '1', '2017-09-06 10:45:00');
INSERT INTO `heart_pressure` VALUES ('56', '200', '100', '1', '2017-09-06 10:47:29');
INSERT INTO `heart_pressure` VALUES ('57', '200', '100', '1', '2017-09-06 10:48:33');
INSERT INTO `heart_pressure` VALUES ('58', '200', '100', '1', '2017-09-06 11:00:05');
INSERT INTO `heart_pressure` VALUES ('59', '200', '100', '1', '2017-09-06 11:21:58');
INSERT INTO `heart_pressure` VALUES ('60', '200', '100', '1', '2017-09-06 11:50:33');
INSERT INTO `heart_pressure` VALUES ('61', '200', '100', '1', '2017-09-06 13:49:54');
INSERT INTO `heart_pressure` VALUES ('62', '200', '100', '1', '2017-09-06 13:52:08');
INSERT INTO `heart_pressure` VALUES ('63', '200', '100', '1', '2017-09-06 15:34:57');
INSERT INTO `heart_pressure` VALUES ('64', '200', '100', '1', '2017-09-06 15:42:01');
INSERT INTO `heart_pressure` VALUES ('65', '200', '100', '1', '2017-09-06 15:47:59');
INSERT INTO `heart_pressure` VALUES ('66', '200', '100', '1', '2017-09-06 15:52:51');
INSERT INTO `heart_pressure` VALUES ('67', '200', '100', '1', '2017-09-06 15:54:09');
INSERT INTO `heart_pressure` VALUES ('68', '200', '100', '1', '2017-09-06 15:56:53');
INSERT INTO `heart_pressure` VALUES ('69', '200', '100', '1', '2017-09-06 15:59:06');
INSERT INTO `heart_pressure` VALUES ('70', '200', '100', '1', '2017-09-06 16:02:27');
INSERT INTO `heart_pressure` VALUES ('71', '200', '100', '1', '2017-09-06 16:07:03');
INSERT INTO `heart_pressure` VALUES ('72', '200', '100', '1', '2017-09-06 16:08:00');
INSERT INTO `heart_pressure` VALUES ('73', '200', '100', '1', '2017-09-06 16:09:05');
INSERT INTO `heart_pressure` VALUES ('74', '200', '100', '1', '2017-09-06 16:21:51');
INSERT INTO `heart_pressure` VALUES ('75', '200', '100', '1', '2017-09-06 16:23:36');
INSERT INTO `heart_pressure` VALUES ('76', '200', '100', '1', '2017-09-06 16:36:25');
INSERT INTO `heart_pressure` VALUES ('77', '200', '100', '1', '2017-09-06 16:44:22');
INSERT INTO `heart_pressure` VALUES ('78', '200', '100', '1', '2017-09-06 16:45:56');
INSERT INTO `heart_pressure` VALUES ('79', '200', '100', '1', '2017-09-06 17:00:31');
INSERT INTO `heart_pressure` VALUES ('80', '200', '100', '1', '2017-09-06 17:41:33');
INSERT INTO `heart_pressure` VALUES ('81', '200', '100', '1', '2017-09-06 18:01:36');
INSERT INTO `heart_pressure` VALUES ('82', '100', '79', '4', '2017-09-06 19:53:43');
INSERT INTO `heart_pressure` VALUES ('83', '104', '76', '4', '2017-09-06 19:55:21');
INSERT INTO `heart_pressure` VALUES ('84', '200', '100', '4', '2017-09-06 21:12:19');
INSERT INTO `heart_pressure` VALUES ('85', '200', '100', '1', '2017-09-06 21:20:44');
INSERT INTO `heart_pressure` VALUES ('86', '134', '84', '3', '2017-09-06 22:22:01');
INSERT INTO `heart_pressure` VALUES ('87', '125', '81', '2', '2017-09-06 22:25:09');
INSERT INTO `heart_pressure` VALUES ('88', '200', '100', '1', '2017-09-07 09:41:35');
INSERT INTO `heart_pressure` VALUES ('89', '200', '100', '4', '2017-09-07 09:52:33');
INSERT INTO `heart_pressure` VALUES ('90', '103', '77', '4', '2017-09-07 09:54:08');
INSERT INTO `heart_pressure` VALUES ('91', '200', '100', '1', '2017-09-07 10:04:31');
INSERT INTO `heart_pressure` VALUES ('92', '200', '100', '1', '2017-09-07 10:05:36');
INSERT INTO `heart_pressure` VALUES ('93', '200', '100', '1', '2017-09-07 10:37:32');
INSERT INTO `heart_pressure` VALUES ('94', '200', '100', '1', '2017-09-07 11:41:58');
INSERT INTO `heart_pressure` VALUES ('95', '200', '100', '1', '2017-09-07 11:58:31');
INSERT INTO `heart_pressure` VALUES ('96', '200', '100', '1', '2017-09-07 13:45:20');
INSERT INTO `heart_pressure` VALUES ('97', '200', '100', '1', '2017-09-07 13:49:37');
INSERT INTO `heart_pressure` VALUES ('98', '200', '100', '1', '2017-09-07 13:51:39');
INSERT INTO `heart_pressure` VALUES ('99', '200', '100', '1', '2017-09-07 13:53:03');
INSERT INTO `heart_pressure` VALUES ('100', '200', '100', '1', '2017-09-07 15:15:01');
INSERT INTO `heart_pressure` VALUES ('101', '200', '100', '1', '2017-09-07 15:16:47');
INSERT INTO `heart_pressure` VALUES ('102', '200', '100', '1', '2017-09-07 15:22:07');
INSERT INTO `heart_pressure` VALUES ('103', '200', '100', '1', '2017-09-07 15:50:40');
INSERT INTO `heart_pressure` VALUES ('104', '200', '100', '1', '2017-09-07 16:13:16');
INSERT INTO `heart_pressure` VALUES ('105', '200', '100', '1', '2017-09-07 16:14:34');
INSERT INTO `heart_pressure` VALUES ('106', '200', '100', '1', '2017-09-07 16:23:14');
INSERT INTO `heart_pressure` VALUES ('107', '200', '100', '1', '2017-09-07 16:24:47');
INSERT INTO `heart_pressure` VALUES ('108', '200', '100', '1', '2017-09-07 16:39:19');
INSERT INTO `heart_pressure` VALUES ('109', '200', '100', '1', '2017-09-07 17:27:37');
INSERT INTO `heart_pressure` VALUES ('110', '200', '100', '1', '2017-09-08 10:05:42');
INSERT INTO `heart_pressure` VALUES ('111', '200', '100', '1', '2017-09-08 10:45:26');
INSERT INTO `heart_pressure` VALUES ('112', '200', '100', '1', '2017-09-08 10:59:41');
INSERT INTO `heart_pressure` VALUES ('113', '200', '100', '1', '2017-09-08 11:05:02');
INSERT INTO `heart_pressure` VALUES ('114', '200', '100', '1', '2017-09-08 11:57:09');
INSERT INTO `heart_pressure` VALUES ('115', '200', '100', '1', '2017-09-08 12:25:00');
INSERT INTO `heart_pressure` VALUES ('116', '200', '100', '1', '2017-09-08 15:00:48');
INSERT INTO `heart_pressure` VALUES ('117', '200', '100', '1', '2017-09-08 15:19:25');
INSERT INTO `heart_pressure` VALUES ('118', '200', '100', '1', '2017-09-08 15:26:02');
INSERT INTO `heart_pressure` VALUES ('119', '200', '100', '1', '2017-09-08 15:40:10');
INSERT INTO `heart_pressure` VALUES ('120', '200', '100', '1', '2017-09-08 15:49:19');
INSERT INTO `heart_pressure` VALUES ('121', '200', '100', '1', '2017-09-08 16:15:50');
INSERT INTO `heart_pressure` VALUES ('122', '200', '100', '1', '2017-09-08 16:33:20');
INSERT INTO `heart_pressure` VALUES ('123', '200', '100', '1', '2017-09-08 16:44:06');
INSERT INTO `heart_pressure` VALUES ('124', '200', '100', '1', '2017-09-08 16:57:50');
INSERT INTO `heart_pressure` VALUES ('125', '200', '100', '1', '2017-09-08 16:58:38');
INSERT INTO `heart_pressure` VALUES ('126', '200', '100', '1', '2017-09-08 17:14:22');
INSERT INTO `heart_pressure` VALUES ('127', '200', '100', '1', '2017-09-08 17:31:01');
INSERT INTO `heart_pressure` VALUES ('128', '200', '100', '1', '2017-09-08 17:36:42');
INSERT INTO `heart_pressure` VALUES ('129', '200', '100', '1', '2017-09-08 17:45:01');
INSERT INTO `heart_pressure` VALUES ('130', '200', '100', '1', '2017-09-09 10:20:04');
INSERT INTO `heart_pressure` VALUES ('131', '200', '100', '1', '2017-09-09 12:12:00');
INSERT INTO `heart_pressure` VALUES ('132', '200', '100', '1', '2017-09-09 14:10:14');
INSERT INTO `heart_pressure` VALUES ('133', '200', '100', '1', '2017-09-09 14:11:40');
INSERT INTO `heart_pressure` VALUES ('134', '200', '100', '1', '2017-09-09 14:17:46');
INSERT INTO `heart_pressure` VALUES ('135', '200', '100', '1', '2017-09-11 09:43:20');
INSERT INTO `heart_pressure` VALUES ('136', '200', '100', '1', '2017-09-11 09:44:50');
INSERT INTO `heart_pressure` VALUES ('137', '200', '100', '1', '2017-09-11 09:46:03');
INSERT INTO `heart_pressure` VALUES ('138', '200', '100', '1', '2017-09-11 09:50:39');
INSERT INTO `heart_pressure` VALUES ('139', '200', '100', '1', '2017-09-11 09:58:25');
INSERT INTO `heart_pressure` VALUES ('140', '200', '100', '1', '2017-09-11 10:01:09');
INSERT INTO `heart_pressure` VALUES ('141', '200', '100', '1', '2017-09-11 10:20:27');
INSERT INTO `heart_pressure` VALUES ('142', '200', '100', '1', '2017-09-11 10:25:54');
INSERT INTO `heart_pressure` VALUES ('143', '200', '100', '1', '2017-09-11 14:09:04');
INSERT INTO `heart_pressure` VALUES ('144', '200', '100', '1', '2017-09-11 14:15:58');
INSERT INTO `heart_pressure` VALUES ('145', '200', '100', '1', '2017-09-11 14:24:36');
INSERT INTO `heart_pressure` VALUES ('146', '200', '100', '1', '2017-09-11 14:42:38');
INSERT INTO `heart_pressure` VALUES ('147', '200', '100', '1', '2017-09-11 14:43:30');
INSERT INTO `heart_pressure` VALUES ('148', '200', '100', '1', '2017-09-11 14:45:51');
INSERT INTO `heart_pressure` VALUES ('149', '200', '100', '1', '2017-09-11 15:05:02');
INSERT INTO `heart_pressure` VALUES ('150', '200', '100', '1', '2017-09-11 15:32:28');
INSERT INTO `heart_pressure` VALUES ('151', '200', '100', '1', '2017-09-11 15:54:15');
INSERT INTO `heart_pressure` VALUES ('152', '200', '100', '1', '2017-09-11 16:37:23');
INSERT INTO `heart_pressure` VALUES ('153', '200', '100', '1', '2017-09-11 16:42:38');
INSERT INTO `heart_pressure` VALUES ('154', '200', '100', '1', '2017-09-11 16:48:54');
INSERT INTO `heart_pressure` VALUES ('155', '200', '100', '1', '2017-09-11 17:02:30');
INSERT INTO `heart_pressure` VALUES ('156', '200', '100', '1', '2017-09-11 17:21:07');
INSERT INTO `heart_pressure` VALUES ('157', '200', '100', '1', '2017-09-11 17:33:42');
INSERT INTO `heart_pressure` VALUES ('158', '200', '100', '1', '2017-09-11 17:36:41');
INSERT INTO `heart_pressure` VALUES ('159', '200', '100', '1', '2017-09-11 17:58:50');
INSERT INTO `heart_pressure` VALUES ('160', '200', '100', '1', '2017-09-11 18:07:23');
INSERT INTO `heart_pressure` VALUES ('161', '200', '100', '1', '2017-09-11 18:26:13');
INSERT INTO `heart_pressure` VALUES ('162', '200', '100', '1', '2017-09-11 19:35:47');
INSERT INTO `heart_pressure` VALUES ('163', '200', '100', '1', '2017-09-11 20:01:38');
INSERT INTO `heart_pressure` VALUES ('164', '200', '100', '1', '2017-09-11 20:08:53');
INSERT INTO `heart_pressure` VALUES ('165', '200', '100', '1', '2017-09-11 20:39:12');
INSERT INTO `heart_pressure` VALUES ('166', '200', '100', '1', '2017-09-11 21:11:34');
INSERT INTO `heart_pressure` VALUES ('167', '200', '100', '1', '2017-09-11 21:39:15');
INSERT INTO `heart_pressure` VALUES ('168', '200', '100', '1', '2017-09-12 09:28:13');
INSERT INTO `heart_pressure` VALUES ('169', '200', '100', '1', '2017-09-12 09:35:24');
INSERT INTO `heart_pressure` VALUES ('170', '200', '100', '1', '2017-09-12 09:40:45');
INSERT INTO `heart_pressure` VALUES ('171', '200', '100', '1', '2017-09-12 10:08:04');
INSERT INTO `heart_pressure` VALUES ('172', '200', '100', '1', '2017-09-12 10:12:05');
INSERT INTO `heart_pressure` VALUES ('173', '200', '100', '1', '2017-09-12 10:24:46');
INSERT INTO `heart_pressure` VALUES ('174', '200', '100', '1', '2017-09-12 10:45:16');
INSERT INTO `heart_pressure` VALUES ('175', '200', '100', '1', '2017-09-12 16:13:02');
INSERT INTO `heart_pressure` VALUES ('176', '200', '100', '1', '2017-09-12 16:47:40');
INSERT INTO `heart_pressure` VALUES ('177', '200', '100', '1', '2017-09-12 17:07:42');
INSERT INTO `heart_pressure` VALUES ('178', '200', '100', '1', '2017-09-12 17:15:01');
INSERT INTO `heart_pressure` VALUES ('179', '200', '100', '1', '2017-09-12 17:29:49');
INSERT INTO `heart_pressure` VALUES ('180', '200', '100', '1', '2017-09-12 17:35:16');
INSERT INTO `heart_pressure` VALUES ('181', '200', '100', '1', '2017-09-12 17:52:46');
INSERT INTO `heart_pressure` VALUES ('182', '200', '100', '1', '2017-09-12 19:20:58');
INSERT INTO `heart_pressure` VALUES ('183', '200', '100', '1', '2017-09-12 19:37:18');
INSERT INTO `heart_pressure` VALUES ('184', '200', '100', '1', '2017-09-12 19:44:00');
INSERT INTO `heart_pressure` VALUES ('185', '200', '100', '1', '2017-09-12 19:53:12');
INSERT INTO `heart_pressure` VALUES ('186', '200', '100', '1', '2017-09-12 19:57:54');
INSERT INTO `heart_pressure` VALUES ('187', '200', '100', '1', '2017-09-12 20:01:55');
INSERT INTO `heart_pressure` VALUES ('188', '200', '100', '1', '2017-09-12 20:33:04');
INSERT INTO `heart_pressure` VALUES ('189', '200', '100', '1', '2017-09-12 20:36:20');
INSERT INTO `heart_pressure` VALUES ('190', '200', '100', '1', '2017-09-12 20:37:10');
INSERT INTO `heart_pressure` VALUES ('191', '200', '100', '1', '2017-09-12 20:43:16');
INSERT INTO `heart_pressure` VALUES ('192', '200', '100', '1', '2017-09-12 20:50:35');
INSERT INTO `heart_pressure` VALUES ('193', '200', '100', '1', '2017-09-12 21:03:57');
INSERT INTO `heart_pressure` VALUES ('194', '200', '100', '1', '2017-09-12 21:08:03');
INSERT INTO `heart_pressure` VALUES ('195', '200', '100', '1', '2017-09-13 09:23:56');
INSERT INTO `heart_pressure` VALUES ('196', '200', '100', '1', '2017-09-13 09:25:38');
INSERT INTO `heart_pressure` VALUES ('197', '200', '100', '1', '2017-09-13 09:29:42');
INSERT INTO `heart_pressure` VALUES ('198', '200', '100', '1', '2017-09-13 10:05:39');
INSERT INTO `heart_pressure` VALUES ('199', '200', '100', '1', '2017-09-13 10:09:17');
INSERT INTO `heart_pressure` VALUES ('200', '200', '100', '1', '2017-09-13 10:59:20');
INSERT INTO `heart_pressure` VALUES ('201', '200', '100', '1', '2017-09-13 11:19:22');
INSERT INTO `heart_pressure` VALUES ('202', '200', '100', '1', '2017-09-13 11:31:42');
INSERT INTO `heart_pressure` VALUES ('203', '200', '100', '1', '2017-09-13 11:38:53');
INSERT INTO `heart_pressure` VALUES ('204', '200', '100', '1', '2017-09-13 15:13:57');
INSERT INTO `heart_pressure` VALUES ('205', '200', '100', '1', '2017-09-13 15:46:44');
INSERT INTO `heart_pressure` VALUES ('206', '125', '72', '3', '2017-09-13 21:01:09');
INSERT INTO `heart_pressure` VALUES ('207', '200', '100', '1', '2017-09-14 09:57:50');
INSERT INTO `heart_pressure` VALUES ('208', '200', '100', '1', '2017-09-14 15:45:50');
INSERT INTO `heart_pressure` VALUES ('209', '200', '100', '1', '2017-09-14 15:49:48');
INSERT INTO `heart_pressure` VALUES ('210', '200', '100', '1', '2017-09-14 15:56:08');
INSERT INTO `heart_pressure` VALUES ('211', '200', '100', '1', '2017-09-14 15:58:27');
INSERT INTO `heart_pressure` VALUES ('212', '200', '100', '1', '2017-09-14 16:40:10');
INSERT INTO `heart_pressure` VALUES ('213', '200', '100', '1', '2017-09-14 17:05:03');
INSERT INTO `heart_pressure` VALUES ('214', '200', '100', '1', '2017-09-14 17:30:51');
INSERT INTO `heart_pressure` VALUES ('215', '200', '100', '1', '2017-09-14 17:52:44');
INSERT INTO `heart_pressure` VALUES ('216', '200', '100', '1', '2017-09-15 09:54:58');
INSERT INTO `heart_pressure` VALUES ('217', '200', '100', '1', '2017-09-15 10:10:14');
INSERT INTO `heart_pressure` VALUES ('218', '200', '100', '1', '2017-09-15 10:30:49');
INSERT INTO `heart_pressure` VALUES ('219', '200', '100', '1', '2017-09-15 10:59:28');
INSERT INTO `heart_pressure` VALUES ('220', '200', '100', '1', '2017-09-15 11:59:17');
INSERT INTO `heart_pressure` VALUES ('221', '200', '100', '1', '2017-09-15 13:41:31');
INSERT INTO `heart_pressure` VALUES ('222', '200', '100', '1', '2017-09-15 13:48:58');
INSERT INTO `heart_pressure` VALUES ('223', '200', '100', '1', '2017-09-15 14:01:11');
INSERT INTO `heart_pressure` VALUES ('224', '200', '100', '1', '2017-09-15 14:22:00');
INSERT INTO `heart_pressure` VALUES ('225', '200', '100', '1', '2017-09-15 14:40:01');
INSERT INTO `heart_pressure` VALUES ('226', '200', '100', '1', '2017-09-15 14:46:56');
INSERT INTO `heart_pressure` VALUES ('227', '200', '100', '1', '2017-09-15 15:49:50');
INSERT INTO `heart_pressure` VALUES ('228', '200', '100', '1', '2017-09-15 17:35:52');
INSERT INTO `heart_pressure` VALUES ('229', '200', '100', '1', '2017-09-15 17:44:11');
INSERT INTO `heart_pressure` VALUES ('230', '200', '100', '1', '2017-09-15 18:07:44');
INSERT INTO `heart_pressure` VALUES ('231', '200', '100', '1', '2017-09-15 23:37:59');
INSERT INTO `heart_pressure` VALUES ('232', '200', '100', '1', '2017-09-15 19:52:13');
INSERT INTO `heart_pressure` VALUES ('233', '200', '100', '1', '2017-09-15 16:28:17');
INSERT INTO `heart_pressure` VALUES ('234', '200', '100', '1', '2017-09-16 00:37:51');
INSERT INTO `heart_pressure` VALUES ('235', '200', '100', '1', '2017-09-16 01:02:00');
INSERT INTO `heart_pressure` VALUES ('236', '200', '100', '1', '2017-09-16 10:43:45');
INSERT INTO `heart_pressure` VALUES ('237', '200', '100', '1', '2017-09-16 11:06:30');
INSERT INTO `heart_pressure` VALUES ('238', '200', '100', '1', '2017-09-16 11:10:28');
INSERT INTO `heart_pressure` VALUES ('239', '200', '100', '1', '2017-09-16 11:19:06');
INSERT INTO `heart_pressure` VALUES ('240', '200', '100', '1', '2017-09-16 11:24:49');
INSERT INTO `heart_pressure` VALUES ('241', '200', '100', '1', '2017-09-16 11:31:47');
INSERT INTO `heart_pressure` VALUES ('242', '200', '100', '1', '2017-09-16 11:40:56');
INSERT INTO `heart_pressure` VALUES ('243', '120', '80', '3', '2017-09-17 15:19:32');
INSERT INTO `heart_pressure` VALUES ('244', '120', '80', '3', '2017-09-17 15:22:35');
INSERT INTO `heart_pressure` VALUES ('245', '120', '80', '3', '2017-09-17 15:23:01');
INSERT INTO `heart_pressure` VALUES ('246', '120', '80', '3', '2017-09-17 15:24:04');
INSERT INTO `heart_pressure` VALUES ('247', '120', '80', '3', '2017-09-17 15:25:00');
INSERT INTO `heart_pressure` VALUES ('248', '120', '80', '3', '2017-09-17 15:25:23');
INSERT INTO `heart_pressure` VALUES ('249', '120', '80', '3', '2017-09-17 15:25:25');
INSERT INTO `heart_pressure` VALUES ('250', '120', '80', '3', '2017-09-17 15:27:27');
INSERT INTO `heart_pressure` VALUES ('251', '120', '90', '3', '2017-09-17 15:27:40');
INSERT INTO `heart_pressure` VALUES ('252', '120', '98', '3', '2017-09-17 15:27:48');
INSERT INTO `heart_pressure` VALUES ('253', '120', '98', '3', '2017-09-17 15:28:23');
INSERT INTO `heart_pressure` VALUES ('254', '120', '98', '3', '2017-09-17 15:28:27');
INSERT INTO `heart_pressure` VALUES ('255', '120', '98', '3', '2017-09-17 15:28:45');
INSERT INTO `heart_pressure` VALUES ('256', '200', '100', '1', '2017-09-19 17:15:12');
INSERT INTO `heart_pressure` VALUES ('257', '200', '100', '1', '2017-09-20 11:03:43');
INSERT INTO `heart_pressure` VALUES ('258', '200', '100', '1', '2017-09-20 11:13:37');
INSERT INTO `heart_pressure` VALUES ('259', '200', '100', '1', '2017-09-21 09:17:59');
INSERT INTO `heart_pressure` VALUES ('260', '200', '100', '1', '2017-09-21 09:22:58');
INSERT INTO `heart_pressure` VALUES ('261', '200', '100', '1', '2017-09-21 09:32:55');
INSERT INTO `heart_pressure` VALUES ('262', '200', '100', '1', '2017-09-21 09:41:02');
INSERT INTO `heart_pressure` VALUES ('263', '200', '100', '1', '2017-09-21 09:58:02');
INSERT INTO `heart_pressure` VALUES ('264', '200', '100', '1', '2017-09-21 10:17:26');
INSERT INTO `heart_pressure` VALUES ('265', '200', '100', '1', '2017-09-21 10:21:27');
INSERT INTO `heart_pressure` VALUES ('266', '200', '100', '1', '2017-09-21 10:52:06');
INSERT INTO `heart_pressure` VALUES ('267', '200', '100', '1', '2017-09-21 11:00:01');
INSERT INTO `heart_pressure` VALUES ('268', '200', '100', '1', '2017-09-21 11:29:03');
INSERT INTO `heart_pressure` VALUES ('269', '200', '100', '1', '2017-09-21 13:53:15');
INSERT INTO `heart_pressure` VALUES ('270', '200', '100', '1', '2017-09-21 14:04:29');
INSERT INTO `heart_pressure` VALUES ('271', '200', '100', '1', '2017-09-21 15:57:50');
INSERT INTO `heart_pressure` VALUES ('272', '200', '100', '1', '2017-09-21 16:05:39');
INSERT INTO `heart_pressure` VALUES ('273', '200', '100', '1', '2017-09-21 16:23:31');
INSERT INTO `heart_pressure` VALUES ('274', '200', '100', '1', '2017-09-21 16:25:59');
INSERT INTO `heart_pressure` VALUES ('275', '200', '100', '1', '2017-09-21 16:38:24');
INSERT INTO `heart_pressure` VALUES ('276', '200', '100', '1', '2017-09-21 18:12:26');
INSERT INTO `heart_pressure` VALUES ('277', '200', '100', '1', '2017-09-21 19:30:17');
INSERT INTO `heart_pressure` VALUES ('278', '200', '100', '1', '2017-09-21 19:49:36');
INSERT INTO `heart_pressure` VALUES ('279', '200', '100', '1', '2017-09-21 19:53:46');
INSERT INTO `heart_pressure` VALUES ('280', '200', '100', '1', '2017-09-21 20:12:49');
INSERT INTO `heart_pressure` VALUES ('281', '200', '100', '1', '2017-09-21 20:16:48');

-- ----------------------------
-- Table structure for `heart_rate`
-- ----------------------------
DROP TABLE IF EXISTS `heart_rate`;
CREATE TABLE `heart_rate` (
  `hr_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `heart_rate` int(10) NOT NULL,
  `user_id` int(11) NOT NULL,
  `upload_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of heart_rate
-- ----------------------------
INSERT INTO `heart_rate` VALUES ('1', '87', '1', '2017-08-27 18:25:59');
INSERT INTO `heart_rate` VALUES ('2', '67', '1', '2017-08-27 18:26:46');
INSERT INTO `heart_rate` VALUES ('3', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('4', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('5', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('6', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('7', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('8', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('9', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('10', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('11', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('12', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('13', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('14', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('15', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('16', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('17', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('18', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('19', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('20', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('21', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('22', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('23', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('24', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('25', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('26', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('27', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('28', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('29', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('30', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('31', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('32', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('33', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('34', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('35', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('36', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('37', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('38', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('39', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('40', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('41', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('42', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('43', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('44', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('45', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('46', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('47', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('48', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('49', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('50', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('51', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('52', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('53', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('54', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('55', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('56', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('57', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('58', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('59', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('60', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('61', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('62', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('63', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('64', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('65', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('66', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('67', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('68', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('69', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('70', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('71', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('72', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('73', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('74', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('75', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('76', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('77', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('78', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('79', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('80', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('81', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('82', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('83', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('84', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('85', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('86', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('87', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('88', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('89', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('90', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('91', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('92', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('93', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('94', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('95', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('96', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('97', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('98', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('99', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('100', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('101', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('102', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('103', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('104', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('105', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('106', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('107', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('108', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('109', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('110', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('111', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('112', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('113', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('114', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('115', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('116', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('117', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('118', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('119', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('120', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('121', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('122', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('123', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('124', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('125', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('126', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('127', '79', '1', '2017-09-03 17:29:34');
INSERT INTO `heart_rate` VALUES ('128', '79', '1', '2017-09-03 17:31:56');
INSERT INTO `heart_rate` VALUES ('129', '20', '1', '2017-09-03 20:05:32');
INSERT INTO `heart_rate` VALUES ('130', '110', '1', '2017-09-03 20:05:50');
INSERT INTO `heart_rate` VALUES ('131', '78', '1', '2017-09-03 20:06:25');
INSERT INTO `heart_rate` VALUES ('132', '78', '1', '2017-09-03 20:24:16');
INSERT INTO `heart_rate` VALUES ('133', '79', '1', '2017-09-03 21:06:29');
INSERT INTO `heart_rate` VALUES ('134', '79', '1', '2017-09-03 23:42:46');
INSERT INTO `heart_rate` VALUES ('135', '79', '1', '2017-09-03 23:43:08');
INSERT INTO `heart_rate` VALUES ('136', '76', '1', '2017-09-03 23:56:37');
INSERT INTO `heart_rate` VALUES ('137', '73', '1', '2017-09-03 23:57:12');
INSERT INTO `heart_rate` VALUES ('138', '75', '1', '2017-09-03 23:57:49');
INSERT INTO `heart_rate` VALUES ('139', '75', '1', '2017-09-03 23:58:10');
INSERT INTO `heart_rate` VALUES ('140', '75', '1', '2017-09-04 00:02:03');
INSERT INTO `heart_rate` VALUES ('141', '75', '1', '2017-09-04 00:10:07');
INSERT INTO `heart_rate` VALUES ('142', '75', '1', '2017-09-04 00:16:21');
INSERT INTO `heart_rate` VALUES ('143', '75', '1', '2017-09-04 00:18:50');
INSERT INTO `heart_rate` VALUES ('144', '75', '1', '2017-09-04 00:18:56');
INSERT INTO `heart_rate` VALUES ('145', '75', '1', '2017-09-04 00:26:53');
INSERT INTO `heart_rate` VALUES ('146', '75', '1', '2017-09-04 06:37:17');
INSERT INTO `heart_rate` VALUES ('147', '75', '1', '2017-09-04 06:40:06');
INSERT INTO `heart_rate` VALUES ('148', '75', '1', '2017-09-04 06:54:04');
INSERT INTO `heart_rate` VALUES ('149', '75', '1', '2017-09-04 07:02:45');
INSERT INTO `heart_rate` VALUES ('150', '75', '1', '2017-09-04 07:03:19');
INSERT INTO `heart_rate` VALUES ('151', '75', '1', '2017-09-04 07:05:23');
INSERT INTO `heart_rate` VALUES ('152', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('153', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('154', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('155', '75', '1', '2017-09-04 11:08:47');
INSERT INTO `heart_rate` VALUES ('156', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('157', '75', '1', '2017-09-04 11:22:59');
INSERT INTO `heart_rate` VALUES ('158', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('159', '75', '1', '2017-09-04 11:24:43');
INSERT INTO `heart_rate` VALUES ('160', '75', '1', '2017-09-04 11:24:51');
INSERT INTO `heart_rate` VALUES ('161', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('162', '75', '1', '2017-09-04 12:20:53');
INSERT INTO `heart_rate` VALUES ('163', '75', '1', '2017-09-04 12:21:04');
INSERT INTO `heart_rate` VALUES ('164', '75', '1', '2017-09-04 12:21:27');
INSERT INTO `heart_rate` VALUES ('165', '56', '1', '2017-09-04 12:57:04');
INSERT INTO `heart_rate` VALUES ('166', '56', '1', '2017-09-04 12:59:37');
INSERT INTO `heart_rate` VALUES ('167', '56', '1', '2017-09-04 13:10:09');
INSERT INTO `heart_rate` VALUES ('168', '67', '1', '2017-09-04 13:23:48');
INSERT INTO `heart_rate` VALUES ('169', '0', '1', '2017-09-04 13:28:04');
INSERT INTO `heart_rate` VALUES ('170', '0', '1', '2017-09-04 13:29:04');
INSERT INTO `heart_rate` VALUES ('171', '0', '1', '2017-09-04 13:29:44');
INSERT INTO `heart_rate` VALUES ('172', '0', '1', '2017-09-04 13:29:48');
INSERT INTO `heart_rate` VALUES ('173', '0', '1', '2017-09-04 13:30:37');
INSERT INTO `heart_rate` VALUES ('174', '56', '1', '2017-09-04 13:31:07');
INSERT INTO `heart_rate` VALUES ('175', '56', '1', '2017-09-04 13:31:25');
INSERT INTO `heart_rate` VALUES ('176', '56', '1', '2017-09-04 13:31:46');
INSERT INTO `heart_rate` VALUES ('177', '0', '1', '2017-09-04 13:32:38');
INSERT INTO `heart_rate` VALUES ('178', '0', '1', '2017-09-04 13:33:57');
INSERT INTO `heart_rate` VALUES ('179', '68', '1', '2017-09-04 13:35:34');
INSERT INTO `heart_rate` VALUES ('180', '68', '1', '2017-09-04 13:35:55');
INSERT INTO `heart_rate` VALUES ('181', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('182', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('183', '100', '1', '1970-01-18 16:58:43');
INSERT INTO `heart_rate` VALUES ('184', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('185', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('186', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('187', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('188', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('189', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('190', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('191', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('192', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('193', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('194', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('195', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('196', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('197', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('198', '87', '1', '2017-09-04 20:35:06');
INSERT INTO `heart_rate` VALUES ('199', '81', '1', '2017-09-04 20:36:40');
INSERT INTO `heart_rate` VALUES ('200', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('201', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('202', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('203', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('204', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('205', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('206', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('207', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('208', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('209', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('210', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('211', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('212', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('213', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('214', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('215', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('216', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('217', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('218', '100', '1', '2017-07-27 10:48:20');
INSERT INTO `heart_rate` VALUES ('219', '100', '1', '2017-09-05 17:09:26');
INSERT INTO `heart_rate` VALUES ('220', '100', '1', '2017-09-05 20:49:02');
INSERT INTO `heart_rate` VALUES ('221', '100', '1', '2017-09-05 20:53:41');
INSERT INTO `heart_rate` VALUES ('222', '100', '1', '2017-09-05 21:07:15');
INSERT INTO `heart_rate` VALUES ('223', '100', '1', '2017-09-05 21:23:19');
INSERT INTO `heart_rate` VALUES ('224', '100', '1', '2017-09-05 21:30:54');
INSERT INTO `heart_rate` VALUES ('225', '100', '1', '2017-09-05 22:28:57');
INSERT INTO `heart_rate` VALUES ('226', '100', '1', '2017-09-05 22:31:36');
INSERT INTO `heart_rate` VALUES ('227', '100', '1', '2017-09-05 22:33:37');
INSERT INTO `heart_rate` VALUES ('228', '100', '1', '2017-09-05 22:42:28');
INSERT INTO `heart_rate` VALUES ('229', '100', '1', '2017-09-05 22:49:20');
INSERT INTO `heart_rate` VALUES ('230', '100', '1', '2017-09-05 22:57:45');
INSERT INTO `heart_rate` VALUES ('231', '100', '1', '2017-09-06 09:14:14');
INSERT INTO `heart_rate` VALUES ('232', '100', '1', '2017-09-06 10:01:23');
INSERT INTO `heart_rate` VALUES ('233', '100', '1', '2017-09-06 10:06:15');
INSERT INTO `heart_rate` VALUES ('234', '100', '1', '2017-09-06 10:21:35');
INSERT INTO `heart_rate` VALUES ('235', '100', '1', '2017-09-06 10:34:20');
INSERT INTO `heart_rate` VALUES ('236', '100', '1', '2017-09-06 10:45:00');
INSERT INTO `heart_rate` VALUES ('237', '100', '1', '2017-09-06 10:47:29');
INSERT INTO `heart_rate` VALUES ('238', '100', '1', '2017-09-06 10:48:33');
INSERT INTO `heart_rate` VALUES ('239', '100', '1', '2017-09-06 11:00:05');
INSERT INTO `heart_rate` VALUES ('240', '100', '1', '2017-09-06 11:21:58');
INSERT INTO `heart_rate` VALUES ('241', '100', '1', '2017-09-06 11:50:33');
INSERT INTO `heart_rate` VALUES ('242', '100', '1', '2017-09-06 13:49:54');
INSERT INTO `heart_rate` VALUES ('243', '100', '1', '2017-09-06 13:52:08');
INSERT INTO `heart_rate` VALUES ('244', '100', '1', '2017-09-06 15:34:57');
INSERT INTO `heart_rate` VALUES ('245', '100', '1', '2017-09-06 15:42:01');
INSERT INTO `heart_rate` VALUES ('246', '100', '1', '2017-09-06 15:47:59');
INSERT INTO `heart_rate` VALUES ('247', '100', '1', '2017-09-06 15:52:51');
INSERT INTO `heart_rate` VALUES ('248', '100', '1', '2017-09-06 15:54:09');
INSERT INTO `heart_rate` VALUES ('249', '100', '1', '2017-09-06 15:56:53');
INSERT INTO `heart_rate` VALUES ('250', '100', '1', '2017-09-06 15:59:06');
INSERT INTO `heart_rate` VALUES ('251', '100', '1', '2017-09-06 16:02:27');
INSERT INTO `heart_rate` VALUES ('252', '100', '1', '2017-09-06 16:07:03');
INSERT INTO `heart_rate` VALUES ('253', '100', '1', '2017-09-06 16:08:00');
INSERT INTO `heart_rate` VALUES ('254', '100', '1', '2017-09-06 16:09:05');
INSERT INTO `heart_rate` VALUES ('255', '100', '1', '2017-09-06 16:21:51');
INSERT INTO `heart_rate` VALUES ('256', '100', '1', '2017-09-06 16:23:36');
INSERT INTO `heart_rate` VALUES ('257', '100', '1', '2017-09-06 16:36:25');
INSERT INTO `heart_rate` VALUES ('258', '100', '1', '2017-09-06 16:44:22');
INSERT INTO `heart_rate` VALUES ('259', '100', '1', '2017-09-06 16:45:56');
INSERT INTO `heart_rate` VALUES ('260', '100', '1', '2017-09-06 17:00:31');
INSERT INTO `heart_rate` VALUES ('261', '100', '1', '2017-09-06 17:41:33');
INSERT INTO `heart_rate` VALUES ('262', '100', '1', '2017-09-06 18:01:36');
INSERT INTO `heart_rate` VALUES ('263', '100', '1', '2017-09-06 19:53:43');
INSERT INTO `heart_rate` VALUES ('264', '100', '1', '2017-09-06 19:55:21');
INSERT INTO `heart_rate` VALUES ('265', '100', '1', '2017-09-06 21:12:19');
INSERT INTO `heart_rate` VALUES ('266', '100', '4', '2017-09-06 21:20:44');
INSERT INTO `heart_rate` VALUES ('267', '71', '3', '2017-09-06 22:22:01');
INSERT INTO `heart_rate` VALUES ('268', '71', '2', '2017-09-06 22:25:09');
INSERT INTO `heart_rate` VALUES ('269', '100', '1', '2017-09-07 09:41:35');
INSERT INTO `heart_rate` VALUES ('270', '100', '1', '2017-09-07 09:52:33');
INSERT INTO `heart_rate` VALUES ('271', '100', '1', '2017-09-07 09:54:08');
INSERT INTO `heart_rate` VALUES ('272', '100', '1', '2017-09-07 10:04:31');
INSERT INTO `heart_rate` VALUES ('273', '80', '4', '2017-09-07 10:05:36');
INSERT INTO `heart_rate` VALUES ('274', '78', '4', '2017-09-07 10:37:32');
INSERT INTO `heart_rate` VALUES ('275', '100', '1', '2017-09-07 11:41:58');
INSERT INTO `heart_rate` VALUES ('276', '100', '1', '2017-09-07 11:58:31');
INSERT INTO `heart_rate` VALUES ('277', '100', '1', '2017-09-07 13:45:20');
INSERT INTO `heart_rate` VALUES ('278', '100', '1', '2017-09-07 13:49:37');
INSERT INTO `heart_rate` VALUES ('279', '100', '1', '2017-09-07 13:51:39');
INSERT INTO `heart_rate` VALUES ('280', '100', '1', '2017-09-07 13:53:03');
INSERT INTO `heart_rate` VALUES ('281', '100', '1', '2017-09-07 15:15:01');
INSERT INTO `heart_rate` VALUES ('282', '100', '1', '2017-09-07 15:16:47');
INSERT INTO `heart_rate` VALUES ('283', '100', '1', '2017-09-07 15:22:07');
INSERT INTO `heart_rate` VALUES ('284', '100', '1', '2017-09-07 15:50:40');
INSERT INTO `heart_rate` VALUES ('285', '100', '1', '2017-09-07 16:13:16');
INSERT INTO `heart_rate` VALUES ('286', '100', '1', '2017-09-07 16:14:34');
INSERT INTO `heart_rate` VALUES ('287', '100', '1', '2017-09-07 16:23:14');
INSERT INTO `heart_rate` VALUES ('288', '100', '1', '2017-09-07 16:24:47');
INSERT INTO `heart_rate` VALUES ('289', '100', '1', '2017-09-07 16:39:19');
INSERT INTO `heart_rate` VALUES ('290', '100', '1', '2017-09-07 17:27:37');
INSERT INTO `heart_rate` VALUES ('291', '100', '1', '2017-09-08 10:05:42');
INSERT INTO `heart_rate` VALUES ('292', '100', '1', '2017-09-08 10:45:26');
INSERT INTO `heart_rate` VALUES ('293', '100', '1', '2017-09-08 10:59:41');
INSERT INTO `heart_rate` VALUES ('294', '100', '1', '2017-09-08 11:05:02');
INSERT INTO `heart_rate` VALUES ('295', '100', '1', '2017-09-08 11:57:09');
INSERT INTO `heart_rate` VALUES ('296', '100', '1', '2017-09-08 12:25:00');
INSERT INTO `heart_rate` VALUES ('297', '100', '1', '2017-09-08 15:00:48');
INSERT INTO `heart_rate` VALUES ('298', '100', '1', '2017-09-08 15:19:25');
INSERT INTO `heart_rate` VALUES ('299', '100', '1', '2017-09-08 15:26:02');
INSERT INTO `heart_rate` VALUES ('300', '100', '1', '2017-09-08 15:40:10');
INSERT INTO `heart_rate` VALUES ('301', '100', '1', '2017-09-08 15:49:19');
INSERT INTO `heart_rate` VALUES ('302', '100', '1', '2017-09-08 16:15:50');
INSERT INTO `heart_rate` VALUES ('303', '100', '1', '2017-09-08 16:33:20');
INSERT INTO `heart_rate` VALUES ('304', '100', '1', '2017-09-08 16:44:06');
INSERT INTO `heart_rate` VALUES ('305', '100', '1', '2017-09-08 16:57:50');
INSERT INTO `heart_rate` VALUES ('306', '100', '1', '2017-09-08 16:58:38');
INSERT INTO `heart_rate` VALUES ('307', '100', '1', '2017-09-08 17:14:22');
INSERT INTO `heart_rate` VALUES ('308', '100', '1', '2017-09-08 17:31:01');
INSERT INTO `heart_rate` VALUES ('309', '100', '1', '2017-09-08 17:36:42');
INSERT INTO `heart_rate` VALUES ('310', '100', '1', '2017-09-08 17:45:01');
INSERT INTO `heart_rate` VALUES ('311', '100', '1', '2017-09-09 10:20:04');
INSERT INTO `heart_rate` VALUES ('312', '100', '1', '2017-09-09 12:12:00');
INSERT INTO `heart_rate` VALUES ('313', '100', '1', '2017-09-09 14:10:14');
INSERT INTO `heart_rate` VALUES ('314', '100', '1', '2017-09-09 14:11:40');
INSERT INTO `heart_rate` VALUES ('315', '100', '1', '2017-09-09 14:17:46');
INSERT INTO `heart_rate` VALUES ('316', '100', '1', '2017-09-11 09:43:20');
INSERT INTO `heart_rate` VALUES ('317', '100', '1', '2017-09-11 09:44:50');
INSERT INTO `heart_rate` VALUES ('318', '100', '1', '2017-09-11 09:46:03');
INSERT INTO `heart_rate` VALUES ('319', '100', '1', '2017-09-11 09:50:39');
INSERT INTO `heart_rate` VALUES ('320', '100', '1', '2017-09-11 09:58:25');
INSERT INTO `heart_rate` VALUES ('321', '100', '1', '2017-09-11 10:01:09');
INSERT INTO `heart_rate` VALUES ('322', '100', '1', '2017-09-11 10:20:27');
INSERT INTO `heart_rate` VALUES ('323', '100', '1', '2017-09-11 10:25:54');
INSERT INTO `heart_rate` VALUES ('324', '100', '1', '2017-09-11 14:09:04');
INSERT INTO `heart_rate` VALUES ('325', '100', '1', '2017-09-11 14:15:58');
INSERT INTO `heart_rate` VALUES ('326', '100', '1', '2017-09-11 14:24:36');
INSERT INTO `heart_rate` VALUES ('327', '100', '1', '2017-09-11 14:42:38');
INSERT INTO `heart_rate` VALUES ('328', '100', '1', '2017-09-11 14:43:30');
INSERT INTO `heart_rate` VALUES ('329', '100', '1', '2017-09-11 14:45:51');
INSERT INTO `heart_rate` VALUES ('330', '100', '1', '2017-09-11 15:05:02');
INSERT INTO `heart_rate` VALUES ('331', '100', '1', '2017-09-11 15:32:28');
INSERT INTO `heart_rate` VALUES ('332', '100', '1', '2017-09-11 15:54:15');
INSERT INTO `heart_rate` VALUES ('333', '100', '1', '2017-09-11 16:37:23');
INSERT INTO `heart_rate` VALUES ('334', '100', '1', '2017-09-11 16:42:38');
INSERT INTO `heart_rate` VALUES ('335', '100', '1', '2017-09-11 16:48:54');
INSERT INTO `heart_rate` VALUES ('336', '100', '1', '2017-09-11 17:02:30');
INSERT INTO `heart_rate` VALUES ('337', '100', '1', '2017-09-11 17:21:07');
INSERT INTO `heart_rate` VALUES ('338', '100', '1', '2017-09-11 17:33:42');
INSERT INTO `heart_rate` VALUES ('339', '100', '1', '2017-09-11 17:36:41');
INSERT INTO `heart_rate` VALUES ('340', '100', '1', '2017-09-11 17:58:50');
INSERT INTO `heart_rate` VALUES ('341', '100', '1', '2017-09-11 18:07:23');
INSERT INTO `heart_rate` VALUES ('342', '100', '1', '2017-09-11 18:26:13');
INSERT INTO `heart_rate` VALUES ('343', '100', '1', '2017-09-11 19:35:47');
INSERT INTO `heart_rate` VALUES ('344', '100', '1', '2017-09-11 20:01:38');
INSERT INTO `heart_rate` VALUES ('345', '100', '1', '2017-09-11 20:08:53');
INSERT INTO `heart_rate` VALUES ('346', '100', '1', '2017-09-11 20:39:12');
INSERT INTO `heart_rate` VALUES ('347', '100', '1', '2017-09-11 21:11:34');
INSERT INTO `heart_rate` VALUES ('348', '100', '1', '2017-09-11 21:39:15');
INSERT INTO `heart_rate` VALUES ('349', '100', '1', '2017-09-12 09:28:13');
INSERT INTO `heart_rate` VALUES ('350', '100', '1', '2017-09-12 09:35:24');
INSERT INTO `heart_rate` VALUES ('351', '100', '1', '2017-09-12 09:40:45');
INSERT INTO `heart_rate` VALUES ('352', '100', '1', '2017-09-12 10:08:04');
INSERT INTO `heart_rate` VALUES ('353', '100', '1', '2017-09-12 10:12:05');
INSERT INTO `heart_rate` VALUES ('354', '100', '1', '2017-09-12 10:24:46');
INSERT INTO `heart_rate` VALUES ('355', '100', '1', '2017-09-12 10:45:16');
INSERT INTO `heart_rate` VALUES ('356', '100', '1', '2017-09-12 16:13:02');
INSERT INTO `heart_rate` VALUES ('357', '100', '1', '2017-09-12 16:47:40');
INSERT INTO `heart_rate` VALUES ('358', '100', '1', '2017-09-12 17:07:42');
INSERT INTO `heart_rate` VALUES ('359', '100', '1', '2017-09-12 17:15:01');
INSERT INTO `heart_rate` VALUES ('360', '100', '1', '2017-09-12 17:29:49');
INSERT INTO `heart_rate` VALUES ('361', '100', '1', '2017-09-12 17:35:16');
INSERT INTO `heart_rate` VALUES ('362', '100', '1', '2017-09-12 17:52:46');
INSERT INTO `heart_rate` VALUES ('363', '100', '1', '2017-09-12 19:20:58');
INSERT INTO `heart_rate` VALUES ('364', '100', '1', '2017-09-12 19:37:18');
INSERT INTO `heart_rate` VALUES ('365', '100', '1', '2017-09-12 19:44:00');
INSERT INTO `heart_rate` VALUES ('366', '100', '1', '2017-09-12 19:53:12');
INSERT INTO `heart_rate` VALUES ('367', '100', '1', '2017-09-12 19:57:54');
INSERT INTO `heart_rate` VALUES ('368', '100', '1', '2017-09-12 20:01:55');
INSERT INTO `heart_rate` VALUES ('369', '100', '1', '2017-09-12 20:33:04');
INSERT INTO `heart_rate` VALUES ('370', '100', '1', '2017-09-12 20:36:20');
INSERT INTO `heart_rate` VALUES ('371', '100', '1', '2017-09-12 20:37:10');
INSERT INTO `heart_rate` VALUES ('372', '100', '1', '2017-09-12 20:43:16');
INSERT INTO `heart_rate` VALUES ('373', '100', '1', '2017-09-12 20:50:35');
INSERT INTO `heart_rate` VALUES ('374', '100', '1', '2017-09-12 21:03:57');
INSERT INTO `heart_rate` VALUES ('375', '100', '1', '2017-09-12 21:08:03');
INSERT INTO `heart_rate` VALUES ('376', '100', '1', '2017-09-13 09:23:56');
INSERT INTO `heart_rate` VALUES ('377', '100', '1', '2017-09-13 09:25:38');
INSERT INTO `heart_rate` VALUES ('378', '100', '1', '2017-09-13 09:29:42');
INSERT INTO `heart_rate` VALUES ('379', '100', '1', '2017-09-13 10:05:39');
INSERT INTO `heart_rate` VALUES ('380', '100', '1', '2017-09-13 10:09:17');
INSERT INTO `heart_rate` VALUES ('381', '100', '1', '2017-09-13 10:59:20');
INSERT INTO `heart_rate` VALUES ('382', '100', '1', '2017-09-13 11:19:22');
INSERT INTO `heart_rate` VALUES ('383', '100', '1', '2017-09-13 11:31:42');
INSERT INTO `heart_rate` VALUES ('384', '100', '1', '2017-09-13 11:38:53');
INSERT INTO `heart_rate` VALUES ('385', '100', '1', '2017-09-13 15:13:57');
INSERT INTO `heart_rate` VALUES ('386', '100', '1', '2017-09-13 15:46:44');
INSERT INTO `heart_rate` VALUES ('387', '69', '3', '2017-09-13 21:01:09');

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `l_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `location_type` varchar(4) NOT NULL COMMENT '类型，0：gps， 1：基站',
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `accuracy` int(10) NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '0',
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1', '1', '0', '34.876', '65.9998', '0', '0', '2017-08-27 19:19:29');
INSERT INTO `location` VALUES ('2', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('3', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('4', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('5', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('6', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('7', '1', '0', '12.306', '123.569', '10', '0', '1970-01-18 16:58:43');
INSERT INTO `location` VALUES ('8', '1', '1', '22.500472', '113.9221332', '0', '0', '1970-01-12 07:24:11');
INSERT INTO `location` VALUES ('10', '1', '1', '22.500472', '113.9221332', '0', '0', '1970-01-12 07:24:11');
INSERT INTO `location` VALUES ('12', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 14:18:00');
INSERT INTO `location` VALUES ('14', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 14:35:00');
INSERT INTO `location` VALUES ('16', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 14:35:00');
INSERT INTO `location` VALUES ('19', '1', '1', '22.500472', '113.9221332', '0', '0', '2004-01-01 04:04:00');
INSERT INTO `location` VALUES ('20', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:31:00');
INSERT INTO `location` VALUES ('22', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:32:00');
INSERT INTO `location` VALUES ('24', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:39:00');
INSERT INTO `location` VALUES ('25', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:39:00');
INSERT INTO `location` VALUES ('26', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('27', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('28', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('29', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('30', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('31', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('32', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('33', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('34', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:40:00');
INSERT INTO `location` VALUES ('36', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:41:00');
INSERT INTO `location` VALUES ('38', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:52:00');
INSERT INTO `location` VALUES ('39', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:52:00');
INSERT INTO `location` VALUES ('40', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:52:00');
INSERT INTO `location` VALUES ('42', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:53:00');
INSERT INTO `location` VALUES ('43', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:53:00');
INSERT INTO `location` VALUES ('44', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:53:00');
INSERT INTO `location` VALUES ('45', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:53:00');
INSERT INTO `location` VALUES ('46', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:53:00');
INSERT INTO `location` VALUES ('49', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 15:54:22');
INSERT INTO `location` VALUES ('51', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:00:23');
INSERT INTO `location` VALUES ('52', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:00:28');
INSERT INTO `location` VALUES ('53', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:00:30');
INSERT INTO `location` VALUES ('54', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:00:32');
INSERT INTO `location` VALUES ('55', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:00:34');
INSERT INTO `location` VALUES ('57', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:04:43');
INSERT INTO `location` VALUES ('58', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:04:45');
INSERT INTO `location` VALUES ('60', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:30:08');
INSERT INTO `location` VALUES ('61', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:30:10');
INSERT INTO `location` VALUES ('62', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:30:14');
INSERT INTO `location` VALUES ('65', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:46:53');
INSERT INTO `location` VALUES ('66', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:47:35');
INSERT INTO `location` VALUES ('67', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:47:39');
INSERT INTO `location` VALUES ('69', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:49:11');
INSERT INTO `location` VALUES ('71', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:51:52');
INSERT INTO `location` VALUES ('72', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:51:57');
INSERT INTO `location` VALUES ('74', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:52:56');
INSERT INTO `location` VALUES ('75', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:02');
INSERT INTO `location` VALUES ('76', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:04');
INSERT INTO `location` VALUES ('77', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:06');
INSERT INTO `location` VALUES ('78', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:07');
INSERT INTO `location` VALUES ('79', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:09');
INSERT INTO `location` VALUES ('80', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:11');
INSERT INTO `location` VALUES ('81', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:12');
INSERT INTO `location` VALUES ('82', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:18');
INSERT INTO `location` VALUES ('83', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:19');
INSERT INTO `location` VALUES ('84', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:21');
INSERT INTO `location` VALUES ('85', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:53:29');
INSERT INTO `location` VALUES ('87', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:54:08');
INSERT INTO `location` VALUES ('88', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:54:24');
INSERT INTO `location` VALUES ('90', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:54:47');
INSERT INTO `location` VALUES ('91', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:54:48');
INSERT INTO `location` VALUES ('93', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:55:27');
INSERT INTO `location` VALUES ('95', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:56:11');
INSERT INTO `location` VALUES ('97', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 20:57:22');
INSERT INTO `location` VALUES ('100', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:09:58');
INSERT INTO `location` VALUES ('102', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:15:56');
INSERT INTO `location` VALUES ('104', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:17:23');
INSERT INTO `location` VALUES ('106', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:25:27');
INSERT INTO `location` VALUES ('107', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:26:53');
INSERT INTO `location` VALUES ('109', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:27:50');
INSERT INTO `location` VALUES ('111', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:33:34');
INSERT INTO `location` VALUES ('113', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:34:16');
INSERT INTO `location` VALUES ('114', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:34:18');
INSERT INTO `location` VALUES ('116', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:36:27');
INSERT INTO `location` VALUES ('118', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:45:54');
INSERT INTO `location` VALUES ('120', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:46:19');
INSERT INTO `location` VALUES ('121', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:46:28');
INSERT INTO `location` VALUES ('123', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:47:27');
INSERT INTO `location` VALUES ('126', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:51:26');
INSERT INTO `location` VALUES ('128', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:55:25');
INSERT INTO `location` VALUES ('129', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:55:31');
INSERT INTO `location` VALUES ('130', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:55:46');
INSERT INTO `location` VALUES ('131', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:55:46');
INSERT INTO `location` VALUES ('133', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:57:04');
INSERT INTO `location` VALUES ('134', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:57:09');
INSERT INTO `location` VALUES ('135', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:57:12');
INSERT INTO `location` VALUES ('136', '1', '1', '22.4983228', '113.9216181', '0', '0', '2017-09-04 21:57:24');
INSERT INTO `location` VALUES ('139', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:58:44');
INSERT INTO `location` VALUES ('140', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 21:58:44');
INSERT INTO `location` VALUES ('143', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 22:02:17');
INSERT INTO `location` VALUES ('144', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 22:02:45');
INSERT INTO `location` VALUES ('146', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-04 22:03:16');
INSERT INTO `location` VALUES ('153', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 14:45:14');
INSERT INTO `location` VALUES ('154', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 14:45:25');
INSERT INTO `location` VALUES ('165', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 15:56:24');
INSERT INTO `location` VALUES ('166', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 19:53:11');
INSERT INTO `location` VALUES ('167', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 20:22:00');
INSERT INTO `location` VALUES ('168', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-05 16:44:29');
INSERT INTO `location` VALUES ('169', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:00:07');
INSERT INTO `location` VALUES ('170', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:00:25');
INSERT INTO `location` VALUES ('171', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:00:40');
INSERT INTO `location` VALUES ('172', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:00:57');
INSERT INTO `location` VALUES ('173', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:01:28');
INSERT INTO `location` VALUES ('174', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:01:34');
INSERT INTO `location` VALUES ('175', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:01:45');
INSERT INTO `location` VALUES ('176', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:01:52');
INSERT INTO `location` VALUES ('177', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:03:15');
INSERT INTO `location` VALUES ('178', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:04:22');
INSERT INTO `location` VALUES ('179', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-06 11:04:42');
INSERT INTO `location` VALUES ('180', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:26:23');
INSERT INTO `location` VALUES ('181', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 11:26:31');
INSERT INTO `location` VALUES ('182', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-06 14:18:36');
INSERT INTO `location` VALUES ('183', '1', '1', '22.6244374', '113.8129484', '0', '1', '2017-09-06 21:21:26');
INSERT INTO `location` VALUES ('184', '1', '1', '22.6244374', '113.8129484', '0', '1', '2017-09-06 21:21:48');
INSERT INTO `location` VALUES ('185', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-07 09:28:01');
INSERT INTO `location` VALUES ('186', '1', '1', '22.500472', '113.9221332', '0', '0', '2017-09-07 09:28:05');
INSERT INTO `location` VALUES ('187', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:41:39');
INSERT INTO `location` VALUES ('188', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:41:54');
INSERT INTO `location` VALUES ('189', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:41:57');
INSERT INTO `location` VALUES ('190', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:42:00');
INSERT INTO `location` VALUES ('191', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:42:19');
INSERT INTO `location` VALUES ('192', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:42:19');
INSERT INTO `location` VALUES ('193', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:42:41');
INSERT INTO `location` VALUES ('194', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:43:30');
INSERT INTO `location` VALUES ('195', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:44:14');
INSERT INTO `location` VALUES ('196', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:45:28');
INSERT INTO `location` VALUES ('197', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:47:22');
INSERT INTO `location` VALUES ('198', '1', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 09:48:17');
INSERT INTO `location` VALUES ('199', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:37:47');
INSERT INTO `location` VALUES ('200', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:38:15');
INSERT INTO `location` VALUES ('201', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:39:38');
INSERT INTO `location` VALUES ('202', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:39:42');
INSERT INTO `location` VALUES ('203', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:39:49');
INSERT INTO `location` VALUES ('204', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:40:18');
INSERT INTO `location` VALUES ('205', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:42:17');
INSERT INTO `location` VALUES ('206', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:42:18');
INSERT INTO `location` VALUES ('207', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:42:20');
INSERT INTO `location` VALUES ('208', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:42:20');
INSERT INTO `location` VALUES ('209', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 10:42:56');
INSERT INTO `location` VALUES ('210', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:10:38');
INSERT INTO `location` VALUES ('211', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:23');
INSERT INTO `location` VALUES ('212', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:27');
INSERT INTO `location` VALUES ('213', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:27');
INSERT INTO `location` VALUES ('214', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:28');
INSERT INTO `location` VALUES ('215', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:28');
INSERT INTO `location` VALUES ('216', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:28');
INSERT INTO `location` VALUES ('217', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:29');
INSERT INTO `location` VALUES ('218', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:29');
INSERT INTO `location` VALUES ('219', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:29');
INSERT INTO `location` VALUES ('220', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:30');
INSERT INTO `location` VALUES ('221', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:30');
INSERT INTO `location` VALUES ('222', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:30');
INSERT INTO `location` VALUES ('223', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:31');
INSERT INTO `location` VALUES ('224', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:31');
INSERT INTO `location` VALUES ('225', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 11:11:32');
INSERT INTO `location` VALUES ('226', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 17:46:31');
INSERT INTO `location` VALUES ('227', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 20:11:07');
INSERT INTO `location` VALUES ('228', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 20:42:04');
INSERT INTO `location` VALUES ('229', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:17:01');
INSERT INTO `location` VALUES ('230', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:17:32');
INSERT INTO `location` VALUES ('231', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:17:41');
INSERT INTO `location` VALUES ('232', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:17:51');
INSERT INTO `location` VALUES ('233', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:18:23');
INSERT INTO `location` VALUES ('234', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:18:34');
INSERT INTO `location` VALUES ('235', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:18:41');
INSERT INTO `location` VALUES ('236', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:19:57');
INSERT INTO `location` VALUES ('237', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:20:19');
INSERT INTO `location` VALUES ('238', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:21:41');
INSERT INTO `location` VALUES ('239', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:21:48');
INSERT INTO `location` VALUES ('240', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:21:55');
INSERT INTO `location` VALUES ('241', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:22:00');
INSERT INTO `location` VALUES ('242', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:22:07');
INSERT INTO `location` VALUES ('243', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:22:09');
INSERT INTO `location` VALUES ('244', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:23:55');
INSERT INTO `location` VALUES ('245', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:49:47');
INSERT INTO `location` VALUES ('246', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:50:04');
INSERT INTO `location` VALUES ('247', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:50:34');
INSERT INTO `location` VALUES ('248', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:50:58');
INSERT INTO `location` VALUES ('249', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:01');
INSERT INTO `location` VALUES ('250', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:08');
INSERT INTO `location` VALUES ('251', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:21');
INSERT INTO `location` VALUES ('252', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:24');
INSERT INTO `location` VALUES ('253', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:30');
INSERT INTO `location` VALUES ('254', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:52:58');
INSERT INTO `location` VALUES ('255', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:53:01');
INSERT INTO `location` VALUES ('256', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:53:04');
INSERT INTO `location` VALUES ('257', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:54:19');
INSERT INTO `location` VALUES ('258', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:54:21');
INSERT INTO `location` VALUES ('259', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:54:23');
INSERT INTO `location` VALUES ('260', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:55:59');
INSERT INTO `location` VALUES ('261', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:17');
INSERT INTO `location` VALUES ('262', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:26');
INSERT INTO `location` VALUES ('263', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:30');
INSERT INTO `location` VALUES ('264', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:35');
INSERT INTO `location` VALUES ('265', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:35');
INSERT INTO `location` VALUES ('266', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:42');
INSERT INTO `location` VALUES ('267', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:47');
INSERT INTO `location` VALUES ('268', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:56:54');
INSERT INTO `location` VALUES ('269', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:57:08');
INSERT INTO `location` VALUES ('270', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:57:20');
INSERT INTO `location` VALUES ('271', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:57:20');
INSERT INTO `location` VALUES ('272', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:57:30');
INSERT INTO `location` VALUES ('273', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:57:41');
INSERT INTO `location` VALUES ('274', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 21:58:10');
INSERT INTO `location` VALUES ('275', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:00:58');
INSERT INTO `location` VALUES ('276', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:06:02');
INSERT INTO `location` VALUES ('277', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:06:09');
INSERT INTO `location` VALUES ('278', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:06:13');
INSERT INTO `location` VALUES ('279', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:08:07');
INSERT INTO `location` VALUES ('280', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:08:10');
INSERT INTO `location` VALUES ('281', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:08:28');
INSERT INTO `location` VALUES ('282', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:19:05');
INSERT INTO `location` VALUES ('283', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:38:42');
INSERT INTO `location` VALUES ('284', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-07 22:39:11');
INSERT INTO `location` VALUES ('285', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:38:21');
INSERT INTO `location` VALUES ('286', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:38:56');
INSERT INTO `location` VALUES ('287', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:43:44');
INSERT INTO `location` VALUES ('288', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:43:50');
INSERT INTO `location` VALUES ('289', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:45:17');
INSERT INTO `location` VALUES ('290', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 09:49:53');
INSERT INTO `location` VALUES ('291', '4', '1', '22.500472', '113.9221332', '0', '0', '2017-09-08 11:54:42');
INSERT INTO `location` VALUES ('292', '4', '1', '22.500472', '113.9221332', '0', '1', '2017-09-08 11:57:53');
INSERT INTO `location` VALUES ('293', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-08 15:01:45');
INSERT INTO `location` VALUES ('294', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-08 15:20:18');
INSERT INTO `location` VALUES ('295', '4', '1', '22.4996357', '113.9231193', '0', '1', '2017-09-08 15:41:40');
INSERT INTO `location` VALUES ('296', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:15:17');
INSERT INTO `location` VALUES ('297', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-08 17:37:35');
INSERT INTO `location` VALUES ('298', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-08 17:38:32');
INSERT INTO `location` VALUES ('299', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:46:28');
INSERT INTO `location` VALUES ('300', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:47:32');
INSERT INTO `location` VALUES ('301', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:48:38');
INSERT INTO `location` VALUES ('302', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:50:18');
INSERT INTO `location` VALUES ('303', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:51:20');
INSERT INTO `location` VALUES ('304', '4', '0', '22.502996', '113.916109', '10', '0', '2017-09-08 17:52:26');
INSERT INTO `location` VALUES ('305', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:55:49');
INSERT INTO `location` VALUES ('306', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:56:44');
INSERT INTO `location` VALUES ('307', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:57:33');
INSERT INTO `location` VALUES ('308', '4', '0', '22.502844', '113.916282', '10', '0', '2017-09-08 17:58:42');
INSERT INTO `location` VALUES ('309', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-08 17:59:41');
INSERT INTO `location` VALUES ('310', '4', '0', '22.502872', '113.916114', '10', '0', '2017-09-08 18:00:42');
INSERT INTO `location` VALUES ('311', '4', '0', '22.502898', '113.916050', '10', '0', '2017-09-09 10:21:05');
INSERT INTO `location` VALUES ('312', '4', '0', '22.502743', '113.916145', '10', '0', '2017-09-09 10:22:40');
INSERT INTO `location` VALUES ('313', '4', '0', '22.502960', '113.916100', '10', '0', '2017-09-09 10:23:40');
INSERT INTO `location` VALUES ('314', '4', '0', '22.503040', '113.916275', '10', '0', '2017-09-09 10:24:39');
INSERT INTO `location` VALUES ('315', '4', '0', '22.502765', '113.916116', '10', '0', '2017-09-09 10:25:35');
INSERT INTO `location` VALUES ('316', '4', '0', '22.503003', '113.915906', '10', '0', '2017-09-09 10:27:11');
INSERT INTO `location` VALUES ('317', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-09 14:18:54');
INSERT INTO `location` VALUES ('318', '4', '1', '22.5000339', '113.9204801', '0', '1', '2017-09-09 14:19:50');
INSERT INTO `location` VALUES ('319', '4', '0', '22.502827', '113.916225', '10', '0', '2017-09-09 14:20:45');
INSERT INTO `location` VALUES ('320', '4', '0', '22.502788', '113.916142', '10', '0', '2017-09-09 14:21:41');
INSERT INTO `location` VALUES ('321', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 16:38:35');
INSERT INTO `location` VALUES ('322', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 16:44:24');
INSERT INTO `location` VALUES ('323', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 16:51:02');
INSERT INTO `location` VALUES ('324', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 17:03:57');
INSERT INTO `location` VALUES ('325', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 17:23:02');
INSERT INTO `location` VALUES ('326', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 17:24:24');
INSERT INTO `location` VALUES ('327', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 17:38:33');
INSERT INTO `location` VALUES ('328', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 17:40:50');
INSERT INTO `location` VALUES ('329', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-11 18:00:50');
INSERT INTO `location` VALUES ('330', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-11 18:01:49');
INSERT INTO `location` VALUES ('331', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:09:05');
INSERT INTO `location` VALUES ('332', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:10:22');
INSERT INTO `location` VALUES ('333', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:12:05');
INSERT INTO `location` VALUES ('334', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:12:57');
INSERT INTO `location` VALUES ('335', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:16:45');
INSERT INTO `location` VALUES ('336', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:23:22');
INSERT INTO `location` VALUES ('337', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 18:27:57');
INSERT INTO `location` VALUES ('338', '4', '1', '22.5003071', '113.9219911', '0', '0', '2017-09-11 19:35:50');
INSERT INTO `location` VALUES ('339', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-11 19:36:55');
INSERT INTO `location` VALUES ('340', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 20:01:57');
INSERT INTO `location` VALUES ('341', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 20:02:39');
INSERT INTO `location` VALUES ('342', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 20:09:01');
INSERT INTO `location` VALUES ('343', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 20:09:41');
INSERT INTO `location` VALUES ('344', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 20:40:16');
INSERT INTO `location` VALUES ('345', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 21:11:50');
INSERT INTO `location` VALUES ('346', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:12:29');
INSERT INTO `location` VALUES ('347', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:12:55');
INSERT INTO `location` VALUES ('348', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 21:39:37');
INSERT INTO `location` VALUES ('349', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:40:19');
INSERT INTO `location` VALUES ('350', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:40:42');
INSERT INTO `location` VALUES ('351', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 21:41:54');
INSERT INTO `location` VALUES ('352', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 21:42:00');
INSERT INTO `location` VALUES ('353', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-11 21:42:16');
INSERT INTO `location` VALUES ('354', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:42:55');
INSERT INTO `location` VALUES ('355', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-11 21:43:21');
INSERT INTO `location` VALUES ('356', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:11:25');
INSERT INTO `location` VALUES ('357', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:11:26');
INSERT INTO `location` VALUES ('358', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:12:06');
INSERT INTO `location` VALUES ('359', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:12:17');
INSERT INTO `location` VALUES ('360', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 10:13:22');
INSERT INTO `location` VALUES ('361', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:32:31');
INSERT INTO `location` VALUES ('362', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:32:39');
INSERT INTO `location` VALUES ('363', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:32:42');
INSERT INTO `location` VALUES ('364', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 10:33:47');
INSERT INTO `location` VALUES ('365', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:37:30');
INSERT INTO `location` VALUES ('366', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:37:32');
INSERT INTO `location` VALUES ('367', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:37:34');
INSERT INTO `location` VALUES ('368', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 10:38:39');
INSERT INTO `location` VALUES ('369', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 10:46:01');
INSERT INTO `location` VALUES ('370', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 16:26:48');
INSERT INTO `location` VALUES ('371', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 16:26:50');
INSERT INTO `location` VALUES ('372', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 16:27:55');
INSERT INTO `location` VALUES ('373', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 16:28:30');
INSERT INTO `location` VALUES ('374', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 16:29:35');
INSERT INTO `location` VALUES ('375', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 16:47:51');
INSERT INTO `location` VALUES ('376', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 16:48:56');
INSERT INTO `location` VALUES ('377', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 17:07:51');
INSERT INTO `location` VALUES ('378', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-12 17:08:56');
INSERT INTO `location` VALUES ('379', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 17:15:31');
INSERT INTO `location` VALUES ('380', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 17:16:36');
INSERT INTO `location` VALUES ('381', '4', '1', '22.5001858', '113.9202252', '0', '0', '2017-09-12 17:18:24');
INSERT INTO `location` VALUES ('382', '4', '1', '22.5001858', '113.9202252', '0', '0', '2017-09-12 17:19:29');
INSERT INTO `location` VALUES ('383', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-12 17:22:16');
INSERT INTO `location` VALUES ('384', '4', '0', '22.502973', '113.916173', '10', '0', '2017-09-12 17:23:21');
INSERT INTO `location` VALUES ('385', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-12 17:24:35');
INSERT INTO `location` VALUES ('386', '4', '0', '22.502563', '113.916215', '10', '0', '2017-09-12 17:25:40');
INSERT INTO `location` VALUES ('387', '4', '1', '22.5001858', '113.9202252', '0', '0', '2017-09-12 17:35:21');
INSERT INTO `location` VALUES ('388', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-12 17:36:26');
INSERT INTO `location` VALUES ('389', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-12 17:37:14');
INSERT INTO `location` VALUES ('390', '4', '0', '22.502592', '113.916112', '10', '0', '2017-09-12 17:38:19');
INSERT INTO `location` VALUES ('391', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 17:53:12');
INSERT INTO `location` VALUES ('392', '4', '0', '22.500082194011', '113.920994194879', '10', '0', '2017-09-12 17:54:17');
INSERT INTO `location` VALUES ('393', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-12 17:55:11');
INSERT INTO `location` VALUES ('394', '4', '0', '22.500014105903', '113.920984157987', '10', '0', '2017-09-12 17:56:16');
INSERT INTO `location` VALUES ('395', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 21:13:33');
INSERT INTO `location` VALUES ('396', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-12 21:14:39');
INSERT INTO `location` VALUES ('397', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-13 09:26:22');
INSERT INTO `location` VALUES ('398', '4', '0', '22.499867350261', '113.921094292535', '10', '0', '2017-09-13 09:30:53');
INSERT INTO `location` VALUES ('399', '4', '0', '22.499897189671', '113.921019151476', '10', '0', '2017-09-13 09:33:12');
INSERT INTO `location` VALUES ('400', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-13 09:33:54');
INSERT INTO `location` VALUES ('401', '4', '0', '22.499818522136', '113.92107123481', '10', '0', '2017-09-13 09:34:59');
INSERT INTO `location` VALUES ('402', '4', '0', '22.499817165799', '113.920952148438', '10', '0', '2017-09-13 09:36:54');
INSERT INTO `location` VALUES ('403', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-13 11:18:27');
INSERT INTO `location` VALUES ('404', '4', '1', '22.5000339', '113.9204801', '0', '0', '2017-09-13 11:37:51');
INSERT INTO `location` VALUES ('405', '4', '0', '22.499840223525', '113.920921223959', '10', '0', '2017-09-13 15:16:14');
INSERT INTO `location` VALUES ('406', '4', '0', '22.500746799046', '113.921684299046', '10', '0', '2017-09-13 15:19:29');
INSERT INTO `location` VALUES ('407', '4', '0', '22.499877115886', '113.920947265625', '10', '0', '2017-09-13 15:22:24');
INSERT INTO `location` VALUES ('408', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-13 15:24:56');
INSERT INTO `location` VALUES ('409', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-13 15:39:41');
INSERT INTO `location` VALUES ('410', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-13 15:48:52');
INSERT INTO `location` VALUES ('411', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:01:24');
INSERT INTO `location` VALUES ('412', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:01:51');
INSERT INTO `location` VALUES ('413', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:01:51');
INSERT INTO `location` VALUES ('414', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-14 10:03:17');
INSERT INTO `location` VALUES ('415', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:04:11');
INSERT INTO `location` VALUES ('416', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:04:36');
INSERT INTO `location` VALUES ('417', '4', '1', '22.4992849', '113.9205553', '0', '0', '2017-09-14 10:05:12');
INSERT INTO `location` VALUES ('418', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 15:51:54');
INSERT INTO `location` VALUES ('419', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 15:53:41');
INSERT INTO `location` VALUES ('420', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 15:59:31');
INSERT INTO `location` VALUES ('421', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 16:01:11');
INSERT INTO `location` VALUES ('422', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 16:04:02');
INSERT INTO `location` VALUES ('423', '4', '0', '22.49952718099', '113.920888129341', '10', '0', '2017-09-14 16:41:34');
INSERT INTO `location` VALUES ('424', '4', '0', '22.499642198351', '113.920935058594', '10', '0', '2017-09-14 16:43:42');
INSERT INTO `location` VALUES ('425', '4', '0', '22.499714084202', '113.920854220921', '10', '0', '2017-09-14 16:45:00');
INSERT INTO `location` VALUES ('426', '4', '0', '22.499637315539', '113.920905219185', '10', '0', '2017-09-14 16:46:50');
INSERT INTO `location` VALUES ('427', '4', '0', '22.498972167969', '113.920999077691', '10', '0', '2017-09-14 17:06:15');
INSERT INTO `location` VALUES ('428', '4', '0', '22.499013400608', '113.921193305122', '10', '0', '2017-09-14 17:32:14');
INSERT INTO `location` VALUES ('429', '4', '0', '22.498811577691', '113.921195203994', '10', '0', '2017-09-14 17:33:32');
INSERT INTO `location` VALUES ('430', '4', '1', '22.50001', '113.9205732', '0', '1', '2017-09-14 17:53:02');
INSERT INTO `location` VALUES ('431', '4', '1', '22.50001', '113.9205732', '0', '1', '2017-09-14 17:53:20');
INSERT INTO `location` VALUES ('432', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 17:53:40');
INSERT INTO `location` VALUES ('433', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 17:54:12');
INSERT INTO `location` VALUES ('434', '4', '1', '22.5003071', '113.9219911', '0', '1', '2017-09-14 17:54:45');
INSERT INTO `location` VALUES ('435', '4', '0', '22.499582519532', '113.921204155816', '10', '0', '2017-09-14 17:55:41');
INSERT INTO `location` VALUES ('436', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-15 13:49:53');
INSERT INTO `location` VALUES ('437', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-21 19:54:37');
INSERT INTO `location` VALUES ('438', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-21 20:16:56');
INSERT INTO `location` VALUES ('439', '4', '1', '22.4992849', '113.9205553', '0', '1', '2017-09-21 20:17:41');

-- ----------------------------
-- Table structure for `token_info`
-- ----------------------------
DROP TABLE IF EXISTS `token_info`;
CREATE TABLE `token_info` (
  `t_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`t_id`),
  UNIQUE KEY `unique_idx_token` (`token`),
  UNIQUE KEY `unique_idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token_info
-- ----------------------------
INSERT INTO `token_info` VALUES ('1', '123456', '1', '2017-08-24 14:24:46');
INSERT INTO `token_info` VALUES ('2', '1qaz', '2', '2017-08-24 15:57:04');
INSERT INTO `token_info` VALUES ('3', '2wsx', '3', '2017-09-06 22:35:03');
INSERT INTO `token_info` VALUES ('4', '3edc', '4', '2017-09-06 22:36:10');
INSERT INTO `token_info` VALUES ('5', '100001', '5', '2017-09-12 14:41:02');
INSERT INTO `token_info` VALUES ('6', '100002', '6', '2017-09-12 14:42:00');
INSERT INTO `token_info` VALUES ('7', '100003', '7', '2017-09-12 14:42:12');
INSERT INTO `token_info` VALUES ('8', '100004', '8', '2017-09-12 14:42:24');
INSERT INTO `token_info` VALUES ('9', '100005', '9', '2017-09-12 14:43:06');
INSERT INTO `token_info` VALUES ('10', '100006', '10', '2017-09-12 14:43:18');
INSERT INTO `token_info` VALUES ('11', '100007', '11', '2017-09-12 14:43:28');
INSERT INTO `token_info` VALUES ('12', '100008', '12', '2017-09-12 14:43:44');
INSERT INTO `token_info` VALUES ('13', '100009', '13', '2017-09-12 14:44:12');
INSERT INTO `token_info` VALUES ('14', '100010', '14', '2017-09-12 14:44:24');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `dv` varchar(128) DEFAULT NULL COMMENT '设备固件版本号',
  `sd` varchar(128) DEFAULT NULL COMMENT '软件版本号',
  `imei` varchar(128) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bindingtime` timestamp NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'test1', '123456', null, null, '85988233', '2017-09-06 21:26:40', null);
INSERT INTO `user_info` VALUES ('2', 'test2', '123456', null, null, '869758001213076', '2017-09-06 21:27:43', null);
INSERT INTO `user_info` VALUES ('3', 'test3', '123456', null, null, '869758001213084', '2017-09-06 21:28:09', null);
INSERT INTO `user_info` VALUES ('4', 'test4', '123456', null, null, '123456789012380', '2017-09-07 09:36:46', null);
INSERT INTO `user_info` VALUES ('5', 'test5', '123456', null, null, '869758001213076', '2017-09-12 14:36:35', null);
INSERT INTO `user_info` VALUES ('6', 'test6', '123456', null, null, '869758001213084', '2017-09-12 14:36:58', null);
INSERT INTO `user_info` VALUES ('7', 'test7', '123456', null, null, '869758001213092', '2017-09-12 14:37:29', null);
INSERT INTO `user_info` VALUES ('8', 'test8', '123456', null, null, '869758001213100', '2017-09-12 14:37:56', null);
INSERT INTO `user_info` VALUES ('9', 'test9', '123456', null, null, '869758001213118', '2017-09-12 14:38:26', null);
INSERT INTO `user_info` VALUES ('10', 'test10', '123456', null, null, '869758001213126', '2017-09-12 14:38:46', null);
INSERT INTO `user_info` VALUES ('11', 'test11', '123456', null, null, '869758001213134', '2017-09-12 14:39:10', null);
INSERT INTO `user_info` VALUES ('12', 'test12', '123456', null, null, '869758001213142', '2017-09-12 14:39:34', null);
INSERT INTO `user_info` VALUES ('13', 'test13', '123456', null, null, '869758001213159', '2017-09-12 14:39:56', null);
INSERT INTO `user_info` VALUES ('14', 'test14', '123456', null, null, '869758001213167', '2017-09-12 14:40:18', null);



-- ----------------------------
-- Table structure for `authcode`
-- ----------------------------
DROP TABLE IF EXISTS `authcode`;
CREATE TABLE `authcode` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tel` varchar(13) NOT NULL,
  `code` varchar(10) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `fence`
-- ----------------------------
DROP TABLE IF EXISTS `fence`;
CREATE TABLE `fence` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `pushlog`
-- ----------------------------
DROP TABLE IF EXISTS `pushlog`;
CREATE TABLE `pushlog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `target` varchar(64) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `fencelog`
-- ----------------------------
DROP TABLE IF EXISTS `fencelog`;
CREATE TABLE `fencelog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `lat1` varchar(64) NOT NULL,
  `lng1` varchar(64) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `content` varchar(128) DEFAULT NULL,
  `upload_time` timestamp NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `call_info`
-- ----------------------------
DROP TABLE IF EXISTS `call_info`;
CREATE TABLE `call_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `msg` text,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sos_white_list`
-- ----------------------------
DROP TABLE IF EXISTS `sos_white_list`;
CREATE TABLE `sos_white_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




