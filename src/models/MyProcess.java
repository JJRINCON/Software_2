package models;

public class MyProcess {

	
	private String name;
	private double time;
	private int priority;
	private boolean locked, suspended, comunication;

	/**
	 * 
	 * @param name
	 * @param time
	 * @param priority
	 * @param states la lsitica de estados para no llenar tanto el constructor, xd :3
	 */
	public MyProcess(String name, double time,int priority, boolean ... states ) {
		super();
		this.name = name;
		this.time = time;
		this.priority = priority;
		this.locked = states[0];
		this.suspended = states[1];
		this.comunication = states[2];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = (this.time-time);
	}

	public void updateTime(int time){
		this.time = time;
	}

	public boolean isLocked() {
		return locked;
	}
		
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public boolean isComunication() {
		return comunication;
	}
	
	public boolean isSuspended() {
		return suspended;
	}
	public void setComunication(boolean comunication) {
		this.comunication = comunication;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
}
