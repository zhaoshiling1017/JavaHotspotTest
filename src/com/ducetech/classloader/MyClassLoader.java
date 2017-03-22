package com.ducetech.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {
    //类存放的路径  
    private String path = "/home/lenzhao/code/";
	public MyClassLoader() {
	}
	
	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file = getClassFile(name);
		try {
			byte[] bytes = getClassBytes(file);
			Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
	    }
		return super.findClass(name);
	}
	
	@Override
	protected URL findResource(String name) {
		URL url = null;
		try {
			url = new URL("file://" + path + name);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	private File getClassFile(String name) {
		name = name.replace(".", "/");
		File file = new File(path + name + ".class");
		return file;
	}
	    
	private byte[] getClassBytes(File file) throws Exception {
		//io方式读取
        /*FileInputStream is = new FileInputStream(file);  
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
         int b = 0;  
         while ((b = is.read()) != -1) {  
             baos.write(b);  
         }  
         return baos.toByteArray(); */ 
		
		// 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);
        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }
}
