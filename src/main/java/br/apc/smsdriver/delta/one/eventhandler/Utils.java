package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.api.dtos.ChipDto;
import br.apc.smsdriver.api.dtos.SmsDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static List<String> detectWhen(String reg, String serialMessage) {
        List<String> tokens = new ArrayList<>();
        Pattern pa = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);

//        boolean lol = ma.find();
//        String g    = ma.group();
//        String g0 = ma.group(0);
//        String g1 = ma.group(1);
//        String g2 = ma.group(2);
//        String g3 = ma.group(3);
//        String g4 = ma.group(4);
//        String g5 = ma.group(5);
//
//        boolean xlol = ma.find();
//        String xg    = ma.group();
//        String xg0 = ma.group(0);
//        String xg1 = ma.group(1);
//        String xg2 = ma.group(2);
//        String xg3 = ma.group(3);
//        String xg4 = ma.group(4);
//        String xg5 = ma.group(5);
//        String xg6 = ma.group(6);
//        String xg7 = ma.group(7);
//        String xg8 = ma.group(8);

        ma = pa.matcher(serialMessage);
        while (ma.find()) {
            String lol = ma.group(3);
            if(lol != null && lol.equals("OK")) throw new RuntimeException("\n\n\n\n\n\n ERRROOOORRRRRRRRR ============== OK OK OK");
            tokens.add(lol);
        }

        //System.out.println(tokens);
        for (String s : tokens) {
            System.err.println("&&&& Utils: \n\n=============\n-> 0: " + s + "\n*************\n");
        }
//        final boolean rea = ma.find();
        return tokens;
    }

    public static List<SmsDto> detectWhenSmsDto(String reg, String serialMessage) {
        List<SmsDto> smsdtos = new ArrayList<>();
        Pattern pa = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);

        while (ma.find()) {
            DateFormat dateFormat = new SimpleDateFormat();
            Date da = null;
            try {
                da = dateFormat.parse(ma.group(6) +" "+ ma.group(7));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean add = smsdtos.add(new SmsDto(
                    ma.group(8),
                    ma.group(4),
                    da,
                    Integer.parseInt(ma.group(2))

            ));
        }
        return smsdtos;
    }

    public static ChipDto detectWhenChipDto(String reg, String serialMessage, String port) {
        ChipDto smsdtos = null;
        Pattern pa = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);

        boolean xlol = ma.find();
        if (xlol){
            String xg    = ma.group();
            String xg0 = ma.group(0);
            String xg1 = ma.group(1);
            String xg2 = ma.group(2);
            String xg3 = ma.group(3);
//            String xg4 = ma.group(4);
//            String xg5 = ma.group(5);
//        String xg6 = ma.group(6);
//        String xg7 = ma.group(7);
//        String xg8 = ma.group(8);
        }

        ma = pa.matcher(serialMessage);
        while (ma.find()) {

            smsdtos = new ChipDto(
                    ma.group(3), //operadora
                    NumberHandler.data.get(port)
//                    ma.group(4)  //numero

            );
        }
        return smsdtos;
    }

}
