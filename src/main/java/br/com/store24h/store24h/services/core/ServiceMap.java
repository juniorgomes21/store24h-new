package br.com.store24h.store24h.services.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceMap {
    private final HashMap<String, String> abrev = new HashMap<>(100) {{
//        put("ul", "28153"); // universal  - 901692 é seu código de verificação do doar.universal.org.
        //29117

        put("wx", "0"); // apple
        put("eh", "0"); // Telegram 2."0"   -
        put("lj", "0"); // Santander        -
        put("nf", "0"); // Netflix          -
        put("ts", "0"); // paypal           -

        put("fb", "27100"); // Facebook         - 663220  :>4 4;O A1@>A0 20H53> ?0@>;O 2 «Facebook»
        put("ka", "28149"); // Shopee           -
        put("ki", "27100"); // 99app            -
        put("sn", "28908"); // Olx              -
        put("tg", "29222"); // Telegram         -
        put("tn", "29454"); // LinkedIN         -

        put("tw", "5655545156"); // Twitter          -
        put("ub", "5655545156"); // Uber             -

        put("mm", "29795"); // Microsoft        -  29415

        put("vp", "29415"); // Kwai             -

        put("uk", "29090"); // Airbnb           -

                    //29415 - 29415 -29090
        put("wa", "29468"); // Whatsapp     - <#> Codigo do WhatsApp: 846-173
        put("??", "29468"); // Whatsapp B   - <#> Codigo do WhatsApp Business: 915-760 Se preferir, use este link para confirmar seu numero: b.whatsapp.com/915760 Nao compartilhe o codigo com
        put("ig", "29468"); // Instagram        -

        put("kx", "103"); // Vivo
        put("pd", "7370797968"); // iFood
        put("am", "28060"); // Amazon
        put("dh", "28060"); // eBay             -
        put("mt", "28060"); // Steam            -
        put("ds", "65858472778371"); // Discord

    }};

    private final HashMap<String, String> abrevReverse = new HashMap<>(100) {{
//        put("ul", "28153"); // universal  - 901692 é seu código de verificação do doar.universal.org.
        put("01", "dh"); // eBay             -
        put("02", "eh"); // Telegram 2."0"   -
        put("03", "fb"); // Facebook         - 663220  :>4 4;O A1@>A0 20H53> ?0@>;O 2 «Facebook»
        put("04", "ig"); // Instagram        -
        put("05", "ka"); // Shopee           -
        put("06", "ki"); // 99app            -
        put("07", "lj"); // Santander        -
        put("08", "mm"); // Microsoft        -
        put("09", "mt"); // Steam            -
        put("a1", "nf"); // Netflix          -
        put("a2", "sn"); // Olx              -
        put("29222", "tg"); // Telegram         -
        put("a3", "tn"); // LinkedIN         -
        put("a4", "ts"); // paypal           -
        put("a5", "tw"); // Twitter          -
        put("a6", "ub"); // Uber             -
        put("a7", "uk"); // Airbnb           -
        put("a8", "vp"); // Kwai             -
        put("wa", "29468"); // Whatsapp     - <#> Codigo do WhatsApp: 846-173
        put("??", "29468"); // Whatsapp B   - <#> Codigo do WhatsApp Business: 915-760 Se preferir, use este link para confirmar seu numero: b.whatsapp.com/915760 Nao compartilhe o codigo com
        put("a9", "wx"); // apple

    }};

    public ServiceMap(){
    }

    public String getNumberByAlias(String alias) {
        return abrev.get(alias);
    }

    public String getAliasByNumber(String serviceNumber) {
        return abrevReverse.get(serviceNumber);
    }
}
