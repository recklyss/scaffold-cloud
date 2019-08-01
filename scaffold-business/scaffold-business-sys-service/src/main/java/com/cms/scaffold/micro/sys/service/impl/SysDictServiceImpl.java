package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.micro.sys.dao.SysDictMapper;
import com.cms.scaffold.micro.sys.domain.SysDict;
import com.cms.scaffold.micro.sys.service.SysDictService;
import com.cms.scaffold.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {
}
