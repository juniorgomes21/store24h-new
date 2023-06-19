package br.com.store24h.store24h.services.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceMap {
    private final HashMap<String, String> abrev = new HashMap<>(100) {{
        put("wx", "0");
        put("eh", "0");
        put("lj", "0");
        put("nf", "0");
        put("ts", "0");
        put("fb", "27100");
        put("ka", "28149");
        put("ki", "27100");
        put("sn", "28908");
        put("tg", "29222");
        put("tn", "29454");
        put("tw", "5655545156");
        put("ub", "5655545156");
        put("mm", "29795");
        put("vp", "29415");
        put("uk", "29090");
        put("wa", "29468");
        put("??", "29468");
        put("ig", "29468");
        put("kx", "103");
        put("pd", "7370797968");
        put("am", "28060");
        put("dh", "28060");
        put("mt", "28060");
        put("ds", "65858472778371");
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
        put("wa", "29468");
        put("??", "29468");
        put("a9", "wx");
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
