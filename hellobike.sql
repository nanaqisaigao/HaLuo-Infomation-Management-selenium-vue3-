/*
 Navicat Premium Data Transfer

 Source Server         : mysql 1
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : hellobike

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 27/01/2024 01:24:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checkclock
-- ----------------------------
DROP TABLE IF EXISTS `checkclock`;
CREATE TABLE `checkclock`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `check_date` date NOT NULL,
  `check_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `valid_time` float NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of checkclock
-- ----------------------------
INSERT INTO `checkclock` VALUES (1, 1, '2024-01-03', '2-3,2-3', 6.1, '2024-01-13 23:42:56', '2024-01-13 23:43:04', 0);
INSERT INTO `checkclock` VALUES (2, 2, '2024-01-04', '2-3,2-3', 6.1, '2024-01-26 23:43:09', '2024-01-26 23:43:12', 0);
INSERT INTO `checkclock` VALUES (3, 3, '2024-01-12', '2-10,12-13', 8.3, '2024-01-25 23:43:15', '2024-01-26 23:43:18', 0);
INSERT INTO `checkclock` VALUES (4, 3, '2024-01-11', '2-10,12-13', 4.1, '2024-01-26 23:43:20', '2024-01-26 23:43:22', 0);
INSERT INTO `checkclock` VALUES (5, 3, '2024-01-13', '12-13,15-19', 16.8, '2024-01-25 23:43:25', '2024-01-26 23:43:28', 0);
INSERT INTO `checkclock` VALUES (6, 5, '2024-01-23', '12-13', 2.1, '2024-01-23 23:41:39', '2024-01-24 23:41:48', 0);
INSERT INTO `checkclock` VALUES (7, 4, '2024-01-03', '1-2', 22, '2024-01-26 23:31:15', '2024-01-26 23:43:37', 0);
INSERT INTO `checkclock` VALUES (8, 5, '2024-01-24', '12-13,14-16', 4.2, '2024-01-26 23:32:23', '2024-01-26 23:42:31', 0);
INSERT INTO `checkclock` VALUES (9, 5, '2024-01-25', '12-13', 2.1, '2024-01-26 23:35:48', '2024-01-26 23:43:32', 0);
INSERT INTO `checkclock` VALUES (10, 5, '2024-01-26', '12-13,15-17', 4.2, '2024-01-26 23:37:12', '2024-01-26 23:42:43', 0);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `college_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机科学与技术学院', '2024-01-26 23:45:59', '2024-01-26 23:57:20', 0);
INSERT INTO `college` VALUES (2, '信息学院', '2024-01-26 23:46:05', '2024-01-26 23:57:26', 0);
INSERT INTO `college` VALUES (3, '医学院', '2024-01-26 23:55:14', '2024-01-26 23:57:30', 0);
INSERT INTO `college` VALUES (4, '国际学院', '2024-01-26 23:58:04', '2024-01-26 23:58:08', 0);

-- ----------------------------
-- Table structure for freetime
-- ----------------------------
DROP TABLE IF EXISTS `freetime`;
CREATE TABLE `freetime`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `free_time` datetime NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of freetime
-- ----------------------------
INSERT INTO `freetime` VALUES (1, 2, '2024-01-26 09:46:51', '2024-01-26 23:47:44', '2024-01-26 23:47:44', 0);
INSERT INTO `freetime` VALUES (2, 3, '2024-01-26 11:49:18', '2024-01-26 23:49:30', '2024-01-26 23:49:39', 0);
INSERT INTO `freetime` VALUES (3, 4, '2024-01-26 15:49:48', '2024-01-26 23:49:59', '2024-01-26 23:49:59', 0);
INSERT INTO `freetime` VALUES (4, 2, '2024-01-30 11:59:22', '2024-01-26 23:59:36', '2024-01-26 23:59:36', 0);

-- ----------------------------
-- Table structure for historypassword
-- ----------------------------
DROP TABLE IF EXISTS `historypassword`;
CREATE TABLE `historypassword`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `historical_password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of historypassword
-- ----------------------------
INSERT INTO `historypassword` VALUES (9, 3, '4f93efbc7315bef13f82adb3f970d3e3', '2024-01-26 23:48:01', '2024-01-26 23:48:30', 1);
INSERT INTO `historypassword` VALUES (10, 3, 'efac59cbb4320f70f8a6155610b62400', '2024-01-26 23:48:01', '2024-01-26 23:48:31', 1);
INSERT INTO `historypassword` VALUES (11, 3, '4ef1d010dd36968ebf0b3beab8636337', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 1);
INSERT INTO `historypassword` VALUES (12, 3, '4ef1d010dd36968ebf0b3beab8636337', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 0);
INSERT INTO `historypassword` VALUES (13, 3, '4f93efbc7315bef13f82adb3f970d3e3', '2024-01-26 23:48:01', '2024-01-26 23:48:42', 0);
INSERT INTO `historypassword` VALUES (14, 3, '00dd6924fb42aa4f07e6969893578fff', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 0);
INSERT INTO `historypassword` VALUES (15, 5, '2bcdaa28f9b35e4b7c9a4942893dee48', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 1);
INSERT INTO `historypassword` VALUES (16, 5, '4b3b032d4cf3d1639ffde3fd0d005ae5', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 0);
INSERT INTO `historypassword` VALUES (17, 5, '4b3b032d4cf3d1639ffde3fd0d005ae5', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 0);
INSERT INTO `historypassword` VALUES (18, 5, '53f4ac4b3c1c624ef0c6ced52c437210', '2024-01-26 23:48:01', '2024-01-26 23:48:01', 0);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `college_id` int NULL DEFAULT NULL,
  `major_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, 1, '计算机', '2024-01-26 23:52:35', '2024-01-26 23:52:35', 0);
INSERT INTO `major` VALUES (2, 1, '软件工程', '2024-01-26 23:52:35', '2024-01-26 23:56:43', 0);
INSERT INTO `major` VALUES (3, 3, '临床医学', '2024-01-26 23:52:35', '2024-01-26 23:58:43', 0);
INSERT INTO `major` VALUES (4, 4, '英语', '2024-01-26 23:52:35', '2024-01-26 23:58:46', 0);
INSERT INTO `major` VALUES (5, 1, '原神', '2024-01-26 23:52:33', '2024-01-26 23:56:47', 0);
INSERT INTO `major` VALUES (6, 2, '自动化', '2024-01-26 23:55:50', '2024-01-26 23:58:26', 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `college_id` int NULL DEFAULT NULL,
  `major_id` int NULL DEFAULT NULL,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `grade` int NULL DEFAULT NULL,
  `education` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 2, 1, 1, '张里', 2021, '本科', '2024-01-24 23:45:07', '2024-01-26 23:45:27', 0);
INSERT INTO `student` VALUES (2, 1, 1, 2, '小李', 2000, '本科', '2024-01-10 23:45:13', '2024-01-26 23:45:27', 0);
INSERT INTO `student` VALUES (3, 3, 3, 3, '小王', 2020, '博士生', '2024-01-26 23:45:17', '2024-01-27 01:23:17', 0);
INSERT INTO `student` VALUES (4, 5, 1, 2, '小明', 2021, '本科', '2024-01-26 23:45:21', '2024-01-26 23:50:03', 0);
INSERT INTO `student` VALUES (5, 4, 2, 6, '小年', 2022, '研究生', '2024-01-27 01:22:31', '2024-01-27 01:23:59', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '155074587', '5a05254570cc97ac9582ad7c5877f1ad', 'Teacher', '2024-01-09 18:48:49', '2024-01-27 01:17:56', 0);
INSERT INTO `user` VALUES (2, '13247948', '140a6e4da3ec91c8e8b7ceb8e1d2f950', 'Teacher', '2024-01-16 23:51:06', '2024-01-27 01:18:06', 0);
INSERT INTO `user` VALUES (3, '123456789', 'bbcefbf34cd64dfa58c2eff6b71434c7', 'Student', '2024-01-16 23:51:15', '2024-01-26 23:51:19', 0);
INSERT INTO `user` VALUES (4, '102786289', '4ccc9f1ab4b9babde2e3f2e63ff1bb49', 'Teacher', '2024-01-16 23:51:20', '2024-01-26 23:51:22', 0);
INSERT INTO `user` VALUES (5, '18984562546', 'd02f3cf86bb402a904f98df6373eb1ac', 'Student', '2024-01-26 23:51:23', '2024-01-26 23:51:25', 0);

-- ----------------------------
-- Table structure for weekreport
-- ----------------------------
DROP TABLE IF EXISTS `weekreport`;
CREATE TABLE `weekreport`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `this_week_work` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `this_week_idea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `finished` tinyint NULL DEFAULT NULL,
  `next_week_work` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bg_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bg_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of weekreport
-- ----------------------------
INSERT INTO `weekreport` VALUES (1, 1, '2023-04-01', '2023-04-07', '老师我啊太难了', '太难了啊啊啊啊', 1, '摆烂', '2024-01-17 21:01:45', '2024-01-17 21:01:45', 0);
INSERT INTO `weekreport` VALUES (2, 1, '2023-04-01', '2023-04-07', '完成登录校验', '有一点点难度', 1, '完成测试', '2024-01-17 21:02:30', '2024-01-17 21:02:30', 0);
INSERT INTO `weekreport` VALUES (3, 1, '2023-04-01', '2023-04-07', '完成登录校验', '有一点点难度', 1, '完成测试', '2024-01-18 19:49:30', '2024-01-18 19:49:30', 0);
INSERT INTO `weekreport` VALUES (4, 1, '2023-04-01', '2023-04-07', '完成登录校验', '有一点点难度', 1, '完成测试', '2024-01-18 20:31:49', '2024-01-18 20:31:49', 0);
INSERT INTO `weekreport` VALUES (5, 1, '2023-04-01', '2023-04-07', '完成登录校验', '有一点点难度', 1, '完成测试', '2024-01-24 15:16:27', '2024-01-24 15:16:27', 0);
INSERT INTO `weekreport` VALUES (6, 2, '2023-04-01', '2023-04-07', '完成登录校验', '有一点点难度', 1, '完成测试', '2024-01-25 13:05:48', '2024-01-25 13:05:48', 0);

SET FOREIGN_KEY_CHECKS = 1;
