package com.softcell.adminservice.domain;

public class CircularLinkedList<T> {

	private Node<T> head;
	
	private Node<T> tail;
	
	private Node<T> current;
	
	private static class Node<T>{
		
		T data;
		Node<T> next;
		
		Node(T element, Node<T> next){
			this.data = element;
			this.next = next;
		}
	}
	
	public synchronized void add(T element){
		
		
		if(head == null){
			
			//self loop
			Node<T> node = new Node<>(element, null);
			head = tail = current = node;
			head.next = head;
			
		}else{
			Node<T> node = new Node<>(element, head);
			tail.next = node;
			tail = tail.next;
			
		}
	}
	
	public synchronized T getNext(){
		if(current != null){
			Node<T> node = current;
			current = current.next;
			return node.data;
		}
		return null;
	}
	
	public synchronized void delete(T element){
		
		if(head != null){
			
			//single element and match found
			if(head.data.equals(element) && head.next == head){
				head = current = tail = null;
				return;
			}
			//single element but match not found
			if(head.next == head)
				return;
			else{
				
				Node<T> temp2 = head;
				Node<T> temp1 = head.next;
				
				while(!temp1.data.equals(head.data)){
					if(temp1.data.equals(element)){
						temp2.next = temp1.next;
						temp1.next = null;
						
						if(temp1 == current)
							current = temp2.next;
						
						break;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
			}
			
			
		}
	}
}
