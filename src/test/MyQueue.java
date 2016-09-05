package test;

import java.util.Stack;

public class MyQueue<T> {
	
	private Stack<T> inStack, outStack;
	private int size;
	
	MyQueue(){
		inStack=new Stack<T>();
		outStack=new Stack<T>();
	}
	
	public boolean enQueue(T t){
		boolean isEnqueued=true;		
		try{
			System.out.println("Enqueue : "+t.toString());
			inStack.push(t);			
		}catch (Exception e){
			isEnqueued=false;
		}
		return isEnqueued;
	}
	
	public int size(){
		return inStack.size()+outStack.size();
	}
	
	public boolean isEmpty(){
		return size()==0?true:false;
	}
	
	public T deQueue(){
		T t=null;
		try{
		
		if(inStack.isEmpty() && outStack.isEmpty())
		 System.out.println("there are no elements in the queue");
		
		if(outStack.isEmpty()){
			 while(!inStack.isEmpty()){
				 outStack.push(inStack.pop());
			 }
		}
		 t = outStack.pop();
		
		
		}catch(Exception e){
			System.out.println("Error occurred");			
		}
		System.out.println("Dequeue : "+t.toString());
		return 	t;
	}
	
	public static void main(String [] args){
		MyQueue<Object> q = new MyQueue<>();
		
		System.out.println("Queue is empty? : "+q.isEmpty());		
		q.enQueue(new Integer(50));	
		System.out.println("Queue is empty? : "+q.isEmpty());
		q.enQueue(new String("String item"));
		q.enQueue(new Integer(90));
		while(!q.isEmpty()){
			q.deQueue();		
		}		
		System.out.println("Queue is empty? "+q.isEmpty());
		
	}
	

}
