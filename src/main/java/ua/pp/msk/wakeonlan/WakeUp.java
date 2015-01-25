/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ua.pp.msk.wakeonlan.engine.Waker;
import ua.pp.msk.wakeonlan.exceptions.WakeException;
import ua.pp.msk.wakeonlan.persistence.Computer;
import ua.pp.msk.wakeonlan.persistence.DeviceManager;

/**
 *
 * @author maskimko
 */
@ManagedBean(name="wakeUp")
@RequestScoped
public class WakeUp {
    
    private List<String> availableMacAddrs;
    private String ipAddr;
    private String selectedMac = null;
    private Waker wkr;
    private DeviceManager dm;
    private String devIp;
    private String devName;
    private String devUser;
    private String devPassword;
    private String devIdentity;
    
    
    @PostConstruct
    public void init(){
        ipAddr = "255.255.255.255";
        wkr = new Waker();
        availableMacAddrs = wkr.getArpMacs();
        dm = DeviceManager.getDeviceManager();
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
    
    
    
}
