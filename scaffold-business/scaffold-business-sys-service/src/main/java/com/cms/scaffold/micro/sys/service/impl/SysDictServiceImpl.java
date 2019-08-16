package com.cms.scaffold.micro.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cms.scaffold.code.util.JedisUtil;
import com.cms.scaffold.common.base.BaseServiceImpl;
import com.cms.scaffold.common.base.BaseStatusCode;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.constant.BasicsConstant;
import com.cms.scaffold.common.constant.CacheConstant;
import com.cms.scaffold.common.exception.BusinessException;
import com.cms.scaffold.micro.sys.dao.SysDictMapper;
import com.cms.scaffold.micro.sys.domain.SysDict;
import com.cms.scaffold.micro.sys.service.SysDictService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Administrator
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Override
    public List<SysDict> findByNid(String nid) {
        return dao.findByNid(nid);
    }

    @Override
    public List<SysDict> findSysDictByPid(Long parentId) {
        return dao.listByPid(parentId);
    }

    @Override
    public List<SysDict> findByPartnerNid(String nid) {
        List<SysDict> list;
        Object obj = JedisUtil.getObjectMapField(CacheConstant.SYS_DICT_NID, nid);
        if (obj == null) {
            list = dao.findByPartnerNid(nid);
            if (list != null && list.size() > 0) {
                JedisUtil.hset(CacheConstant.SYS_DICT_NID, nid, list);
            }
        } else {
            list = (List<SysDict>) obj;
        }
        return list;

    }

    @Override
    public void loadDictIntoRedis() {
        List<SysDict> list = dao.findByPartnerNid("");
        Map<String, Object> map = new HashMap<>();
        Set<String> set = new HashSet<String>();
        for (SysDict dict : list) {
            List<SysDict> sysList;
            String nid = dict.getNid();
            set.add(nid);
            if (map.containsKey(nid)) {
                sysList = (List<SysDict>) map.get(nid);
            } else {
                sysList = new ArrayList();
            }
            sysList.add(dict);
            map.put(nid, sysList);
        }
        if (map.size() != 0) {
            JedisUtil.setObjectMap(CacheConstant.SYS_DICT_NID, map, 0);
        }

        //遍历
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String nid = iterator.next();
            List<SysDict> nidList = dao.findByPartnerNid(nid);
            Map<String, String> hashMap = new HashMap<>();
            for (SysDict sysDict : nidList) {
                hashMap.put(sysDict.getValue(), sysDict.getName());
            }
            if (hashMap.size() != 0) {
                JedisUtil.setMap(String.format(CacheConstant.SYS_DICT_NID_NAME, nid),
                        hashMap, 0);
            }
        }

    }

    @Override
    public int save(SysDict dict) {
        SysDict parentDict = dao.selectById(dict.getPid());
        if (BasicsConstant.BASICS_DICT_TYPE_FOLDER == dict.getType().intValue()) {
            if (StrUtil.isBlank(parentDict.getNid())) {
                dict.setNid(dict.getNid());
            } else {
                dict.setNid(parentDict.getNid() + "_" + dict.getNid());
            }
        } else {
            dict.setNid(parentDict.getNid());
        }
        //保存
        if (dict.getId() == null) {
            dao.insert(Builder.build(dict, SysDict.class));
        } else {
            SysDict tempSysDict = dao.selectById(dict.getId());
            if (!tempSysDict.getPid().equals(dict.getPid())) {
                throw new BusinessException(BaseStatusCode.DICT_PID_ERROR);
            }
            dao.updateAll(dict);
        }
        JedisUtil.mapObjectRemove(CacheConstant.SYS_DICT_NID, parentDict.getNid());
        return 1;
    }

    @Override
    public String findFatherIds(Long id) {
        StringBuilder ids = new StringBuilder();
        recursionFindPid(ids, id);
        return ids.toString().replaceFirst(",", "");
    }

    private void recursionFindPid(StringBuilder ids, Long id) {
        if (id == 0) {
            return;
        }
        Long pid = dao.findPid(id);
        ids.append(",").append(pid);
        recursionFindPid(ids, pid);
    }
}
