package com.thinking.machines.school.bl.comparators;
import com.thinking.machines.school.bl.beans.interfaces.*;
import java.util.*;
import com.thinking.machines.school.bl.enums.*;
public class StudentGenderComparator implements Comparator<StudentBeanInterface>
{
	public int compare(StudentBeanInterface left,StudentBeanInterface right)
	{
		GENDER gender=left.getGender();
		char leftGender=' ';
		if(gender==GENDER.MALE) leftGender='M';
		else if(gender==GENDER.FEMALE) leftGender='F';
		else leftGender='T';

		char rightGender=' ';
		gender=right.getGender();
		if(gender==GENDER.MALE) rightGender='M';
		else if(gender==GENDER.FEMALE) rightGender='F';
		else rightGender='T';

		return leftGender-rightGender;
	}
}
