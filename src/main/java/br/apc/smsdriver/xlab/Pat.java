package br.apc.smsdriver.xlab;

import br.apc.smsdriver.delta.one.eventhandler.Utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.apc.smsdriver.xlab.PatStrings.*;

public class Pat {

    public static void main(String[] args) {

//        testATIExtractor();
        operadora();
//        testRuido();
//        all();
    }

    public static void all(){
        final String REG =  "[\\r\\n]\\+COPS: 0,0,\"([A-Z]+)\",2[\\n\\n]+OK[\\r\\n]";
        Pattern.compile(REG, Pattern.MULTILINE);

//        List<String> lis = Utils.detectWhen(REG, st);
//        final String REG2 =  "[\\r\\n]\\+COPS: [\\d,]+\"([\\d]{3,})\"[,\\d]+[\\n\\n]+OK[\\r\\n]";
//        Pattern.compile(REG2, Pattern.MULTILINE);
//        // Combinação das duas formas de sucesso
//        final String REGFINAL =  "[\\r\\n]\\+COPS: [\\d,]+\"([\\d]{3,}|[A-Z]+)\"[,\\d]+[\\n\\n]+OK[\\r\\n]";
//        Pattern.compile(REGFINAL, Pattern.MULTILINE);
//        List<String> lis2 = Utils.detectWhen(REGFINAL, TIM);
        final String REG3 =  "[\\r\\n](\\+CMGL: ([\\d]+),\"REC (READ|UNREAD)\",\"[\\+]*([\\d\\w]+)\",,\"((\\d\\d/\\d\\d/\\d\\d),(\\d\\d:\\d\\d):\\d\\d-\\d\\d)\"[\\r\\n]+([\\+\\.]*.*))";
        Pattern.compile(REG3, Pattern.MULTILINE);
        List<String> lis3 = Utils.detectWhen(REG3, MSGBIG);
        System.out.println(lis3);
    }

    private static void operadora() {
//        final String STRMANUF = "((Manufacturer: .*)|(Revision: .*)|(Model: .*)|(IMEI: (\\d*))|(\\+GCAP: .*))+";
        String st_errada = "AT\n" +
                "\n" +
                "OK\n" +
                "AT+CMGF=1\n" +
                "\n" +
                "OK\n";

        String st_sp =  "\\n+COPS: 0,0,\"VIVO\",2\\n\\nOK\\n";
        String st = "\n" +
                "+COPS: 0,0,\"VIVO\",2\n" +
                "\n" +
                "OK\n";
        String num = "\n" +
                "+COPS: 0,2,\"72402\",2\n" +
                "\n" +
                "OK\n";
//        String lol = """
//                +COPS: 0,0,"VIVO",2
//
//                OK
//                """;
        final String STRCOPSX = "((\\+COPS: \\d,\\d,\")([\\d]+|[A-Z]+))";
//        final String STRCOPSX = "((\\+COPS: [\\d,\"]+)([\\d]{3,}|[A-Z]+))";
        Pattern.compile(STRCOPSX, Pattern.MULTILINE);
        final String STRCOPS = "((\\+COPS: [\\d,][\\d,][\"])|([A-Z]+))";
        Pattern.compile(STRCOPS, Pattern.MULTILINE);
        final String STRCOPS2 = "((\\+COPS: \\d,\\d,\")|(\\d){3,})";
        Pattern.compile(STRCOPS2, Pattern.MULTILINE);
        Collection<String> tokens = Utils.detectWhen(STRCOPSX, st_sp);
        tokens.addAll(Utils.detectWhen(STRCOPSX, num));

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
        String st_sp =  "\\n+COPS: 0,0,\"VIVO\",2\\n\\nOK\\n";
        if (!(Utils.detectWhen(PATTERN_GARBAGE_MODEM, serialMessage).size() > 0)) {
            if ((Utils.detectWhen(PATTERN_NETWORK_ARRIVE_MODEM, serialMessage).size() > 0)) {
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
        Pattern.compile(ATI, Pattern.MULTILINE);
        List<String> tokens = Utils.detectWhen(ATI, serialMessage);
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

}
