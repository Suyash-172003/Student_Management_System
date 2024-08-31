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
public class StudentDeleteUI extends Frame implements ActionListener
{
private Label heading,l1,l4,l5,l6,l7;
private TextField t1,t2,t3;
private Button b1,b2,b3;
Checkbox cb1;
CheckboxGroup cg1;
Checkbox r1,r2,r3;
Panel pFields,p4;
public StudentDeleteUI()
{
heading=new Label("Student(Delete Module)");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(heading);
p1.add(new Label(" "));


l1=new Label("Roll Number");
t1=new TextField(10);

b1=new Button("Get");
Panel p5=new Panel();
p5.setLayout(new GridLayout(2,5));
p5.add(new Label(" "));
p5.add(l1);
p5.add(new Label(" "));
p5.add(t1);
p5.add(new Label(" "));

p5.add(new Label(" "));
p5.add(new Label(" "));
p5.add(new Label(" "));
p5.add(new Label(" "));
p5.add(new Label(" "));


Panel p6=new Panel();
p6.setLayout(new GridLayout(2,3));
p6.add(new Label(" "));
p6.add(b1);
p6.add(new Label(" "));

p6.add(new Label(" "));
p6.add(new Label(" "));
p6.add(new Label(" "));




b1.addActionListener(this);



pFields = new Panel();
pFields.setLayout(new GridLayout(5,2));
l4=new Label("Name");
t2=new TextField(10);

cg1=new CheckboxGroup();
l5=new Label("Gender");
r1=new Checkbox("Male",cg1,true);
r2=new Checkbox("Female",cg1,false);
r3=new Checkbox("Transgender",cg1,false);
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(r1);
p2.add(r2);
p2.add(r3);

l6=new Label("Indian");
cb1=new Checkbox();

l7=new Label("Date Of Birth");
t3=new TextField(20);

b2=new Button("Delete");
b3=new Button("Cancel");
b2.addActionListener(this);
b3.addActionListener(this);
 p4=new Panel();
p4.setLayout(new GridLayout(3,5));
p4.add(new Label(" "));
p4.add(b2);
p4.add(new Label(" "));
p4.add(b3);
p4.add(new Label(" "));

p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));

p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));
p4.add(new Label(" "));


pFields.add(l4);
pFields.add(t2);
pFields.add(l5);
pFields.add(p2);
pFields.add(l6);
pFields.add(cb1);
pFields.add(l7);
pFields.add(t3);
pFields.add(new Label(" "));
pFields.add(new Label(" "));


pFields.setVisible(false);
p4.setVisible(false);

GridLayout g=new GridLayout(5,1);
setLayout(g);
add(p1);
add(p5);
add(p6);
add(pFields);
add(p4);
addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
});

setLocation(300,50);
setSize(500,450);
setVisible(true);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==b1)
{
int rollNumber;
System.out.println("Delete button clicked");
try
{
rollNumber=Integer.parseInt(t1.getText().trim());
StudentManagerInterface studentManagerInterface = new StudentManager();
StudentBeanInterface studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);

t2.setText(studentBeanInterface.getName());
String gender = String.valueOf(studentBeanInterface.getGender());
r1.setState("Male".equalsIgnoreCase(gender));
r2.setState("Female".equalsIgnoreCase(gender));
r3.setState("Transgender".equalsIgnoreCase(gender));
cb1.setState(studentBeanInterface.getIsIndian());
t3.setText(new SimpleDateFormat("dd-MM-yyyy").format(studentBeanInterface.getDateOfBirth()));
pFields.setVisible(true); 
p4.setVisible(true);
}catch(Exception e)
{
}
}
if(ev.getSource()==b2)
{
try
{
int rollNumber=Integer.parseInt(t1.getText().trim());
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentManagerInterface.delete(rollNumber);
}catch(Exception e)
{
System.out.println(e);
}
}
if(ev.getSource()==b3)
{
dispose();
}
}
}