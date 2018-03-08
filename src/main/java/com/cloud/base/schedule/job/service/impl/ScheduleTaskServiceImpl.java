package com.cloud.base.schedule.job.service.impl;

import com.cloud.base.schedule.job.dao.ScheduleTaskDao;
import com.cloud.base.schedule.job.enums.JobTypeEnum;
import com.cloud.base.schedule.job.model.ScheduleTask;
import com.cloud.base.schedule.job.service.ScheduleTaskService;
import com.cloud.base.schedule.job.web.dto.ScheduleTaskRequest;
import com.cloud.common.dto.BaseRespDTO;
import com.cloud.common.enums.ResultCode;
import com.cloud.common.enums.YesOrNoEnum;
import com.cloud.common.util.EmptyChecker;
import com.netflix.ribbon.proxy.annotation.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Jon_China
 * @create 2018/3/1
 */
@Service(value = "scheduleTaskService")
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ScheduleTaskDao scheduleTaskDao;

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

    @Override
    public String scheduleRemoteJob(String uri,String method, Map<String, Object> params) {
        if(EmptyChecker.isEmpty(uri)){
            return new BaseRespDTO(ResultCode.PARAMS_NOT_FOUND).toString();
        }
        try {
            String result;
            //目前只处理GET,POST请求
            if(Http.HttpMethod.GET.name().equalsIgnoreCase(method)){
                result = this.restTemplate.getForEntity(uri,String.class, params.get("id")).getBody();
            }else {
                MultiValueMap<String,Object> valueMap = new LinkedMultiValueMap<>();
                valueMap.setAll(params);
                result = this.restTemplate.postForEntity(uri,null,String.class,params).getBody();
            }
            LOGGER.info("result of scheduleRemoteJob : {}",result);
            return result;
        }catch (Exception e){
            LOGGER.error("exception occurred in scheduleRemoteJob:",e);
            return new BaseRespDTO(ResultCode.FAIL).toString();
        }
    }

    /**
     * 新增任务
     *
     * @param request
     * @return
     */
    @Override
    public BaseRespDTO addScheduleTask(ScheduleTaskRequest request) {
        ScheduleTask task = new ScheduleTask();
        BeanCopier copier = BeanCopier.create(ScheduleTaskRequest.class, ScheduleTask.class,false);
        copier.copy(request,task,null);
        task.setJobType(JobTypeEnum.REMOTE.getCode());
        task.setJobStatus(YesOrNoEnum.NO.getCode());
        task.setIsConcurrent(YesOrNoEnum.NO.getCode());
        int effectRow = this.scheduleTaskDao.save(task);
        if(effectRow == 1){
            return new BaseRespDTO();
        }
        return new BaseRespDTO(ResultCode.FAIL);
    }
}
