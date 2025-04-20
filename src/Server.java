import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {

    public static void main(String... args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        String serverName = "Server of Rahul Krishna";

        try {
            // open a server on port 5001 and listen for client connections
            serverSocket = new ServerSocket(5001);

            boolean flag = true;

            // continuously accept clients
            while(flag) {
                System.out.println("---\nListening for new client...");

                // blocking; listens for a client connection
                socket = serverSocket.accept();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // receive message from client
                String messageReceived = dis.readUTF();

                // decode message
                // [0] = name of client
                // [1] = selected integer
                String[] decoded = messageReceived.split(";");

                // display client and server name
                System.out.println(decoded[0] + " connected to " + serverName);

                // display clients number
                System.out.println("Client's selected integer: " + decoded[1]);

                int serverNumber;

                // terminate after receiving an integer value that is out of range
                if(Integer.parseInt(decoded[1]) < 1 || Integer.parseInt(decoded[1]) > 100) {
                    // sets sentinel value to prompt socket closure
                    serverNumber = -1;
                    flag = false;
                } else {
                    // generate random integer between 1-100
                    serverNumber = (int) ((Math.random() * 99) + 1);
                    // display random integer
                    System.out.println("Server's random integer: " + serverNumber);

                    // compute and display the sum of server + client integer
                    System.out.println("The sum of the server and client integers is: " + (Integer.parseInt(decoded[1]) + serverNumber));
                }

                // construct message to send
                String messageToSend = serverName + ";" + serverNumber;
                // send message to client
                dos.writeUTF(messageToSend);

                System.out.println("Closing connection with " + decoded[0]);
            }

            // close server and socket
            serverSocket.close();
            socket.close();
            System.out.println("Closing " + serverName);
            // fall off stack
        } catch(Exception e) {
            //e.printStackTrace();
        }

    }

}
