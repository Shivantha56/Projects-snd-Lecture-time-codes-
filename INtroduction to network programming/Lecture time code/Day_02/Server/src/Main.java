import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try {

            System.out.println("Server");
            ServerSocket serverSocket = new ServerSocket(30000);
            Socket socket = serverSocket.accept();

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


            String message = "";
            String reply = "";


            while (!message.equals("Bye")){


                message = dataInputStream.readUTF();
                System.out.println(message);

                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);


            }

            bufferedReader.close();
            dataOutputStream.flush();
            dataOutputStream.flush();
            socket.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}