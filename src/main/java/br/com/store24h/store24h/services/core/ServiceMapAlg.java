package br.com.store24h.store24h.services.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceMapAlg {
//    private final String pattern = "(\\d{3,6}-\\d{3,6}|\\d{4,6})";
//    private final String wa = "WhatsApp code (\\d{3,6}-\\d{3,6})";
//    private final String uber = "Seu codigo Uber � (\\d{4,6})";
//    private final String olx = "Use (\\d{4,6}) para validar seu telefone na OLX";
//    private final String pattern2 = "Seu codigo de verificacao do LinkedIn e (\\d{4,6})";
//    private final String shopee = "Shopee: crie sua conta usando o codigo de verificacao (\\d{4,6})";
//    private final String pattern2 = "(\\d{4,6}) e seu codigo de verificacao da Amazon";
//    private final String pattern2 = "eBay: o seu codigo de seguranca e (\\d{4,6})";
//    private final String pattern2 = "<99 >Seu codigo de verificacao e \\((\\d{4,6})";
//    private final String pattern2 = "�Kwai�4248 is your verification code(\\d{4,6})";
//
//
//
//    private final String pattern2 = "Seu codigo Uber � (\\d{4,6})";
//    private final String pattern2 = "Seu codigo Uber � (\\d{4,6})";
//    private final String pattern2 = "Seu codigo Uber � (\\d{4,6})";
//    private final String pattern2 = "Seu codigo Uber � (\\d{4,6})";

    private final HashMap<String, String> abrev = new HashMap<>(100) {{
//        put("ul", "28153"); // universal  - 901692 é seu código de verificação do doar.universal.org.
        //29117

        put("wa", "Codigo do WhatsApp([ Business:]? \\d{3,6}-\\d{3,6})|WhatsApp code([ Business:]? \\d{3,6}-\\d{3,6})"); // Whatsapp     - <#> Codigo do WhatsApp: 846-173
        put("ka", "Shopee: crie sua conta usando o codigo de verificacao (\\d{4,6})"); // Shopee           -
        put("ki", "<99 >Seu codigo de verificacao e \\((\\d{4,6})"); // 99app            -
        put("sn", "Use (\\d{4,6}) para validar seu telefone na OLX"); // Olx              -
        put("tn", "Seu codigo de verificacao do LinkedIn e (\\d{4,6})"); // LinkedIN         -
        put("ub", "Seu codigo Uber � (\\d{4,6})"); // Uber             -
        put("vp", "�Kwai�4248 is your verification code(\\d{4,6})"); // Kwai             -
        put("am", "(\\d{4,6}) e seu codigo de verificacao da Amazon"); // Amazon
        put("dh", "eBay: o seu codigo de seguranca e (\\d{4,6})"); // eBay             -

//        put("??", "Codigo do WhatsApp Business: (\\d{3,6}-\\d{3,6})"); // Whatsapp B   - <#> Codigo do WhatsApp Business: 915-760 Se preferir, use este link para confirmar seu numero: b.whatsapp.com/915760 Nao compartilhe o codigo com
//        put("wx", "0"); // apple
//        put("eh", "0"); // Telegram 2."0"   -
//        put("lj", "0"); // Santander        -
//        put("nf", "0"); // Netflix          -
//        put("ts", "0"); // paypal           -
//        put("tg", "29222"); // Telegram         -
//        put("fb", "27100"); // Facebook         - 663220  :>4 4;O A1@>A0 20H53> ?0@>;O 2 «Facebook»
//        put("tw", "5655545156"); // Twitter          -
//        put("mm", "29795"); // Microsoft        -  29415
//        put("uk", "29090"); // Airbnb           -
//
//                    //29415 - 29415 -29090
//        put("ig", "29468"); // Instagram        -
//        put("kx", "103"); // Vivo
//        put("pd", "7370797968"); // iFood
//        put("mt", "28060"); // Steam            -
//        put("ds", "65858472778371"); // Discord

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
        put("wa", "WhatsApp code (\\d{3,6}-\\d{3,6})"); // Whatsapp     - <#> Codigo do WhatsApp: 846-173
        put("??", "29468"); // Whatsapp B   - <#> Codigo do WhatsApp Business: 915-760 Se preferir, use este link para confirmar seu numero: b.whatsapp.com/915760 Nao compartilhe o codigo com
        put("a9", "wx"); // apple

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
