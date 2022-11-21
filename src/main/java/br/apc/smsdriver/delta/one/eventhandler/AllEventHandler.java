/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.Driver24hubApplication;
import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
//import br.com.store24h.store24h.Store24hApplication;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Archer
 */
public class AllEventHandler implements SmsEventHandler {
//    private static final String PARTTEN_GARBAGE_MODEM =
//        "(\\+ZUSIMR:){1,}(\\+ZMTime:){1,}(\\+ZEND:){1,}(\\+ZPASR:){1,}(\\++ZDONR:){1,}";   |(\+ZEND:)|(\+ZPASR:)|(\+ZDONR:)
    private static final String PARTTEN_GARBAGE_MODEM ="((\\^MODE:)|(\\^RSSI:)|(\\+ZDIST:)|(\\+ZMTime)|(\\^DSFLOWRPT)|(\\+ZUSIMR)){1,}";
    private static final String PARTTEN_NETWORK_ARRIVE_MODEM = "\\+CMTI:";
    Pattern pa = Pattern.compile(PARTTEN_GARBAGE_MODEM, Pattern.MULTILINE);
    public static final SmsEventHandler[]
            eventHandlersLoader = new SmsEventHandler[]{
            //other Handler,
            new NumberHandler(),
//            new OperadoraHandler(),
            new OperadoraDtoHandler(),
//            new MetaModemHandler(),
            new MetaModemDtoHandler()
//            new NoneEventHandler(),
//            new NewMessageHandler(),
//            new NewMessageDtoHandler(),
    };

    public AllEventHandler() {
        eventHandlersAll.addAll(Arrays.asList(eventHandlersLoader));
    }

    /**
     * Todos Os responsáveis por dá suporte a uma caracteristica
     */
    public static final Set<SmsEventHandler> eventHandlersAll = Collections.synchronizedSet(new HashSet<>());

    /**
     * A mensagem que o modem retorna aos comandos
     */
    private String serialMessage = "";

    /**
     * Os modem mapeados de acordo com suas portas
     */ //HashMap<>(49, .75f);
    public static final Map<String, GsmModemSistemaControlador> drivers = new HashMap<>();

    public String getSerialMessage() {
        return serialMessage;
    }

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
//        Set<SmsEventHandler> eventHandlers;
        NewMessageHandler newSms = new NewMessageHandler();
        SmsEventHandler[] lol = new SmsEventHandler[]{
                //other Handler,
//                new NoneEventHandler(),
                newSms,
//            new NumberHandler(),
//            new OperadoraHandler()
        };
////        eventHandlersAll.addAll(Arrays.asList(lol));
//        eventHandlersAll.addAll(List.of(
//                        //other Handler,
////                new NoneEventHandler(),
//                        newSms
////            new NumberHandler(),
////            new OperadoraHandler()
//                )
//        );
//        eventHandlers = new HashSet<>(eventHandlersAll);

        for (SmsEventHandler eventHandler : eventHandlersAll) {
            eventHandler.check(map, serialMessage);
        }

        return true;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        this.serialMessage = serialMessage;
//        String PARTTEN_NETWORK_ARRIVE_MODEM = "\\+CMTI:";

        if (!(Utils.detectWhen(AllEventHandler.PARTTEN_GARBAGE_MODEM, serialMessage).size() > 0)) {
            //se detectar chegada de sms da rede, solicita a leitura do mesmo e interrompe o fluxo aqui neste momento
            if (Utils.detectWhen(AllEventHandler.PARTTEN_NETWORK_ARRIVE_MODEM, serialMessage).size()> 0) {
                // TODO retirar askMessage e outros methods de Store24hApplication
                Driver24hubApplication.askMessage(gsmModemSistemaControlador);
                return true;
            }
//            System.err.println("***************************\n\n\n" + serialMessage);
            // executa a sequencia strategy, inclusive de update da mensagem nova
            for (SmsEventHandler eventHandler : eventHandlersAll) {
                eventHandler.check(gsmModemSistemaControlador, serialMessage);
            }
        } else {
            // System.err.println("***************************\n\n\n" + serialMessage);
        }

        return true;
    }

}
