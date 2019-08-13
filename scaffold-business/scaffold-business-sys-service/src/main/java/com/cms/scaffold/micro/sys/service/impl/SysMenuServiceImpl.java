package com.cms.scaffold.micro.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.common.base.BaseServiceImpl;
import com.cms.scaffold.micro.sys.dao.SysMenuMapper;
import com.cms.scaffold.micro.sys.domain.SysMenu;
import com.cms.scaffold.micro.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    public List<SysMenu> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

    @Override
    public List<SysMenu> findByOperateId(Long id) {
        return dao.findByOperateId(id);
    }

    @Override
    public List<SysMenu> findByPidAndOperateId(Long pId, Long operateId) {
        return dao.findByPidAndOperateId(pId, operateId);
    }

    @Override
    public int saveOrUpdate(SysMenu menu) {
        SysMenu parentSysMenu = dao.selectById(menu.getPid());
        menu.setLevelId(parentSysMenu.getLevelId() + 1);
        //判断设置地址链接
        if (StrUtil.isNotBlank(menu.getUrl())) {
            if (menu.getUrl().indexOf("/") == 0) {
                menu.setCode(menu.getUrl().
                        replaceFirst("/", "").
                        replaceAll("/", ":"));
            } else {
                menu.setCode(menu.getUrl().replaceAll("/", ":"));
            }
        }
        //保存
        if (menu.getId() == null) {
            return dao.insert(menu);
        } else {
            return dao.update(menu);
        }
    }

    @Override
    public String findFatherIds(Long id) {
        StringJoiner pids = new StringJoiner(",");
        recursionFindPid(pids, id);
        return pids.toString();
    }

    private void recursionFindPid(StringJoiner ids,Long id){
        if(null == id || id == 0){
            return;
        }
        Long pid = dao.findPid(id);
        ids.add(pid.toString());
        recursionFindPid(ids,pid);
    }
}
