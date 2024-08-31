package com.thinking.machines.school.ui;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.utils.*;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class StudentUI
{
	private void drawLine(int size)
	{
		for(int i=1;i<=size;++i)
		{
			System.out.print("-");
		}
		System.out.println();
	}
	public java.util.Date toDate(String dateString)
	{
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			java.util.Date d;
			d=simpleDateFormat.parse(dateString);
			String pcs1[];
			String pcs2[];
			pcs1=dateString.split("/");
			pcs2=simpleDateFormat.format(d).split("/");
			int dd1,dd2;
			int mm1,mm2;
			int yyyy1,yyyy2;
			dd1=Integer.parseInt(pcs1[0]);
			mm1=Integer.parseInt(pcs1[1]);
			yyyy1=Integer.parseInt(pcs1[2]);

			dd2=Integer.parseInt(pcs2[0]);
			mm2=Integer.parseInt(pcs2[1]);
			yyyy2=Integer.parseInt(pcs2[2]);

			if(dd1!=dd2 || mm1!=mm2 || yyyy1!=yyyy2) return null;
			return d;
		}catch(ParseException parseException)
		{
			return null;
		}
	}
	public void addStudent()
	{
	        drawLine(40);
		System.out.println("Student (Add Module)");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		java.util.Date dateOfBirth;
		String dateOfBirthString;
		char confirm;
		rollNumber=Keyboard.getInt("Roll number :");
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll number :");
			return;
		}
		name=Keyboard.getString("Name :");
		if(name==null || name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		gender=Keyboard.getCharacter("Gender(M/F/T) :");
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid input");
			return;
		}
		isIndian=Keyboard.getCharacter("IsIndian(Y/N) :");
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid input");
			return;
		}
		dateOfBirthString=Keyboard.getString("Date of birth(dd/mm/yyy :");
		if(dateOfBirthString==null || dateOfBirthString.length()==0)
		{
			System.out.println("Invalid input");
			return;
		}
		dateOfBirth=toDate(dateOfBirthString);
		if(dateOfBirth==null)
		{
			System.out.println("Invalid input");
			return;
		}
		confirm=Keyboard.getCharacter("Save (Y/N) :");
		if(confirm!='Y' && confirm!='N')
		{
			System.out.println("Invalid input");
			return;
		}
		if(confirm=='N')
		{
			System.out.println("Student not added");
			return;
		}
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
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
			studentManagerInterface.add(studentBeanInterface);
			System.out.println("Student Saved :");
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			System.out.println("Student not Saved ");
		}
	}
	public void updateStudent()
	{
		drawLine(40);
		System.out.println("Student (Update Module)");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		char confirm;
		java.util.Date dateOfBirth;
		String dateOfBirthString;
		rollNumber=Keyboard.getInt("Roll number :");
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll rumber");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentBeanInterface studentBeanInterface;
		StudentManagerInterface studentManagerInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			System.out.println("Name :"+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : M");
			}
			if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender :F");
			}
			if(studentBeanInterface.getGender()==GENDER.TRANSGENDER)
			{
				System.out.println("Gender :T");
			}
			if(studentBeanInterface.getIsIndian()) System.out.println("Indian :Yes");
			else System.out.println("Indian :No");
			System.out.println("Date of Birth :"+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
			confirm=Keyboard.getCharacter("Edit (Y/N) :");
			if(confirm=='y') confirm='Y';
			if(confirm=='n') confirm='N';
			if(confirm!='Y' && confirm!='N')
			{
				System.out.println("Invalid choice (Student not Updated)");
				return;
			}
			if(confirm=='N')
			{
				System.out.println("Student not updated");
				return;
			}

		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			return;
		}
		name=Keyboard.getString("Name :");
		if(name==null || name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		gender=Keyboard.getCharacter("Gender :");
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid Input");
			return;
		}
		isIndian=Keyboard.getCharacter("Is Indian (Y/N)");
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid Input");
			return;
		}
		dateOfBirthString=Keyboard.getString("Date of Birth (dd/MM/yyyy)");
		if(dateOfBirthString==null || dateOfBirthString.length()==0)
		{
			System.out.println("Invalid Input");
			return;
		}
		dateOfBirth=toDate(dateOfBirthString);
		if(dateOfBirth==null)
		{
			System.out.println("Invalid Input");
			return;
		}
		confirm=Keyboard.getCharacter("Update (Y/N) :");
		if(confirm!='Y' && confirm!='N')
		{
			System.out.println("Invalid Input");
			return;
		}
		if(confirm=='N')
		{
			System.out.println("Student Not Updated :");
			return;
		}
		try
		{
			studentBeanInterface=new StudentBean();
			studentBeanInterface.setRollNumber(rollNumber);
			studentBeanInterface.setName(name);
		if(gender=='M')	studentBeanInterface.setGender(GENDER.MALE);
		else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
		else studentBeanInterface.setGender(GENDER.TRANSGENDER);

		if(isIndian=='Y') studentBeanInterface.setIsIndian(true);
		else studentBeanInterface.setIsIndian(false);

		studentBeanInterface.setDateOfBirth(dateOfBirth);
		studentManagerInterface.update(studentBeanInterface);

		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			System.out.println("Student Not Updated");
		}
	}
	public void deleteStudent()
	{
	        drawLine(40);
		System.out.println("Student (Delete Module)");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		char confirm;
		java.util.Date dateOfBirth;
		String dateOfBirthString;
		rollNumber=Keyboard.getInt("Roll number :");
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll rumber");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentBeanInterface studentBeanInterface;
		StudentManagerInterface studentManagerInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			System.out.println("Name :"+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : M");
			}
			if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender :F");
			}
			if(studentBeanInterface.getGender()==GENDER.TRANSGENDER)
			{
				System.out.println("Gender :T");
			}
			if(studentBeanInterface.getIsIndian()) System.out.println("Indian :Yes");
			else System.out.println("Indian :No");
			System.out.println("Date of Birth :"+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			return;
		}
		confirm=Keyboard.getCharacter("Delete (Y/N) :");
		if(confirm!='Y' && confirm!='N')
		{
			System.out.println("Invalid Input");
			return;
		}
		if(confirm=='N')
		{
			System.out.println("Student Not Deleted :");
			return;
		}
		try
		{
			studentBeanInterface=new StudentBean();
		studentManagerInterface.delete(rollNumber);
		System.out.println("Student Deleted");
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			System.out.println("Student Not Deleted");
		
	}
	}
	public void getStudent()
	{
	        drawLine(40);
		System.out.println("Student (GetStudent Module)");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		char confirm;
		java.util.Date dateOfBirth;
		String dateOfBirthString;
		rollNumber=Keyboard.getInt("Roll number :");
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll rumber");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentBeanInterface studentBeanInterface;
		StudentManagerInterface studentManagerInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			System.out.println("Name :"+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : M");
			}
			if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender :F");
			}
			if(studentBeanInterface.getGender()==GENDER.TRANSGENDER)
			{
				System.out.println("Gender :T");
			}
			if(studentBeanInterface.getIsIndian()) System.out.println("Indian :Yes");
			else System.out.println("Indian :No");
			System.out.println("Date of Birth :"+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			return;
		}
	}
	public void getStudents()
	{
		drawLine(40);
		System.out.println("Students (Get Students by orderby Module)");
		drawLine(40);
		StudentBeanInterface studentBeanInterface;
		studentBeanInterface=new StudentBean();
		StudentManagerInterface studentManagerInterface;
		studentManagerInterface=new StudentManager();
		java.util.List<StudentBeanInterface> students;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		int choice;
		while(true)
		{
			System.out.println("1.List of Students ordered by roll number :");
			System.out.println("2.List ordered ny name :");
			System.out.println("3.List ordered by date of birth :");
			System.out.println("4.List ordered by age :");
			System.out.println("5.List ordered by gender:");
			System.out.println("6.Exit:");
			choice=Keyboard.getInt("Enter Choice :");
			if(choice==1)
			{
				try
				{
				students=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
				System.out.println("Number of Students :"+students.size());
				for(int x=0;x<students.size();x++)
				{
					studentBeanInterface=students.get(x);
					System.out.println("Roll Number :"+studentBeanInterface.getRollNumber());
					System.out.println("Name :"+studentBeanInterface.getName());
					GENDER gender=studentBeanInterface.getGender();
					if(gender==GENDER.MALE)
					{
					System.out.println("Gender :"+'M');
					}
					else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
					else System.out.println("Gender :"+'T');
				        System.out.println("IsIndain :"+studentBeanInterface.getIsIndian());
					String dateOfBirthString;
					java.util.Date dateOfBirth;
					dateOfBirth=studentBeanInterface.getDateOfBirth();
					dateOfBirthString=simpleDateFormat.format(dateOfBirth);
					System.out.println("Date Of Birth :"+dateOfBirthString);
				}
				}catch(BLException blException)
				{
					System.out.println(blException);
				}
			}
			if(choice==2)
			{
				try
				{
				students=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.NAME);
				System.out.println("Number of Students :"+students.size());
				for(int x=0;x<students.size();x++)
				{
					studentBeanInterface=students.get(x);
					System.out.println("Roll Number :"+studentBeanInterface.getRollNumber());
					System.out.println("Name :"+studentBeanInterface.getName());
					GENDER gender=studentBeanInterface.getGender();
					if(gender==GENDER.MALE)
					{
					System.out.println("Gender :"+'M');
					}
					else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
					else System.out.println("Gender :"+'T');
				        System.out.println("IsIndain :"+studentBeanInterface.getIsIndian());
					String dateOfBirthString;
					java.util.Date dateOfBirth;
					dateOfBirth=studentBeanInterface.getDateOfBirth();
					dateOfBirthString=simpleDateFormat.format(dateOfBirth);
					System.out.println("Date Of Birth :"+dateOfBirthString);
				}
				}catch(BLException blException)
				{
					System.out.println(blException);
				}
			}
			if(choice==3)
			{
				try
				{
					String dob="5/11/1998";
					java.util.Date ddob;
					ddob=simpleDateFormat.parse(dob);
				students=studentManagerInterface.getByDateOfBirth(ddob,StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH);
				System.out.println("Number of Students :"+students.size());
				for(int x=0;x<students.size();x++)
				{
					studentBeanInterface=students.get(x);
					System.out.println("Roll Number :"+studentBeanInterface.getRollNumber());
					System.out.println("Name :"+studentBeanInterface.getName());
					GENDER gender=studentBeanInterface.getGender();
					if(gender==GENDER.MALE)
					{
					System.out.println("Gender :"+'M');
					}
					else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
					else System.out.println("Gender :"+'T');
				        System.out.println("IsIndain :"+studentBeanInterface.getIsIndian());
					String dateOfBirthString;
					java.util.Date dateOfBirth;
					dateOfBirth=studentBeanInterface.getDateOfBirth();
					dateOfBirthString=simpleDateFormat.format(dateOfBirth);
					System.out.println("Date Of Birth :"+dateOfBirthString);
				}
				}catch(BLException blException)
				{
					System.out.println(blException);
				}catch(ParseException pe)
				{
				}
			}
			if(choice==4)
			{
                                try
				{
					int age=35;
				students=studentManagerInterface.getByAge(age,StudentManagerInterface.ORDER_BY.AGE);
				System.out.println("Number of Students :"+students.size());
				for(int x=0;x<students.size();x++)
				{
					studentBeanInterface=students.get(x);
					System.out.println("Roll Number :"+studentBeanInterface.getRollNumber());
					System.out.println("Name :"+studentBeanInterface.getName());
					GENDER gender=studentBeanInterface.getGender();
					if(gender==GENDER.MALE)
					{
					System.out.println("Gender :"+'M');
					}
					else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
					else System.out.println("Gender :"+'T');
				        System.out.println("IsIndain :"+studentBeanInterface.getIsIndian());
					String dateOfBirthString;
					java.util.Date dateOfBirth;
					dateOfBirth=studentBeanInterface.getDateOfBirth();
					dateOfBirthString=simpleDateFormat.format(dateOfBirth);
					System.out.println("Date Of Birth :"+dateOfBirthString);
				}
				}catch(BLException blException)
				{
					System.out.println(blException);
				}
			}
			if(choice==5)
			{	try
				{
					GENDER gen=GENDER.MALE;
				students=studentManagerInterface.getByGender(gen,StudentManagerInterface.ORDER_BY.GENDER);
				System.out.println("Number of Students :"+students.size());
				for(int x=0;x<students.size();x++)
				{
					studentBeanInterface=students.get(x);
					System.out.println("Roll Number :"+studentBeanInterface.getRollNumber());
					System.out.println("Name :"+studentBeanInterface.getName());
					GENDER gender=studentBeanInterface.getGender();
					if(gender==GENDER.MALE)
					{
					System.out.println("Gender :"+'M');
					}
					else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
					else System.out.println("Gender :"+'T');
				        System.out.println("IsIndain :"+studentBeanInterface.getIsIndian());
					String dateOfBirthString;
					java.util.Date dateOfBirth;
					dateOfBirth=studentBeanInterface.getDateOfBirth();
					dateOfBirthString=simpleDateFormat.format(dateOfBirth);
					System.out.println("Date Of Birth :"+dateOfBirthString);
				}
				}catch(BLException blException)
				{
					System.out.println(blException);
				}
			}
			if(choice==6)
			{
				break;
			}
		}
}
public void getFilteredStudents()
{
}
}
