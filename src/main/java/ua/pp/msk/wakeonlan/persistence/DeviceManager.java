/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author maskimko
 */
public class DeviceManager {

//    private static EntityManagerFactory emf;
//    private static EntityManager em;
    private static DeviceManager dm;
    private static DataSource ds;

    public static DeviceManager getDeviceManager() throws NamingException {
        if (dm == null) {
            synchronized (DeviceManager.class) {
                if (dm == null) {
                    dm = new DeviceManager();
                }
            }
        }
        return dm;
    }

    private DeviceManager() throws NamingException {
//        if (emf == null) {
//            emf = Persistence.createEntityManagerFactory("sqlitePU");
//        }
//        em = emf.createEntityManager();
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/wakeonlanDB");
    }

    public List<Computer> getComputers() {
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        Query allComputerQuery = em.createNamedQuery("findAllComputers", Computer.class);
//        List<Computer> resultList = allComputerQuery.getResultList();
        List<Computer> resultList = new LinkedList<Computer>();
        Connection connection = null;
        try {
            connection = ds.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet compQuery = stmt.executeQuery("SELECT * FROM Computer");
            while (compQuery.next()) {
                Computer comp = new Computer();
                long id = compQuery.getLong("id");
                comp.setId(id);
                String ipAddr = compQuery.getString("ipAddress");
                comp.setIpAddress(ipAddr);
                String macAddr = compQuery.getString("macAddress");
                comp.setMacAddress(macAddr);
                String user = compQuery.getString("user");
                comp.setUser(user);
                String password = compQuery.getString("password");
                comp.setPassword(password);
                String identity = compQuery.getString("identity");
                comp.setIdentity(identity);
                String type = compQuery.getString("type");
                comp.setDeviceType(type);
                resultList.add(comp);
            }
        } catch (SQLException sex) {
            Logger.getLogger(this.getClass()).error("Cannot communicate to the database", sex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeviceManager.class.getName()).error("Cannot close the connection ", ex);
                }
            }
        }
        return resultList;
    }
    
    private long getNextId(){
        List<Computer> computers = getComputers();
        if (computers.isEmpty()){
         return 0;   
        }else {
        Collections.sort(computers);
        long id = computers.get(computers.size()-1).getId();
        return ++id;
        }
    }

    public void addComputer(Computer comp) {
       Connection connection = null; 
        long id = comp.getId();
        if (id == -1 ){
            comp.setId(getNextId());
        }
       try {
           connection = ds.getConnection();
           Statement stmt = connection.createStatement();
           StringBuilder sb = new StringBuilder("INSERT INTO Computer VALUES ( '").append(comp.getId())
                   .append("', '").append(comp.getIpAddress())
                   .append("', '").append(comp.getMacAddress())
                   .append("', '").append(comp.getName())
                   .append("', '").append(comp.getUser())
                   .append("', '").append(comp.getPassword())
                   .append("', '").append(comp.getIdentity())
                   .append("', '").append(comp.getDeviceType().toString()).append("')");
           stmt.executeUpdate(sb.toString());
    } catch (SQLException sex) {
           Logger.getLogger(this.getClass()).error("Cannot communicate to the database", sex);
    } finally {
           if ( connection != null) {
               try {
                   connection.close();
               } catch (SQLException ex) {
             Logger.getLogger(DeviceManager.class.getName()).error("Cannot close the connection ", ex);
               }
           }
       }
    }
}
