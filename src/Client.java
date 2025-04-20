import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {

        // sentinel value
        int userInput = -1;

        // accept an integer input from the user
        System.out.print("Enter an integer from 1-100: ");
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextInt();

        try {
            // open a TCP socket
            Socket socket = new Socket("127.0.0.1", 5001);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // construct message to send
            String clientName = "Client of Rahul Krishna";
            String messageToSend = clientName + ";" + userInput;
            // send message to server
            dos.writeUTF(messageToSend);

            // receive message from server
            String messageReceived = dis.readUTF();

            // decode message
            // [0] = name of server
            // [1] = selected integer
            String[] decoded = messageReceived.split(";");

            // display data and compute sum
            System.out.println(clientName + " connected to " + decoded[0]);
            System.out.println("Server's random integer: " + decoded[1]);
            System.out.println("Client's selected integer: " + userInput);

            // compute and display the sum of server + client integer
            System.out.println("The sum of the server and client integers is: " + (Integer.parseInt(decoded[1]) + userInput));

            // close socket
            socket.close();
            // fall off stack to terminate
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
