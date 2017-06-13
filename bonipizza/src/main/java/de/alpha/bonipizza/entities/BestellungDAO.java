package de.alpha.bonipizza.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import jdk.nashorn.internal.objects.Global;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.util.*;

/**
 * Created by prechtla on 18.05.17.
 */
public class BestellungDAO {
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        if (factory == null){
            factory = new Configuration().configure().buildSessionFactory();
            return factory;
        }else
            return factory;
    }

    public static List<Bestellung> getAlleBestellungen() {
        Transaction tx = null;
        try (Session session = BestellungDAO.getSessionFactory().openSession()) {
            //tx = session.beginTransaction();
            List bestellungen = session.createQuery("FROM Bestellung").list();
            //tx.commit();
            return bestellungen;

        } catch (HibernateException he) {
            //if(tx == null) tx.rollback();
            he.printStackTrace();
            return new ArrayList<Bestellung>();
        }
    }

    public static void deleteBestellung(Bestellung einzelement) {
        Transaction tx = null;
        try (Session session = BestellungDAO.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(einzelement);
            tx.commit();
        } catch (HibernateException he) {
            if (tx == null) tx.rollback();
            he.printStackTrace();
        }
    }

    public static void createBestellung(Bestellung einzelelement) {
        Transaction tx = null;
        try (Session session = BestellungDAO.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(einzelelement);
            tx.commit();
        } catch (HibernateException he) {
            if (tx == null) tx.rollback();
            he.printStackTrace();
        }
    }
//    public static void saveOrUpdateBestellungen( Bestellung bestellung)
//    {
//        List<Bestellung> bestellungsListe = getAlleBestellungen();
//        if( bestellungsListe == null || bestellung == null ) {
//            return;
//        }
//        bestellungsListe.remove(bestellung);
//        bestellungsListe.add(bestellung);
//        writeAll(bestellungsListe);
//    }

}
