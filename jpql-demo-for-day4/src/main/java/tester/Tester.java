package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jdk.nashorn.internal.objects.NativeArray;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000 ", Employee.class);
            List<Employee> employees = query.getResultList();
            employees.forEach(Employee -> {
                System.out.println("Opgave1 = " + Employee.toString());
            });

            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            TypedQuery<Employee> query2 = em.createQuery("SELECT e FROM Employee e WHERE e.id =\"klo999\"", Employee.class);
            List<Employee> employeeKlo = query2.getResultList();
            employeeKlo.forEach(Employee -> {
                System.out.println("Opgave2 = " + Employee.getFirstName());
            });

            //3) Create a query to fetch the highest salary and print the value
            TypedQuery<BigDecimal> query3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e", BigDecimal.class);
            BigDecimal MaxSalary = query3.getSingleResult();
            System.out.println("Opgave3 = " + MaxSalary);

            //4) Create a query to fetch the firstName of all Employees and print the names
            TypedQuery<String> query4 = em.createQuery("SELECT e.firstName FROM Employee e", String.class);
            List<String> employeeFirstName = query4.getResultList();
            System.out.println("Opgave4 = " + employeeFirstName.toString());

            //5 Create a query to calculate the number of employees and print the number
            TypedQuery<Integer> query5 = em.createQuery("SELECT COUNT(e) FROM Employee e", int.class);
            System.out.println("Opgave5 = " + query5.getSingleResult());

            //6 Create a query to fetch the Employee with the higest salary and print all his details
            TypedQuery<Employee> query6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary IN (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            Employee employeeMaxSalary = query6.getSingleResult();
            System.out.println("Opgave6 = " + employeeMaxSalary.toString());
        } finally {
            em.close();
            emf.close();
        }
    }

}
