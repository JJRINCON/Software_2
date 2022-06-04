package models;

public class Queue<T> {

	private Node<T> firstNode;

	public Queue() {
		this.firstNode = null;
	}

	public void push(T data, int priority) {
		Node<T> temp = new Node<T>(data, priority);
		if (firstNode == null) {
			firstNode = temp;
		}else if(firstNode.getPriority() > priority ){
			Node<T> aux = firstNode;
			firstNode = temp;
			firstNode.setNext(aux);
		}else{
			pushPriority(temp);
		}

	}

	
	private void pushPriority(Node<T> temp) {
		Node<T> tempNode = firstNode;
		while (tempNode != null  ) {
				tempNode = validePushByPriority(temp, tempNode);
		}
	}

	private Node<T> validePushByPriority(Node<T> temp, Node<T> tempNode) {
		if ( tempNode.getNext().getPriority()> temp.getPriority()) {
			tempNode = pushByPriorty(temp, tempNode);
		}else {
			tempNode = valideFinishQueueProprity(temp, tempNode);
		}
		return tempNode;
	}

	private Node<T> pushByPriorty(Node<T> temp, Node<T> tempNode) {
		temp.setNext(tempNode.getNext());
		tempNode.setNext(temp);
		tempNode = null;
		return tempNode;
	}

	private Node<T> valideFinishQueueProprity(Node<T> temp, Node<T> tempNode) {
		tempNode = tempNode.getNext();
		if(tempNode.getNext()==null) {
			tempNode.setNext(temp);
			tempNode = null;
		}
		return tempNode;
	}
	
	
	public T pop() {
		Node<T> tempNode = firstNode;
		firstNode = firstNode.getNext();
		return tempNode.getData();
	}

	public int size(){
		int size = 0;
		Node<T> actualNode = firstNode;
		while(actualNode != null){
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
