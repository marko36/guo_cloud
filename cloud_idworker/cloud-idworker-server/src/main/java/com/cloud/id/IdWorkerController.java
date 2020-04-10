package com.cloud.id;

import com.jc.cloud.common.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/idworker")
public class IdWorkerController {
    @RequestMapping("/list")
    public ObjectRestResponse list(String ipu){
        try {
            StringBuilder answer = new StringBuilder();
            if(!StringUtils.isEmpty(ipu)) {
                answer.append(WorkerIdServerLock.list(ipu));
            } else {
                answer.append(WorkerIdServerLock.list());
            }
            return new ObjectRestResponse().data(answer.toString()).rel(true);
        }catch (Exception e){
            log.error("idwork list error:{0}",e.getMessage());
            return new ObjectRestResponse().data("生成id失败").rel(false);
        }
    }
    @RequestMapping("/see")
    public ObjectRestResponse see(){
        StringBuilder answer = new StringBuilder();
        answer.append(WorkerIdServerLock.current());
        return new ObjectRestResponse().data(answer.toString()).rel(true);
    }
    @RequestMapping("/inc")
    public ObjectRestResponse inc(String ipu){
        StringBuilder answer = new StringBuilder();
        if (!StringUtils.isEmpty(ipu)) {
            String increment = WorkerIdServerLock.incr(ipu);
            answer.append(increment);
        } else {
            answer.append("ipu is required");
        }
        return new ObjectRestResponse().data(answer.toString()).rel(true);
    }

    @RequestMapping("/sync")
    public ObjectRestResponse sync(String ipu,String ids){
        StringBuilder answer = new StringBuilder();
        if (ipu != null && ipu.length() > 0 && ids != null && ids.length() > 0)
            answer.append(WorkerIdServerLock.sync(ipu, ids));
        return new ObjectRestResponse().data(answer.toString()).rel(true);
    }
}
