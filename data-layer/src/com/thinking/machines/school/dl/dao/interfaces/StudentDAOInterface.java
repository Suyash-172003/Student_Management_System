package com.thinking.machines.school.dl.dao.interfaces;
import java.util.*;
import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
public interface StudentDAOInterface
{
	public void add(StudentDTOInterface studentDTOInterface) throws DAOException;
	public void update(StudentDTOInterface studentDTOInterface) throws DAOException;
	public void delete(int rollNumber) throws DAOException;
	public StudentDTOInterface get(int rollNumber) throws DAOException;
	public List<StudentDTOInterface> getAll() throws DAOException;
}
