/*
Navicat MySQL Data Transfer

Source Server         : 本机环境
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : p2p_basics

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-02-22 10:30:05
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`            int(8) NOT NULL AUTO_INCREMENT COMMENT '主键标示',
    `name`          varchar(128) DEFAULT '' COMMENT '菜单名称',
    `i18n_nid`      varchar(50)  DEFAULT NULL COMMENT '中文对应的国际化标识NID',
    `pid`           int(8)       DEFAULT '0' COMMENT '父级ID',
    `level_id`      tinyint(2)   DEFAULT '0' COMMENT '等级',
    `url`           varchar(512) DEFAULT '' COMMENT '链接地址',
    `icon_cls`      varchar(512) DEFAULT '' COMMENT '图标',
    `status`        tinyint(4)   DEFAULT '0' COMMENT '状态',
    `sort`          tinyint(4)   DEFAULT '1' COMMENT '排序',
    `code`          varchar(128) DEFAULT NULL COMMENT '代码',
    `state`         varchar(16)  DEFAULT NULL COMMENT '是否可展开',
    `resource_type` varchar(16)  DEFAULT NULL COMMENT '资源类型',
    `add_time`      datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `add_user`      varchar(30)  DEFAULT NULL COMMENT '添加用户',
    `update_time`   datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_user`   varchar(30)  DEFAULT NULL COMMENT '修改用户',
    `remark`        varchar(256) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8 COMMENT ='菜单表';

-- ----------------------------
-- Table structure for sys_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate`;
CREATE TABLE `sys_operate`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `user_name`    varchar(50)  DEFAULT NULL COMMENT '用户名',
    `real_name`    varchar(50)  DEFAULT NULL,
    `pwd`          varchar(255) DEFAULT NULL COMMENT '密码',
    `mobile_phone` varchar(50)  DEFAULT NULL COMMENT '手机号',
    `add_time`     datetime     DEFAULT NULL COMMENT '添加时间',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `partner_id`   int(11)      DEFAULT NULL COMMENT '商户id',
    `login_ip`     varchar(255) DEFAULT NULL COMMENT '最近登录ip',
    `login_time`   datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近登录时间',
    `status`       int(11)      DEFAULT '1' COMMENT '状态',
    `openid`       varchar(32)  DEFAULT NULL COMMENT '钉钉系统用户id',
    PRIMARY KEY (`id`),
    KEY `sys_operate_partner` (`partner_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='操作员表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '角色名',
    `remark`      varchar(255) DEFAULT NULL,
    `add_time`    datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    `status`      int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `role_id`     int(11)  DEFAULT NULL COMMENT '角色id',
    `menu_id`     int(11)  DEFAULT NULL COMMENT '菜单id',
    `add_time`    datetime DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色菜单表';

-- ----------------------------
-- Table structure for sys_role_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_operate`;
CREATE TABLE `sys_role_operate`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `operate_id` int(11) DEFAULT NULL,
    `role_id`    int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='操作员与角色关系表';

DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50)  DEFAULT NULL COMMENT '名称',
    `i18n_nid`    varchar(50)  DEFAULT NULL COMMENT '中文对应的国际化标识NID',
    `nid`         varchar(100) DEFAULT NULL COMMENT '唯一标识',
    `pid`         int(11)      DEFAULT NULL COMMENT '父级id',
    `value`       varchar(50)  DEFAULT NULL COMMENT '值',
    `type`        int(2)       DEFAULT NULL COMMENT '类型  (详情见dict表basics_dict_type) ',
    `code`        varchar(50)  DEFAULT NULL COMMENT '代码',
    `java_type`   varchar(50)  DEFAULT NULL COMMENT 'java类型 (详情见dict表basics_java_type)',
    `sort`        int(3)       DEFAULT '1' COMMENT '排序',
    `status`      int(2)       DEFAULT '1' COMMENT '状态，(详情见dict表basics_use_status) ',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    `add_time`    datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `relate_id`   varchar(100) DEFAULT '' COMMENT '关联字典id',
    PRIMARY KEY (`id`),
    KEY `nid` (`nid`),
    KEY `pid` (`pid`),
    KEY `value` (`value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='字典表';

CREATE TABLE `job_info`
(
    `id`              int(11)   NOT NULL AUTO_INCREMENT,
    `job_name`        varchar(50)    DEFAULT NULL COMMENT '任务名称',
    `job_group`       varchar(50)    DEFAULT NULL COMMENT '任务分组',
    `job_description` varchar(50)    DEFAULT NULL COMMENT '任务描述',
    `job_env`         varchar(10)    DEFAULT 'all' COMMENT '配置在某种语言环境才可启动任务 all为全部 zh_CN en_US id_ID等',
    `start_withrun`   smallint(2)    DEFAULT '0' COMMENT '是否随着程序启动自动启动任务 0否 1是',
    `job_status`      smallint(2)    DEFAULT '1' COMMENT '1正在运行 0已经停止',
    `cron_expression` varchar(20)    DEFAULT NULL COMMENT 'cron表达式',
    `bean_class`      varchar(150)   DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
    `spring_id`       varchar(20)    DEFAULT NULL COMMENT 'Spring bean 名',
    `method_name`     varchar(128)   DEFAULT NULL,
    `param_json`      varchar(255)   DEFAULT NULL COMMENT '方法执行需要的参数，配置为json',
    `is_concurrent`   smallint(2)    DEFAULT NULL COMMENT '任务是否可以并发(一个还没完就执行下一个） 1可以 0不可以',
    `create_time`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `sys_config`
(
    `id`     int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`   varchar(30)   DEFAULT '' COMMENT '名称',
    `nid`    varchar(50)   DEFAULT '' COMMENT '标识',
    `value`  varchar(2058) DEFAULT '' COMMENT '名称对应的值',
    `type`   tinyint(4)    DEFAULT NULL COMMENT '类型 1:系统底层配置信息， 2:各种费率配置信息， 3:邮件/短信配置信息， 4:附加增值功能配置信息， 5:第三方资金托管相关的配置， 6.网站汇付天下相关配置信息， 7:短信模板替换参数配置信息',
    `status` tinyint(1)    DEFAULT '0' COMMENT '状态 0:不启用 1：启用',
    `remark` varchar(255)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统参数表';

-- ----------------------------
-- Table structure for `sys_operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `trace_id`       varchar(32)  NOT NULL COMMENT '追踪日志id',
    `class_name`     varchar(255)      DEFAULT NULL COMMENT '类名',
    `request_url`    varchar(255) NOT NULL COMMENT '请求的地址',
    `request_param`  text COMMENT '请求的参数',
    `request_method` varchar(100) NOT NULL COMMENT '请求的方法名',
    `response_param` text COMMENT '返回参数',
    `operate_id`     int(11)      NOT NULL COMMENT '操作人ID',
    `operate_name`   varchar(20)       DEFAULT NULL COMMENT '操作人姓名',
    `add_time`       timestamp    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='操作员操作记录表';

-- ----------------------------
-- Table structure for `sys_event_hook`
-- ----------------------------
CREATE TABLE `sys_event_hook`
(
    `id`                int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name`              varchar(32)           DEFAULT NULL COMMENT '名称',
    `service_bean`      varchar(32)           DEFAULT NULL COMMENT '源事件对象bean',
    `service_method`    varchar(32)           DEFAULT NULL COMMENT '源事件方法',
    `status`            varchar(8)            DEFAULT NULL COMMENT '状态:1启动 0禁止  字典代码:basis_status',
    `execute_type`      varchar(8)            DEFAULT NULL COMMENT '执行类型  java代表执行代码 sql代表执行',
    `execute_sql`       varchar(255)          DEFAULT NULL COMMENT '执行sql',
    `execute_sql_param` varchar(255)          DEFAULT NULL COMMENT '执行sql参数',
    `execute_bean`      varchar(255)          DEFAULT NULL COMMENT '执行对象类',
    `execute_method`    varchar(255)          DEFAULT NULL COMMENT '执行对象方法',
    `execute_param`     varchar(255)          DEFAULT NULL COMMENT '执行对象参数',
    `add_time`          timestamp        NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`       timestamp        NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='事件hook表';

-- ----------------------------
-- Records of sys_event_hook
-- ----------------------------
-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `nid`          varchar(30)   DEFAULT '',
    `receive_user` varchar(11)   DEFAULT NULL COMMENT '接收用户',
    `status`       tinyint(1)    DEFAULT '0' COMMENT '状态，0：发送失败；1：发送成功',
    `use_status`   tinyint(1)    DEFAULT '0' COMMENT '是否已使用 0未使用 1已使用',
    `title`        varchar(50)   DEFAULT '' COMMENT '标题',
    `content`      text COMMENT '发送内容',
    `result`       varchar(1000) DEFAULT '' COMMENT '发送结果信息',
    `add_time`     datetime      DEFAULT NULL COMMENT '添加时间',
    `code`         varchar(6)    DEFAULT NULL COMMENT '验证码',
    PRIMARY KEY (`id`),
    KEY `index_add_time` (`add_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='短信记录表';

-- ----------------------------
-- Table structure for notice_type
-- ----------------------------
DROP TABLE IF EXISTS `notice_type`;
CREATE TABLE `notice_type`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `nid`           varchar(30) NOT NULL DEFAULT '' COMMENT '编码，与notice_type组合起来唯一',
    `name`          varchar(30)          DEFAULT '' COMMENT '名称',
    `send`          tinyint(1)           DEFAULT '0' COMMENT '是否发送：0-不发送，1-发送',
    `title_templet` varchar(250)         DEFAULT '' COMMENT '标题的freemarker模板',
    `templet`       varchar(1024)        DEFAULT '' COMMENT '内容的freemarker模板',
    `remark`        varchar(250)         DEFAULT '' COMMENT '备注',
    `add_time`      datetime             DEFAULT NULL COMMENT '添加时间',
    `add_ip`        varchar(15)          DEFAULT '' COMMENT '添加IP',
    `update_time`   datetime             DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='短信模版表';

DROP TABLE IF EXISTS `sys_i18n`;
CREATE TABLE `sys_i18n`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `model`       varchar(32)  NOT NULL DEFAULT 'market' COMMENT '模块',
    `name`        varchar(64)  NOT NULL COMMENT '名称',
    `text`        varchar(256) NOT NULL COMMENT '内容',
    `zh_CN`       varchar(256)          DEFAULT NULL COMMENT '中文内容',
    `en_US`       varchar(256)          DEFAULT NULL COMMENT '英文内容',
    `in_ID`       varchar(256)          DEFAULT NULL COMMENT '印尼内容',
    `add_time`    datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='国际化翻译表';