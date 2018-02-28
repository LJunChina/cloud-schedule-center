package com.cloud.base.schedule.job.dao;

import com.cloud.base.schedule.job.CloudScheduleCenterApplicationTests;
import com.cloud.base.schedule.job.model.ScheduleTask;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务查询单元测试
 *
 * @author Jon_China
 * @create 2018/2/28
 */
public class ScheduleTaskDaoTest extends CloudScheduleCenterApplicationTests {

    @Autowired
    private ScheduleTaskDao scheduleTaskDao;

    @Test
    public void testGetAllTasks(){
        ScheduleTask task = new ScheduleTask();
        task.setBeanClass("com.cloud.base.schedule.job.task.ScheduleTask");
        task.setCronExpression("0/4 * * ? * * *");
        task.setDescription("描述信息");
        task.setIsConcurrent("0");
        task.setJobGroup("group");
        task.setJobName("name");
        task.setJobStatus("0");
        task.setMethodName("invoke");
        task.setRemoteUri("/template/1");
        Assert.assertTrue(this.scheduleTaskDao.save(task) == 1);
        Assert.assertNotNull(this.scheduleTaskDao.getAllTasks());
    }
}
