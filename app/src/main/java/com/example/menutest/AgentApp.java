package com.example.menutest;
import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;


/**
 * 点击保存进度或者直接退出后退出应用
 */
public class AgentApp extends Application {
	
	private List<Activity> activities=new ArrayList<Activity>();
	private static AgentApp instance;
	private AgentApp(){}
	public static AgentApp getInstangce() {
		if (null==instance) {
			instance=new AgentApp();
		}
		return instance;
	}
	
	public void addActivity (Activity activity) {
		activities.add(activity);
		
	}
	
	public void onTerminate() {
		super.onTerminate();
		for (Activity activity:activities) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}

