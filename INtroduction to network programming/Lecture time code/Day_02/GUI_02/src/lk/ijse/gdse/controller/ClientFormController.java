package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {

    OutputStream outputStream;
    DataOutputStream dataOutputStream;

    DataInputStream dataInputStream;

    Socket accept;

    String message;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(textField.getText().trim());
        textArea.appendText("Server: " + textField.getText() + "\n");
        dataOutputStream.flush();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 1234);
                textArea.appendText("Server Online\n");
                outputStream = socket.getOutputStream();

                dataOutputStream = new DataOutputStream(outputStream);
                dataInputStream = new DataInputStream(socket.getInputStream());
                String msg = "";
                while (!msg.equals("end")) {
                    textArea.appendText("Server : " + dataInputStream.readUTF()+"\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

//
//    new Thread(() -> {
//        try {
//            Socket socket = new Socket("localhost", 1234);
//            txtArea.appendText("Server Online\n");
//            outputStream = socket.getOutputStream();
//
//            dataOutputStream = new DataOutputStream(outputStream);
//            dataInputStream = new DataInputStream(socket.getInputStream());
//            String msg = "";
//            while (!msg.equals("end")) {
//                txtArea.appendText("Server : " + dataInputStream.readUTF()+"\n");
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }).start();
}
