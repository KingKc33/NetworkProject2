package EchoServiceCS316;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;

public class EchoServer {
    public static void main(String[] args) throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(3000);

        while (true) {
            DatagramPacket clientRequest = new DatagramPacket(new byte[1024],
                    1024);
            serverSocket.receive(clientRequest);
            byte[] content = Arrays.copyOf(
                    clientRequest.getData(),
                    clientRequest.getLength()
            );


            String message = new String(content);
            if (message.equals("time")){
                LocalDateTime now = LocalDateTime.now();
                String localTime = now.toLocalTime().toString();
                byte[] newContent = localTime.getBytes();

                InetAddress clientIP = clientRequest.getAddress();
                int clientPort = clientRequest.getPort();




                DatagramPacket serverReply = new DatagramPacket(newContent,
                        newContent.length,
                        clientIP,
                        clientPort
                );
                serverSocket.send(serverReply);

            }
            else if(message.equals("date")){
                LocalDateTime now = LocalDateTime.now();
                String localDate = now.toLocalDate().toString();
                byte[] newContent = localDate.getBytes();

                InetAddress clientIP = clientRequest.getAddress();
                int clientPort = clientRequest.getPort();

                DatagramPacket serverReply = new DatagramPacket(newContent,
                        newContent.length,
                        clientIP,
                        clientPort
                );

                serverSocket.send(serverReply);
            }



            InetAddress clientIP = clientRequest.getAddress();
            int clientPort = clientRequest.getPort();

            DatagramPacket serverReply = new DatagramPacket(content,
                    content.length,
                    clientIP,
                    clientPort
            );
            serverSocket.send(serverReply);

        }



    }
}
