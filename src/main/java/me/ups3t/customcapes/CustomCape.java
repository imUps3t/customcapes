package me.ups3t.customcapes;

import me.ups3t.customcapes.cape_server.CapeServer;

public class CustomCape {

    public static void main(String[] args) {

        System.out.println("[!] Custom Capes by ups3t");
        System.out.println("[!] Cape Server running on port 80");

        CapeServer.hostServer(80);

    }

}
