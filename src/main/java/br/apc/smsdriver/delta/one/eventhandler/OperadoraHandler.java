package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperadoraHandler implements SmsEventHandler {

    public static final Map<String, String> data = Collections.synchronizedMap(new HashMap<>());

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        return false;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        final String STRENTRY = "(\\+COPS: [\\d,\"]+)";
        Pattern.compile(STRENTRY, Pattern.MULTILINE);
        final String STRCOPS = "((\\+COPS: [\\d,\"]+)|([A-Z]+))";
        Pattern.compile(STRCOPS, Pattern.MULTILINE);
//        final String STRCOPS2 = "((\\+COPS: [\\d,\"]+)|(72402|[A-Z]+))";
//        Pattern.compile(STRCOPS2, Pattern.MULTILINE);

        List<String> lis = Utils.detectWhen(STRENTRY, serialMessage);
        if (lis.size() != 1) return false;

        lis = Utils.detectWhen(STRCOPS, serialMessage);
        if (lis.size() == 2) {
            final String STRCOPSX = "((\\+COPS: \\d,\\d,\")|(\\d){3,})";
            Pattern.compile(STRCOPSX, Pattern.MULTILINE);
            lis = Utils.detectWhen(STRCOPSX, serialMessage);
            data.put(gsmModemSistemaControlador.getPortName(), lis.get(1));
            System.out.println(data);
            return true;
        }
        if (lis.size() == 3) {
            data.put(gsmModemSistemaControlador.getPortName(), lis.get(1));
            System.out.println(data);
            return true;
        }

        return false;
    }
}
