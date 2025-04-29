# S25 CS 4390.004 Socket Programming Project

## Features
- Simple Protocol - Message exchange first sends the name and then the number in two distinct messages.
- Input Validation Feedback - Server-side checks and control-flow to ensure input validity.
- Session Logging - Outputs descriptive logging for all actions.
- Graceful Shutdown - Ensures server socket and both client and server sockets are closed properly.

## Server.java

### Flow
Server listens on port 5001 and waits for an incoming client. When a client connects, the server:
1. Receives a message from the client containing the client's name and an integer.
2. Prints this information.
3. Terminates if the client's integer is outside the valid bounds.
4. Generates a random integer between 1 and 100 (inclusive).
5. Calculates and prints the sum of the client's and server's integers.
6. Sends the server name and the random integer to the client.
7. Loop back to step 1.

### Logic
- Setup 
  - A ServerSocket is created on port 5001. The server then enters a while loop to continuously accept clients.
- Client Handling
  - On a client connection the server creates a DataInput and DataOutput streams to facilitate communication between the web sockets. 
  - Two UTF-formatted messages are then read from the client.
- Processing 
  - If the client's integer is not in the range of [1,100], the server assigns the sentinel value of '-1' as the data to be sent and the while loop flag as false to stop continuous execution.
  - If the client's integer is valid, a random integer in the range of [1,100] is generated. The sum of the client's and server's integers is computed and displayed.
- Response
  - Two UTF-formatted response messages are sent to the client.
- Cleanup
  - The server prints a disconnection message and either continues or terminates based on the client's integer.

## Client.java

### Flow
The client accepts an integer input from the user, connects to a server running on "localhost:5001", and exchanges messages. When a client is created it:
1. Accepts an integer from the user.
2. Sends the client name and the integer to the server.
3. Receives a message from the server containing the server's name and a random integer.
4. Prints this information.
5. Terminates if the server's integer is the sentinel value of '-1'.
6. Calculates and prints the sum of the client's and server's integers.

### Logic
- User Input
  - Prompts and accepts user input for an integer. Uses Scanner to read input from the console.
- Server Handling
  - Opens a Socket to localhost "127.0.0.1" on port 5001 to establish server connection.
  - On a server connection the client creates a DataInput and DataOutput streams to facilitate communication between the web sockets.
- Message
  - Two UTF-formatted messages are sent to the server.
- Response
  - Blocks while waiting for a message from the server.
  - Two UTF-formatted messages are then read from the server.
- Processing
  - If the server sends '-1' as the integer, it indicates as a shutdown flag and ends the session without completing the computation.
  - If the server sends a valid integer, the sum is calculated and displayed.
- Cleanup
  - The client prints a disconnection message and terminates gracefully.

![image](https://github.com/user-attachments/assets/3ca60b47-b834-4c9c-9d9b-0ea3c11a4283)
