
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Client");
            Socket socket = new Socket("localhost" , 30000);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";


            while (!message.equals("Bye")) {

                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);

                message = dataInputStream.readUTF();
                System.out.println(message);

            }

            bufferedReader.close();
            dataOutputStream.flush();
            dataOutputStream.flush();
            socket.close();

            System.out.println("task finished");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}