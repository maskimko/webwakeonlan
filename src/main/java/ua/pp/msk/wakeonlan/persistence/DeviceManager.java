/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author maskimko
 */
public class DeviceManager {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static DeviceManager dm;

    public static DeviceManager getDeviceManager(){
        if (dm == null){
            synchronized(DeviceManager.class){
                if (dm == null){
                    dm = new DeviceManager();
                }
            }
        }
        return dm;
    }
    
    private DeviceManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("sqlitePU");
        }
        em = emf.createEntityManager();
    }

    public List<Computer> getComputers() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query allComputerQuery = em.createNamedQuery("findAllComputers", Computer.class);
        List<Computer> resultList = allComputerQuery.getResultList();
        return resultList;
    }

    public void addComputer(Computer comp) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(comp);
        transaction.commit();
    }
}
