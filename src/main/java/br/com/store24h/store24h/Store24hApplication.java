package br.com.store24h.store24h;

import br.apc.smsdriver.DashPortas;
import br.apc.smsdriver.delta.ListarPortas;
import br.apc.smsdriver.delta.one.GsmModemSistemaControlador;
import br.apc.smsdriver.delta.one.SmsDriverTools;
import br.apc.smsdriver.delta.one.eventhandler.NewMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.util.ArrayList;

@EnableCaching
@SpringBootApplication
@EnableSpringDataWebSupport
public class Store24hApplication {

	public static void main(String[] args) {
//		DashPortas lol = new DashPortas();
//		lol.setVisible(true);
		SpringApplication.run(Store24hApplication.class, args);
		ListarPortas.list(args);
		//        m1("COM12");
//        m2("COM203");
		SmsDriverTools.getModemComs().forEach((modemCom) -> {
			mAllMsg(modemCom);
		});
	}

	public static void mAllMsg(String port) {
//                String port = "COM12";
//        1 - Cria objeto GsmModemSistemaControlador
		GsmModemSistemaControlador gsmm = new GsmModemSistemaControlador(port);
		NewMessageHandler.msgs.put(port, new ArrayList<>());
//        NewMessageHandlerJFrame frame = gsmm.getjFrame();
//        frame.setVisible(true);
//        gsmm.readAllMessages();

//        3 - Pega allEventHandler e checa pelo de interesse e seta um observer
//        AllEventHandler aeh = gsmm.getAllEventHandler();
		System.err.println("###########################################");
		//        2 - Chama o metodo que dispara o Handle a ser testado;

		gsmm.getChipNumber();

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
		NewMessageHandler.msgs.put(port, new ArrayList<>());
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
		NewMessageHandler.msgs.put(port, new ArrayList<>());
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
