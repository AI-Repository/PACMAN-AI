package screenpac.remote;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;

public class UDPServer extends Thread {
    public static void main(String[] args) throws Exception {
        // create a UDP listen server
        new UDPServer("Test").start();
    }

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;

    public UDPServer(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    public void run() {
        while (true) {
            try {
                // try and read it
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String s = new String(packet.getData(), 0, packet.getLength());
                long arrivedAt = System.currentTimeMillis();
                long sentAt = new Long(s);
                System.out.println("Received: " + s);
                System.out.println("Delay: " + (arrivedAt - sentAt) + "ms");
                System.out.println("");
                String dString = new Date().toString();
                buf = dString.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
