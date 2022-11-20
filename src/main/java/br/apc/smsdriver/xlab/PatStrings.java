package br.apc.smsdriver.xlab;

public class PatStrings {
    public static String TIM = "\n" +
            "+COPS: 0,2,\"72402\",2\n" +
            "\n" +
            "OK\n";
    public static String st = "AT+COPS?\n" +
            "\n" +
            "+COPS: 0,0,\"VIVO\",2\n" +
            "\n" +
            "OK\n" +
            "AT+CMGL=\"ALL\"\n" +
            "\n" +
            "+CMGL: 0,\"REC READ\",\"+5591998317849\",,\"22/11/19,13:36:03-12\"\n" +
            "Esse vai\n" +
            "+CMGL: 1,\"REC READ\",\"+5591998317849\",,\"22/11/19,14:18:33-12\"\n" +
            "Gggg\n" +
            "\n" +
            "OK\n";
    public static String FAIL = "\"" +
            "\r\n+CMGL: 35,\"REC READ\",\"TIM\",,\"22/10/23,00:20:33-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga" +
            "\r\n+CMGL: 40,\"REC UNREAD\",\"BETAInfo\",,\"22/11/19,07:24:11-12\"\r\nHey Beta, acabou sua internet? Nao fique sem navegar! Faca uma recarga e volte a aproveitar seu plano ao maximo! Acesse meuplano.tim.com.br e recarregue!" +
            "\r\n+CMGL: 41,\"REC READ\",\"TIM\",,\"22/11/03,08:36:45-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga" +
            "\r\n+CMGL: 44,\"REC UNREAD\",\"TIM\",,\"22/11/19,08:26:36-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga";

