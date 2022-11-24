/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Archer
 */
public class AllEventHandler implements SmsEventHandler {
    private List<SmsEventHandler> eventHandlersAll = new ArrayList<>();
    private String serialMessage = "";

    public String getSerialMessage() {

        return serialMessage;

    }

    public List<SmsEventHandler> getEventHandlersAll() {
        return eventHandlersAll;
    }

    //    public static final Map<String, GsmModemSistemaControlador> map = new HashMap<>(49, .75f);
    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        Set<SmsEventHandler> eventHandlers = new HashSet<>();
        NewMessageHandler newSms = new NewMessageHandler();
        SmsEventHandler[] lol = new SmsEventHandler[]{
                //other Handler,
                new NoneEventHandler(),
                newSms,
//            new NumberHandler(),
//            new OperadoraHandler()
        };
        eventHandlersAll = Arrays.asList(lol);
        eventHandlers = new HashSet<>(eventHandlersAll);

        for (SmsEventHandler eventHandler : eventHandlers) {
            eventHandler.check(map, serialMessage);
        }

        return true;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        this.serialMessage = serialMessage;
        Set<SmsEventHandler> eventHandlers;// = new HashSet<>();
        System.err.println("***************************\n\n\n"+serialMessage);
        NewMessageHandler newSms = new NewMessageHandler();
        NewMessageHandler.msgs.get(gsmModemSistemaControlador.getPortName());
        SmsEventHandler[] lol = new SmsEventHandler[]{
                //other Handler,
                new NumberHandler(),
//            new NoneEventHandler(),
                newSms,
//            new OperadoraHandler()
        };

        eventHandlersAll = Arrays.asList(lol);
        eventHandlers = new HashSet<>(eventHandlersAll);

        for (SmsEventHandler eventHandler : eventHandlers) {
            eventHandler.check(gsmModemSistemaControlador, serialMessage);
        }

        return true;
    }

}
