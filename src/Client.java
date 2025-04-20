import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {

        // sentinel value
        int userInput = -1;

        Scanner scanner;

        // accept an integer input from the user
        // validate that the integer is within the bounds of 1-100
        while(userInput < 0 || userInput > 100) {
            System.out.print("Enter an integer from 1-100: ");

            scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
        }

        try {
            // open a TCP socket
            Socket socket = new Socket("127.0.0.1", 5001);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // construct message to send
            String messageToSend = "Client of Rahul Krishna;" + userInput;
            // send message
            dos.writeUTF(messageToSend);

            // receive message from server
            // TODO
            // String messageReceived = dis.readUTF();

            // display data and compute sum
            // TODO

            // close socket
            socket.close();
            // fall off stack to terminate
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
