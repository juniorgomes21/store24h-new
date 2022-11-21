package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.api.dtos.ChipDto;
import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class OperadoraDtoHandler implements SmsEventHandler {

    public static final Map<String, ChipDto> data = Collections.synchronizedMap(new HashMap<>());

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        return false;
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {

        final String STRCOPSX = "((\\+COPS: \\d,\\d,\")([\\d]+|[A-Z]+))";
        Pattern.compile(STRCOPSX, Pattern.MULTILINE);
        ChipDto lis = Utils.detectWhenChipDto(STRCOPSX, serialMessage, gsmModemSistemaControlador.getPortName());
        if (lis != null) data.put(gsmModemSistemaControlador.getPortName(), lis);
        return lis != null;
    }
}
