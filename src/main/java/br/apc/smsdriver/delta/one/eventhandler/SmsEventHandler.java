package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import java.util.Map;

/**
 *
 * @author Archer
 */
public interface SmsEventHandler {
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage);
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage);
}
