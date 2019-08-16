/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rizky
 */
public class homeController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnDownload;

    @FXML
    private Label textCari;

    @FXML
    private ScrollPane scroll;

    @FXML
    private JFXButton buttonInstaller;

    @FXML
    private JFXTextField fieldSearch;

    @FXML
    private Pane badgeHasil;

    @FXML
    private Label namaPaket1;

    @FXML
    private Label textHasil;

    @FXML
    private Pane pane;

    @FXML
    private VBox boxHasil;

    @FXML
    private VBox vutama;
    @FXML
    private HBox horizontal;

    @FXML
    private JFXButton store;

    @FXML
    void Store(ActionEvent event) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("home.fxml"));
            anchor.getChildren().setAll(pane);
        } catch (Exception e) {

        }
    }

    @FXML
    private void newWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("installer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Laos Package Installer");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {

        }
    }

    String http(String link) throws MalformedURLException, IOException {
        String url = "http://artemtech.me/api/lpm/detail.php?app=" + link;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    String mirror(String link) throws MalformedURLException, IOException {
        String url = "http://artemtech.me/api/lpm/mirror.php?app=" + link;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    String gambar(String link) throws MalformedURLException, IOException {

        String url = "http://artemtech.me/api/lpm/convert.php?icon=" + link;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    @FXML
    void detail(ActionEvent event, String param) {
        System.out.println(param);
        try {

            String respon = this.http(param);
            JSONObject data = new JSONObject(respon);
            String nama_paket = data.getString("nama_paket");
            String icon_paket = data.getString("icon_paket");
            JSONObject deskripsi = data.getJSONObject("deskripsi_paket");
            String detail = deskripsi.getString("detail");

            System.out.println(icon_paket);
            AnchorPane root = new AnchorPane();
            root.setPrefWidth(600);
            root.setPrefHeight(500);
            root.setMinWidth(600);
            root.setMinHeight(500);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Pane topPane = new Pane();
            topPane.setPrefSize(500, 50);
            topPane.setStyle("-fx-background-color: #000000");

            Label logo = new Label();
            logo.setText("LaosManager");
            logo.setLayoutX(10);
            logo.setLayoutY(15);
            logo.setTextFill(Color.WHITE);
            logo.setFont(Font.font("Monserrat", FontWeight.BLACK, 20));

            topPane.getChildren().add(logo);
            String linkGbr = gambar(icon_paket);
            Image gambar = new Image("http://www.iconsalot.com/asset/icons/blackvariant/button-ui-2-app-pack-8/1024/NetBeans-icon.png");
            ImageView imageView = new ImageView(gambar);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setX(20);
            imageView.setX(20);
            imageView.setPreserveRatio(true);

            Label label = new Label();
            label.setText(nama_paket);
            label.setFont(Font.font("Monserrat", FontWeight.BLACK, 20));

            Label size = new Label();
            size.setText("Size : ");

            Label deskripsi_detail = new Label();
            deskripsi_detail.setText(detail);
            deskripsi_detail.setPrefWidth(450);

            deskripsi_detail.setMaxWidth(500);
            deskripsi_detail.setWrapText(true);

            ScrollPane desScroll = new ScrollPane();
            desScroll.setContent(deskripsi_detail);
            desScroll.setMinWidth(450);
            desScroll.setMinHeight(300);
            desScroll.hbarPolicyProperty();

            HBox horizontal = new HBox(10);
            VBox vertikal = new VBox(10);
            VBox utama = new VBox(10);

            JFXButton tombol = new JFXButton();
            tombol.setText("Install");
            tombol.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        tombol.setDisable(true);

                        String dataMirror = mirror(nama_paket);

                        JSONObject data2 = new JSONObject(dataMirror);

                        String link = data2.getString("tautan");

                        String s = null;
                        Runtime rt = Runtime.getRuntime();

                        String perintah[] = {
                            "/bin/bash",
                            "-c",
                            "echo " + " | wget -nH --directory-prefix=$HOME/paket-lpm/ " + link
                        };

                        Process process = rt.exec(perintah);

                        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                        // Tampilkan output command
                        System.out.println("Output:\n");
                        while ((s = stdInput.readLine()) != null) {
                            System.out.println(s);
                        }

                        // Tampilkan error
                        System.out.println("Error:\n");
                        while ((s = stdError.readLine()) != null) {
                            System.out.println(s);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            vertikal.getChildren().addAll(label, desScroll, tombol);
            horizontal.getChildren().addAll(imageView, vertikal);

            utama.getChildren().addAll(topPane, horizontal);
            root.getChildren().add(utama);

            stage.setTitle("Laos Store - " + param);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {

        }

    }

    @FXML
    void ActionCari(ActionEvent event) throws MalformedURLException, IOException, JSONException {
        boxHasil.getChildren().removeAll();

        String url = "http://artemtech.me/api/lpm/search.php?app=" + fieldSearch.getText();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String responeString = response.toString();

        //print result
        JSONObject data = new JSONObject(responeString);
        Boolean status = data.getBoolean("status");
        if (status == true) {
            textHasil.setVisible(true);
            scroll.setVisible(true);
            JSONObject nama_paket = data.getJSONObject("pilihan_paket");
            String[] result = new String[nama_paket.length() + 1];
            int resultLength = result.length;
            for (int i = 0; i < resultLength; i++) {
                if (i == 0) {
                    result[i] = data.getString("nama_paket");
                } else {
                    result[i] = nama_paket.getString(Integer.toString(i));
                }
            }

            for (int i = 0; i < resultLength; i++) {
                System.out.println("Aplikasi " + result[i]);
            }

            for (int i = 0; i < result.length; i++) {

                Pane kotakHasil = new Pane();
                kotakHasil.setStyle("-fx-background-color: #039BE5;");
                kotakHasil.setPrefHeight(53);
                kotakHasil.setMinHeight(53);
                kotakHasil.setPrefWidth(597);

                JFXButton btnDetail = new JFXButton();
                btnDetail.setText("Detail");
                final String nilai = result[i];
                btnDetail.setStyle("-fx-background-color: #ffffff; -jfx-button-type: RAISED");
                btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        detail(event, nilai);

                    }
                });

                Label namaApp = new Label();
                namaApp.setText(result[i]);
                namaApp.setTextFill(Color.WHITE);
                namaApp.setFont(Font.font("Monserrat", FontWeight.BLACK, 15));

                VBox horHasil = new VBox();
                horHasil.setAlignment(Pos.CENTER);
                horHasil.setPrefHeight(53);
                horHasil.setMinHeight(53);
                horHasil.setPrefWidth(597);
                horHasil.setMinWidth(597);
                horHasil.getChildren().addAll(namaApp, btnDetail);

                kotakHasil.getChildren().add(horHasil);
                boxHasil.getChildren().add(kotakHasil);

            }

        } else {
            textHasil.setVisible(true);
            scroll.setVisible(false);
            textHasil.setText("Aplikasi Tidak Tersedia");
            textHasil.setTextFill(Color.RED);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textHasil.setVisible(false);
        scroll.setVisible(false);

    }

}
