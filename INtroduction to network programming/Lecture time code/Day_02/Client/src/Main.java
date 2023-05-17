import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 30000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message = "";

            String reply = "";

            while (!reply.equals("Bye")) {


                reply = bufferedReader.readLine();
                //Cli input
                dataOutputStream.writeUTF(reply);
                //write and send
                dataInputStream.readUTF();

                System.out.println(message);
                //write and send
                dataOutputStream.flush();
            }
            dataOutputStream.flush();
            dataOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}