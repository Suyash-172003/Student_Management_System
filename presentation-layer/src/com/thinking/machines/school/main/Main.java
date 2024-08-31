package com.thinking.machines.school.main;
import com.thinking.machines.school.ui.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends Frame implements ActionListener
{
        private MenuItem btnAdd,btnEdit,btnDelete,btnGet,btnViewall,btnExit;
private Menu studentMenu;
private MenuBar mb;
private Label heading;
        private StudentUI studentUI;
        Main()
        {
        super("Student Management System");
        studentUI=new StudentUI();
btnAdd=new MenuItem("Add Student");
btnEdit=new MenuItem("Edit Student");
btnDelete=new MenuItem("Delete Student");
btnGet=new MenuItem("Get Student");
btnViewall=new MenuItem("ViewAll Student");
btnExit=new MenuItem("Exit");

studentMenu=new Menu("Student Module");
studentMenu.add(btnAdd);
studentMenu.add(btnEdit);
studentMenu.add(btnDelete);
studentMenu.add(btnGet);
studentMenu.add(btnViewall);
studentMenu.add(btnExit);

btnAdd.addActionListener(this);
btnEdit.addActionListener(this);
btnDelete.addActionListener(this);
btnGet.addActionListener(this);
btnViewall.addActionListener(this);
btnExit.addActionListener(this);


mb=new MenuBar();
mb.add(studentMenu);
setMenuBar(mb);

addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent ev)
{
System.exit(0);
}
});

setLocation(100,100);
setSize(600,600);
setVisible(true);



        }
public void actionPerformed(ActionEvent ev)
        {
                if(ev.getSource()==btnAdd)
                {
StudentAddUI studentAddUI;
studentAddUI=new StudentAddUI();
                }
                if(ev.getSource()==btnEdit)
                {
System.out.println("Edit");

StudentUpdateUI studentUpdateUI;
studentUpdateUI=new StudentUpdateUI();
                }
                if(ev.getSource()==btnDelete)
                {
System.out.println("Delete");
StudentDeleteUI studentDeleteUI;
studentDeleteUI=new StudentDeleteUI();
                }
                if(ev.getSource()==btnGet)
                {
StudentGetUI studentGetUI;
studentGetUI=new StudentGetUI();
}
                if(ev.getSource()==btnViewall)
                {
StudentViewallUI studentViewallUI;
studentViewallUI=new StudentViewallUI();
                }
                if(ev.getSource()==btnExit)
                {
                        System.exit(0);
                }
        }
        public static void main(String args[])
        {
                Main m=new Main();
        }
}
