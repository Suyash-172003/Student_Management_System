package com.thinking.machines.school.bl.beans.interfaces;
import com.thinking.machines.school.bl.enums.*;
public interface StudentBeanInterface extends  java.io.Serializable
{
	public void setRollNumber(int rollNumber);
	public int getRollNumber();

	public void setName(String name);
	public String getName();

	public void setGender(GENDER gender);
	public GENDER getGender();

	public void setIsIndian(boolean isIndian);
	public boolean getIsIndian();

	public void setDateOfBirth(java.util.Date dateOfBirth);
	public java.util.Date getDateOfBirth();

	public int getAge();
}
