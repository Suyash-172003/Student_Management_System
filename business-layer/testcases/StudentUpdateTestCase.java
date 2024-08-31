import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
class StudentUpdateTestCase
{
	public static void main(String args[])
	{
		int rollNumber=Integer.parseInt(args[0]);
		String name=args[1];
		char gender=args[2].charAt(0);
		boolean isIndian=Boolean.parseBoolean(args[3]);
		int dd=Integer.parseInt(args[4]);
		int mm=Integer.parseInt(args[5]);
		int yyyy=Integer.parseInt(args[6]);
		java.util.Date dateOfBirth;
		dateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
		StudentBeanInterface studentBeanInterface;
		studentBeanInterface=new StudentBean();
		studentBeanInterface.setRollNumber(rollNumber);
		studentBeanInterface.setName(name);
		if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
		else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
		else studentBeanInterface.setGender(GENDER.TRANSGENDER);
		studentBeanInterface.setIsIndian(isIndian);
		studentBeanInterface.setDateOfBirth(dateOfBirth);
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			studentManagerInterface.update(studentBeanInterface);
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
