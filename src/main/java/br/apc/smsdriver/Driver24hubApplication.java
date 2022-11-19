package br.apc.smsdriver;

import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import br.apc.smsdriver.delta.one.SmsDriverTools;
import br.apc.smsdriver.delta.one.eventhandler.AllEventHandler;
import br.apc.smsdriver.delta.one.eventhandler.MetaModemHandler;
import br.apc.smsdriver.delta.one.eventhandler.NewMessageHandler;
import br.apc.smsdriver.delta.one.eventhandler.NumberHandler;
import jssc.SerialPortException;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.HashMap;

@EnableCaching
@SpringBootApplication
@EnableSpringDataWebSupport
public class Driver24hubApplication {

	public static synchronized void main(String[] args) {
		SpringApplication.run(Driver24hubApplication.class, args);
//		ListarPortas.list(args);
////        m1("COM12");
////        m2("COM203");
////		SmsDriverTools.getModemComsAndNames().forEach((modemCom, modemName) -> {
////			System.err.printf("%s: %s\n", modemCom, modemName);
////			numberOfPorts[1]++;
////		});
//
		System.err.printf("%d: \n",1);
		initConnections();
//		askNumbers();
//		askManufactures();
		askOperadoras();
//		askMessages();
		System.err.printf("%s", "".length() == 0);
		System.err.printf("\n%d: \n-=-=-=-=-=-=-=-=-=-=-\nALL SYSTEMS GO! LET´S GO FOX!\n-=-=-=-=-=-=-=-=-=-=-\n",2);
//		while (true){
//			for (GsmModemSistemaControlador gsmModemSistemaControlador :
//					AllEventHandler.drivers.values()) {
//				gsmModemSistemaControlador.readUnreadMessages();
//			}
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 *
	 * Inicia as conexões e prepara estruturas, 1s
	 */
	private static synchronized void initConnections() {
		final int[] numberOfPorts = {0};
		for (String modemCom : SmsDriverTools.getModemComs()) {
			numberOfPorts[0]++;
			try {
				Thread.sleep(20*numberOfPorts[0]);
				AllEventHandler.drivers.put(modemCom, new GsmModemSistemaControlador(modemCom));
				MetaModemHandler.data.put(modemCom, new HashMap<>());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * Fecha todas as connexões que forem possíves, 1s
	 */
	public static synchronized void endAllConnections() {
		for (GsmModemSistemaControlador modem: AllEventHandler.drivers.values()) {
			final int[] numberOfPorts = {0};
			try {
				modem.closePort();
				Thread.sleep(20*numberOfPorts[0]);
			} catch (SerialPortException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * Deve atualizar as conexões e prepara estruturas novamente. Não precisa chamar endAllConnections(), 2s
	 */
	public static synchronized void reConnctions() {
		final int[] numberOfPorts = {0};
		try {
			endAllConnections(); //1s
			Thread.sleep(20*numberOfPorts[0]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clearAllData();
		initConnections(); //1s
	}

	public static synchronized void clearAllData(){
		// TODO torna generico em um laço
		NumberHandler.data.clear();
		NewMessageHandler.data.clear();
	}

	/**
	 *
	 * solicita aos modems os números nos chips. Pode levar até 2,5s para que todos respondam
	 */
	private static synchronized void askNumbers() {
		final int[] numberOfPorts = {0};
		for (GsmModemSistemaControlador gsmModemSistemaControlador: AllEventHandler.drivers.values()) {
//			System.err.printf("%s\n", gsmModemSistemaControlador.getPortName());
			numberOfPorts[0]++;
			try {
				gsmModemSistemaControlador.getChipNumber(); //cmd
				Thread.sleep(50*numberOfPorts[0]);
				System.err.printf("\n\nNumeros Atualizados em:\n%s\nNa porta %s\n"
						,NumberHandler.data.get(gsmModemSistemaControlador.getPortName())
						,gsmModemSistemaControlador.getPortName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * Pede informação sobre as operadoras
	 */
	private static synchronized void askOperadoras() {
		final int[] numberOfPorts = {0};
		for (GsmModemSistemaControlador gsmModemSistemaControlador: AllEventHandler.drivers.values()) {
//			System.err.printf("%s\n", gsmModemSistemaControlador.getPortName());
			numberOfPorts[0]++;
			try {
				gsmModemSistemaControlador.askOperadora(); //cmd
				Thread.sleep(50*numberOfPorts[0]);
				System.err.printf("\n\nNome das Operadoras Atualizados em:\n%s\nNa porta %s\n"
						,NumberHandler.data.get(gsmModemSistemaControlador.getPortName())
						,gsmModemSistemaControlador.getPortName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * solicita aos modems os números nos chips. Pode levar até 2,5s para que todos respondam
	 */
	private static synchronized void askManufactures() {
		final int[] numberOfPorts = {0};

		for (GsmModemSistemaControlador gsmModemSistemaControlador: AllEventHandler.drivers.values()) {
//			System.err.printf("%s\n", gsmModemSistemaControlador.getPortName());
			numberOfPorts[0]++;
			try {
				gsmModemSistemaControlador.doATI(); //cmd
				Thread.sleep(50*numberOfPorts[0]);
				System.err.printf("\n\nInfos Manufacture Atualizadas em:\n%s\nNa porta %s\n"
						,MetaModemHandler.data.get(gsmModemSistemaControlador.getPortName())
						,gsmModemSistemaControlador.getPortName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void reAskNumbers(){
		reConnctions();
		askNumbers();
	}

	/**
	 *
	 * Solicita todas as mensagens em todos os modem.
	 */
	public static synchronized void askMessages(){
		final int[] numberOfPorts = {0};
		for (GsmModemSistemaControlador gsmModemSistemaControlador: AllEventHandler.drivers.values()) {
			numberOfPorts[0]++;
			try {
				gsmModemSistemaControlador.readAllMessages();; //cmd
				Thread.sleep(300*numberOfPorts[0]);
				System.err.printf("\n\nMensagens Atualizadas de:\n%s\nNa porta %s\n"
						, NumberHandler.data.get(gsmModemSistemaControlador.getPortName())
						,gsmModemSistemaControlador.getPortName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * Solicita todas as mensagens em um modem.
	 * @param gsmModemSistemaControlador
	 */
	public static synchronized void askMessage(@NotNull GsmModemSistemaControlador gsmModemSistemaControlador){
		try {
			gsmModemSistemaControlador.readAllMessages();; //cmd
			Thread.sleep(1);
			System.err.printf("\n\nNumeros Atualizados de:\n%s\n", gsmModemSistemaControlador.getPortName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * Inicializa as estruturas de dados
	 * @param port
	 */
//	private static void mAllNumbers(String port) {
////                String port = "COM12";
////        1 - Cria objeto GsmModemSistemaControlador
//		GsmModemSistemaControlador gsmm = new GsmModemSistemaControlador(port);
//		System.err.println("###########################################");
//		//        2 - Chama o metodo que dispara o Handle a ser testado;
//
//		gsmm.getChipNumber();
//	}

	/**
	 *
	 * Inicializa as estruturas de dados
	 * @param port
	 */
	public static void mAllMsg(String port) {
//                String port = "COM12";
//        1 - Cria objeto GsmModemSistemaControlador
		GsmModemSistemaControlador gsmm = new GsmModemSistemaControlador(port);
		NewMessageHandler.data.put(port, "");
//        NewMessageHandlerJFrame frame = gsmm.getjFrame();
//        frame.setVisible(true);
        gsmm.readAllMessages();

//        3 - Pega allEventHandler e checa pelo de interesse e seta um observer
//        AllEventHandler aeh = gsmm.getAllEventHandler();
		System.err.println("###########################################");
		//        2 - Chama o metodo que dispara o Handle a ser testado;

//		gsmm.getChipNumber();

//        aeh.getSerialMessage();
//        NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!");
//        NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!", gsmm);
//        jfm.setVisible(true);
// frame = gsmm.getjFrame();;
//        frame.setVisible(true);

//        gsmm.readAllMessages();
	}

	public static void m1(String port) {
//                String port = "COM12";
//        1 - Cria objeto GsmModemSistemaControlador
		GsmModemSistemaControlador gsmm = new GsmModemSistemaControlador(port);
		NewMessageHandler.data.put(port, "");
//        NewMessageHandlerJFrame frame = gsmm.getjFrame();
//        frame.setVisible(true);
		gsmm.readAllMessages();

//        3 - Pega allEventHandler e checa pelo de interesse e seta um observer
//        AllEventHandler aeh = gsmm.getAllEventHandler();

		System.err.println("###########################################");
		//        2 - Chama o metodo que dispara o Handle a ser testado;

		gsmm.getChipNumber();

//        aeh.getSerialMessage();
//        NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!");
//		NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!", gsmm);
//		jfm.setVisible(true);
// frame = gsmm.getjFrame();;
//        frame.setVisible(true);

		gsmm.readAllMessages();
	}

	public static void m2(String port) {
//                String port = "COM203";
//        1 - Cria objeto GsmModemSistemaControlador
		GsmModemSistemaControlador gsmm = new GsmModemSistemaControlador(port);
		NewMessageHandler.data.put(port, "");
//        NewMessageHandlerJFrame frame = gsmm.getjFrame();
//        frame.setVisible(true);
		gsmm.readAllMessages();

//        3 - Pega allEventHandler e checa pelo de interesse e seta um observer
//        AllEventHandler aeh = gsmm.getAllEventHandler();

		System.err.println("###########################################");
		//        2 - Chama o metodo que dispara o Handle a ser testado;

		gsmm.getChipNumber();

//        aeh.getSerialMessage();
//        NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!");
//		NewMessageHandlerJFrame jfm = new NewMessageHandlerJFrame("Vamos lá!", gsmm);
//		jfm.setVisible(true);
// frame = gsmm.getjFrame();;
//        frame.setVisible(true);

		gsmm.readAllMessages();
	}


}
