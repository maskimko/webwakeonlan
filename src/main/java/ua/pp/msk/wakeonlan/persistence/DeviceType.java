/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.persistence;

/**
 *
 * @author maskimko
 */
public enum DeviceType {
    
    DESKTOP("Desktop", "Not portable computer with display and human interaction devices. Usually stands on the desk.", null),
    LAPTOP("Laptop","Portable computer, with embedded keyboard and display.", null), 
    TABLET("Tablet", "Portable computer. Usually without hard keyboard, with sensor display", null), 
    CELLPHONE("Cell phone", "Portable phone. Can be a smatrphone.", null),
    SERVER("Server", "Hardware server. Usually has no human interaction devices", null), 
    VIRTUALMACHINE("Virtual Machine", "Virtual computer. Usually it is a virtual server", null), 
    OTHER("Unknown", "Unknown device type, which can communicate via IP", null);
    
    private String typeName;
    private String description;
    private String iconPath;
    
     DeviceType(String typeName, String description, String iconPath){
        this.typeName = typeName;
        this.description = description;
        this.iconPath = iconPath;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }

    public String getIconPath() {
        return iconPath;
    }
     
     
}
