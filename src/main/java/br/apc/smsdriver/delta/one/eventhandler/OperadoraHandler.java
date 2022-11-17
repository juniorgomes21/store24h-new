package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperadoraHandler implements SmsEventHandler{
    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        return false;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        final String STRCOPS = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        String st_sp = serialMessage;
        Pattern pa = Pattern.compile(STRCOPS, Pattern.MULTILINE);
        Matcher ma = pa.matcher(st_sp);
        final boolean rea = ma.find();
//        AT+COPS?
//                +COPS: 0,0,"VIVO",2
//
//        OK



        return rea;
    }
}
