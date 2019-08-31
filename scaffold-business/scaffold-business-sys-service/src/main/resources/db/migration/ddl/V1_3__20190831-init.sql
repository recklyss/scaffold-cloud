-- 创建MQ消息表
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message_id` varchar(255) NOT NULL COMMENT '消息ID',
  `message_key` varchar(255) NOT NULL COMMENT '消息KEY',
  `operate` varchar(255) NOT NULL DEFAULT '' COMMENT '操作',
  `request_no` varchar(255) NOT NULL DEFAULT '' COMMENT '请求流水号',
  `status` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '1-消费正常 2-消费异常',
  `request_param` varchar(255) NOT NULL DEFAULT '' COMMENT '请求参数',
  `response_param` varchar(255) NOT NULL DEFAULT '' COMMENT '响应参数',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `trigger_num` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '请求次数',
  `tags` varchar(255) NOT NULL COMMENT '消息TAG',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';