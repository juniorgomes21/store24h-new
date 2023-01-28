package br.com.store24h.store24h.services.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceMap {
    private final HashMap<String, String> abrev = new HashMap<>(100) {{
//        put("ul", "28153"); // universal  - 901692 é seu código de verificação do doar.universal.org.
        put("dh", "0"); // eBay             -
        put("eh", "0"); // Telegram 2."0"   -
        put("fb", "0"); // Facebook         - 663220  :>4 4;O A1@>A0 20H53> ?0@>;O 2 «Facebook»
        put("ig", "0"); // Instagram        -
        put("ka", "0"); // Shopee           -
        put("ki", "0"); // 99app            -
        put("lj", "0"); // Santander        -
        put("mm", "0"); // Microsoft        -
        put("mt", "0"); // Steam            -
        put("nf", "0"); // Netflix          -
        put("sn", "0"); // Olx              -
        put("tg", "0"); // Telegram         -
        put("tn", "0"); // LinkedIN         -
        put("ts", "0"); // paypal           -
        put("tw", "0"); // Twitter          -
        put("ub", "0"); // Uber             -
        put("uk", "0"); // Airbnb           -
        put("vp", "0"); // Kwai             -
        put("wa", "29468"); // Whatsapp     - <#> Codigo do WhatsApp: 846-173
        put("??", "29468"); // Whatsapp B   - <#> Codigo do WhatsApp Business: 915-760 Se preferir, use este link para confirmar seu numero: b.whatsapp.com/915760 Nao compartilhe o codigo com
        put("wx", "0"); // apple
//        put("uk", "0"); // Airbnb
//        put("uk", "0"); // Airbnb
//        put("uk", "0"); // Airbnb
//        put("uk", "0"); // Airbnb

    }};

    public ServiceMap(){
    }

    public String getByAlias(String alias) {
        return abrev.get(alias);
    }

}
