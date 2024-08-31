package com.thinking.machines.school.dl.dao;
import java.util.*;
import java.io.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import java.sql.*;
import java.text.*;
import com.thinking.machines.school.dl.dao.connection.DAOConnection;
public class StudentDAO implements  StudentDAOInterface
{
	public void add(StudentDTOInterface studentDTOInterface) throws DAOException
	{
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
			Connection connection;
			connection=DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
			preparedStatement.setInt(1,studentDTOInterface.getRollNumber());
			ResultSet resultSet;
			boolean exists;
			resultSet=preparedStatement.executeQuery();
			exists=resultSet.next();
			resultSet.close();
			preparedStatement.close();
			if(exists)
			{
				connection.close();
				throw new DAOException("Roll number :"+studentDTOInterface.getRollNumber()+"+exists");
			}
			java.util.Date utilDateOfBirth=studentDTOInterface.getDateOfBirth();
			java.sql.Date dateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());

			preparedStatement=connection.prepareStatement("insert into student values(?,?,?,?,?)");
			preparedStatement.setInt(1,studentDTOInterface.getRollNumber());
			preparedStatement.setString(2,studentDTOInterface.getName());
			preparedStatement.setString(3,String.valueOf(studentDTOInterface.getGender()));
			preparedStatement.setBoolean(4,studentDTOInterface.getIsIndian());
			preparedStatement.setDate(5,dateOfBirth);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		}catch(SQLException sqlException)
		{
			throw new DAOException("SQLException :"+sqlException.getMessage());
		}
		catch(ClassNotFoundException classNotFoundException)
		{
			throw new DAOException("ClassNotFoundException :"+classNotFoundException.getMessage());
		}
	}
public void update(StudentDTOInterface studentDTOInterface) throws DAOException
{
try
{
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,studentDTOInterface.getRollNumber());
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists==false)
{
connection.close();
throw new DAOException("Roll number :"+studentDTOInterface.getRollNumber()+"+ does not exists");
}
int rollNumber=studentDTOInterface.getRollNumber();
String name=studentDTOInterface.getName();
String gender=String.valueOf(studentDTOInterface.getGender());
Boolean isIndian=studentDTOInterface.getIsIndian();
java.util.Date utilDateOfBirth=studentDTOInterface.getDateOfBirth();
java.sql.Date dateOfBirth=new java.sql.Date(utilDateOfBirth.getYear(),utilDateOfBirth.getMonth(),utilDateOfBirth.getDate());
preparedStatement=connection.prepareStatement("update student set name=?,gender=?,is_indian=?,date_of_birth=? where roll_number=?");
preparedStatement.setString(1,name);
preparedStatement.setString(2,gender);
preparedStatement.setBoolean(3,isIndian);
preparedStatement.setDate(4,dateOfBirth);
preparedStatement.setInt(5,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException("SQLException :"+sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException("ClassNotFoundException :"+classNotFoundException.getMessage());
}	
}
public void delete(int rollNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
if(exists==false)
{
connection.close();
throw new DAOException(rollNumber+" does not exists");
}
preparedStatement=connection.prepareStatement("delete from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException("SQLException :"+sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException("ClassNotFoundException :"+classNotFoundException.getMessage());
}	
}
public StudentDTOInterface get(int rollNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists==false)
{
connection.close();
throw new DAOException(rollNumber+" does not exists");
}
preparedStatement = connection.prepareStatement("select name, gender,is_indian,date_of_birth from student where roll_number=?");
        preparedStatement.setInt(1, rollNumber);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();

        String name = resultSet.getString("name").trim();
        String gender = resultSet.getString("gender").trim();
        boolean isIndian = resultSet.getBoolean("is_indian");
        java.sql.Date dateOfBirth = resultSet.getDate("date_of_birth");
java.util.Date utilDateOfBirth=new java.util.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
          StudentDTOInterface studentDTOInterface = new StudentDTO();
        studentDTOInterface.setRollNumber(rollNumber);
        studentDTOInterface.setName(name);
        studentDTOInterface.setGender(gender.charAt(0));         
studentDTOInterface.setIsIndian(isIndian);
        studentDTOInterface.setDateOfBirth(utilDateOfBirth);
        resultSet.close();
        preparedStatement.close();
        connection.close();
return studentDTOInterface;
}catch(SQLException sqlException)
{
throw new DAOException("SQLException :"+sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException("ClassNotFoundException :"+classNotFoundException.getMessage());
}
}
public List<StudentDTOInterface> getAll() throws DAOException
{
try
{
List<StudentDTOInterface> students=new ArrayList<StudentDTOInterface>();
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO();
int rollNumber;
String name;
String gender;
boolean isIndian;
java.sql.Date dateOfBirth;
java.util.Date utilDateOfBirth;
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();

ResultSet resultSet=statement.executeQuery("select * from student order by roll_number");
while(resultSet.next())
{
rollNumber=resultSet.getInt("roll_number");
name=resultSet.getString("name").trim();
gender=resultSet.getString("gender").trim();
isIndian=resultSet.getBoolean("is_indian");
dateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender.charAt(0));
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(utilDateOfBirth);
students.add(studentDTOInterface);
}
resultSet.close();
statement.close();
connection.close();
return students;
}catch(SQLException sqlException)
{
throw new DAOException("SQLException :"+sqlException.getMessage());
}
catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException("ClassNotFoundException :"+classNotFoundException.getMessage());
}
}
}
