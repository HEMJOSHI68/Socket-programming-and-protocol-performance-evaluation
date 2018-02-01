
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class client_java_udp {

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Step 1:Create the socket object for carrying
        // the data
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        try {
            String user_ip_address;

            System.out.println("Enter server name or IP address");
            user_ip_address = sc.next();
            {
                if (user_ip_address.equals("Localhost") || user_ip_address.equals("127.0.0.1")) {
                    try {
                        // Taking input Port number 
                        String user_Port;
                        System.out.println("Enter Port");
                        user_Port = sc.next();

                        if (Integer.parseInt(user_Port) <= 4444) {
                            try {
                                Scanner sc1 = new Scanner(System.in);
                                System.out.print("Enter the expression");

                                String inp = sc1.nextLine();
                                buf = new byte[65535];

                                // convert the String input into the byte array.
                                buf = inp.getBytes();

                                // Step 2:Create the datagramPacket for sending the data.
                                DatagramPacket DpSend
                                        = new DatagramPacket(buf, buf.length, ip, 1234);

                                // invoke the send call to actually send the data.
                                ds.send(DpSend);

                                buf = new byte[65535];
                                DatagramPacket DpReceive
                                        = new DatagramPacket(buf, buf.length);
                                ds.receive(DpReceive);

                                System.out.println("Answer = "
                                        + new String(buf, 0, buf.length));
                            } catch (Exception e) {
                                System.out.println("Error not prcessing");
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Error not prcessing");
                    }

                } else {
                    System.out.println("Could not connect to server.");
                    ds.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Could not connect to server. Terminating");
        }
    }
}