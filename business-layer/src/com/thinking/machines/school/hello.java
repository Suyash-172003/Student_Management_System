package com.thinking.machines.school.bl;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import java.io.*;
import java.net.*;
import java.text.*;
class ServerException extends Exception
{
	public ServerException(String message)
	{
		super(message);
	}
}
class RequestProcessor extends Thread
{
		private Socket socket;
		public RequestProcessor(Socket socket)
		{
			this.socket=socket;
			this.start();
		}
		public void run()
		{
			try
			{
	                InputStream is;
			InputStreamReader isr;
			OutputStream os;
			OutputStreamWriter osw;
			String requestString;
			String responseString=" ";
			int oneByte;
			StringBuffer sb;

			int rollNumber;
			String name;
			char gender;
			String command;
			char isIndian;
			java.util.Date dateOfBirth;
			String splits[];
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

				is=this.socket.getInputStream();
				isr=new InputStreamReader(is);
				sb=new StringBuffer();
	while(true)
				{
					oneByte=isr.read();
					if(oneByte==-1) break;
					if(oneByte=='#') break;
					sb.append((char)oneByte);
				}
				requestString=sb.toString();
				System.out.println("Request arrived :"+requestString);
				splits=requestString.split(",");
				command=splits[0];
				if(command.equals("add"))
				{
				rollNumber=Integer.parseInt(splits[1]);
				name=splits[2];
	                        gender=splits[3].charAt(0);
				isIndian=splits[4].charAt(0);
				dateOfBirth=sdf.parse(splits[5]);
				System.out.println("Roll Number :"+rollNumber);
				System.out.println("Name :"+name);
				System.out.println("Gender :"+gender);
				System.out.println("IsIndian :"+isIndian);
				System.out.println("Date of birth :"+dateOfBirth);

				StudentBeanInterface studentBeanInterface;
				studentBeanInterface=new StudentBean();
				studentBeanInterface.setRollNumber(rollNumber);
				studentBeanInterface.setName(name);
				if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
				else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
				else studentBeanInterface.setGender(GENDER.TRANSGENDER);
				if(isIndian=='Y') studentBeanInterface.setIsIndian(true);
				else studentBeanInterface.setIsIndian(false);
				studentBeanInterface.setDateOfBirth(dateOfBirth);

				StudentManagerInterface studentManagerInterface;
				studentManagerInterface=new StudentManager();
				try
				{
					studentManagerInterface.add(studentBeanInterface);
					responseString="true#";
				}catch(BLException blException)
				{
					responseString="false"+blException.getMessage()+'#';
				}
				}
				os=this.socket.getOutputStream();
				osw=new OutputStreamWriter(os);
				osw.write(responseString);
				osw.flush();
		System.out.println("Response sent :"+responseString);
				this.socket.close();
		}catch(Exception exception)
		{
			System.out.println(exception);
		}
	}
	}
class MultiThreadedChotaServer 
{
	private ServerSocket serverSocket;
	private int portNumber;
	public MultiThreadedChotaServer(int portNumber) throws ServerException
	{
		this.portNumber=portNumber;
		try
		{
			this.serverSocket=new ServerSocket(this.portNumber);
		}catch(Exception e)
		{
			throw new ServerException(e.getMessage());
		}
	}
	public void startServer()
	{
		try
		{
		Socket cs;
		while(true)
		{
			System.out.println("Server is ready to accept connection on:"+this.portNumber);
			cs=this.serverSocket.accept();
			new RequestProcessor(cs);
		}
	}catch(Exception exception)
		{
			System.out.println(exception);
		}
	}
public static void main(String args[])
{
	if(args.length!=1)
	{
		System.out.println("Usage [java MultiThreadedChotaServer port_number]");
		return;
	}
	try
	{
		int portNumber=Integer.parseInt(args[0]);
		if(portNumber<1 || portNumber>65535	{
			throw new ServerException("Port number should be between 1 and 65535");
		}
		MultiThreadedChotaServer mtcs;
		mtcs=new MultiThreadedChotaServer(portNumber);
		mtcs.startServer();
	}catch(ServerException serverException)
	{
		System.out.println(serverException.getMessage());
	}
	catch(NumberFormatException numberFormatException)
	{
		System.out.println("Port number should be number");
	}
}
}

