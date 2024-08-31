package com.thinking.machines.school.bl.manager;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.comparators.*;
import java.util.*;
import java.text.*;
import com.thinking.machines.school.*;

import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dao.*;

public class StudentManager implements  StudentManagerInterface
{
	private int getAge(java.util.Date dateOfBirth)
	{
		java.util.Date today=new java.util.Date();
		int todayDD=today.getDate();
		int todayMM=today.getMonth()+1;
		int todayYYYY=today.getYear()+1900;

		int dobDD=dateOfBirth.getDate();
		int dobMM=dateOfBirth.getMonth()+1;
		int dobYYYY=dateOfBirth.getYear()+1900;
		int age=todayYYYY-dobYYYY;

		if(todayMM<dobMM)
		{
			age--;
		}
		else if(todayMM==dobMM)
		{
			if(todayDD<dobDD) age--;
		}
		return age;
	}
	
	public void add(StudentBeanInterface studentBeanInterface) throws BLException
	{
int rollNumber;
String name;
GENDER gender;
boolean isIndian;
java.util.Date dateOfBirth;
String request;
SimpleDateFormat sdf;
sdf=new SimpleDateFormat("dd/MM/yyyy");

rollNumber=studentBeanInterface.getRollNumber();
name=studentBeanInterface.getName();
gender=studentBeanInterface.getGender();
char gen;
if(gender==GENDER.MALE)
{
gen='M';
}
else if(gender==GENDER.FEMALE)
{
gen='F';
}
else
{
gen='T';
}

isIndian=studentBeanInterface.getIsIndian();
dateOfBirth=studentBeanInterface.getDateOfBirth();

request="add,"+rollNumber+","+name+","+gender+","+isIndian+","+sdf.format(dateOfBirth)+"#";
ChotaClient client=new ChotaClient("localhost",7070);
try{
String response=client.sendRequest(request);
String pcs[];
pcs=response.split(",");
if(pcs[0].equals("true"))
{ 
System.out.println("Data Saved");
}
else
{
System.out.println ("Not saved"); 
}
}catch(ServerException se)
{
} 
        }
	public void update(StudentBeanInterface studentBeanInterface) throws BLException
	{
		throw new BLException("Not yet implemented");
	}
	public void delete(int rollNumber) throws BLException
	{
throw new BLException("Not yet implemented");
}
	public StudentBeanInterface getByRollNumber(int rollNumber) throws BLException
	{
throw new BLException("Not yet implemented");
	}
	public List<StudentBeanInterface> getAll(StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
throw new BLException("Not yet implemented");
	}
	public List<StudentBeanInterface> getByGender(GENDER gender,StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
	throw new BLException("Not yet implemented");
	
	}
	public List<StudentBeanInterface> getByAge(int age,StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
throw new BLException("Not yet implemented");

	}
	public List<StudentBeanInterface> getByDateOfBirth(java.util.Date dateOfBirth,StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
throw new BLException("Not yet implemented");

							}
	public List<StudentBeanInterface> getIndians(StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
throw new BLException("Not yet implemented");

			}
	public List<StudentBeanInterface> getForeigners(StudentManagerInterface.ORDER_BY orderBy) throws BLException
	{
throw new BLException("Not yet implemented");

			}
}
