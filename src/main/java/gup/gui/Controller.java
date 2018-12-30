package gup.gui;

import gup.battle.battlefield;
import gup.battle.unit;
import gup.character.human;
import gup.character.stggc.stggc_student;
import gup.character.stggc.stggc_teammember;
import gup.character.university.shimada_alice;
import gup.character.university.shimada_chiyo;
import gup.character.university.university_student;
import gup.colour.colours;
import gup.replay.Counter;
import gup.replay.Record;
import gup.battle.war;
import gup.replay.Replay;
import gup.replay.serialize;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static gup.Main.onegame;

//import static gup.replay.Replay.record;

public class Controller {

    private FileChooser fileChooser = new FileChooser();
    @FXML
    private BorderPane mainPaneWindow;
    @FXML
    private GridPane gridPane;
    @FXML
    private Canvas Maincanvas;      //主画布
    @FXML
    private Button startbutton;     //开始的按钮


    public void startgame(javafx.event.ActionEvent actionEvent) {
        display dis = new display();
        battlefield.setGridPane(gridPane);
        //battlefield.setBackground(mainPaneWindow);
        //battlefield.setcanvas(Maincanvas);
        //war onegame = new war();
        //Record oneRecord = new Record();
        //onegame.initialgame();
        onegame.fight();
        dis.start();

    }
    public void savegame(javafx.event.ActionEvent actionEvent){
        DirectoryChooser directoryChooser=new DirectoryChooser();
        File file = directoryChooser.showDialog(mainPaneWindow.getScene().getWindow());
        String path = file.getPath();//选择的文件夹路径

        serialize.save(path);
    }

    public void replaygame(javafx.event.ActionEvent actionEvent) {
        battlefield.setGridPane(gridPane);
        File file = fileChooser.showOpenDialog(mainPaneWindow.getScene().getWindow());
        Replay.record = serialize.load(file);
        Replay replay = new Replay();
        Counter counter = new Counter();
        Counter.counter = -1;
        counter.start();
        replay.start();
        /*for(int k = 0; k < Replay.record.getactionRecord().size(); k++){
            unit[][] field = Replay.record.getmovementRecord().get(k);
            for(int i = 0; i < 17; i++)
                for(int j = 0; j < 17; j++)
                    if(!field[i][j].get_is_occupied() && j != 16)
                        System.out.print(' ');
                    else if(!field[i][j].get_is_occupied() && j == 16)
                        System.out.println(' ');
                    else if(field[i][j].get_is_occupied() && j != 16)
                        System.out.print(field[i][j].gethuman().getcode());
                    else
                        System.out.println(field[i][j].gethuman().getcode());
        }*/

        //Replay.replay(record);
    }
    public BorderPane getMainPaneWindow(){
        return mainPaneWindow;
    }
}

/*D:\大学资料\大三上\java\project\Finalwar*/
