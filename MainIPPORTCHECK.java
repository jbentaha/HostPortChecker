/*============================================================================
 *
 * Copyright (c) 2000-2019 Smart Trade Technologies. All Rights Reserved.
 *
 * This software is the proprietary information of Smart Trade Technologies
 * Use is subject to license terms. Duplication or distribution prohibited.
 *
 *============================================================================*/

package com.smarttrade.cty.lptests;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author jbentahar
 */
public class MainIPPORTCHECK {

    public static void main(String[] args) {
        final MainIPPORTCHECK check = new MainIPPORTCHECK();
        final boolean lpIsAlive = check.lpIsAlive("64.212.61.50", 10115, 100000);
        System.err.println("is Alive : " + lpIsAlive);
    }

    private boolean lpIsAlive(String host, int port, int timeOut) {
        System.err.println("Checking IP / Port: " + host + " / " + port);
        Socket socket = null;
        try {
            socket = new Socket();
            socket.setReuseAddress(true);
            socket.connect(new InetSocketAddress(host, port), timeOut);
            final boolean bool = socket.isClosed();
            socket.close();
            System.err.println("Connection " + host + ":" + port + " -> " + (!bool ? "OK" : "KO"));
            return !bool;
        } catch (final Exception e) {
            System.err.println("Error during lpIsAlive" + e);
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (final IOException e1) {
                System.err.println("Error closing socket" + e1);
            }
        }
        System.err.println("Connection " + host + ":" + port + " -> KO");
        return false;
    }

}
