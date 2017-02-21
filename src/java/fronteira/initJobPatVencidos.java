/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import util.AgenteVerificadorNegociosPendentes;
import util.AgenteVerificadorPatrimoniosVencidos;

/**
 *
 * @author joaosantos
 */
public class initJobPatVencidos {

    public static final String PATR_VENCIDO_JOB = "PatVenc";
    public static void inicio() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(AgenteVerificadorPatrimoniosVencidos.class)
                .withIdentity(PATR_VENCIDO_JOB, "group1")
                .build();

        //        #script de backup TODO DIA AS 3 DA MANHA
        //* 3 * * * root /root/backup.sh
        CronTrigger trigger = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("0 50 23 * * ?"))
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        
        

        sched.start();
        Thread.sleep(10L * 1000L);
//        sched.shutdown(true);
    }
}
