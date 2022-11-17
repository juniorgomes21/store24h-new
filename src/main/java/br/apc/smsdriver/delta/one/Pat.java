package br.apc.smsdriver.delta.one;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pat {

    public static void main(String[] args) {

        testATIExtractor();
//        operadora();
    }

    private static void operadora() {
//        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        String st_sp =  "\n+COPS: 0,0,\"VIVO\",2\n\nOK\n";
        final String STRCOPS = "\\+COPS";
//        "\\+CNUM: ";/
        Pattern pa = Pattern.compile(STRCOPS, Pattern.MULTILINE);
        Matcher ma = pa.matcher(st_sp);
        final boolean rea = ma.find();
//        AT+COPS?
//                +COPS: 0,0,"VIVO",2
        Set<String> tokens = new HashSet<>();
        while (ma.find()) {
            tokens.add(ma.group());
        }
        tokens.size();

    }

    /**
     *
     * Testa a captura de ruidos
     */
    private static void testRuido() {
        final String PATTERN_GARBAGE_MODEM ="((\\+ZMTime)|(\\+ZUSIMR)|(\\+ZEND:)|(\\+ZPASR:)|(\\+ZDONR:)){1,}";
        final String PATTERN_NETWORK_ARRIVE_MODEM = "\\+CMTI:";
        String serialMessage = "\n\n+ZUSIMR:2\n\n oi\\+ZEND: outra \n\n+ZMTime: coisa\n\n+ZUSIMR:3";
        if (detectWhen(PATTERN_GARBAGE_MODEM, serialMessage) == false) {
            if (detectWhen(PATTERN_NETWORK_ARRIVE_MODEM, serialMessage) == true ) {
                //                Store24hApplication.askMessage(gsmModemSistemaControlador);
                //                return tru
                System.out.println("***************************\n\n\n" + serialMessage);
            }
            System.err.println("***************************\n\n\n" + serialMessage);
            //            for (SmsEventHandler eventHandler : eventHandlersAll) {
            //                eventHandler.check(gsmModemSistemaControlador, serialMessage);
            //            }
        }
    }

    /**
     *
     * O comando ATI trás varias informações e redus o ruido.
     */
    private static void testATIExtractor() {
        String serialMessage =
            "OK\r\nAT+CMGF=1\r\nOK\r\nOK\r\nManufacturer: ONDA COMMUNICATION\r\nModel: MSA110UP\r\nRevision: MSA110UP.TIMBR.FW.B01\r\nIMEI: 864446003219904\r\n+GCAP: +CGSM,+DS,+ES";
        final String ATI = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";

        Pattern pa = Pattern.compile(ATI, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);
        Set<String> tokens = new HashSet<>();
        while (ma.find()) {
            tokens.add(ma.group(0));
        }
        System.out.println(tokens);
        List<String[]> lol = tokens.stream().map(s -> s.split(": ")).collect(Collectors.toList());
        for(String[]tu: lol){
            System.out.printf("====(%s, %s)====\n", tu[0], tu[1]);
        }
        Map<String, List<String>> result = tokens.stream().collect(
                Collectors.groupingBy(strElem -> strElem.split(": ")[0])
            );
        System.out.println(result);

        Optional<String> resultn = tokens.stream()
//                .max(new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return o1.split(": ")[0].compareTo(o2.split(": ")[1]);
//                    }
//                });
                .max(Comparator.comparing(e -> e.split(": ")[0]));
                //.
//                .collect(Collectors
//                    .groupingBy(strElem -> String.valueOf(strElem.split(": ")[0].length()))
//        );
        System.out.println(resultn);

        HashMap<String, String> lolx =

                tokens.stream().collect(
                // HashMap::new
                () -> new HashMap<>(), //m
                (m, e) -> m.put(e.split(": ")[0], e.split(": ")[1])
                , (m, l2) -> {}

        );
        System.out.println(lolx);




//        ArrayList<String> lolz = tokens.stream().collect(
//                Collectors.groupingBy(
//                    () -> new ArrayList<>(),
//                    (l, e) -> l.add(e.split(": ")[1])
//                )
//                //, (l1, l2) -> l1.add(String.valueOf(l2))
//                //Collectors.groupingBy(strElem -> strElem.split(": ")[0])
//        );
//        System.out.println(lolz);

        //System.out.println(lol.get(lol.size()-1));

    }

    private static boolean detectWhen(String reg, String serialMessage) {
        Pattern pa = Pattern.compile(reg, Pattern.MULTILINE);
        Matcher ma = pa.matcher(serialMessage);
//        final boolean rea = ma.find();
        Set<String> mKeys = new HashSet<>();

        while (ma.find()) {
            mKeys.add(ma.group());
        }
        //System.out.println(mKeys);
        for (String s :
                mKeys) {
            System.err.println("\n\n=============\n0: " + s + "\n*************\n");
        }
//~~~~~~~~
//        System.err.println("\n\n=============\n0: " + ma.group() + "\n*************\n");
        ma.find();
//        System.out.println("\n\n=============\n1: " + ma.group(1) + "\n*************\n");
//        System.out.println("\n\n=============\n2: " + ma.group(2) + "\n*************\n");
//        System.out.println("\n\n=============\n3: " + ma.group(3) + "\n*************\n");
        return mKeys.size() > 0 ;
    }





}
