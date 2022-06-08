package models;

public class MyProcess implements Comparable<MyProcess>{

	
	private String name, nameComunicationProcess;
	private double time;
	private int priority;
	private boolean locked, suspended,destroid, comunication;

	/**
	 * 
	 * @param name
	 * @param time
	 * @param priority
	 * @param states la lsitica de estados para no llenar tanto el constructor, xd :3
	 */
	public MyProcess(String name, double time,int priority,String nameComunicationProcess, boolean ... states ) {
		super();
		this.name = name;
		this.time = time;
		this.priority = priority;
		this.nameComunicationProcess = nameComunicationProcess;
		this.locked = states[0];
		this.suspended = states[1];
		this.destroid = states[2];
		this.comunication = states[3];
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
	public boolean isDestroid() {
		return destroid;
	}
	public void setDestroid(boolean destroid) {
		this.destroid = destroid;
	}

	public String getNameComunicationProcess() {
		return nameComunicationProcess;
	}
	
	public void setNameComunicationProcess(String nameComunicationProcess) {
		this.nameComunicationProcess = nameComunicationProcess;
	}
	@Override
	public int compareTo(MyProcess p) {
		return getPriority()-p.getPriority();
	}
	
	@Override
	public String toString() {
		return getName() +" " +getTime() + " " + getPriority()+""+
				getNameComunicationProcess() + " "+ isLocked() + " "+ 
				isSuspended() + " "+ isDestroid() + " "+ isComunication();
		}

}
