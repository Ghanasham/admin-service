package com.softcell.adminservice.domain;

public class ManagerCircularLinkedList {

	private Manager head;
	
	private Manager tail;
	
	private Manager current;
	
	public ManagerCircularLinkedList(){
		
	}
	
	public synchronized void add(Manager manager){
		
		if(head == null){
			
			head = tail = current = manager;
			head.next = head; //self loop
		}else{
			
			tail.next = manager;
			tail = tail.next;
			tail.next = head;
		}
	}
	
	public synchronized Manager getNext(){
		Manager manager = current;
		current = current.next;
		return manager;
	}
	
	public synchronized void delete(Manager manager){
		
		if(head != null){
			
			//single element and match found
			if(head.equals(manager) && head.next == null)
				head = null;
			
			//single element but match not found
			if(head.next == null)
				return;
			else{
				
				Manager temp2 = head;
				Manager temp1 = head.next;
				
				while(!temp1.equals(head)){
					if(temp1.equals(manager)){
						temp2.next = temp1.next;
						temp1.next = null;
						break;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
			}
			
			
		}
	}
}
