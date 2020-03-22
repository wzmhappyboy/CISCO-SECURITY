/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;


import io.cisco.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysConfigDao  {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

	List<SysConfigEntity> queryPage();

	void save(SysConfigEntity sysConfigEntity);

	void updateById(SysConfigEntity sysConfigEntity);

	SysConfigEntity getById(Long id);

	void  deleteById(Long id);
}
