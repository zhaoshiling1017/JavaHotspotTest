package com.ducetech.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);  
		  
		executorService.execute(new Runnable() {  
		    public void run() {  
		        System.out.println(Thread.currentThread().getName());  
		    }  
		});  
		executorService.execute(new Runnable() {  
		    public void run() {  
		        System.out.println(Thread.currentThread().getName());  
		    }  
		});  
		 System.out.println(Thread.currentThread().getName()+"--"+Thread.currentThread().getId());
		 executorService.shutdown(); 
	}
}
