package at.iaik.websharkgwt.server.webserverstorage;

import java.util.HashSet;


public class TaskControllerSingleton {
	static private TaskControllerSingleton taskStore;
	public static TaskControllerSingleton getInstance() {
		if (taskStore == null) {
			taskStore = new TaskControllerSingleton();
		}
		return taskStore;
	}
	
	private HashSet<String> runningTasks = new HashSet<String>();
	private HashSet<String> tasksCalledToStop = new HashSet<String>();
	
	public boolean isTaskRunning(String sessionID) {
		return runningTasks.contains(sessionID);
	}
	
	public boolean isTaskBeingStopped(String sessionID) {
		return tasksCalledToStop.contains(sessionID);
	}
	
	public boolean runTask(String sessionID,Thread threadToRun) {
		if (runningTasks.contains(sessionID)) {
			return false;
		}
		tasksCalledToStop.remove(sessionID);
		runningTasks.add(sessionID);
		
		threadToRun.start();
		return true;
	}
	
	public boolean stopTask(String sessionID) {
		if (runningTasks.contains(sessionID)) {
			tasksCalledToStop.add(sessionID);
			System.out.println("#########+++++++++++++++++++++++#####################");
			return true;
		} else {
			return false;
		}
	}
	
	public void removeTask(String sessionID) {
		tasksCalledToStop.remove(sessionID);
		runningTasks.remove(sessionID);
	}
	
	
}
