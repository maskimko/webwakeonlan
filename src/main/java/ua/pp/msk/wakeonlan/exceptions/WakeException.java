/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.wakeonlan.exceptions;

/**
 *
 * @author maskimko
 */
public class WakeException extends Exception {

    public WakeException(String message) {
        super(message);
    }

    public WakeException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
