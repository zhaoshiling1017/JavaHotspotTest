package com.ducetech.classloader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestMyClassLoader {
	
	public static void main(String[] args) throws Exception {
		MyClassLoader mcl = new MyClassLoader();
		Thread.currentThread().setContextClassLoader(mcl);
		//MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
		Class<?> c1 = Class.forName("com.ducetech.classloader.Person", true, mcl);
		Object obj = c1.newInstance();
		System.out.println(obj);
		System.out.println(obj.getClass().getClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
		InputStream in = mcl.getResourceAsStream("com/ducetech/classloader/Person.java");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));      
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {      
            sb.append(line + "\n");      
        }
		in.close();
		System.out.println(sb.toString());
	}

}
