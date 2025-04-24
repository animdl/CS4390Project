import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {

    public static void main(String... args) {

        String clientName = "Client of Rahul Krishna";
        String host = "192.168.0.220";

        // sentinel value
        int clientNumber = -1;

        // accept an integer input from the user
        System.out.print("Enter an integer from 1-100: ");
        Scanner scanner = new Scanner(System.in);
        clientNumber = scanner.nextInt();

        try {
            // open a TCP socket
            Socket socket = new Socket(host, 5001);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // send clientName to server
            dos.writeUTF(clientName);
            // send clientNumber to server
            dos.writeUTF("" + clientNumber);

            // receive serverName from server
            String serverName = dis.readUTF();
            // receive serverNumber from server
            int serverNumber = Integer.parseInt(dis.readUTF());

            System.out.println("---\n" + clientName + " connected to " + serverName + " on host " + host + " port 5001");

            if(serverNumber != -1) {
                // display data and compute sum
                System.out.println("Server's random integer: " + serverNumber);
                System.out.println("Client's selected integer: " + clientNumber);

                // compute and display the sum of server + client integer
                System.out.println("The sum of the server and client integers is: " + (serverNumber + clientNumber));
            }

            System.out.println("Closing connection with " + serverName);

            // close socket
            socket.close();
            System.out.println("Closing " + clientName + "\n---");
            // fall off stack to terminate
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
