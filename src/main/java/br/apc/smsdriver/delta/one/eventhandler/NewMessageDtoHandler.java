/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.api.dtos.SmsDto;
import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Archer
 */
public class NewMessageDtoHandler implements SmsEventHandler {
    public static final Map<String, List<SmsDto>> data = Collections.synchronizedMap(new HashMap<>(100));//p<String, new ArrayList<>>(49));
    private String serialMessage = "";
    static {}
    public String getSerialMessage() {
        return serialMessage;
    }

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        String rega = "[\\r\\n](\\+CMGL: ([\\d]+),\"REC (READ|UNREAD)\",\"[\\+]*([\\d\\w]+)\",,\"((\\d\\d/\\d\\d/\\d\\d),(\\d\\d:\\d\\d):\\d\\d-\\d\\d)\"[\\r\\n]+([\\+\\.]*.*))";
        List<SmsDto> smses = Utils.detectWhenDto(rega, serialMessage);

        String port = gsmModemSistemaControlador.getPortName();

        // TODO transformar estes null em string apropriadas
        NewMessageDtoHandler.data.put(port, smses);

        return smses.size() > 0;
    }

}