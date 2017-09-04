package net.dagene.pmis.util;

import java.util.Timer;
import java.util.TimerTask;

public class SliceUtil {
	public SliceUtil(){
		System.out.println("这是SilceUtil");
		getSliceListFromMySQL();
	}
	
	private static void getSliceListFromMySQL(){
		Timer timer = new Timer(); 
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
            }  
        }, 2000);// 设定指定的时间time,此处为2000毫秒  		
	}

}
