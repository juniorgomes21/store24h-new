/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Archer
 */
public class NumberHandler implements SmsEventHandler{
//    private Map<String, GsmModemSistemaControlador> map;
//    private String serialMessagei;

    //    public NumberHandler() {
//
//    }
    public static final List<String> numbers = Collections.synchronizedList(new ArrayList<>(49));
    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {

        findNumber(map, serialMessage);
        return false;
    }

    private void findNumber(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        String st_sp = serialMessage;//"+CNUM: \"\",\"+5593981175747\",145\n\n\n OK\n\n\n";
        String reg1 = "(\\+CNUM: \"\",\"\\+)(\\d{11,13})";
//            +CNUM: ,"+5581982620465",145
        String reg2 = "(\\+CNUM: ,\"\\+)(\\d{11,13})";
        String rega = "\\+CNUM: ";
        String regb = "\\d{11,13}";
        Pattern pa = Pattern.compile(rega);
        Pattern pb = Pattern.compile(regb);
        Matcher ma = pa.matcher(st_sp);
        Matcher mb = pb.matcher(st_sp);

        final boolean rea = ma.find();
        final boolean reb = mb.find();

//            final boolean re1 = m1.find();
//            final boolean re2 = m2.find();
//            boolean re = false;


        if (rea == true && reb ==true) {
            System.err.println("Um numero de celular apareceu! \n\n" + mb.group(0));
            this.numbers.add(mb.group());
//                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m.group(2)));
//                numbers.add(m.group(2));
            //re = re1;
        }
//            if (re1 == true) {
//                System.err.println("Um numero de celular apareceu! \n\n" + m1.group(2));
//                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m1.group(2)));
//                numbers.add(m2.group(2));
//                re = re1;
//            }
//            if (re2 == true) {
//                System.err.println("Um numero de celular apareceu! \n\n" + m2.group(2));
//                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m2.group(2)));
//                numbers.add(m1.group(2));
//                re = re2;
//            }

        return rea;

    }

}
