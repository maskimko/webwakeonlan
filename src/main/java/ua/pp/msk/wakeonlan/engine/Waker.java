/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.engine;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import ua.pp.msk.WakeOnLan;
import ua.pp.msk.project1.lib.routelibrary.ArpTableInformation;
import ua.pp.msk.project1.lib.routelibrary.ArpTableInformationImpl;
import ua.pp.msk.project1.lib.routelibrary.ArpTableRecord;
import ua.pp.msk.project1.lib.routelibrary.Converter;
import ua.pp.msk.wakeonlan.exceptions.WakeException;

/**
 *
 * @author maskimko
 */
public class Waker {
    public void doWakeOnLan(String address, String mac) throws WakeException{
        WakeOnLan wol = new WakeOnLan();        
        try {
            InetAddress ip = InetAddress.getByName(address);
            wol.wakeOnLan(ip, mac);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Waker.class.getName()).error("Cannot process arguments " + address + " " + mac, ex);
            throw new WakeException("Cannot process arguments " + address + " " + mac, ex);
        } catch (IOException ex) {
            Logger.getLogger(Waker.class.getName()).error("Input/Output error", ex);
            throw new WakeException("Input/Output error", ex);
        }
        
    }
    
    public List<String> getArpMacs(){
        List<String> macs = new ArrayList<String>();
        ArpTableInformation ati = new ArpTableInformationImpl();
        List<ArpTableRecord> arpTable = ati.getArpTable();
        final byte[] incomplete = new byte[]{0,0,0,0,0,0};
        for (ArpTableRecord atr: arpTable) {
            byte[] hwAddress = atr.getHwAddress();
            if (!Arrays.equals(hwAddress, incomplete)) {
                macs.add(Converter.macToString(hwAddress));
            }
        }
        return macs;
    }
}
