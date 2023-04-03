package com.xmr;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xmr on 2020/3/11.
 */
@Component
public class MyTask {
//    @Scheduled(cron = "0/10 * * * * *")
//    public void tenSecondTask() {
//        System.out.println("每10秒执行一次：" + getDate());
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    @Scheduled(cron = "0 55 14 ? * *")
    public void regularTimeTask() {
        System.out.println("每天上午14点55分执行：" + getDate());
    }

    @Scheduled(fixedRate = 10 * 1000)
    public void fiveSecondTate() {
        System.out.println("固定10秒执行一次：" + getDate());
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedDelay = 1000 * 10)
//    public void tenSecondDelayTask() {
//        System.out.println("间隔10秒执行一次：" + getDate());
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
