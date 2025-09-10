package edu.tennis.score.homasapienss.utils;

import edu.tennis.score.homasapienss.entities.Match;
import edu.tennis.score.homasapienss.entities.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Class.forName("org.h2.Driver");
                // 1. Загружаем настройки из hibernate.properties
                Configuration configuration = new Configuration();
//                 2. Регистрируем entity-классы
                configuration.addAnnotatedClass(Player.class);
                configuration.addAnnotatedClass(Match.class);
//
                // 3. Строим SessionFactory
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create Hibernate SessionFactory", e);
            }
        }
        return sessionFactory;
    }

}
