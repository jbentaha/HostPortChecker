import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author jbentaha
 */
public class MainIPPORTCHECK {

    public static void main(String[] args) {
        final MainIPPORTCHECK check = new MainIPPORTCHECK();
        final boolean isAlive = check.lpIsAlive(ip, port, timeOut);
        System.err.println("is Alive : " + isAlive);
    }

    private boolean isAlive(String host, int port, int timeOut) {
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
