INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('1', '字典管理', 'sys.dictmanage', '', '0', '', '1', NULL, NULL, '1', '1', '', '2018-04-03 20:13:16',
        '2018-04-03 20:13:16', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('2', '基础配置', 'sys.basemanage', 'basics', '1', NULL, '1', NULL, NULL, '1', '1', NULL, '2019-02-28 00:00:00',
        '2019-02-28 00:00:00', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('3', 'java类型', 'sys.javatype', 'basics_java_type', '2', NULL, '1', NULL, NULL, '1', '1', NULL,
        '2018-04-15 02:33:49', '2018-04-15 02:33:49', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('4', 'String', '', 'basics_java_type', '3', 'String', '2', 'String', 'String', '1', '1', NULL,
        '2018-04-15 03:00:14', '2018-04-15 03:00:14', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('5', 'Byte', '', 'basics_java_type', '3', 'Byte', '2', 'Byte', 'String', '2', '1', NULL, '2018-04-15 02:49:38',
        '2018-04-15 02:49:38', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('6', 'Short', '', 'basics_java_type', '3', 'Short', '2', 'Short', 'String', '3', '1', NULL,
        '2018-04-15 02:50:26', '2018-04-15 02:50:26', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('7', 'Integer', '', 'basics_java_type', '3', 'Integer', '2', 'Integer', 'String', '4', '1', NULL,
        '2018-04-15 02:54:25', '2018-04-15 02:54:25', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('8', 'Long', '', 'basics_java_type', '3', 'Long', '2', 'Long', 'String', '5', '1', NULL, '2018-04-15 02:57:07',
        '2018-04-15 02:57:07', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('9', 'Float', '', 'basics_java_type', '3', 'Float', '2', 'Float', 'String', '6', '1', NULL,
        '2018-04-15 02:57:26', '2018-04-15 02:57:26', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('10', 'Double', '', 'basics_java_type', '3', 'Double', '2', 'Double', 'String', '7', '1', NULL,
        '2018-04-15 02:58:11', '2018-04-15 02:58:11', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('11', 'Char', '', 'basics_java_type', '3', 'Char', '2', 'Char', 'String', '8', '1', NULL, '2018-04-15 02:58:53',
        '2018-04-15 02:58:53', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('12', 'Boolean', '', 'basics_java_type', '3', 'Boolean', '2', 'Boolean', 'String', '9', '1', NULL,
        '2018-04-15 02:59:38', '2018-04-15 02:59:38', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('13', '字典类型', 'sys.dicttype', 'basics_dict_type', '2', NULL, '1', NULL, NULL, '2', '1', NULL,
        '2018-04-15 22:54:56', '2018-04-15 22:54:56', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('14', '目录', 'sys.catalog', 'basics_dict_type', '13', '1', '2', 'folder', 'Integer', '1', '1', NULL,
        '2018-04-15 22:55:00', '2018-04-15 22:55:00', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('15', '数值项', 'sys.value', 'basics_dict_type', '13', '2', '2', 'value', 'Integer', '2', '1', NULL,
        '2018-04-15 22:56:41', '2018-04-15 22:56:41', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('16', '使用状态', 'sys.status', 'basics_use_status', '2', '', '1', '', '', '3', '1', NULL, '2018-04-16 00:13:24',
        '2018-04-16 00:13:24', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('17', '启用', 'sys.enable', 'basics_use_status', '16', '1', '2', 'enable', 'Integer', '1', '1', NULL,
        '2018-04-16 00:13:53', '2018-04-16 00:13:53', '');
INSERT INTO `sys_dict` (`id`, `name`, `i18n_nid`, `nid`, `pid`, `value`, `type`, `code`, `java_type`, `sort`,
                            `status`, `remark`, `add_time`, `update_time`, `relate_id`)
VALUES ('18', '禁用', 'sys.disable', 'basics_use_status', '16', '2', '2', 'disable', 'Integer', '2', '1', NULL,
        '2018-04-16 00:14:50', '2018-04-16 00:14:50', '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('-1', '国际化管理', 'sys.internationalmanage', '7', '0', '/sys/sysI18n/sysI18nIndex', '', '1', '2',
        'sys:sysI18n:sysI18nIndex', 'open', 'menu', '2019-03-13 01:13:08', NULL, '2019-03-13 01:13:08', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('1', '系统资源管理', 'sys.sysresourcemanage', '0', '1', '', '', '1', '1', NULL, 'closed', 'menu',
        '2018-03-20 13:25:54', NULL, '2018-03-20 13:25:54', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('2', '平台管理', 'sys.platformmanage', '1', '2', '', '', '1', '3', NULL, 'closed', 'menu', '2018-03-20 13:26:36',
        NULL, '2018-03-20 13:26:36', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('3', '权限配置', 'sys.jurisdictionmanage', '2', '3', '', '', '1', '1', NULL, 'closed', 'menu',
        '2018-03-20 13:27:05', NULL, '2018-03-20 13:27:05', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('4', '资源管理', 'sys.resourcesmanage', '3', '4', '/sys/sysMenu/sysMenuIndex', '', '1', '1',
        'sys:sysMenu:sysMenuIndex', 'open', 'menu', '2018-03-20 13:23:20', NULL, '2018-03-20 13:23:20', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('5', '操作员管理', 'sys.operatormanage', '3', '4', '/sys/sysOperate/sysOperateIndex', '', '1', '2',
        'sys:sysOperate:sysOperateIndex', 'open', 'menu', '2018-04-12 13:07:35', NULL, '2018-04-12 13:07:35', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('6', '角色管理', 'sys.rolemanage', '3', '4', '/sys/sysRole/sysRole', '', '1', '3', 'sys:sysRole:sysRole', 'open',
        'menu', '2018-04-12 13:08:28', NULL, '2018-04-12 13:08:28', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('7', '系统配置', 'sys.systemmanage', '2', '3', '', '', '1', '2', NULL, 'closed', 'menu', '2018-04-12 13:12:00',
        NULL, '2018-04-12 13:12:00', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('8', '字典管理', 'sys.dictmanage', '7', '4', '/sys/sysDict/sysDictIndex', '', '1', '1', 'sys:sysDict:sysDictIndex',
        'open', 'menu', '2018-04-12 13:17:25', NULL, '2018-04-12 13:17:25', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`,
                            `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`)
VALUES ('9', '功能管理', 'sys.functionmanage', '1', '2', '', '', '1', '1', NULL, 'closed', 'menu', '2018-04-12 13:19:58',
        NULL, '2018-04-12 13:19:58', NULL, '');


INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('-1', '1', '-1', '2019-03-13 00:33:28', '2019-03-13 00:33:28');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('1', '1', '1', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('2', '1', '2', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('3', '1', '3', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('4', '1', '4', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('5', '1', '5', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('6', '1', '6', '2018-04-02 20:56:30', '2018-04-02 20:56:30');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('7', '1', '7', '2018-04-09 19:01:22', '2018-04-09 19:01:22');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('8', '1', '8', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `add_time`, `update_time`)
VALUES ('9', '1', '9', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_operate` (`id`, `user_name`, `real_name`, `pwd`, `mobile_phone`, `add_time`, `update_time`,
                               `partner_id`, `login_ip`, `login_time`, `status`, `openid`)
VALUES ('1', 'admin123', '123', '0192023A7BBD73250516F069DF18B500', '123123', NULL, NULL, '1', NULL,
        '2018-04-08 15:58:11', '0', NULL);
INSERT INTO `sys_role` (`id`, `name`, `remark`, `add_time`, `update_time`, `status`)
VALUES ('1', '管理员', '管理员', NULL, NULL, '1');
INSERT INTO `sys_role_operate` (`id`, `operate_id`, `role_id`)
VALUES ('1', '1', '1');