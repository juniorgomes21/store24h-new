package br.apc.smsdriver.delta.one.eventhandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static List<String> detectWhen(String reg, String serialMessage) {
        List<String> tokens = new ArrayList<>();
        Pattern pa = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);
        while (ma.find()) {
            tokens.add(ma.group());
        }
        String TIM = "\n" +
                "+COPS: 0,2,\"72402\",2\n" +
                "\n" +
                "OK\n";
        //System.out.println(tokens);
        for (String s : tokens) {
            System.err.println("\n\n=============\n0: " + s + "\n*************\n");
        }
//        final boolean rea = ma.find();
        return tokens;
    }
}
