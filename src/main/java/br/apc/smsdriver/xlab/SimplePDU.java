package br.apc.smsdriver.xlab;

// https://nantipov.org/2019/03/gsm-7bit-pdu-encoding-algorithm-in-java/
public class SimplePDU {


    public static String stringTo7bitHex(String text) {
        StringBuilder output = new StringBuilder();
        byte[] bytes = text.getBytes();
        int f = 0;
        while (f < bytes.length - 1) {
            int t = (f % 8) + 1;
            if (t < 8) {
                byte b = (byte) (((bytes[f] >>> (t - 1)) | (bytes[f + 1] << (8 - t))) & 0x000000FF);
                output.append(intToHex(b & 0x000000FF));
            }
            f++;
        }
        if ((f % 8) + 1 < 8) {
            output.append(intToHex((bytes[f] >>> (f % 8)) & 0x000000FF));
        }
        return output.toString();
    }

    private static String intToHex(int i) {
        String hex = Integer.toHexString(i);
        if (hex.length() % 2 != 0) hex = 0 + hex;
        return hex.toUpperCase();
    }

}
