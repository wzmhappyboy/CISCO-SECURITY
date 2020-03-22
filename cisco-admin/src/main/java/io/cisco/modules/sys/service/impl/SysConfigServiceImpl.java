/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import io.cisco.common.exception.RRException;
import io.cisco.modules.sys.dao.SysConfigDao;
import io.cisco.modules.sys.entity.SysConfigEntity;
import io.cisco.modules.sys.redis.SysConfigRedis;
import io.cisco.modules.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl  implements SysConfigService {
	@Autowired
	private SysConfigRedis sysConfigRedis;
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public PageInfo<SysConfigEntity> queryPage(Map<String, Object> params) {
//		String paramKey = (String)params.get("paramKey");
//		IPage<SysConfigEntity> page = this.page(
//			new Query<SysConfigEntity>().getPage(params),
//			new QueryWrapper<SysConfigEntity>()
//				.like(StringUtils.isNotBlank(paramKey),"param_key", paramKey)
//				.eq("status", 1)
//		);
//
//		return new PageUtils(page);

		List<SysConfigEntity> list=sysConfigDao.queryPage();
		return new PageInfo<>(list);

	}
	
	@Override
	public void saveConfig(SysConfigEntity config) {
		sysConfigDao.save(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		sysConfigDao.updateById(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] ids) {
		for(Long id : ids){
			SysConfigEntity config = sysConfigDao.getById(id);
			sysConfigRedis.delete(config.getParamKey());
		}

		//this.removeByIds(Arrays.asList(ids));
	}

	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if(config == null){
			config = sysConfigDao.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getParamValue();
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}

	@Override
	public SysConfigEntity getById(long id){
		return sysConfigDao.getById(id);
	}


}
