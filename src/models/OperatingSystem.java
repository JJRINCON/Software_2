package models;

import java.util.ArrayList;
import java.util.Collections;

public class OperatingSystem {

	private Queue<MyProcess> processQueueReady;
	private ArrayList<MyProcess> readyAndDespachado;
	private ArrayList<MyProcess> locked;
	private ArrayList<MyProcess> wakeUp;
	private ArrayList<MyProcess> suspended;
	private ArrayList<MyProcess> reanude;
	private ArrayList<MyProcess> executing;
	private ArrayList<MyProcess> expired;
	private ArrayList<MyProcess> destroyed;
	private ArrayList<MyProcess> processTerminated;

	public OperatingSystem() {
		this.processQueueReady = new Queue<>();
		this.locked = new ArrayList<>();
		this.wakeUp = new ArrayList<>();
		this.processTerminated = new ArrayList<>();
		this.suspended = new ArrayList<>();
		this.executing = new ArrayList<>();
		this.expired = new ArrayList<>();
		this.readyAndDespachado = new ArrayList<>();
		this.destroyed = new ArrayList<>();
		this.reanude = new ArrayList<>();
	}

	public boolean addProcess(MyProcess myProcess) {
		if (search(myProcess.getName()) == null) {
			addProcess(readyAndDespachado, myProcess, false);
			processQueueReady.push(myProcess, myProcess.getPriority());
			return true;
		}
		return false;
	}

	/**
	 * Me avisa si no funciona, xd
	 * 
	 * @param actualName
	 * @param name
	 * @param time
	 * @param states
	 */
	public void editProcess(String actualName, String name, int time, int priority,boolean ... states) {
		edit(search(actualName), name, time, priority, states);
		edit(searchInList(actualName, readyAndDespachado), name, time, priority, states);
	}

	private void edit(MyProcess myProcess, String name, int time, int priority, boolean ... states) {
		myProcess.setName(name);
		myProcess.updateTime(time);
		myProcess.setPriority(priority);
		myProcess.setLocked(states[0]);
		myProcess.setSuspended(states[1]);
		myProcess.setDestroid(states[2]);
		MyProcess temp = myProcess;
		deleteProccess(name);
		addProcess(temp);
	}

	/**
	 * Eliminar de la cola y de la lista de listos
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteProccess(String name) {
		boolean isDelete = false;
		Node<MyProcess> temp = processQueueReady.peek();
		readyAndDespachado.remove(searchInList(name, readyAndDespachado));
		if (temp.getData().getName().equals(name)) {
			processQueueReady.pop();
			isDelete = true;
		} else {
			isDelete = deleteProcess(name, isDelete, temp);
		}
		return isDelete;
	}

	private boolean deleteProcess(String name, boolean isDelete, Node<MyProcess> temp) {
		while (temp.getNext() != null) {
			if (temp.getNext().getData().getName().equals(name)) {
				temp.setNext(temp.getNext().getNext());
				isDelete = true;
			} else {
				temp = temp.getNext();
			}
		}
		return isDelete;
	}

	private MyProcess searchInList(String name, ArrayList<MyProcess> myProcesses) {
		for (MyProcess myProcess : myProcesses) {
			if (name.equals(myProcess.getName())) {
				return myProcess;
			}
		}
		return null;
	}

	public MyProcess search(String name) {
		Node<MyProcess> temp = processQueueReady.peek();
		while (temp != null) {
			if (temp.getData().getName().equals(name)) {
				return temp.getData();
			} else {
				temp = temp.getNext();
			}
		}
		return null;
	}

	public void startSimulation() {
		while (!processQueueReady.isEmpty()) {
			MyProcess process = processQueueReady.peek().getData();
			valideSystemTimer(process);
		}
		Collections.sort(readyAndDespachado);
	}

	private void valideSystemTimer(MyProcess process) {
		addProcess(executing, process, true);
		if ((process.getTime() - 5) > 0) {
			proccessTimeDiscount(process);
		} else {
			MyProcess myProcess = processQueueReady.pop();
			myProcess.setTime((int) myProcess.getTime());
			addProcess(processTerminated, myProcess, false);
		}
	}

	private void proccessTimeDiscount(MyProcess process ) {
		process.setTime(5);
		valideLocked(process);	
	}

	private void valideLocked(MyProcess process) {
		if (process.isLocked()) {
			addProcess(locked, process, false);
			valideSuspended(process);
			valideDestroyed(process);
		} else if (process.isSuspended()) {
			addProcess(suspended, process, false);
			valideDestroyed(process);
		} else if(process.isDestroid()) {
			valideDestroyed(process);
		}else {
			addProcess(expired, process, false);
			addProcess(readyAndDespachado, process, false);
			MyProcess myProcess = processQueueReady.pop();
			processQueueReady.push(myProcess, myProcess.getPriority());
		}
	}

	private void valideDestroyed(MyProcess process) {
		if (process.isDestroid()) {		
			addProcess(executing,process,true);
			addProcess(destroyed, process, false);
			if ((process.getTime() - 5) > 0) {
				process.setTime(5);
				addProcess(expired, process, false);
				addProcess(readyAndDespachado, process, false);
				MyProcess myProcess = processQueueReady.pop();
				processQueueReady.push(myProcess, myProcess.getPriority());
			}else {
				MyProcess myProcess = processQueueReady.pop();
				myProcess.setTime((int) myProcess.getTime());
				addProcess(processTerminated, myProcess, false);
			}
		}else {
			addProcess(reanude, process, false);
		}
	}

	private void valideSuspended(MyProcess process) {
		
		if (process.isSuspended()) {
			addProcess(suspended, process, false);
		} else {
			addProcess(wakeUp, process, false);
		}
	}

	private void addProcess(ArrayList<MyProcess> myProcesses, MyProcess myProcess, boolean isExecuting) {
		boolean[] states = new boolean[] { myProcess.isLocked(), myProcess.isSuspended(), myProcess.isDestroid(),
				myProcess.isComunication() };
		if (isExecuting) {
			myProcesses
					.add(new MyProcess(myProcess.getName(), (myProcess.getTime() - 5 < 0 ? 0 : myProcess.getTime() - 5),
							myProcess.getPriority(), states));
		} else {
			myProcesses.add(new MyProcess(myProcess.getName(), myProcess.getTime(), myProcess.getPriority(), states));
		}
	}

	/**
	 * 
	 * @return Los procesos que se van agregando a la lista, estos toca ir
	 *         actualizando cada que se agregan a la interfaz
	 */
	public Queue<MyProcess> getProcessQueue() {
		return processQueueReady;
	}

