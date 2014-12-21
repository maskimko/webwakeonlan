/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author maskimko
 */
@ManagedBean(name="helloWorldBean")
@RequestScoped
public class HelloWorldBean {
    private String msg;
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    @PostConstruct
    private void init(){
        msg = "Hello World! JFS example";
    }
}
