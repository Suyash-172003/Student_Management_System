import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import java.text.*;
class StudentGetTestCase
{
	public static void main(String args[])
	{
		int rollNumber=Integer.parseInt(args[0]);
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			StudentBeanInterface studentBeanInterface;
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			String name=studentBeanInterface.getName();
			GENDER gender=studentBeanInterface.getGender();
			boolean isIndian=studentBeanInterface.getIsIndian();
			java.util.Date dateOfBirth=studentBeanInterface.getDateOfBirth();
			SimpleDateFormat simpleDateFormat;
			simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			String dateOfBirthString;
			dateOfBirthString=simpleDateFormat.format(dateOfBirth);
			System.out.println("Roll Number :"+rollNumber);
			System.out.println("Name :"+name);
			if(gender==GENDER.MALE) System.out.println("Gender :"+'M');
			else if(gender==GENDER.FEMALE) System.out.println("Gender :"+'F');
			else System.out.println("Gender :"+'T');
			System.out.println("Is Indian :"+isIndian);
			System.out.println("Date of birth :"+dateOfBirthString);
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
