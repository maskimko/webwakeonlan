/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up.pp.msk.ssh;

/**
 *
 * @author maskimko
 */
public interface DeviceInfo {

    //cat /proc/uptime
    public long getUpTime();

    public long getIdleTime();

    //uptime

    public String getUptimeInfo();

    public String getLoadAverage();
    public float getLoadAverage1min();
    public float getLoadAverage5min();
    public float getLoadAverage15min();
    
    //who | wc -l 

    public int getLoggedInUsers();

    public String getIpAddress();

    public String getMacAddress();

    public String getHostname();

    //cat /proc/meminfo in kb

    public long getTotalRam();

    public long getFreeRam();

    //grep processor /proc/cpuinfo | wc -l 

    public int getNumberOfCores();

}
