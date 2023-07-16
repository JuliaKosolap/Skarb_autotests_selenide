package org.example.crudservice;

import org.example.HibernateUtil;
import org.example.entity.Volunteer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VolunteerCrudService {
    public void create(Volunteer volunteer) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(volunteer);
        transaction.commit();
        session.close();
    }
    public ArrayList<String> getAllUsersEmails() {
        ArrayList<String> emailList = new ArrayList<>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Volunteer> volunteers = session.createQuery(
                "from users", Volunteer.class).list();
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.get(i);
            String email = volunteer.getEmail();
            emailList.add(email);
        }
        return emailList;
    }
}
