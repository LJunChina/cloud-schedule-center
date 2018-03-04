package com.cloud.base.schedule.job.service;

import com.cloud.common.dto.BaseRespDTO;

import java.util.Map;

/**
 * @author Jon_China
 * @create 2018/3/1
 */
public interface ScheduleTaskService {

    BaseRespDTO getUserInfo(String id);


    /**
     * 远程调用任务
     * @param uri
     * @param params
     * @param method
     * @return
     */
    String scheduleRemoteJob(String uri, String method,Map<String,Object> params);

}
