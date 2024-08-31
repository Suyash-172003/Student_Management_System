import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.exceptions.*;
class StudentDeleteTestCase
{
	public static void main(String args[])
	{
		try
		{
		int rollNumber=Integer.parseInt(args[0]);
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO();
		studentDAOInterface.delete(rollNumber);
		System.out.println("Student deleted");
		}catch(DAOException daoException)
		{
			System.out.println(daoException.getMessage());
		}

	}
}
