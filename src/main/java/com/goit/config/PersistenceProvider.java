package com.goit.config;

import javax.persistence.*;

public class PersistenceProvider {

  private static final EntityManagerFactory emf;
  static {
    try {
      emf = Persistence
          .createEntityManagerFactory("ProjectManagementSystem");

    } catch (Throwable t) {
      t.printStackTrace();
      throw t;
    }
  }

  public static EntityManager getEntityManager() {
    return emf.createEntityManager();
  }
}