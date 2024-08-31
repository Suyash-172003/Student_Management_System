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
public class StudentViewallUI extends Frame implements ActionListener
{
private Label heading,l1,l2,l3,l4,l5,lr,ln,lg,li,ld,lIndex;
private Checkbox cb1,r1,r2,r3;
private CheckboxGroup cg1;
private Button b1,b2,b3,b4;
private java.util.List<StudentBeanInterface> students;
private int currentIndex;
public StudentViewallUI()
{
students=new ArrayList<StudentBeanInterface>();
currentIndex=-1;
loadStudents();
heading=new Label("(StudentViewall Module)");
Panel p1;
p1=new Panel(new GridLayout(1,3));
p1.add(new Label("          "));
p1.add(heading);
p1.add(new Label("          "));

l1=new Label("Roll Number");
lr=new Label("");
l2=new Label("Name");
ln=new Label("");
l3=new Label("Gender");
lg=new Label("");
l4=new Label("Indian");
li=new Label("");
l5=new Label("Date Of Birth");
ld=new Label("");

b1=new Button("First");
b2=new Button("Previous");
b3=new Button("Next");
b4=new Button("Last");
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);

Panel p3=new Panel();
p3.setLayout(new GridLayout(1,9));
p3.add(new Label("     "));
p3.add(b1);
p3.add(new Label("     "));
p3.add(b2);
p3.add(new Label("     "));
p3.add(b3);
p3.add(new Label("     "));
p3.add(b4);
p3.add(new Label("     "));

Panel p4=new Panel();
p4.setLayout(new GridLayout(5,2));
p4.add(l1);
p4.add(lr);
p4.add(l2);
p4.add(ln);
p4.add(l3);
p4.add(lg);
p4.add(l4);
p4.add(li);
p4.add(l5);
p4.add(ld);

Panel p5=new Panel();
lIndex=new Label("Index :0 0f 0");
p5.add(lIndex);


BorderLayout b1=new BorderLayout();
setLayout(b1);
add(p1,BorderLayout.NORTH);
add(p4,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);

add(p5,BorderLayout.EAST);
add(new Label("        "),BorderLayout.WEST);

addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent ev)
{
dispose();
}
});

setLocation(300,50);
setSize(500,350);
setVisible(true);
}
private void loadStudents()
{
try
{
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
students=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
}catch(Exception e)
{
System.out.println(e);
}
if(!students.isEmpty())
{
currentIndex=0;
displayStudent();
}
}
private void displayStudent()
{
if(currentIndex>=0 && currentIndex<students.size())
{
StudentBeanInterface studentBeanInterface = students.get(currentIndex);
if(lr!=null) lr.setText(String.valueOf(studentBeanInterface.getRollNumber()));
if(ln!=null)ln.setText(studentBeanInterface.getName());
if(lg!=null)lg.setText(studentBeanInterface.getGender().toString());
if(li!=null)li.setText(String.valueOf(studentBeanInterface.getIsIndian()));
if(ld!=null)ld.setText(new SimpleDateFormat("dd-MM-yyyy").format(studentBeanInterface.getDateOfBirth()));
updateIndex();
}
}
public void updateIndex()
{
if(lIndex!=null) lIndex.setText("Index :"+(currentIndex+1)+"of"+(students.size()));
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==b1)
{
currentIndex=0;
}
if(ev.getSource()==b2)
{
if(currentIndex>0) currentIndex--;
}
if(ev.getSource()==b3)
{
if(currentIndex<students.size()-1) currentIndex++;
}
if(ev.getSource()==b4)
{
currentIndex=students.size()-1;
}
displayStudent();
}
}