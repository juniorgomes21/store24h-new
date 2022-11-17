/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import java.util.Map;

/**
 *
 * @author Archer
 */
public class NoneEventHandler implements SmsEventHandler{

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        System.out.println("Outros eventos não importantes: ");
        return false;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
//        System.out.println("====\n Abaixo esta a mensagem serial :");
//        System.err.println(serialMessage);
//        System.out.println("Da porta " + gsmModemSistemaControlador.getPortName()+"\n-------");
        return true;

    }

}
