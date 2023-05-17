import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(30000);
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";


            while (!message.equals("Bye")){

                System.out.println("A");

                dataInputStream.readUTF();

                System.out.println(dataInputStream.readUTF());

                System.out.println("read");

                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);

                dataOutputStream.flush();


            }

            dataOutputStream.flush();
            dataOutputStream.close();


            System.out.println("Task finished");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}