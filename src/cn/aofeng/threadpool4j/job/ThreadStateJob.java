package cn.aofeng.threadpool4j.job;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.aofeng.threadpool4j.ThreadStateInfo;
import cn.aofeng.threadpool4j.ThreadUtil;

/**
 * 收集所有线程组中所有线程的状态信息，统计并输出汇总信息。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class ThreadStateJob extends AbstractJob {

    private static Logger _logger = LoggerFactory.getLogger(ThreadStateJob.class);
    
    public ThreadStateJob(int interval) {
        super._interval = interval;
    }

    @Override
    protected void execute() {
        Map<String, ThreadStateInfo> statMap = ThreadUtil.statAllGroupThreadState();
        
        for (Entry<String, ThreadStateInfo> entry : statMap.entrySet()) {
            ThreadStateInfo stateInfo = entry.getValue();
            _logger.info("ThreadGroup:{"+entry.getKey()+"}, New:{"+stateInfo.getNewCount()+"},  Runnable:{"+
                            stateInfo.getRunnableCount()+"}, Blocked:{"+stateInfo.getBlockedCount()+"}, Waiting:{"+
                            stateInfo.getWaitingCount()+"}, TimedWaiting:{"+stateInfo.getTimedWaitingCount()+"}, Terminated:{"+stateInfo.getTerminatedCount()+"}");
        }
        
        super.sleep();
    } // end of execute

}
