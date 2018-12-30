package gup;

import gup.battle.battlefield;
import gup.battle.war;
import gup.gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    public static war onegame = new war();
    public static battlefield bf = new battlefield();
    //public static BorderPane mainpane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/battlefield.fxml"));
        primaryStage.setTitle("Girls und Panzer");
        primaryStage.setScene(new Scene(root, 917, 616));
        primaryStage.show();
        onegame.initialgame();
        BackgroundImage image = new BackgroundImage(new Image(getClass().getResourceAsStream("/picture/background" +
                ".jpg"),917,616,false,
                true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(image));
        //Controller controller = new Controller();
        /*BackgroundImage image = new BackgroundImage(new Image("picture/background.jpg",917,616,false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        controller.getMainPaneWindow().setBackground(new Background(image));*/
        //battlefield.setBackground(controller.getMainPaneWindow());
        //battlefield.setGridPane(gridPane);
        //war onegame = new war();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
