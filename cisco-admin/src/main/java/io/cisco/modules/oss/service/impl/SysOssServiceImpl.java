/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.cisco.modules.oss.dao.SysOssDao;
import io.cisco.modules.oss.entity.SysOssEntity;
import io.cisco.modules.oss.service.SysOssService;
import io.cisco.modules.sys.entity.SysConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

//	@Override
//	public PageInfo<SysOssEntity> queryPage(Map<String, Object> params) {
//		if (params.get("paramKey")==null||params.get("paramKey").equals("")){
//
//			int page = Integer.parseInt(params.get("page").toString());
//			int limit = Integer.parseInt(params.get("limit").toString());
//			PageHelper.startPage(page,limit);
//
//
//			List<SysConfigEntity> list=sysConfigDao.queryPage();
//			return new PageInfo<>(list);}
//		else {
//			String key = (String)params.get("paramKey");
//			List<SysConfigEntity> list=sysConfigDao.getByName(key);
//			return new PageInfo<>(list);
//		}
//	}
//
}
