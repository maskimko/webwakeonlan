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
    
    @PostConstruct
    public void init(){
        ipAddr = "255.255.255.255";
        wkr = new Waker();
        availableMacAddrs = wkr.getArpMacs();
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

    public String getIpAddr() {
        return ipAddr;
    }

    public String getSelectedMac() {
        return selectedMac;
    }
    
    public String doWakeUp(){
        String result = "failure";
        try {
        wkr.doWakeOnLan(ipAddr, selectedMac);
        result = "success";
        } catch (WakeException we) {
        }
        return result;
    }
}
