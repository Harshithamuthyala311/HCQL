package com.klef.jfsd.exam;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome");
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Insert Student");
            System.out.println("2. Fetch Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
     
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    do {
                        Customer customer = new Customer();
                        System.out.print("Enter Name: ");
                        customer.setName(sc.next());
                        System.out.print("Enter Email: ");
                        customer.setEmail(sc.next());
                        System.out.print("Enter Age: ");
                        customer.setAge(sc.nextInt());
                        System.out.print("Enter Location: ");
                        customer.setLocation(sc.next());

                        t = s.beginTransaction();
                        s.save(customer);
                        t.commit();
                        System.out.println("Student record inserted successfully.");

                        System.out.print("Insert another student? (yes/no): ");
                    } while (sc.next().equalsIgnoreCase("yes"));
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    Long id = sc.nextLong();
                    Customer fetchedStudent = s.get(Customer.class, id);
                    if (fetchedStudent != null) {
                        System.out.println("\nStudent Details:");
                        System.out.println("ID: " + fetchedStudent.getId());
                        System.out.println("Name: " + fetchedStudent.getName());
                        System.out.println("Email: " + fetchedStudent.getEmail());
                        System.out.println("Age: " + fetchedStudent.getAge());
                        System.out.println("Location: " + fetchedStudent.getLocation());
                    } else {
                        System.out.println("No student found with ID " + id);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    Long updateId = sc.nextLong();
                    Customer updateStudent = s.get(Customer.class, updateId);
                    if (updateStudent != null) {
                        System.out.print("Enter new Name: ");
                        updateStudent.setName(sc.next());
                        System.out.print("Enter new Email: ");
                        updateStudent.setEmail(sc.next());
                        System.out.print("Enter new Age: ");
                        updateStudent.setAge(sc.nextInt());
                        System.out.print("Enter new Location: ");
                        updateStudent.setLocation(sc.next());

                        t = s.beginTransaction();
                        s.update(updateStudent);
                        t.commit();
                        System.out.println("Student record updated successfully.");
                    } else {
                        System.out.println("No student found with ID " + updateId);
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    Long deleteId = sc.nextLong();
                    Customer deleteStudent = s.get(Customer.class, deleteId);
                    if (deleteStudent != null) {
                        t = s.beginTransaction();
                        s.delete(deleteStudent);
                        t.commit();
                        System.out.println("Student record deleted successfully.");
                    } else {
                        System.out.println("No student found with ID " + deleteId);
                    }
                    break;

                case 5:
                    System.out.println("Exiting the system...");
                    sc.close();
                    s.close();
                    sf.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
