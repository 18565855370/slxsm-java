package com.slxsm.tracelog;

import com.sun.corba.se.impl.presentation.rmi.IDLTypesUtil;

import java.util.Random;

/**
 * 采用InheritableThreadLocal追踪日志
 */
public class Demo1 {

    /**
     * 生成永不重复的ID
     */
    static class IDUtil{

        public static String getId(){
            long millis = System.currentTimeMillis();
            Random random = new Random();
            int end3 = random.nextInt(999);
            // 如果不足三位前面补0
            return millis + String .format("%03d",end3);
        }
    }

    static class TraceRunnable implements Runnable{

        private String tranceId;
        private Runnable target;

        public TraceRunnable(String tranceId, Runnable target){
            this.tranceId = tranceId;
            this.target = target;
        }

        public void run() {
            TraceUtil.set(this.tranceId);
            //MDC.........TODO
        }
    }

    static class TraceUtil{
        private static final String REQUEST_HEADER_TRACE_ID = "com.ms.header.trace.id";
        private static final String MDC_TRACE_ID = "trace_id";

        private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();

        public static String getTraceId(){
            String traceId = inheritableThreadLocal.get();
            if (traceId == null){
                traceId = IDUtil.getId();
                inheritableThreadLocal.set(traceId);
            }
            return traceId;
        }

        public static void set(String traceId){
            inheritableThreadLocal.set(traceId);
        }

        public static void remove(String traceId){
            inheritableThreadLocal.remove();
        }

    }
}
