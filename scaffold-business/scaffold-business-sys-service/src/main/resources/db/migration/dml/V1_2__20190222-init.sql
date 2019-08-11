-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '字典管理', 'sys.dictmanage', '', '0', '', '1', null, null, '1', '1', '', '2018-04-03 20:13:16', '2018-04-03 20:13:16', '');
INSERT INTO `sys_dict` VALUES ('2', '基础配置', 'sys.basemanage', 'basics', '1', null, '1', null, null, '1', '1', null, '2019-02-28 00:00:00', '2019-02-28 00:00:00', '');
INSERT INTO `sys_dict` VALUES ('3', 'java类型', 'sys.javatype', 'basics_java_type', '2', null, '1', null, null, '1', '1', null, '2018-04-15 02:33:49', '2018-04-15 02:33:49', '');
INSERT INTO `sys_dict` VALUES ('4', 'String', '', 'basics_java_type', '3', 'String', '2', 'String', 'String', '1', '1', null, '2018-04-15 03:00:14', '2018-04-15 03:00:14', '');
INSERT INTO `sys_dict` VALUES ('5', 'Byte', '', 'basics_java_type', '3', 'Byte', '2', 'Byte', 'String', '2', '1', null, '2018-04-15 02:49:38', '2018-04-15 02:49:38', '');
INSERT INTO `sys_dict` VALUES ('6', 'Short', '', 'basics_java_type', '3', 'Short', '2', 'Short', 'String', '3', '1', null, '2018-04-15 02:50:26', '2018-04-15 02:50:26', '');
INSERT INTO `sys_dict` VALUES ('7', 'Integer', '', 'basics_java_type', '3', 'Integer', '2', 'Integer', 'String', '4', '1', null, '2018-04-15 02:54:25', '2018-04-15 02:54:25', '');
INSERT INTO `sys_dict` VALUES ('8', 'Long', '', 'basics_java_type', '3', 'Long', '2', 'Long', 'String', '5', '1', null, '2018-04-15 02:57:07', '2018-04-15 02:57:07', '');
INSERT INTO `sys_dict` VALUES ('9', 'Float', '', 'basics_java_type', '3', 'Float', '2', 'Float', 'String', '6', '1', null, '2018-04-15 02:57:26', '2018-04-15 02:57:26', '');
INSERT INTO `sys_dict` VALUES ('10', 'Double', '', 'basics_java_type', '3', 'Double', '2', 'Double', 'String', '7', '1', null, '2018-04-15 02:58:11', '2018-04-15 02:58:11', '');
INSERT INTO `sys_dict` VALUES ('11', 'Char', '', 'basics_java_type', '3', 'Char', '2', 'Char', 'String', '8', '1', null, '2018-04-15 02:58:53', '2018-04-15 02:58:53', '');
INSERT INTO `sys_dict` VALUES ('12', 'Boolean', '', 'basics_java_type', '3', 'Boolean', '2', 'Boolean', 'String', '9', '1', null, '2018-04-15 02:59:38', '2018-04-15 02:59:38', '');
INSERT INTO `sys_dict` VALUES ('13', '字典类型', 'sys.dicttype', 'basics_dict_type', '2', null, '1', null, null, '2', '1', null, '2018-04-15 22:54:56', '2018-04-15 22:54:56', '');
INSERT INTO `sys_dict` VALUES ('14', '目录', 'sys.catalog', 'basics_dict_type', '13', '1', '2', 'folder', 'Integer', '1', '1', null, '2018-04-15 22:55:00', '2018-04-15 22:55:00', '');
INSERT INTO `sys_dict` VALUES ('15', '数值项', 'sys.value', 'basics_dict_type', '13', '2', '2', 'value', 'Integer', '2', '1', null, '2018-04-15 22:56:41', '2018-04-15 22:56:41', '');
INSERT INTO `sys_dict` VALUES ('16', '使用状态', 'sys.status', 'basics_use_status', '2', '', '1', '', '', '3', '1', null, '2018-04-16 00:13:24', '2018-04-16 00:13:24', '');
INSERT INTO `sys_dict` VALUES ('17', '启用', 'sys.enable', 'basics_use_status', '16', '1', '2', 'enable', 'Integer', '1', '1', null, '2018-04-16 00:13:53', '2018-04-16 00:13:53', '');
INSERT INTO `sys_dict` VALUES ('18', '禁用', 'sys.disable', 'basics_use_status', '16', '2', '2', 'disable', 'Integer', '2', '1', null, '2018-04-16 00:14:50', '2018-04-16 00:14:50', '');

