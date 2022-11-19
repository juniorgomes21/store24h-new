/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

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
public class MetaModemHandler implements SmsEventHandler{

    public static final Map<String, Map<String, String>> data = Collections.synchronizedMap(new HashMap<>());
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
        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        String st_sp = serialMessage;
        Pattern pa = Pattern.compile(STRMANUF);
        Matcher ma = pa.matcher(st_sp);
        final boolean rea = ma.find();

        if (rea) {
            Set<String> tokens = new HashSet<>();
            while (ma.find()) {
                tokens.add(ma.group(0));
            }
//        System.out.println(tokens);
            List<String[]> lol = tokens.stream().map(s -> s.split(": ")).collect(Collectors.toList());

            for (String[] x: lol){
                data.get(gsmModemSistemaControlador.getPortName()).put(x[0],x[1]);
            }

            // se quiser salvar no db
            //TODO restringir esta chamada para quando o modo de SUPORTE estiver ativo
//            new Thread(() ->
//
//                //ModemDAO.configEntity(gsmModemSistemaControlador.getPortName())
//            );
        }
        return rea;
    }
}
