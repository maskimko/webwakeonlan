/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.persistence;

import java.io.Serializable;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author maskimko
 */
//@Entity
//@NamedQueries({
//    @NamedQuery(name = "findAllComputers", query = "SELECT c FROM Computer c"),
//    @NamedQuery(name = "findByMac", query = "SELECT c FROM Computer c WHERE c.macAddress LIKE :mac")
//})
public class Computer implements Serializable, Comparable<Computer> {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = -1L;
    @NotNull
    @Size(min = 7, max = 15, message = "Seems to be ip address has invalid size")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.\n" +
"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
    private String ipAddress;
    @NotNull
    @Size(min = 17, max = 17)
    @Pattern(regexp = "^([0-9a-fA-F][:-]){5}[0-9a-fA-F]")
    private String macAddress;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    private String user;
    private String password;
    private String identity;
    private DeviceType dt;

    public Computer() {
    }

    public Computer(String ipAddress, String macAddress, String name) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.name = name;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceType getDeviceType() {
        return dt;
    }

    public void setDeviceType(String deviceType){
        this.dt = DeviceType.OTHER;
        for (DeviceType dt: DeviceType.values()){
            if (dt.toString().toLowerCase().equals(deviceType.toLowerCase())){
                this.dt = dt;
                break;
            }
        }
    }
    public void setDeviceType(DeviceType dt) {
        this.dt = dt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Computer)) {
            return false;
        }
        Computer other = (Computer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.pp.msk.wakeonlan.persistence.Computer[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Computer o) {
      Long thisId = getId();
      Long foreignId = o.getId();
      return thisId.compareTo(foreignId);
    }
    
}
