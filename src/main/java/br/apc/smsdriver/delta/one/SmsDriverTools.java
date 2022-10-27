/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one;

import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Archer
 */
public class SmsDriverTools {
    /**
     * Modem com suporte homologados
     */
    public static String devicePortName =
            "HUAWEI Mobile Connect - 3G PC UI Interface, HSPADataCard NMEA Device, ONDA HS-USB NMEA Device";

    public static List<String> getModemComs() {
        List<String> mcoms = new ArrayList<>();
        SerialPort[] portList = SerialPort.getCommPorts();
//        SerialPort.get
        System.out.println("====>>>>> Lista atualizada de portas");
        for (SerialPort serialPortx : portList) {
            String st_sp = serialPortx.getDescriptivePortName();
            System.out.println("====>>>>>" + st_sp);
            String[] pd = st_sp.split("\\s\\(COM\\d{1,3}\\)");
            //            System.out.println("|||||||||||>>>>>>>>" + pd[0]);
            if (SmsDriverTools.devicePortName.contains(pd[0])) {
                Pattern p = Pattern.compile("COM\\d{1,3}");
                Matcher m = p.matcher(st_sp);
                final boolean re = m.find();
                if (re == true) {
                    //                    System.out.println("com.apcodes.gsmcmd.GSMModemDetector.main()m.find:============>>>> " + re);
                    //                    System.out.println("====>>>>>" + m.group());
                    //                    strs.add(m.group());
                    //                    String pref = "pd[0] ";
                    String pref = "";
                    //jComboBox1_PORTA_COM.addItem(m.group());
                    //

                    mcoms.add(m.group());
//                    if(!m.group().contains("[]")) {
////                        System.out.println("====>>>>>" + m.group());
//                    }
                }
            }
        }
        return mcoms;
    }

}
