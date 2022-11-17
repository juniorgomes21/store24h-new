package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Archer
 */
public interface SmsEventHandler {
    boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage);
    boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage);
}
