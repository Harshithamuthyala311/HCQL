package com.klu.EXP1;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insert Student");
            System.out.println("2. Fetch Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        Student student = new Student();
                        System.out.print("Enter Name: ");
                        student.setName(sc.next());
                        System.out.print("Enter Gender: ");
                        student.setGender(sc.next());
                        System.out.print("Enter Department: ");
                        student.setDepartment(sc.next());
                        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                        student.setDob(sc.next());
                        System.out.print("Enter Contact Number: ");
                        student.setContactnumber(sc.nextLong());
                        System.out.print("Enter CGPA: ");
                        student.setCgpa(sc.nextDouble());
                        System.out.print("Enter Number of Backlogs: ");
                        student.setNob(sc.nextInt());

                        t = s.beginTransaction();
                        s.save(student);
                        t.commit();
                        System.out.println("Inserted Data");

                        System.out.print("Do you want to insert another student? (yes/no): ");
                        String insertMore = sc.next();
                        if (!insertMore.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    Long id = sc.nextLong();
                    Student fetchedStudent = s.get(Student.class, id);
                    if (fetchedStudent != null) {
                        System.out.println("ID: " + fetchedStudent.getId());
                        System.out.println("Name: " + fetchedStudent.getName());
                        System.out.println("Gender: " + fetchedStudent.getGender());
                        System.out.println("Department: " + fetchedStudent.getDepartment());
                        System.out.println("DOB: " + fetchedStudent.getDob());
                        System.out.println("Contact Number: " + fetchedStudent.getContactnumber());
                        System.out.println("CGPA: " + fetchedStudent.getCgpa());
                        System.out.println("Number of Backlogs: " + fetchedStudent.getNob());
                    } else {
                        System.out.println("Student not found");
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    Long updateId = sc.nextLong();
                    Student updateStudent = s.get(Student.class, updateId);
                    if (updateStudent != null) {
                        System.out.print("Enter new Name: ");
                        updateStudent.setName(sc.next());
                        System.out.print("Enter new Gender: ");
                        updateStudent.setGender(sc.next());
                        System.out.print("Enter new Department: ");
                        updateStudent.setDepartment(sc.next());
                        System.out.print("Enter new Date of Birth (YYYY-MM-DD): ");
                        updateStudent.setDob(sc.next());
                        System.out.print("Enter new Contact Number: ");
                        updateStudent.setContactnumber(sc.nextLong());
                        System.out.print("Enter new CGPA: ");
                        updateStudent.setCgpa(sc.nextDouble());
                        System.out.print("Enter new Number of Backlogs: ");
                        updateStudent.setNob(sc.nextInt());

                        t = s.beginTransaction();
                        s.update(updateStudent);
                        t.commit();
                        System.out.println("Updated Data");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    Long deleteId = sc.nextLong();
                    Student deleteStudent = s.get(Student.class, deleteId);
                    if (deleteStudent != null) {
                        t = s.beginTransaction();
                        s.delete(deleteStudent);
                        t.commit();
                        System.out.println("Deleted Data");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    s.close();
                    sf.close();
                    return;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}