/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author rizky
 */
public class InstallerController implements Initializable {

    @FXML
    private JFXTextField inputFile;

    @FXML
    private JFXButton pilihFolder;

    @FXML
    private AnchorPane pathFolder;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton btnPilihFile;

    @FXML
    private JFXButton btnBatal;

    @FXML
    private Label labelError;

    @FXML
    private JFXCheckBox ekstrak;

    @FXML
    private JFXTextField pathEkstrak;

    @FXML
    void btnSelectFileActionPerformed(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Alldeb File", "alldeb");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        if (file == null) {
            labelError.setText("[!] Pilih satu file .alldeb");
            labelError.setTextFill(Color.web("#0076a3"));
        } else {
            labelError.setText("");

        }
        try {
            String fileName = file.getAbsolutePath();
            inputFile.setText(fileName);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    void btnSelectFolderActionPerformed(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory == null) {
            labelError.setText("Directory Kosong");
            labelError.setTextFill(Color.web("#0076a3"));
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
        }
        try {
            String s = selectedDirectory.getAbsolutePath();
            pathEkstrak.setText(s);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    @FXML
    void aksiEkstrak(ActionEvent event) {
        if (ekstrak.isSelected()) {
            pilihFolder.setDisable(false);
        } else {
            pilihFolder.setDisable(true);
        }
    }

    @FXML
    void aksiLanjut(ActionEvent event) {
        try {

            String s = null;
            Runtime rt = Runtime.getRuntime();

            String pathFileSelected = inputFile.getText();
            String pathFolderEkstrak;
            int opsi = 4;
            if (ekstrak.isSelected()) {
                opsi = 3;
                pathFolderEkstrak = pathEkstrak.getText();
            } else {
                opsi = 1;
                pathFolderEkstrak = "$HOME/lpm/";
            }

            String command[] = {
                "/bin/bash",
                "-c",
                "echo " + password.getText()
                + " | sudo -S bash /usr/share/lpm/./alldeb-manager-cli " + pathFileSelected + " " + pathFolderEkstrak + " " + opsi + " --laos"
            };
            Process proc = rt.exec(command);

            BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError2 = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // Tampilkan output command
            System.out.println("Output:\n");
            while ((s = stdInput2.readLine()) != null) {
                System.out.println(s);
                if (s.contains("Selesai.")) {
                    labelError.setText("Instalasi berhasil.");
                    labelError.setTextFill(Color.GREEN);
                }
            }

            // Tampilkan error
            System.out.println("Error:\n");
            while ((s = stdError2.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void btnBatalAksi(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
