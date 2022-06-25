package models;

public class Queue<T> {

	private Node<T> firstNode;
	private Node<T> finishNode;

	public Queue() {
		this.firstNode = null;
		this.finishNode= null;
	}

	public void push(T data, int priority) {
		Node<T> temp = new Node<T>(data, priority);
		if (firstNode == null) {
			firstNode = temp;
		} else if (firstNode.getPriority() > priority) {
			Node<T> aux = firstNode;
			firstNode = temp;
			firstNode.setNext(aux);
		} else {
			pushPriority(temp);
		}

	}

	public void pushReady(T data, int priority) {
		Node<T> temp = new Node<T>(data, 0);
		if (firstNode == null) {
			firstNode = temp;
			finishNode= temp;
		}else {
			finishNode.setNext(temp);
			finishNode = finishNode.getNext();
		}
	}

	public void show() {
		Node<T> temp = firstNode;
		while (temp != null) {
			System.out.println(temp.getData() + " " + temp.getPriority());
			temp = temp.getNext();
		}

	}

	private void pushPriority(Node<T> temp) {
		Node<T> tempNode = firstNode;
		while (tempNode != null ) {
			tempNode = validePriorityPush(temp, tempNode);
		}
	}

	private Node<T> validePriorityPush(Node<T> temp, Node<T> tempNode) {
		if(tempNode.getPriority() > temp.getPriority()) {
			tempNode = priorityPush(temp, tempNode);
		}else if(tempNode.getNext()==null) {
				tempNode.setNext(temp);
				tempNode = null;
		}else {
			tempNode = tempNode.getNext();				
		}
		return tempNode;
	}

	private Node<T> priorityPush(Node<T> temp, Node<T> tempNode) {
		temp.setNext(tempNode);
		searchDad(tempNode).setNext(temp);
		tempNode = null;
		return tempNode;
	}

	private Node<T> searchDad(Node<T> node) {
		Node<T> tempNode = firstNode;
		while (tempNode != null) {
			if (tempNode.getNext().equals(node)) {
				return tempNode;
			}
			tempNode = tempNode.getNext();
		}
		return null;
	}

	public T pop() {
		Node<T> tempNode = firstNode;
		firstNode = firstNode.getNext();
		return tempNode.getData();
	}

	public int size() {
		int size = 0;
		Node<T> actualNode = firstNode;
		while (actualNode != null) {
			size++;
			actualNode = actualNode.getNext();
		}
		return size;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	public Node<T> peek() {
		return firstNode;
	}


}
