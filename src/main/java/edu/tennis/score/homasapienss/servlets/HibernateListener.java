package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.utils.HibernateUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Это вызовет создание SessionFactory и генерацию схемы
        HibernateUtil.getSessionFactory();
        System.out.println("Hibernate SessionFactory initialized and DB schema created.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();
        System.out.println("Hibernate SessionFactory closed.");
    }
}
