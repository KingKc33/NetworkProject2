package EchoServiceCS316;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {

            if (args.length != 2) {
                System.out.println("Syntax: EchoClient <serverIP> <serverPort>");
                return;
            }



        InetAddress serverIP = InetAddress.getByName(args[0]);
            int serverPort = Integer.parseInt(args[1]);
            while (true) {
                System.out.println("Retrieve date or time?");
                DatagramSocket socket = new DatagramSocket();

                Scanner keyboard = new Scanner(System.in);
                String message = keyboard.nextLine();
                if (message.equals("time")){
                }
                else if (message.equals("date")){
                }
                else{
                    System.out.println("invalid input");
                    return;
                }


                DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, serverIP, serverPort);
                socket.send(request);

                DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);
                socket.receive(reply);
                byte[] content = Arrays.copyOf(
                        reply.getData(),
                        reply.getLength()
                );
                System.out.println(new String(content));

                socket.close();
            }

    }

}
