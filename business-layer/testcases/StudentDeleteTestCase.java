import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
class StudentDeleteTestCase
{
	public static void main(String args[])
	{
		int rollNumber=Integer.parseInt(args[0]);
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			studentManagerInterface.delete(rollNumber);
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
