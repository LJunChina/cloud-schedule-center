package com.cloud.base.schedule.job.web;

import com.alibaba.fastjson.JSONObject;
import com.cloud.base.schedule.job.service.ScheduleTaskService;
import com.cloud.base.schedule.job.web.dto.ScheduleTaskRequest;
import com.cloud.common.dto.BaseRespDTO;
import com.cloud.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务API
 *
 * @author Jon_China
 * @create 2018/3/4
 */
@RestController
@RequestMapping(value = "/schedule-task")
public class ScheduleTaskResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskResource.class);

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    /**
     * 新增job
     * @param body
     * @return
     */
    @PostMapping(value = "/add")
    public String addTask(@RequestBody String body){
        LOGGER.info("params of addTask:",body);
        try {
            ScheduleTaskRequest request = JSONObject.parseObject(body, ScheduleTaskRequest.class);
            BaseRespDTO baseRespDTO = this.scheduleTaskService.addScheduleTask(request);
            String result = baseRespDTO.toString();
            LOGGER.info("result of addTask:",result);
            return result;
        }catch (Exception e){
            LOGGER.error("exception occurred in addTask:",e);
            return new BaseRespDTO(ResultCode.ERROR).toString();
        }
    }
}
