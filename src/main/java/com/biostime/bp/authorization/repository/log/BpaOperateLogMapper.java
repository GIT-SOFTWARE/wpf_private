package com.biostime.bp.authorization.repository.log;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.biostime.bp.authorization.domain.log.BpaOperateLog;
import tk.mybatis.mapper.common.Mapper;

public interface BpaOperateLogMapper extends Mapper<BpaOperateLog> {
	public BpaOperateLog queryLastResetPasswordOrLoginSuccessOperateLogByUid(@Param("uid")Long uid,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
}