import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {

    public static void main(String... args) {

        try {
            // open a server on port 5001 and listen for client connections
            ServerSocket serverSocket = new ServerSocket(5001);
            Socket socket = serverSocket.accept();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while(true) {
                // receive message from client
                String messageReceived = dis.readUTF();

                // decode message
                // [0] = name of client
                // [1] = selected integer
                String[] decoded = messageReceived.split(";");

                // terminate after receiving an integer value that is out of range
                if(Integer.parseInt(decoded[1]) < 0 || Integer.parseInt(decoded[1]) > 100) {
                    break;
                }

                // display client and server name
                String serverName = "Server of Rahul Krishna";
                System.out.println(decoded[0] + " connected to " + serverName);

                // generate random integer between 1-100
                int randomNumber = (int) ((Math.random() * 99) + 1);
                // display random integer
                System.out.println("Server selected random integer: " + randomNumber);

                // compute and display the sum of server + client integer
                System.out.println("The sum of the server and client integers is: " + (Integer.parseInt(decoded[1]) + randomNumber));

                // construct message to send
                String messageToSend = serverName + ";" + randomNumber;
                // send message to client
                dos.writeUTF(messageToSend);
            }
            // fall off stack

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
