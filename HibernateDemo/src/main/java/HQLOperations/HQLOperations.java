package HQLOperations;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HQLOperations {

    public static void main(String[] args) {
        HQLOperations operations = new HQLOperations();
        operations.addClient();
        operations.displayAllClientsCompleteObject();
    }

    // Add Client using a persistent object
    public void addClient() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession(); // Create connection

        Transaction t = session.beginTransaction();

        Client client = new Client();
        client.setName("John Doe");
        client.setGender("Male");
        client.setAge(30);
        client.setLocation("New York");
        client.setEmail("john.doe@example.com");
        client.setMobile("1234567890");

        session.persist(client);
        t.commit();

        System.out.println("Client Added Successfully");
        sf.close();
        session.close();
    }

    // Display all clients (complete object)
    public void displayAllClientsCompleteObject() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession(); // Create connection

        String hql = "from Client"; // Equivalent to SELECT * FROM clients
        Query<Client> qry = session.createQuery(hql, Client.class);
        List<Client> clientList = qry.getResultList();

        System.out.println("Total Clients = " + clientList.size());
        for (Client c : clientList) {
            System.out.println("ID: " + c.getId());
            System.out.println("Name: " + c.getName());
            System.out.println("Gender: " + c.getGender());
            System.out.println("Age: " + c.getAge());
            System.out.println("Location: " + c.getLocation());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Mobile: " + c.getMobile());
        }

        session.close();
        sf.close();
    }
}
