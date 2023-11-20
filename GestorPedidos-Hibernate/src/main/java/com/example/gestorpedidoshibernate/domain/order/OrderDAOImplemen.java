package com.example.gestorpedidoshibernate.domain.order;

import com.example.gestorpedidoshibernate.HibernateUtil;
import com.example.gestorpedidoshibernate.domain.item.Item;
import com.example.gestorpedidoshibernate.domain.product.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImplemen implements OrderDAO{
    @Override
    public Order save(Order data) {


        Order exit = null;

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction(); // Inicio transaccion

            s.persist(data);//Persist hace que un juegoe ste sincroniado con la base de datos


            t.commit();

            exit = data;
        } catch (Exception ex) {

            System.out.println("Error saving Order");
        }
        return data;
    }


}




