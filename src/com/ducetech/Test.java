package com.ducetech;




public class Test {
	public static void main(String[] args) throws Exception {
		String clzName = Test.class.getName();
		String[] strs = clzName.split("\\."); 
		int len = strs.length;
		String serviceName = strs[len - 1] + "Impl";
		System.out.println(serviceName);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len -1; i++) {
			buffer.append(strs[i] + ".");
		}
		buffer.append("impl" + "." + serviceName);
		System.out.println(buffer.toString());
	}
}
