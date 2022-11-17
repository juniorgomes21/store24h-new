package br.apc.smsdriver.delta.one.model.dao;

import br.apc.smsdriver.delta.one.eventhandler.MetaModemHandler;
import br.apc.smsdriver.delta.one.model.ModemModel;

public class ModemDAO {
    public static ModemModel configEntity(String porta){
        ModemModel model = new ModemModel(
                porta,
                MetaModemHandler.data.get(porta).get("Model"),
                MetaModemHandler.data.get(porta).get("Manufacturer"),
                Long.parseLong(MetaModemHandler.data.get(porta).get("IMEI")),
                MetaModemHandler.data.get(porta).get("Revision"),
                MetaModemHandler.data.get(porta).get("+GCAP")
        );
//        System.out.println(model);
        saveOrUpdate(model);
        return model;
    }

    private static void saveOrUpdate(ModemModel model) {

    }
}