-- ----------------------------
-- Records of sys_i18n
-- ----------------------------
INSERT INTO `sys_i18n` VALUES ('1', 'sys', 'login', '登陆', '登陆', 'Login', '2019-03-26 15:55:07', '2019-04-29 14:59:56');
INSERT INTO `sys_i18n` VALUES ('2', 'sys', 'logout', '注销', '注销', 'Exit', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('3', 'sys', 'userlogin', '用户登陆', '用户登陆', 'Sign Up', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('4', 'sys', 'username', '用户名', '用户名', 'User Account', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('5', 'sys', 'password', '密码', '密码', 'Password', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('6', 'sys', 'reset', '重置', '重置', 'Reset', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('7', 'sys', 'passworderror', '用户名或密码错误，请重试！', '用户名或密码错误，请重试！', 'Account Or Password Error!Try again later', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('8', 'sys', 'houtaiguanli', 'CMS后台管理系统', '后台管理', 'CMS Manager', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('9', 'sys', 'change', '修改', '修改', 'change', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('10', 'sys', 'update', '更新', '更新', 'update', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('11', 'sys', 'edit', '编辑', '编辑', 'edit', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('12', 'sys', 'save', '保存', '保存', 'save', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('13', 'sys', 'refresh', '刷新', '刷新', 'refresh', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('14', 'sys', 'close', '关闭', '关闭', 'close', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('15', 'sys', 'closeOtherTabs', '关闭其他', '关闭其他', 'close other tabs', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('16', 'sys', 'closeAllTabs', '关闭全部', '关闭全部', 'close all tabs', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('17', 'sys', 'closeRightTabs', '关闭右侧标签', '关闭右侧标签', 'close right tabs', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('18', 'sys', 'closeLeftTabs', '关闭左侧标签', '关闭左侧标签', 'close left tabs', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('19', 'sys', 'language', '语言', '语言', 'language', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('20', 'sys', 'set', '设置', '设置', 'manage', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('21', 'sys', 'promptMessage', '提示信息', '提示信息', 'Message', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('22', 'sys', 'networkError', '网络错误', '网络错误', 'Network Error', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('23', 'sys', 'role', '角色', '角色', 'role', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('24', 'sys', 'theme', '主题', '主题', 'theme', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('25', 'sys', 'default', '默认', '默认', 'default', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('26', 'sys', 'light', '亮', '亮', 'light', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('27', 'sys', 'black', '黑色', '黑色', 'black', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('28', 'sys', 'homepage', '首页', '首页', 'HomePage', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('29', 'sys', 'module', '模块', '模块', 'module', '2019-03-26 19:50:56', '2019-03-26 19:50:56');
INSERT INTO `sys_i18n` VALUES ('30', 'sys', 'chinese', '中文', '中文', 'chinese', '2019-03-26 19:52:37', '2019-03-26 19:52:37');
INSERT INTO `sys_i18n` VALUES ('31', 'sys', 'english', '英文', '英文', 'english', '2019-03-26 19:52:46', '2019-03-26 19:52:46');
INSERT INTO `sys_i18n` VALUES ('32', 'sys', 'indonesian', '印尼语', '印尼语', 'indonesian', '2019-03-26 19:53:23', '2019-03-26 19:53:23');
INSERT INTO `sys_i18n` VALUES ('33', 'sys', 'name', '名称', '名称', 'name', '2019-03-26 19:54:06', '2019-03-26 19:54:06');
INSERT INTO `sys_i18n` VALUES ('34', 'sys', 'add', '新增', '新增', 'add', '2019-03-26 20:10:27', '2019-03-26 20:10:27');
INSERT INTO `sys_i18n` VALUES ('35', 'sys', 'red', '红色', '红色', 'red', '2019-03-26 15:55:07', '2019-03-26 15:55:07');
INSERT INTO `sys_i18n` VALUES ('36', 'sys', 'blue', '蓝色', '蓝色', 'blue', '2019-03-26 20:41:36', '2019-03-26 20:41:36');
INSERT INTO `sys_i18n` VALUES ('37', 'sys', 'green', '绿色', '绿色', 'green', '2019-03-27 10:35:13', '2019-03-27 10:35:13');
INSERT INTO `sys_i18n` VALUES ('38', 'sys', 'purple', '紫色', '紫色', 'purple', '2019-03-27 10:35:29', '2019-03-27 10:35:29');
INSERT INTO `sys_i18n` VALUES ('39', 'sys', 'yellow', '橙色', '橙色', 'yellow', '2019-03-27 10:35:38', '2019-03-27 10:35:38');
INSERT INTO `sys_i18n` VALUES ('40', 'sys', 'remark', '备注', '备注', 'remark', '2019-04-04 10:38:21', '2019-04-04 10:38:21');
INSERT INTO `sys_i18n` VALUES ('41', 'sys', 'optional', '选填', '选填', 'optional', '2019-04-04 10:41:55', '2019-04-04 10:41:55');
INSERT INTO `sys_i18n` VALUES ('42', 'sys', 'search', '查询', '查询', 'search', '2019-03-27 11:24:41', '2019-03-27 11:24:41');
INSERT INTO `sys_i18n` VALUES ('43', 'sys', 'empty', '清空', '清空', 'empty', '2019-03-27 11:25:08', '2019-03-27 11:25:08');
INSERT INTO `sys_i18n` VALUES ('44', 'sys', 'status', '状态', '状态', 'status', '2019-03-27 11:52:03', '2019-03-27 11:52:03');
INSERT INTO `sys_i18n` VALUES ('45', 'sys', 'enable', '启用', '启用', 'enable', '2019-03-27 11:52:22', '2019-03-27 11:52:22');
INSERT INTO `sys_i18n` VALUES ('46', 'sys', 'disable', '禁用', '禁用', 'disable', '2019-03-27 11:52:42', '2019-03-27 11:52:42');
INSERT INTO `sys_i18n` VALUES ('47', 'sys', 'mustinsert', '必填', '必填', 'mustinsert', '2019-03-27 11:53:19', '2019-03-27 11:53:19');
INSERT INTO `sys_i18n` VALUES ('48', 'sys', 'dicttype', '字典类型', '字典类型', 'Type of Dict', '2019-03-27 11:53:39', '2019-03-27 11:53:39');
INSERT INTO `sys_i18n` VALUES ('49', 'sys', 'parent', '父级', '父级', 'parent', '2019-03-27 11:54:08', '2019-03-27 11:54:08');
INSERT INTO `sys_i18n` VALUES ('50', 'sys', 'value', '值', '值', 'value', '2019-03-27 13:52:53', '2019-03-27 13:52:53');
INSERT INTO `sys_i18n` VALUES ('51', 'sys', 'code', '代码', '代码', 'code', '2019-03-27 13:53:27', '2019-03-27 13:53:27');
INSERT INTO `sys_i18n` VALUES ('52', 'sys', 'javatype', 'java类型', 'java类型', 'Type of Java', '2019-03-27 13:54:20', '2019-03-27 13:54:20');
INSERT INTO `sys_i18n` VALUES ('53', 'sys', 'sort', '排序', '排序', 'sort', '2019-03-27 13:55:13', '2019-03-27 13:55:13');
INSERT INTO `sys_i18n` VALUES ('54', 'sys', 'pleasecheckrow', '请先选中数据', '请先选中数据', 'Please check at lease one row', '2019-03-27 14:02:41', '2019-03-27 14:02:41');
INSERT INTO `sys_i18n` VALUES ('55', 'sys', 'icon', '图标', '图标', 'icon', '2019-03-27 14:15:49', '2019-03-27 14:15:49');
INSERT INTO `sys_i18n` VALUES ('56', 'sys', 'url', '链接', '链接', 'url', '2019-03-27 14:16:45', '2019-03-27 14:16:45');
INSERT INTO `sys_i18n` VALUES ('57', 'sys', 'openorclose', '是否打开', '是否打开', 'open or close', '2019-03-27 14:18:11', '2019-03-27 14:18:11');
INSERT INTO `sys_i18n` VALUES ('58', 'sys', 'open', '打开', '打开', 'open', '2019-03-27 14:18:45', '2019-03-27 14:18:45');
INSERT INTO `sys_i18n` VALUES ('59', 'sys', 'resourcetype', '资源类型', '资源类型', 'Resource Type', '2019-03-27 14:21:38', '2019-03-27 14:21:38');
INSERT INTO `sys_i18n` VALUES ('60', 'sys', 'menu', '菜单', '菜单', 'menu', '2019-03-27 14:34:30', '2019-03-27 14:34:30');
INSERT INTO `sys_i18n` VALUES ('61', 'sys', 'window', '窗体', '窗体', 'window', '2019-03-27 14:34:47', '2019-03-27 14:34:47');
INSERT INTO `sys_i18n` VALUES ('62', 'sys', 'button', '按钮', '按钮', 'button', '2019-03-27 14:34:57', '2019-03-27 14:34:57');
INSERT INTO `sys_i18n` VALUES ('63', 'sys', 'level', '级别', '级别', 'level', '2019-03-27 14:47:54', '2019-03-27 14:47:54');
INSERT INTO `sys_i18n` VALUES ('64', 'sys', 'mobilephone', '联系电话', '联系电话', 'mobile', '2019-03-27 14:54:06', '2019-03-27 14:54:06');
INSERT INTO `sys_i18n` VALUES ('65', 'sys', 'realname', '真实姓名', '真实姓名', 'Real Name', '2019-03-27 15:16:10', '2019-03-27 15:16:10');
INSERT INTO `sys_i18n` VALUES ('66', 'sys', 'editpassword', '修改密码', '修改密码', 'Edit Password', '2019-03-27 15:35:48', '2019-03-27 15:35:48');
INSERT INTO `sys_i18n` VALUES ('67', 'sys', 'describe', '描述', '描述', 'describe', '2019-03-27 15:45:18', '2019-03-27 15:45:18');
INSERT INTO `sys_i18n` VALUES ('68', 'sys', 'authorized', '已授权', '已授权', 'authorized', '2019-03-27 15:48:44', '2019-03-27 15:48:44');
INSERT INTO `sys_i18n` VALUES ('69', 'sys', 'unauthorized', '未授权', '未授权', 'unauthorized', '2019-03-27 15:49:00', '2019-03-27 15:49:00');
INSERT INTO `sys_i18n` VALUES ('70', 'sys', 'confirmmsg', '确定要执行该操作吗？', '确定要执行该操作吗？', 'Are you sure you want to do this?', '2019-03-27 15:53:54', '2019-03-27 15:53:54');
INSERT INTO `sys_i18n` VALUES ('71', 'sys', 'pleasecheckleftrow', '请勾选左边的分组', '请勾选左边的分组', 'Please check the group on the left', '2019-03-27 15:55:32', '2019-03-27 15:55:32');
INSERT INTO `sys_i18n` VALUES ('72', 'sys', 'setauthorized', '赋予授权', '赋予授权', 'Give Authorized', '2019-03-27 15:57:34', '2019-03-27 15:57:34');
INSERT INTO `sys_i18n` VALUES ('73', 'sys', 'setunauthorized', '取消授权', '取消授权', 'Cancel Authorized', '2019-03-27 15:57:54', '2019-03-27 15:57:54');
INSERT INTO `sys_i18n` VALUES ('74', 'sys', 'addtime', '添加时间', '添加时间', 'Add Time', '2019-03-27 15:58:40', '2019-03-27 15:58:40');
INSERT INTO `sys_i18n` VALUES ('75', 'sys', 'updatetime', '更新时间', '更新时间', 'Update Time', '2019-03-27 15:58:58', '2019-03-27 15:58:58');
INSERT INTO `sys_i18n` VALUES ('76', 'sys', 'man', '男', '男', 'man', '2019-03-27 16:11:02', '2019-03-27 16:11:02');
INSERT INTO `sys_i18n` VALUES ('77', 'sys', 'women', '女', '女', 'women', '2019-03-27 16:11:19', '2019-03-27 16:11:19');
INSERT INTO `sys_i18n` VALUES ('78', 'sys', 'type', '类型', '类型', 'type', '2019-03-27 16:12:26', '2019-03-27 16:12:26');
INSERT INTO `sys_i18n` VALUES ('79', 'sys', 'yes', '是', '是', 'yes', '2019-03-27 20:02:03', '2019-03-27 20:02:03');
INSERT INTO `sys_i18n` VALUES ('80', 'sys', 'no', '否', '否', 'no', '2019-03-27 20:02:18', '2019-03-27 20:02:18');
INSERT INTO `sys_i18n` VALUES ('81', 'sys', 'corn', 'corn表达式', 'corn表达式', 'corn', '2019-03-27 20:03:44', '2019-03-27 20:03:44');
INSERT INTO `sys_i18n` VALUES ('82', 'sys', 'startwithrun', '是否随程序重启', '是否随程序重启', 'Start With Application Or Not', '2019-03-27 20:05:01', '2019-03-27 20:05:01');
INSERT INTO `sys_i18n` VALUES ('83', 'sys', 'param', '参数', '参数', 'param', '2019-03-27 20:07:10', '2019-03-27 20:07:10');
INSERT INTO `sys_i18n` VALUES ('84', 'sys', 'methodname', '方法名称', '方法名称', 'Method name', '2019-03-27 20:07:49', '2019-03-27 20:07:49');
INSERT INTO `sys_i18n` VALUES ('85', 'sys', 'isconcurrent', '是否可以并发', '是否可以并发', 'Can execute concurrently', '2019-03-27 20:11:29', '2019-03-27 20:11:29');
INSERT INTO `sys_i18n` VALUES ('86', 'sys', 'group', '分组', '分组', 'group', '2019-03-27 20:12:25', '2019-03-27 20:12:25');
INSERT INTO `sys_i18n` VALUES ('87', 'sys', 'classname', '类名', '类名', 'Class Name', '2019-03-27 20:13:24', '2019-03-27 20:13:24');
INSERT INTO `sys_i18n` VALUES ('88', 'sys', 'operate', '操作', '操作', 'operate', '2019-03-27 20:15:11', '2019-03-27 20:15:11');
INSERT INTO `sys_i18n` VALUES ('89', 'sys', 'test', '测试', '测试', 'test', '2019-03-27 20:19:02', '2019-03-27 20:19:02');
INSERT INTO `sys_i18n` VALUES ('90', 'sys', 'startedandlooklogs', '已执行，请查看日志！', '已执行，请查看日志！', 'Started and check your logs', '2019-03-27 20:23:23', '2019-03-27 20:23:23');
INSERT INTO `sys_i18n` VALUES ('91', 'sys', 'start', '启动', '启动', 'start', '2019-03-27 20:23:33', '2019-03-27 20:23:33');
INSERT INTO `sys_i18n` VALUES ('92', 'sys', 'stop', '停止', '停止', 'stop', '2019-03-27 20:23:42', '2019-03-27 20:23:42');
INSERT INTO `sys_i18n` VALUES ('93', 'sys', 'delete', '删除', '删除', 'delete', '2019-03-27 20:23:52', '2019-03-27 20:23:52');
INSERT INTO `sys_i18n` VALUES ('94', 'sys', 'runonce', '执行一次', '执行一次', 'Run it now', '2019-03-27 20:24:15', '2019-03-27 20:24:15');
INSERT INTO `sys_i18n` VALUES ('95', 'sys', 'catalog', '目录', '目录', 'catalog', '2019-03-28 19:10:58', '2019-03-28 19:10:58');
INSERT INTO `sys_i18n` VALUES ('96', 'sys', 'dictmanage', '字典管理', '字典管理', 'Dict Manage', '2019-03-28 19:11:58', '2019-03-28 19:11:58');
INSERT INTO `sys_i18n` VALUES ('97', 'sys', 'basemanage', '基础配置', '基础配置', 'Basic Manage', '2019-03-28 19:12:49', '2019-03-28 19:12:49');
INSERT INTO `sys_i18n` VALUES ('98', 'sys', 'internationalmanage', '国际化管理', '国际化管理', 'International Manage', '2019-03-28 19:13:42', '2019-03-28 19:13:42');
INSERT INTO `sys_i18n` VALUES ('99', 'sys', 'sysresourcemanage', '系统资源管理', '系统资源管理', 'System Resource Manage', '2019-03-28 19:14:12', '2019-03-28 19:14:12');
INSERT INTO `sys_i18n` VALUES ('100', 'sys', 'platformmanage', '平台管理', '平台管理', 'Platform Manage', '2019-03-28 19:16:01', '2019-03-28 19:16:01');
INSERT INTO `sys_i18n` VALUES ('101', 'sys', 'jurisdictionmanage', '权限配置', '权限配置', 'Authority Manage', '2019-03-28 19:16:29', '2019-03-28 19:16:29');
INSERT INTO `sys_i18n` VALUES ('102', 'sys', 'resourcesmanage', '资源管理', '资源管理', 'Resources Manage', '2019-03-28 19:16:42', '2019-03-28 19:16:42');
INSERT INTO `sys_i18n` VALUES ('103', 'sys', 'operatormanage', '操作员管理', '操作员管理', 'Operator Manage', '2019-03-28 19:17:01', '2019-03-28 19:17:01');
INSERT INTO `sys_i18n` VALUES ('104', 'sys', 'rolemanage', '角色管理', '角色管理', 'Role Manage', '2019-03-28 19:17:32', '2019-03-28 19:17:32');
INSERT INTO `sys_i18n` VALUES ('105', 'sys', 'systemmanage', '系统配置', '系统配置', 'Application Manage', '2019-03-28 19:17:53', '2019-03-28 19:17:53');
INSERT INTO `sys_i18n` VALUES ('106', 'sys', 'functionmanage', '功能管理', '功能管理', 'Functional Manage', '2019-03-28 19:18:51', '2019-03-28 19:18:51');
INSERT INTO `sys_i18n` VALUES ('107', 'sys', 'online', '上线', '上线', 'online', '2019-04-02 14:17:20', '2019-04-02 14:17:20');
INSERT INTO `sys_i18n` VALUES ('108', 'sys', 'offline', '下线', '下线', 'offline', '2019-04-02 14:17:30', '2019-04-02 14:17:30');
INSERT INTO `sys_i18n` VALUES ('109', 'sys', 'upperShelf', '上架', '上架', 'On The Shelves', '2019-04-02 14:18:17', '2019-04-02 14:18:17');
INSERT INTO `sys_i18n` VALUES ('110', 'sys', 'lowerShelf', '下架', '下架', 'Off The Shelves', '2019-04-02 14:18:27', '2019-04-02 14:18:27');
INSERT INTO `sys_i18n` VALUES ('111', 'sys', 'download', '下载', '下载', 'download', '2019-04-02 16:53:33', '2019-04-02 16:53:33');
INSERT INTO `sys_i18n` VALUES ('112', 'sys', 'view', '查看', '查看', 'view', '2019-04-02 17:25:30', '2019-04-02 17:25:30');
INSERT INTO `sys_i18n` VALUES ('113', 'sys', 'lastUpdateOperator', '最近修改人', '最近修改人', 'The last operated User', '2019-04-02 17:47:57', '2019-04-02 17:47:57');
INSERT INTO `sys_i18n` VALUES ('114', 'sys', 'addOperator', '创建人', '创建人', 'Add Operator', '2019-04-03 10:55:56', '2019-04-03 10:55:56');
INSERT INTO `sys_i18n` VALUES ('115', 'sys', 'cancel', '取消', '取消', 'cancel', '2019-04-03 11:39:32', '2019-04-03 11:39:32');
INSERT INTO `sys_i18n` VALUES ('116', 'sys', 'tips', '提示', '提示', 'tips', '2019-04-17 15:14:56', '2019-04-17 15:14:56');
INSERT INTO `sys_i18n` VALUES ('117', 'sys', 'errorMessage', '错误信息', '错误信息', 'Error Message', '2019-04-17 15:16:33', '2019-04-17 15:16:33');
INSERT INTO `sys_i18n` VALUES ('118', 'sys', 'copy', '复制', '复制', 'copy', '2019-04-17 15:37:18', '2019-04-17 15:37:18');
INSERT INTO `sys_i18n` VALUES ('119', 'sys', 'log', '日志', '日志', 'log', '2019-04-17 15:37:38', '2019-04-17 15:37:38');
INSERT INTO `sys_i18n` VALUES ('120', 'sys', 'submit', '提交', '提交', 'submit', '2019-04-17 15:41:27', '2019-04-17 15:41:27');
INSERT INTO `sys_i18n` VALUES ('121', 'sys', 'allSelect', '全选', '全选', 'Select All', '2019-04-17 17:03:01', '2019-04-17 17:03:01');
INSERT INTO `sys_i18n` VALUES ('122', 'sys', 'deleteTemplate', '删除模板', '删除模板', 'Delete Template', '2019-04-17 17:17:34', '2019-04-17 17:17:34');
INSERT INTO `sys_i18n` VALUES ('123', 'sys', 'operationTips', '操作提示', '操作提示', 'Operation Tips', '2019-04-17 17:18:41', '2019-04-17 17:18:41');
INSERT INTO `sys_i18n` VALUES ('124', 'sys', 'accountNumber', '账号', '账号', 'account', '2019-04-17 17:26:08', '2019-04-17 17:26:08');
INSERT INTO `sys_i18n` VALUES ('125', 'sys', 'pleaseSelectDeleteData', '请选择要删除的数据', '请选择要删除的数据', 'Please select the data to delete', '2019-04-17 17:28:15', '2019-04-17 17:28:15');
INSERT INTO `sys_i18n` VALUES ('126', 'sys', 'confirmDelete', '确认删除？', '确认删除？', 'Are you sure?', '2019-04-17 17:29:08', '2019-04-17 17:29:08');
INSERT INTO `sys_i18n` VALUES ('127', 'sys', 'operateSuccess', '操作成功', '操作成功', 'Operate Successfully', '2019-04-17 17:38:03', '2019-04-17 17:38:03');
INSERT INTO `sys_i18n` VALUES ('128', 'sys', 'moreQueries', '更多查询', '更多查询', 'Query More', '2019-04-17 17:41:15', '2019-04-17 17:41:15');
INSERT INTO `sys_i18n` VALUES ('129', 'sys', 'saveScreeningCondition', '保存筛选条件', '保存筛选条件', 'Save filter conditions', '2019-04-17 17:42:02', '2019-04-17 17:42:02');
INSERT INTO `sys_i18n` VALUES ('130', 'sys', 'moveDown', '下移↓', '下移↓', 'Move Down', '2019-04-17 17:46:36', '2019-04-17 17:46:36');
INSERT INTO `sys_i18n` VALUES ('131', 'sys', 'moveUp', '上移↑', '上移↑', 'Move Up', '2019-04-17 17:47:24', '2019-04-17 17:47:24');
INSERT INTO `sys_i18n` VALUES ('132', 'sys', 'whole', '全部', '全部', 'All', '2019-04-17 17:51:23', '2019-04-17 17:51:23');
INSERT INTO `sys_i18n` VALUES ('133', 'sys', 'confirm', '确认', '确认', 'Confirm', '2019-04-17 17:55:19', '2019-04-17 17:55:19');

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('-1', '国际化管理', 'sys.internationalmanage', '7', '0', '/sys/sysI18n/sysI18nIndex', '', '1', '2', 'sys:sysI18n:sysI18nIndex', 'open', 'window', '2019-03-13 01:13:08', NULL, '2019-03-13 01:13:08', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('1', '系统资源管理', 'sys.sysresourcemanage', '0', '1', '', '', '1', '1', NULL, 'closed', 'menu', '2018-03-20 13:25:54', NULL, '2018-03-20 13:25:54', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('2', '平台管理', 'sys.platformmanage', '1', '2', '', '', '1', '3', NULL, 'closed', 'menu', '2018-03-20 13:26:36', NULL, '2018-03-20 13:26:36', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('3', '权限配置', 'sys.jurisdictionmanage', '2', '3', '', '', '1', '1', NULL, 'closed', 'menu', '2018-03-20 13:27:05', NULL, '2018-03-20 13:27:05', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('4', '资源管理', 'sys.resourcesmanage', '3', '4', '/sys/sysMenu/sysMenuIndex', '', '1', '1', 'sys:sysMenu:sysMenuIndex', 'open', 'window', '2018-03-20 13:23:20', NULL, '2018-03-20 13:23:20', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('5', '操作员管理', 'sys.operatormanage', '3', '4', '/sys/sysOperate/sysOperateIndex', '', '1', '2', 'sys:sysOperate:sysOperateIndex', 'open', 'window', '2018-04-12 13:07:35', NULL, '2018-04-12 13:07:35', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('6', '角色管理', 'sys.rolemanage', '3', '4', '/sys/sysRole/sysRole', '', '1', '3', 'sys:sysRole:sysRole', 'open', 'window', '2018-04-12 13:08:28', NULL, '2018-04-12 13:08:28', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('7', '系统配置', 'sys.systemmanage', '2', '3', '', '', '1', '2', NULL, 'closed', 'menu', '2018-04-12 13:12:00', NULL, '2018-04-12 13:12:00', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('8', '字典管理', 'sys.dictmanage', '7', '4', '/sys/sysDict/sysDictIndex', '', '1', '1', 'sys:sysDict:sysDictIndex', 'open', 'window', '2018-04-12 13:17:25', NULL, '2018-04-12 13:17:25', NULL, '');
INSERT INTO `sys_menu` (`id`, `name`, `i18n_nid`, `pid`, `level_id`, `url`, `icon_cls`, `status`, `sort`, `code`, `state`, `resource_type`, `add_time`, `add_user`, `update_time`, `update_user`, `remark`) VALUES ('9', '功能管理', 'sys.functionmanage', '1', '2', '', '', '1', '1', NULL, 'closed', 'menu', '2018-04-12 13:19:58', NULL, '2018-04-12 13:19:58', NULL, '');

-- ----------------------------
-- Records of sys_operate
-- ----------------------------
INSERT INTO `sys_operate` VALUES ('1', 'admin', '管理员马云', '0192023A7BBD73250516F069DF18B500', '18819192218', '2019-08-11 16:52:59', '2019-08-11 16:52:59', null, null, '2019-08-11 16:55:55', '0');

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('-1', '1', '-1', '2019-03-13 00:33:28', '2019-03-13 00:33:28');
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6', '2018-04-02 20:56:30', '2018-04-02 20:56:30');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7', '2018-04-09 19:01:22', '2018-04-09 19:01:22');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8', '2018-03-30 16:04:45', '2018-03-30 16:04:45');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9', '2018-03-30 16:04:45', '2018-03-30 16:04:45');

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员', null, null, '1');

-- ----------------------------
-- Records of sys_role_operate
-- ----------------------------
INSERT INTO `sys_role_operate` VALUES ('1', '1', '1');