/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.api.dtos.ModemDto;
import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//import static br.apc.smsdriver.delta.one.model.dao.ModemDAO.configEntity;

/**
 *
 * @author Archer
 */
public class MetaModemDtoHandler implements SmsEventHandler{

    public static final Map<String, ModemDto> data = Collections.synchronizedMap(new HashMap<>());
//    public static final Map<String, ModemModel> modemModelMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {

        findNumber(map, serialMessage);
        return false;
    }

    private void findNumber(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        final String ATI = "([\\r\\n]+(Manufacturer: (.*))[\\r\\n]+(Model: (.*))[\\r\\n]+(Revision: (.*))[\\r\\n]+IMEI: (\\d*)[\\r\\n]+\\+GCAP: (.*)[\\r\\n]+OK[\\r\\n]+)";
//        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        Pattern pa = Pattern.compile(ATI);
        String st_sp = serialMessage;
        ModemDto resp = Utils.detectWhenModemDto(ATI, serialMessage, gsmModemSistemaControlador.getPortName());
        if (resp != null) data.put(gsmModemSistemaControlador.getPortName(), resp);
        return resp != null;
    }
}
