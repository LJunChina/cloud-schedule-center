package com.cloud.base.schedule.job.dao;

import com.cloud.base.schedule.job.model.ScheduleTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定时任务数据访问
 *
 * @author Jon_China
 * @create 2018/2/28
 */
@Repository(value = "scheduleTaskDao")
public interface ScheduleTaskDao {

    List<ScheduleTask> getAllTasks();

    int save(ScheduleTask scheduleTask);
}
