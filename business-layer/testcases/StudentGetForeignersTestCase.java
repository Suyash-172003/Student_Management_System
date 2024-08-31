import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import java.util.*;
import java.text.*;
class StudentGetForeignersTestCase
{
	public static void main(String args[])
	{
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			StudentBeanInterface studentBeanInterface;
			int rollNumber;
			String name;
			GENDER gender;
			boolean isIndian;
			java.util.Date dateOfBirth;
			SimpleDateFormat simpleDateFormat;
			simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			String dateOfBirthString;
			List<StudentBeanInterface> students;
			students=studentManagerInterface.getForeigners(StudentManagerInterface.ORDER_BY.NAME);
			int x;
			System.out.println("Number of students :"+students.size());
			for(x=0;x<students.size();++x)
			{
				studentBeanInterface=students.get(x);
				rollNumber=studentBeanInterface.getRollNumber();
				name=studentBeanInterface.getName();
				gender=studentBeanInterface.getGender();
				isIndian=studentBeanInterface.getIsIndian();
				dateOfBirth=studentBeanInterface.getDateOfBirth();
				dateOfBirthString=simpleDateFormat.format(dateOfBirth);
				System.out.println("Roll Number :"+rollNumber);
				System.out.println("Name :"+name);
				if(gender==GENDER.MALE) System.out.println("Gender :"+'M');
				else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
				else System.out.println("Gender :"+'T');
				System.out.println("IsIndian :"+isIndian);
				System.out.println("Date od birth :"+dateOfBirthString);
			}
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}