    public static String MSGBIGUINHO = "\"" +
            "\r\n+CMGL: 0,\"REC READ\",\"29468\",,\"22/10/16,11:38:51-12\"\r\n<#>  035 914 is your Instagram code. Don't share it. SIYRxKrru1t" +
            "\r\n+CMGL: 2,\"REC READ\",\"28060\",,\"22/10/16,11:38:54-12\"\r\nTelegram code 45141" +
            "\r\n+CMGL: 3,\"REC READ\",\"29091\",,\"22/10/16,11:38:59-12\"\r\nG-518651  seu c odigo de verifica  o do Google." +
            "\r\n+CMGL: 1,\"REC READ\",\"27592\",,\"22/10/16,11:43:25-12\"\r\n0053006500750020006300F3006400690067006F00200064006500200063006F006E006600690072006D006100E700E3006F002000E9003A002000330038003500370035003700200046006100E700610020006100200076006500720069006600690063006100E700E3006F0020006E006F0020005300680065006C006C00200042006F0078002E";// + ""
    public static String MSGBIG = "\"" +
            "\r\n+CMGL: 0,\"REC READ\",\"29468\",,\"22/10/16,11:38:51-12\"\r\n<#> 035 914 is your Instagram code. Don't share it. SIYRxKrru1t" +
            "\r\n+CMGL: 1,\"REC READ\",\"27592\",,\"22/10/16,11:43:25-12\"\r\n0053006500750020006300F3006400690067006F00200064006500200063006F006E006600690072006D006100E700E3006F002000E9003A002000330038003500370035003700200046006100E700610020006100200076006500720069006600690063006100E700E3006F0020006E006F0020005300680065006C006C00200042006F0078002E" +
            "\r\n+CMGL: 2,\"REC READ\",\"28060\",,\"22/10/16,11:38:54-12\"\r\nTelegram code 45141" +
            "\r\n+CMGL: 3,\"REC READ\",\"29091\",,\"22/10/16,11:38:59-12\"\r\nG-518651  seu c odigo de verifica  o do Google." +
            "\r\n+CMGL: 4,\"REC READ\",\"92706\",,\"22/10/16,11:40:26-12\"\r\nFB-10067 is your Facebook confirmation code \n@m.facebook.com #10067" +
            "\r\n+CMGL: 5,\"REC READ\",\"093981175747\",,\"22/10/16,16:54:07-12\"\r\neu mesmo no ti bbg" +
            "\n\r\n+CMGL: 6,\"REC READ\",\"093981175747\",,\"22/10/16,16:54:59-12\"\r\noutra de mim mesmo tim bbg mas de outbox sim" +
            "\r\n+CMGL: 7,\"REC READ\",\"093981175747\",,\"22/10/16,17:08:56-12\"\r\ndE MIM MEMSO VAI" +
            "\r\n+CMGL: 8,\"REC READ\",\"091998317849\",,\"22/10/16,16:59:26-12\"\r\n0050006F0072007100750065002000720065006300650062006F0020006D0061006900730020006E00E3006F00200065006E00760069006F003F003F003F" +
            "\r\n+CMGL: 9,\"REC READ\",\"091998317849\",,\"22/10/16,16:59:45-12\"\r\n004F0069002000630061006400EA00200076006F006300EA003F" +
            "\r\n+CMGL: 10,\"REC READ\",\"091998317849\",,\"22/10/16,17:02:14-12\"\r\nAgora estou recebendo do modem." +
            "\r\n+CMGL: 11,\"REC READ\",\"091998317849\",,\"22/10/16,17:03:57-12\"\r\n004D006F00640065006D002C002000760063002000650073007400E100200072006500760065006E0064006F003F" +
            "\r\n+CMGL: 12,\"REC READ\",\"093981175747\",,\"22/10/16,17:12:20-12\"\r\ndE MIM MEMSO VAI2. e LIBERA AS OUTRAS" +
            "\r\n+CMGL: 13,\"REC READ\",\"091998317849\",,\"22/10/16,17:10:25-12\"\r\n00520065006300650062006100200065006E007400E3006F0021" +
            "\r\n+CMGL: 14,\"REC READ\",\"091998317849\",,\"22/10/16,17:11:29-12\"\r\n00520065006300650062006F00200064006500200069006D00650064006900610074006F00200064006F0020006D006F00640065006D002E0020004D006100730020006F0020006D006F00640065006D0020007300F300200072006500630065006200650020006400650070006F0069007300200064006500200065006E0076006900610072" +
            "\r\n+CMGL: 15,\"REC READ\",\"091998317849\",,\"22/10/16,17:11:30-12\"\r\n00200070007200610020007300690020006D00650073006D006F002E002000540069006D00200062006200670020006E006F0020006D006F00640065006D002E0020" +
            "\r\n+CMGL: 16,\"REC READ\",\"091998317849\",,\"22/10/16,17:18:16-12\"\r\n00500065006700610020006100ED0020" +
            "\r\n+CMGL: 17,\"REC READ\",\"093981175747\",,\"22/10/17,00:23:59-12\"\r\nwwwwwwwwwwwwwwwwwwwww" +
            "\r\n+CMGL: 18,\"REC READ\",\"29343\",,\"22/10/16,22:03:56-12\"\r\nInter: Atencao! Nunca solicitamos esse codigo. Insira o codigo somente no seu App do Inter para alterar sua senha de acesso a conta 7848V5" +
            "\r\n+CMGL: 19,\"REC READ\",\"091998317849\",,\"22/10/16,21:23:09-12\"\r\n00530069006D002E002000410067006F00720061002000650073007400E100200065006E007600690061006E0064006F0020006100730020006D0065006E0073006100670065006E00730020" +
            "\r\n+CMGL: 20,\"REC READ\",\"091998317849\",,\"22/10/16,22:48:09-12\"\r\nE agora?" +
            "\r\n+CMGL: 21,\"REC READ\",\"091998317849\",,\"22/10/16,22:48:22-12\"\r\n++++++++" +
            "\r\n+CMGL: 22,\"REC READ\",\"091998317849\",,\"22/10/17,01:33:04-12\"\r\nDo celular pro modem -2" +
            "\r\n+CMGL: 23,\"REC READ\",\"091998317849\",,\"22/10/22,21:39:07-12\"\r\n004F006C00E10021" +
            "\r\n+CMGL: 24,\"REC READ\",\"091998317849\",,\"22/10/22,21:43:03-12\"\r\nChega ou nao" +
            "\r\n+CMGL: 25,\"REC READ\",\"091998317849\",,\"22/10/22,21:44:08-12\"\r\nEstou achando que vai" +
            "\r\n+CMGL: 26,\"REC READ\",\"091998317849\",,\"22/10/22,22:44:59-12\"\r\nGggg" +
            "\r\n+CMGL: 27,\"REC READ\",\"1027\",,\"22/10/22,23:28:35-12\"\r\nTIM Informa: Em instantes seu celular recebera configuracao p/ enviar MMS e navegar na Internet. A configuracao sera gratis. Caso solicite senha digite 1234." +
            "\r\n+CMGL: 28,\"REC READ\",\"1027\",,\"22/10/22,23:28:38-12\"\r\n�\t��Z�\rtim�\f��tim�V�TIM MMS�TIM MMS�U�6w4�TIM MMS�9189.40.191.96" +
            "\r\n+CMGL: 29,\"REC READ\",\"1027\",,\"22/10/22,23:28:39-12\"\r\n�\"TIM NAP�4http://mms.tim.br" +
            "\r\n+CMGL: 30,\"REC READ\",\"1027\",,\"22/10/22,23:28:40-12\"\r\n1.96� 189.40.191.96�!��\"TIM MMS�S�#8080�U�TIM MMS���TIM MMS�\bmms.tim.br" +
            "\r\n+CMGL: 31,\"REC READ\",\"1027\",,\"22/10/22,23:28:41-12\"\r\n$/-����FA0552C9BD54584207C5103F1D1D7EF0F3FB0010j�F�Q�189.40.191.96�TIM MMS�R�/189.40.19" +
            "\r\n+CMGL: 32,\"REC READ\",\"091998317849\",,\"22/10/22,23:32:01-12\"\r\noi de meu chip np modem para o do bbg no meu cell" +
            "\r\n+CMGL: 33,\"REC READ\",\"091998317849\",,\"22/10/22,23:41:09-12\"\r\nchega ou nao?" +
            "\r\n+CMGL: 34,\"REC READ\",\"091998317849\",,\"22/10/22,23:42:40-12\"\r\nlol" +
            "\r\n+CMGL: 35,\"REC READ\",\"TIM\",,\"22/10/23,00:20:33-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga" +
            "\r\n+CMGL: 36,\"REC READ\",\"091998317849\",,\"22/10/23,00:21:46-12\"\r\nVejamos" +
            "\r\n+CMGL: 37,\"REC READ\",\"1028\",,\"22/10/22,23:28:39-12\"\r\nAST�\"TIM CONNECT FAST�Y�:" +
            "\r\n+CMGL: 38,\"REC READ\",\"1028\",,\"22/10/22,23:28:40-12\"\r\n%/-����CDF598C8C6C6A06D03F64C442FCA139DEDFD0859j�F�U�TIM CONNECT FAST���TIM CONNECT FAST" +
            "\r\n+CMGL: 39,\"REC READ\",\"100\",,\"22/10/24,16:42:46-12\"\r\nTIM RECADO: Voce tem um novo recado. Para ouvir ligue *100." +
            "\r\n+CMGL: 40,\"REC UNREAD\",\"BETAInfo\",,\"22/11/19,07:24:11-12\"\r\nHey Beta, acabou sua internet? Nao fique sem navegar! Faca uma recarga e volte a aproveitar seu plano ao maximo! Acesse meuplano.tim.com.br e recarregue!" +
            "\r\n+CMGL: 41,\"REC READ\",\"TIM\",,\"22/11/03,08:36:45-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga" +
            "\r\n+CMGL: 42,\"REC READ\",\"29181\",,\"22/11/03,09:18:01-12\"\r\nInter: Atencao, o Inter nao entra em contato pelo: 3003-4070. Seu token de confirmacao: I6Y6C7. Passe ele para o atendente dar sequencia ao atendimento." +
            "\r\n+CMGL: 43,\"REC READ\",\"29181\",,\"22/11/03,09:18:52-12\"\r\nInter: Atencao, o Inter nao entra em contato pelo: 3003-4070. Seu token de confirmacao: R4G0E5. Passe ele para o atendente dar sequencia ao atendimento." +
            "\r\n+CMGL: 44,\"REC UNREAD\",\"TIM\",,\"22/11/19,08:26:36-12\"\r\nBETA, seu saldo eh menor q o valor da renovacao da oferta. Sem renovar vc nao cumpre seus desafios e perde a chance de virar LAB+. Recarregue tim.com.br/recarga" +
            "\r\n+CMGL: 45,\"REC UNREAD\",\"091998317849\",,\"22/11/19,12:09:15-12\"\r\nGo fox\r\n\r\nOK\r\n\"";
}
