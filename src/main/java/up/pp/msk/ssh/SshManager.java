/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up.pp.msk.ssh;


import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.slf4j.LoggerFactory;
import ua.pp.msk.edem.ssh.DeviceInfo;
import ua.pp.msk.edem.ssh.DeviceInfoImpl;


/**
 *
 * @author maskimko
 */
public class SshManager {
    
    
    
    public DeviceInfo getDeviceInfo(InetSocketAddress host, String user, String password){
        DeviceInfo di = null;
        try {
            //TODO rewrite type safely
             di = new DeviceInfoImpl(host.getHostString(), host.getPort(), user, password);
        } catch (UnknownHostException ex) {
            LoggerFactory.getLogger(SshManager.class.getName()).error("Cannot get device information", ex);
        }
        return di;
    }
    
}
