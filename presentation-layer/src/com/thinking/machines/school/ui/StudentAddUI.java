package com.thinking.machines.school.ui;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.utils.*;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import com.thinking.machines.school.ui.*;
public class StudentAddUI extends Frame implements ActionListener
{
private Label heading,l1,l2,l3,l4,l5;
private TextField t1,t2,t3;
private Checkbox cb1;
private CheckboxGroup cg1;
private Checkbox r1,r2,r3;
private Button bt1,bt2;
public StudentAddUI()
{
heading=new Label("Student(Add Module)");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label("               "));
p1.add(heading);
p1.add(new Label("               "));

l1=new Label("Roll number");
t1=new TextField(10);

l2=new Label("Name");
t2=new TextField(30);

cg1=new CheckboxGroup();
l3=new Label("Gender");
r1=new Checkbox("Male",cg1,true);
r2=new Checkbox("Female",cg1,false);
r3=new Checkbox("Transgender",cg1,false);
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(r1);
p2.add(r2);
p2.add(r3);

l4=new Label("Indian");
cb1=new Checkbox();

l5=new Label("Date Of Birth");
t3=new TextField(30);

bt1=new Button("Save");
bt2=new Button("Cancel");
bt1.addActionListener(this);
bt2.addActionListener(this);
Panel p3=new Panel();
p3.setLayout(new GridLayout(1,5));
p3.add(new Label("                "));
p3.add(bt1);
p3.add(new Label("                "));
p3.add(bt2);
p3.add(new Label("                "));

Panel p4=new Panel();
p4.setLayout(new GridLayout(5,2));
p4.add(l1);
p4.add(t1);
p4.add(l2);
p4.add(t2);
p4.add(l3);
p4.add(p2);
p4.add(l4);
p4.add(cb1);
p4.add(l5);
p4.add(t3);

BorderLayout b1=new BorderLayout();
setLayout(b1);
add(p1,BorderLayout.NORTH);
add(p4,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);
add(new Label("        "),BorderLayout.EAST);
add(new Label("        "),BorderLayout.WEST);
addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
});

setLocation(300,50);
setSize(500,350);
setVisible(true);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==bt1)
{
try
{
int rollNumber=Integer.parseInt(t1.getText().trim());
String name=t2.getText().trim();
String gender = cg1.getSelectedCheckbox().getLabel();
boolean isIndian = cb1.getState();
String dateOfBirth = t3.getText().trim();

StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
if(gender.equals("Male")) studentBeanInterface.setGender(GENDER.MALE);
else if(gender.equals("Female")) studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);

studentBeanInterface.setIsIndian(isIndian);
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
java.util.Date dob=sdf.parse(dateOfBirth);
studentBeanInterface.setDateOfBirth(dob);
studentManagerInterface.add(studentBeanInterface);

}catch(Exception e)
{
System.out.println(e);
}
}
if(ev.getSource()==bt2)
{
System.out.println("Cancel");
dispose();
}
}
}