	public void verifyProcessName(String name) throws Exception {
		Node<MyProcess> temp = processQueueReady.peek();
		while (temp != null) {
			if (temp.getData().getName().equals(name)) {
				throw new Exception("Nombre de proceso no disponible");
			}
			temp = temp.getNext();
		}
	}

	public void show() {

		System.out.println("Listos y despachados");
		for (MyProcess myProcess : readyAndDespachado) {
			System.out.println(myProcess.toString());
		}

		System.out.println("En ejecucion");
		for (MyProcess myProcess : executing) {
			System.out.println(myProcess.toString());
		}

		System.out.println("Expirados");
		for (MyProcess myProcess : expired) {
			System.out.println(myProcess.toString());
		}

		System.out.println("Bloqueo, bloqueado, despertar");
		for (MyProcess myProcess : locked) {
			System.out.println(myProcess.toString());
		}

		System.out.println("Suspender  reanudad");
		for (MyProcess myProcess : suspended) {
			System.out.println(myProcess.toString());
		}

		System.out.println("Destruir");
		for (MyProcess myProcess : destroyed) {
			System.out.println(myProcess.toString());
		}
		
		
		System.out.println("Terminados");
		for (MyProcess myProcess : processTerminated) {
			System.out.println(myProcess.toString());
		}
		
	}

	public ArrayList<MyProcess> getProcessQueueLocked() {
		return locked;
	}

	public ArrayList<MyProcess> getWakeUp() {
		return wakeUp;
	}

	/**
	 * 
	 * @return Procesos terminados
	 */
	public ArrayList<MyProcess> getProcessTerminated() {
		return processTerminated;
	}

	/**
	 * 
	 * @return Lista de los procesos listos
	 */
	public ArrayList<MyProcess> getReadyProccess() {
		return readyAndDespachado;
	}

	/**
	 * 
	 * @return Procesos despachados
	 */
	public ArrayList<MyProcess> getProcessDespachados() {

		return readyAndDespachado;
	}

	/**
	 * 
	 * @return Processos en ejecucion
	 */
	public ArrayList<MyProcess> getExecuting() {
		return executing;
	}

	/**
	 * 
	 * @return Procesos expirados
	 */
	public ArrayList<MyProcess> getProcessExpired() {
		return expired;
	}

	/**
	 * 
	 * @return Los que pasan a bloqueado
	 */
	public ArrayList<MyProcess> getProcessToLocked() {
		return locked;
	}

	/**
	 * 
	 * @return Porcesos bloqueados
	 */
	public ArrayList<MyProcess> getProcessLocked() {
		return locked;
	}

	/**
	 * 
	 * @return Procesos despertados
	 */
	public ArrayList<MyProcess> getProcessWakeUp() {
		return wakeUp;
	}

	public ArrayList<MyProcess> getSuspended() {
		return suspended;
	}
	
	public ArrayList<MyProcess> getDestroyed() {
		return destroyed;
	}

	public static Object[][] processInfo(ArrayList<MyProcess> processes) {
		Object[][] processInfo = new Object[processes.size()][3];
		for (int i = 0; i < processes.size(); i++) {
			processInfo[i][0] = processes.get(i).getName();
			processInfo[i][1] = processes.get(i).getTime();
			processInfo[i][2] = processes.get(i).isLocked();
		}
		return processInfo;
	}

	public static void main(String[] args) {
		OperatingSystem operatingSystem = new OperatingSystem();
		operatingSystem.addProcess(new MyProcess("P1", 15, 2, new boolean[] { true, true, true, false }));
		operatingSystem.addProcess(new MyProcess("P2", 20, 1, new boolean[] { true, false, true, false }));
		operatingSystem.addProcess(new MyProcess("P3", 13, 3, new boolean[] { false, false, true, false }));

		operatingSystem.startSimulation();

		operatingSystem.show();
	}

}
