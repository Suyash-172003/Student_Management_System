package com.thinking.machines.school;
import java.io.*;
import java.net.*;
import com.thinking.machines.school.bl.exceptions.*;
public class ChotaClient
{
			private String server;
			private int portNumber;
			public ChotaClient(String server,int portNumber)
			{
				this.server=server;
				this.portNumber=portNumber;
			}
			public String sendRequest(String request) throws ServerException
			{
try
{
			Socket client=new Socket(this.server,this.portNumber);
			OutputStream os;
			os=client.getOutputStream();
			OutputStreamWriter osw;
			osw=new OutputStreamWriter(os);
			osw.write(request);
			osw.flush();
			System.out.println("Request sent :"+request);

			InputStream is;
			is=client.getInputStream();
			InputStreamReader isr;
			isr=new InputStreamReader(is);
			StringBuffer sb=new StringBuffer();
			int oneByte;
			while(true)
			{
				oneByte=isr.read();
				if(oneByte==-1) break;
				if(oneByte=='#') break;
				sb.append((char)oneByte);
			}
			String response=sb.toString();
client.close();
			return response;
}catch (IOException e)
        {
            throw new ServerException("Error communicating with server: " + e.getMessage());
        }
}
}

