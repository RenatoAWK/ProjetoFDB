package com.bsi.fbd.minisiga.modelo;

public class Connection {
    private static String ip = null;
    private static String url = null;

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Connection.ip = ip;
        Connection.url = "http://"+ip+"/";
    }

    public static String getUrl() {
        return url;
    }
}
