/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service.impl;

import com.github.pagehelper.PageInfo;
import io.cisco.modules.sys.dao.SysLogDao;
import io.cisco.modules.sys.entity.SysLogEntity;
import io.cisco.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl  implements SysLogService {
@Autowired
SysLogDao sysLogDao;
    @Override
    public PageInfo<SysLogEntity> queryPage(Map<String, Object> params) {
        List<SysLogEntity> list=sysLogDao.queryPage();
        return new PageInfo<>(list);
    }

    @Override
    public void save(SysLogEntity sysLogEntity){
        sysLogDao.save(sysLogEntity);
    }
}
