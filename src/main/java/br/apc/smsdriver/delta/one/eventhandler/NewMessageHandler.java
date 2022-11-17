/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one.eventhandler;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Archer
 */
public class NewMessageHandler implements SmsEventHandler{
    public static final Map<String, String> data = Collections.synchronizedMap(new HashMap<>());//p<String, new ArrayList<>>(49));
    private String serialMessage = "";
    static {}
    public String getSerialMessage() {
        return serialMessage;
    }

    @Override
    public boolean check(Map<String, GsmModemSistemaControlador> map, String serialMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
        String st_sp = serialMessage;
        String rega = "\\+CMGL: ";
        Pattern pa = Pattern.compile(rega);
        Matcher ma = pa.matcher(st_sp);
        final boolean rea = ma.find();

        if (rea == true) {
            String myal = NewMessageHandler.data.get(gsmModemSistemaControlador.getPortName());
            boolean conq = false;
            if (myal != null && myal != "") {
                conq = myal.contains(serialMessage);
                if (!conq) {
                    myal = NewMessageHandler.data.put(gsmModemSistemaControlador.getPortName(), myal.concat(serialMessage));
//                    this.serialMessage = serialMessage;
                }
            }

            if (myal == null){
                NewMessageHandler.data.put(gsmModemSistemaControlador.getPortName(), serialMessage);
            }

            System.err.println("\n++++++++++++++++++++++++ \n\n\n\nINICIO da MSG\n "
                    + NumberHandler.data.get(gsmModemSistemaControlador.getPortName())
                    + "\nUma Mensagem apareceu! \n\n"
                    + ma.group(0) +
                    "\n++++++++++++||||++++++++++++ FIM da MSG"
            );
        }

        return rea;
    }

//    @Override
//    public boolean check(GsmModemSistemaControlador gsmModemSistemaControlador, String serialMessage) {
//
//        String st_sp = serialMessage;//"+CNUM: \"\",\"+5593981175747\",145\n\n\n OK\n\n\n";
////            String reg1 = "(\\+CNUM: \"\",\"\\+)(\\d{11,13})";
////            +CNUM: ,"+5581982620465",145
////            String reg2 = "(\\+CNUM: ,\"\\+)(\\d{11,13})";
////            String reg = "\\+CMGL: ";
//        String rega = "\\+CMGL: ";
////            String regb = "\\d{11,13}";
////            Pattern p = Pattern.compile(reg);
//        Pattern pa = Pattern.compile(rega);
////            Pattern pb = Pattern.compile(regb);
////            Pattern p1 = Pattern.compile(reg1);
////            Pattern p2 = Pattern.compile(reg2);
//
////            Matcher m = p.matcher(st_sp);
//        Matcher ma = pa.matcher(st_sp);
////            Matcher mb = pb.matcher(st_sp);
//
////            Matcher m1 = p1.matcher(st_sp);
////            Matcher m2 = p2.matcher(st_sp);
////            final boolean re = m.find();
//        final boolean rea = ma.find();
////            final boolean reb = mb.find();
//
////            final boolean re1 = m1.find();
////            final boolean re2 = m2.find();
////            boolean re = false;
//
//        if (rea == true) {
//            this.serialMessage = serialMessage;
//            System.err.println("Uma Mensagem apareceu!>>>>>> \n\n" + ma.group(0) +"\\n++++++++++++++++++++++++ FIM da MSG");
////                NewMsgJFrame jft = new NewMsgJFrame();
////                jft.setText(serialMessage);
////                jft.setVisible(true);
//                
////                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m.group(2)));
////                numbers.add(m.group(2));
//            //re = re1;
//        }
////            if (re1 == true) {
////                System.err.println("Um numero de celular apareceu! \n\n" + m1.group(2));
////                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m1.group(2)));
////                numbers.add(m2.group(2));
////                re = re1;
////            }
////            if (re2 == true) {
////                System.err.println("Um numero de celular apareceu! \n\n" + m2.group(2));
////                gsmModemSistemaControlador.getModemModel().setChip(new ChipModel(m2.group(2)));
////                numbers.add(m1.group(2));
////                re = re2;
////            }           
//        return rea;
//    }    

}