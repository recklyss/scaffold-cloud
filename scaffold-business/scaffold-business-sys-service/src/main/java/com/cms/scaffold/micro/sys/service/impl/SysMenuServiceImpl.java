package com.cms.scaffold.micro.sys.service.impl;

import com.cms.scaffold.micro.sys.dao.SysMenuMapper;
import com.cms.scaffold.micro.sys.domain.SysMenu;
import com.cms.scaffold.micro.sys.service.SysMenuService;
import com.cms.scaffold.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    public List<SysMenu> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }
}
