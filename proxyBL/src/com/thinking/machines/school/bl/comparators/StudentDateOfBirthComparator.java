package com.thinking.machines.school.bl.comparators;
import com.thinking.machines.school.bl.beans.interfaces.*;
import java.util.*;
public class StudentDateOfBirthComparator implements Comparator<StudentBeanInterface>
{
	public int compare(StudentBeanInterface left,StudentBeanInterface right)
	{
		return left.getDateOfBirth().compareTo(right.getDateOfBirth());
	}
}
