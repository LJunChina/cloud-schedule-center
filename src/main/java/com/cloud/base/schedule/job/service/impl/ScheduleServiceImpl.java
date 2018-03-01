package com.cloud.base.schedule.job.service.impl;

import com.cloud.base.schedule.job.service.ScheduleService;
import com.cloud.common.dto.BaseRespDTO;
import com.cloud.common.enums.ResultCode;
import com.cloud.common.util.EmptyChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jon_China
 * @create 2018/3/1
 */
//@Service(value = "scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseRespDTO getUserInfo(String id) {
        if(EmptyChecker.isEmpty(id)){
            return new BaseRespDTO(ResultCode.PARAMS_NOT_FOUND);
        }
        try {
            BaseRespDTO baseRespDTO = this.restTemplate.getForEntity("http://user-microservice/get-user-detail/{1}",BaseRespDTO.class,id).getBody();
            String result = baseRespDTO.toString();
            LOGGER.info("result of getUserInfo : {}",result);
            return baseRespDTO;
        }catch (Exception e){
            LOGGER.error("exception occurred in getUserInfo:",e);
            return new BaseRespDTO(ResultCode.FAIL);
        }
    }
}
