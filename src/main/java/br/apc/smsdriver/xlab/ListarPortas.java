/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta;

/**
 *
 * @author Archer
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;
import static jssc.SerialPort.BAUDRATE_9600;
import static jssc.SerialPort.DATABITS_8;
import static jssc.SerialPort.PARITY_NONE;
import static jssc.SerialPort.STOPBITS_1;
public class ListarPortas {
    public static void write(String[] args) {
        try {
            SerialPort port = new SerialPort("COM1");
            port.openPort();
            port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
            // port.setParams(9600, 8, 1, 0); // alternate technique
            port.writeBytes("Testing serial from Java".getBytes());
            port.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(ListarPortas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void list(String[] args) {
        for(String port : SerialPortList.getPortNames()) {
            System.out.println(port);
        }
    }

    public static void read(String[] args) {
        try {
            SerialPort port = new SerialPort("COM1");
            port.openPort();
            port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
            // port.setParams(9600, 8, 1, 0); // alternate technique
            byte[] buffer = port.readBytes(10 /* read first 10 bytes */);
            port.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(ListarPortas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        list(args);
        //write(args);
//        read(args);

    }
}
