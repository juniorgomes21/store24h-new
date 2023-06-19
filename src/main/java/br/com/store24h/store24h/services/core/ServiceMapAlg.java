package br.com.store24h.store24h.services.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceMapAlg {

    private final HashMap<String, String> abrev = new HashMap<>(100) {{
        put("wa", "Codigo do WhatsApp([ Business:]? \\d{3,6}-\\d{3,6})|WhatsApp code([ Business:]? \\d{3,6}-\\d{3,6})");
        put("ka", "Shopee: crie sua conta usando o codigo de verificacao (\\d{4,6})");
        put("ki", "<99 >Seu codigo de verificacao e \\((\\d{4,6})");
        put("sn", "Use (\\d{4,6}) para validar seu telefone na OLX");
        put("tn", "Seu codigo de verificacao do LinkedIn e (\\d{4,6})");
        put("ub", "Seu codigo Uber � (\\d{4,6})");
        put("vp", "�Kwai�4248 is your verification code(\\d{4,6})");
        put("am", "(\\d{4,6}) e seu codigo de verificacao da Amazon");
        put("dh", "eBay: o seu codigo de seguranca e (\\d{4,6})");
    }};

    private final HashMap<String, String> abrevReverse = new HashMap<>(100) {{
        put("01", "dh");
        put("02", "eh");
        put("03", "fb");
        put("04", "ig");
        put("05", "ka");
        put("06", "ki");
        put("07", "lj");
        put("08", "mm");
        put("09", "mt");
        put("a1", "nf");
        put("a2", "sn");
        put("29222", "tg");
        put("a3", "tn");
        put("a4", "ts");
        put("a5", "tw");
        put("a6", "ub");
        put("a7", "uk");
        put("a8", "vp");
        put("wa", "WhatsApp code (\\d{3,6}-\\d{3,6})");
        put("??", "29468");
        put("a9", "wx");
    }};

    public ServiceMapAlg(){
    }

    public String getRegxByAlias(String alias) {
        return abrev.get(alias);
    }

    public String getAliasByNumber(String serviceNumber) {
        return abrevReverse.get(serviceNumber);
    }
}
