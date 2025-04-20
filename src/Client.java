import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {

        String clientName = "Client of Rahul Krishna";

        // sentinel value
        int clientNumber = -1;

        // accept an integer input from the user
        System.out.print("Enter an integer from 1-100: ");
        Scanner scanner = new Scanner(System.in);
        clientNumber = scanner.nextInt();

        try {
            // open a TCP socket
            Socket socket = new Socket("127.0.0.1", 5001);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // construct message to send
            String messageToSend = clientName + ";" + clientNumber;
            // send message to server
            dos.writeUTF(messageToSend);

            // receive message from server
            String messageReceived = dis.readUTF();

            // decode message
            // [0] = name of server
            // [1] = selected integer
            String[] decoded = messageReceived.split(";");

            System.out.println(clientName + " connected to " + decoded[0]);

            if(Integer.parseInt(decoded[1]) != -1) {
                // display data and compute sum
                System.out.println("Server's random integer: " + decoded[1]);
                System.out.println("Client's selected integer: " + clientNumber);

                // compute and display the sum of server + client integer
                System.out.println("The sum of the server and client integers is: " + (Integer.parseInt(decoded[1]) + clientNumber));
            }

            System.out.println("Closing connection with " + decoded[0]);

            // close socket
            socket.close();
            System.out.println("Closing " + clientName);
            // fall off stack to terminate
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
