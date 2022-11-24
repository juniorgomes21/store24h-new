/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.apc.smsdriver.delta.one;

import br.apc.smsdriver.delta.one.eventhandler.AllEventHandler;
import br.apc.smsdriver.delta.one.eventhandler.SmsEventHandler;
import br.apc.smsdriver.delta.one.model.ModemModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.apc.smsdriver.delta.one.model.SmsModel;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Archer
 */
public class GsmModemSistemaControlador extends SerialPort implements SerialPortEventListener {

    @SuppressWarnings("unused")
    private static final String COMMAND_AT = "AT";
    private static final String COMMAND_ATI = "ATI";

    private static final String COMMAND_ENVOIE_SMS = "AT+CMGS=";

    private static final String COMMAND_CHIP_NUMBER = "AT+CNUM";

    private static final String COMMAND_GET_ALL_SMS = "AT+CMGL=\"ALL\"";
    private static final String COMMAND_GET_NEW_SMS = "AT+CMGL=\"REC UNREAD\"";

    private static final String COMMAND_SEND_MESSAGES = "AT+CMGD=0[,1]";

    private static final String COMMAND_REMISE_A_ZERO = "ATZ";
    private static final String COMMAND_SMS_MODE_TEXT = "AT+CMGF=1";

    private static final String COMMAND_SET_UP_MEMORIES = "AT+CPMS=\"MT\",\"MT\",\"MT\"";

    private static final String COMMAND_SET_DETAILED_ERRORS = "AT+CMEE=1";

    private static final String COMMAND_DELETE_ALL_MESSAGES = "AT+CMGD=0[,4]";
    private static final String COMMAND_DELETE_READ_MESSAGES = "AT+CMGD=0[,1]";

    private static final String COMMAND_QUASTION_SET_UP_MEMORIES = "AT+CPMS?";

    private static final String COMMAND_LIST_SUPPORTED_STORAGE_MODES = "AT+CPMS=?";
    private static final String COMMAND_QUASTION_SMS_MODE_TEXT_CHECK = "AT+CMGF=?";

    private AllEventHandler allEventHandler = new AllEventHandler();

    public AllEventHandler getAllEventHandler() {
        return allEventHandler;
    }


    private String lastResponse = "";
    private String port = "";
    List<SmsEventHandler> handlers = new ArrayList<>();
    private ModemModel modemModel;
    private int eventcount = 1;

//    public void setjFrame(NewMessageHandlerJFrame jFrame) {
//        this.jFrame = jFrame;
//    }
//
//    public NewMessageHandlerJFrame getjFrame() {
//        return jFrame;
//    }

