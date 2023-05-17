package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class ServerFormController implements Initializable {

    ServerSocket serverSocket;
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
        new Thread(()->{
            try {
                serverSocket=new ServerSocket(3601);
                textArea.appendText("server Started");
                accept=serverSocket.accept();
                textArea.appendText("Client Connected");
                dataOutputStream=new DataOutputStream(accept.getOutputStream());
                dataInputStream=new DataInputStream(accept.getInputStream());

                message=dataInputStream.readUTF();
                textField.appendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();
    }
}
