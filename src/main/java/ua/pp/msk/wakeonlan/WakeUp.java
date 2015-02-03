/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ua.pp.msk.wakeonlan.engine.Waker;
import ua.pp.msk.wakeonlan.exceptions.WakeException;
import ua.pp.msk.wakeonlan.persistence.Computer;
import ua.pp.msk.wakeonlan.persistence.DeviceManager;
import ua.pp.msk.wakeonlan.persistence.DeviceType;

/**
 *
 * @author maskimko
 */
@ManagedBean(name="wakeUp")
@ViewScoped
public class WakeUp {
    
    private List<String> availableMacAddrs;
    private String ipAddr;
    private String selectedMac;
    private Waker wkr;
    private DeviceManager dm;
    private String devIp;
    private String devName;
    private String devUser;
    private String devPassword;
    private String devIdentity;
    private String type;
    
    
    @PostConstruct
    public void init(){
        ipAddr = "255.255.255.255";
        wkr = new Waker();
        availableMacAddrs = wkr.getArpMacs();
        try {
            dm = DeviceManager.getDeviceManager();
        } catch (NamingException ex) {
            Logger.getLogger(WakeUp.class.getName()).error("Cannot initialize DeviceManager class " + ex.getMessage(), ex);
        }
    }
    
    public void setIpAddress(String address){
        this.ipAddr = address;
    }
    
    public void setMacAddress(String address){
        this.selectedMac = address;
    }
    
    public List<String> getMacAddresses(){
        return availableMacAddrs;
    }

    public String getIpAddress() {
        return ipAddr;
    }

    public String getMacAddress() {
        return selectedMac;
    }
    
    public String doWakeUp(){
        String result = "doesNotSent";
        try {
            if (selectedMac == null) {
                Logger.getLogger(this.getClass()).error("user did not select any mac addresses ");
                return result;
            }
        wkr.doWakeOnLan(ipAddr, selectedMac);
        result = "sent";
        } catch (WakeException we) {
        }
        return result;
    }
    
    public List<Computer> getDevices(){
        List<Computer> computers = dm.getComputers();
        return computers;
    }

    public String getDeviceIp() {
        return devIp;
    }

    public void setDeviceIp(String devIp) {
        this.devIp = devIp;
    }

    public String getDeviceName() {
        return devName;
    }

    public void setDeviceName(String devName) {
        this.devName = devName;
    }

    public String getDeviceType() {
        return type;
    }
    
    public List<String> getDeviceTypes(){
        List<String> types = new ArrayList<String>();
        for (DeviceType t : DeviceType.values() ){
            types.add(t.getTypeName());
        }
        return types;
    }

    public void setDeviceType(String type) {
        this.type = type;
    }
    
    
    public String addDevice(){
        Computer comp = new Computer(devIp, selectedMac, devName);
        comp.setUser(devUser);
        comp.setPassword(devPassword);
        comp.setIdentity(devIdentity);
        comp.setDeviceType(type);
        dm.addComputer(comp);
        return "added";
    }
    
    
  
}