    public GsmModemSistemaControlador(String porta) {
        super(porta);
        port = porta;
        modemModel = new ModemModel(porta);
//        allEventHandler.getEventHandlersAll().get(porta);
        try {

            if (!isOpened()) {
                System.out.println("Porta não está aberta, cheque o numero " );
                boolean openPort = this.openPort();
            }
            //setParams já comando direto no modem, especificamente na porta serial do modem e não pode ser feito antes de abrir
            setParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

//            System.out.println("Modem ");
            startGsm();
//            initHandlers();
            this.addEventListener(this);
            //this.startGsm();
        } catch (SerialPortException ex) {
            Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModemModel getModemModel() {
        return modemModel;
    }

//    private void initHandlers(){
//        handlers.add(new NumberHandler());
//        handlers.add(new OperadoraHandler());
//        handlers.add(new NumberHandler());
//        handlers.add(new NumberHandler());
//        
//    }

    public final synchronized void startGsm() throws SerialPortException {
        doAT();
        this.writeString(GsmModemSistemaControlador.COMMAND_SMS_MODE_TEXT + "\r\n");
//        this.writeString(GsmModemSistemaControlador.COMMAND_REMISE_A_ZERO + "\r\n");
//        this.writeString(GsmModemSistemaControlador.COMMAND_SET_DETAILED_ERRORS + "\r\n");
//        this.writeString(GsmModemSistemaControlador.COMMAND_QUASTION_SET_UP_MEMORIES + "\r\n");        
////        this.writeString(COMMAND_ENVOIE_SMS+"\"91998317849\"\r Eu eim Please call me soon ."+ "\r\n"+"\u001A");
////        this.writeString(GsmModemSistemaControlador.COMMAND_GET_ALL_SMS + "\r\n");
//        this.writeString(GsmModemSistemaControlador.COMMAND_CHIP_NUMBER + "\r\n");
//        getChipNumber();
//        readAllMessages();

//            SmsModel sms = new SmsModel();
//            sms.setConteudo(smsConteudo);
//            sms.setEmissor("5591998317849");
//            sms.setData(LocalDate.now());
//            sms.setHora(LocalTime.now());
//            sms.setId(1);
//        this.writeString(new SmsModel("number", COMMAND_AT));
//        doAT();
//        doATI();
//        getChipNumber();
//        checkStatus();
//        sendMessage(null);
    }
    public synchronized void doAT(){
        try {
            this.writeString(GsmModemSistemaControlador.COMMAND_AT + "\r\n");
        } catch (SerialPortException ex) {
            Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void doATI(){
        try {
            this.writeString(GsmModemSistemaControlador.COMMAND_ATI + "\r\n");
        } catch (SerialPortException ex) {
            Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void checkStatus() {
        try {
//            if (this.isOpened())
            this.writeString(GsmModemSistemaControlador.COMMAND_AT + "\r\n");
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    /**
     *
     * Retorna uma string onde há o numero do chip, não está tratada ainda
     */
    public synchronized void getChipNumber() {
        try {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.isOpened())
                this.writeString(GsmModemSistemaControlador.COMMAND_CHIP_NUMBER + "\r\n");
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void sendMessage(SmsModel sms) {
        try {
            if (this.isOpened()) {
//                String str1 = GsmModemSistemaControlador.COMMAND_ENVOIE_SMS 
//                        + "\""
//                        + sms.getDestinatario()
//                        + "\""
//                        + "\r";
//                String num = "5591998317849";
//                String s = GsmModemSistemaControlador.COMMAND_ENVOIE_SMS + 
//                        "\"+" + sms.getDestinatario()+ "\"\r" 
//                        + sms.getConteudo() + 
//                        " You Did Fox!! Do Java no PC.\u001A";
                String z = "\"+5593981175747\"\rPlease call me soon.\u001A";

                //z = "\"91998317849\"\rPlease call me soon .\u001A";
                String strToSend = GsmModemSistemaControlador.COMMAND_ENVOIE_SMS+z;

                boolean res = this.writeString(
                        strToSend
                );
                if (res) {
                    System.out.print("\n\n Enviou!!!!!!!!! \n>>>>>>>\n" + strToSend + "\n\n");
                }
//                System.out.println("str1: \n" + str1);
            }
//            fecharConexao();
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void readAllMessages() {
        try {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean msg;
            System.out.println("Lendo");
//                if (this.isOpened()){
//                    msg = this.writeString(GsmModemSistemaControlador.COMMAND_SMS_MODE_TEXT);
//                    System.out.println("Lido COMMAND_SMS_MODE_TEXT " + msg);
//                    msg = this.writeString(GsmModemSistemaControlador.COMMAND_SMS_MODE_TEXT_CHECK + "\r\n");
//                    System.out.println("Lido COMMAND_SMS_MODE_TEXT_CHECK " + msg);
            msg = this.writeString(GsmModemSistemaControlador.COMMAND_GET_ALL_SMS + "\r\n");
//                    System.out.println("Lido COMMAND_GET_ALL_SMS " + msg);
//                    SerialPortEvent event = new SerialPortEvent(this.getPortName(), DATABITS_8, PARITY_NONE);
//                    event.notify();
//                    event.isRING();
//                    serialEvent(event);
//                    fecharConexao();
            System.out.println("Lido e Fechada");
//                } else {
//                    this.openPort();
//                    this.readAllMessages();
//                    System.out.println("Tentando ler se a Porta estiver fechada");
//                }

        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void readUnreadMessages() {
        try {
            if (this.isOpened())
                this.writeString(GsmModemSistemaControlador.COMMAND_GET_NEW_SMS + "\r\n");
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void deleteAllMessages() {
        try {
            if (this.isOpened())
                this.writeString(GsmModemSistemaControlador.COMMAND_DELETE_ALL_MESSAGES
                        + "\r\n");
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void deleteReadMessages() {
        try {
            if (this.isOpened())
                this.writeString(GsmModemSistemaControlador.COMMAND_DELETE_READ_MESSAGES
                        + "\r\n");
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }

    public synchronized void fecharConexao() {
        try {
            this.closePort();
        } catch (SerialPortException exp) {
            exp.printStackTrace();
        }
    }



    @Override
    public void serialEvent(SerialPortEvent spe) {

        try {
            this.lastResponse = this.readString();
//            System.out.println("It is an sms. Resp: " + this.lastResponse);
            if (this.lastResponse != null) {
//                System.out.println("U Mensagem Nova: " + this.lastResponse + "!= de null\n\n\n");  
//                this.readAllMessages();
//                ver();
//                AllEventHandler.class.
                allEventHandler.check(this, this.lastResponse);

            } else {
                System.out.println("\nZ Nada para ler, apesar de ter ocorrido o SerialPortEvent!!!!");
            }
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }







//    
//    private void lerSmsEvent(){
//        // =========================================== 2
//        if (this.lastResponse.contains("+CMGL") && this.lastResponse.contains("REC")){
//            System.out.println("T SMS Foi Lido em eventos, disparar tratamentos adicionais. Inici \n<<<<<<<<<" + this.lastResponse + "fimmmm\n>>>>>>>>>>>>>>>>>>>");
//        //                    readAllMessages();
//        }
//    }
//    
//    private void lerSmsNovo(){
//        // =========================================== 1
//        // if the response contains sms
//        if (this.lastResponse.contains("+CMTI")){
//            System.out.println("Q Mensagem Nova +CMTI: " + this.lastResponse);
//            try {   
////              readAllMessages();
//                startGsm();
//            } catch (SerialPortException ex) {
//                Logger.getLogger(GsmModemSistemaControlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    
//    }
//    
//    private void ver(){
//        eventcount++;
//        if (eventcount%2 == 0) {
//            System.out.println("W Commando AT: " + this.lastResponse);
////                    System.out.println(this.lastResponse);   
//            System.out.println("W Comando nº "+ eventcount);
//        } else if (eventcount%2 != 0) {
//            System.out.println("\n========inicio" +
//                     this.lastResponse + 
//                    "\n=========fim"); 
//            System.out.println("\nX Resposta do Comando: " + this.lastResponse); 
//            System.out.println("\nX Comando nº "+ eventcount+ "\n\n");
//        }
//    }


}
