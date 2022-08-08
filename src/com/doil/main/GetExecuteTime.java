/**
 * 
 */
package com.doil.main;

import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


/**
 * @author doil
 *
 */
public class GetExecuteTime {
	
	/**
	 * 실행시간 측정결과 output
	 * @author doil
	 *
	 */
	public class DataWrapper{
		
		public Object data;
		private long time;
		
		public DataWrapper(Object data) {
			super();
			this.data = data;
		}
		
		
		public DataWrapper(Object data, long time) {
			super();
			this.data = data;
			this.time = time;
		}

		public Object getData() {
			return data;
		}

		public long getTime() {
			return time;
		}
		
	}
	
	public Object getExecutionTime(String methodName , Object[] parameters , boolean isUseExecuteArea) {
		
		
		/******* 예외처리 구간 *******/
		StackTraceElement[] ste = new Throwable().getStackTrace();
		
		try {
			if(ste.length < 1) 
				throw new Exception("err");
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		
		StackTraceElement caller = ste[1];
		Method method = null;
		
		try {
			 method = findMethod(caller , methodName);
			if(method == null)
				throw new Exception("err: cannot find that method");
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		////////////////////////////

		
		long startTime = System.currentTimeMillis();
		System.out.printf("\n[Method Name : %s] \nstartTime : %s\n" , methodName , currentTimeMillis(startTime));
		if(isUseExecuteArea) makeStartLine("execution area");
		
		
		Object result = null;
		try {
			// 메소드 실행
			 result = method.invoke(caller.getClassName() , parameters);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
        long endTime = System.currentTimeMillis();
        if(isUseExecuteArea) makeEndLine();
        System.out.printf("finishTime : %s (%.3f sec)\n" , currentTimeMillis(endTime) , diffTime(startTime , endTime) * 0.001);
        
        return result;
	}
	
	public Object getExecutionTime(String methodName , Object[] parameters , boolean isUseExecuteArea , String msg) {
		
		
		/******* 예외처리 구간 *******/
		StackTraceElement[] ste = new Throwable().getStackTrace();
		
		try {
			if(ste.length < 1) 
				throw new Exception("err");
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		
		StackTraceElement caller = ste[1];
		Method method = null;
		
		try {
			 method = findMethod(caller , methodName);
			if(method == null)
				throw new Exception("err: cannot find that method");
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		////////////////////////////

		
		long startTime = System.currentTimeMillis();
		
		System.out.printf("\n[Method Name : %s]\nMSG : %s\nstartTime : %s\n", methodName , msg , currentTimeMillis(startTime));
		if(isUseExecuteArea)
			makeStartLine("execution area");
		
		
		Object result = null;
		try {
			// 메소드 실행
			 result = method.invoke(caller.getClassName() , parameters);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
        long endTime = System.currentTimeMillis();
        if(isUseExecuteArea) 
        	makeEndLine();
        System.out.printf("finishTime : %s (%.3f sec)\n" , currentTimeMillis(endTime) , diffTime(startTime , endTime) * 0.001);
        
        return result;
	}

	
	
	public DataWrapper getJustExecutionTime(String methodName , Object[] parameters) {
		
		
		/******* 예외처리 구간 *******/
		StackTraceElement[] ste = new Throwable().getStackTrace();
		
		try {
			if(ste.length < 1) 
				throw new Exception("err");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

		
		StackTraceElement caller = ste[1];
		Method method = null;
		
		try {
			 method = findMethod(caller , methodName);
			if(method == null)
				throw new Exception("err: cannot find that method");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		////////////////////////////

		
		long startTime = System.currentTimeMillis();
		Object result = null;
		try {
			// 메소드 실행
			 result = method.invoke(caller.getClassName() , parameters);
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
        long endTime = System.currentTimeMillis();
        
        
        DataWrapper output = new DataWrapper(result , diffTime(startTime , endTime));
        
        return output;
	}
	
	
	
	
	private Method findMethod(StackTraceElement caller, String methodName)  {
		
		Class<?> clazz;
		Object instance;
		try {
			clazz = Class.forName(caller.getClassName());
			instance = clazz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		List<Method> methods = Arrays.asList( instance.getClass().getDeclaredMethods() );
		

		
		
		Method result = methods.stream()
							.filter( item -> item.getName().equals(methodName))
							.findFirst()
							.orElse(null);
		
		return result;
	}
	
	private long diffTime(Long startTime , Long endTime) {
		return (endTime - startTime);
	}
	
	private String currentTimeMillis(long curTime) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS zz");
		return timeFormat.format(new Date(curTime));
	}
	
	private void makeStartLine(String msg) {
		int msgLength = msg.length();
		
		
		String line = "--------------------------------------";
		int barSize =  ( (line.length() - msgLength) / 2 ) - 2;
		for(int i = 0 ; i < barSize; i++) System.out.print("-");
		System.out.printf(" [%s] ",msg);
		for(int i = 0 ; i < barSize; i++) System.out.print("-");
		System.out.println();
	}
	
	private void makeEndLine() {
		String line = "--------------------------------------";
		System.out.println(line);
	}
	
	public void makeHardLine() {
		String line = "===========================================";
		System.out.println(line);
	}
	
	
        
        

}
