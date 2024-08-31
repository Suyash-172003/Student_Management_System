package com.thinking.machines.school.dl.dto.interfaces;
public interface StudentDTOInterface extends java.io.Serializable
{
	public void setRollNumber(int rollNumber);
	public int getRollNumber();

	public void setName(String name);
	public String getName();

	public void setGender(char gender);
	public char getGender();

	public void setIsIndian(boolean isIndian);
	public boolean getIsIndian();

	public void setDateOfBirth(java.util.Date dataOfBirth);
	public java.util.Date getDateOfBirth();
}
