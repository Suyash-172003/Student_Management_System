package com.thinking.machines.school.bl.exceptions;
public class ServerException extends Exception implements java.io.Serializable
{
	public ServerException(String message)
	{
		super(message);
	}
}
