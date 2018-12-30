package gup.replay;

import gup.battle.battlefield;
import gup.battle.unit;
import gup.battle.war;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static gup.battle.battlefield.unitsize;
import static gup.battle.battlefield.gridPane;
//import static gup.replay.Record.record;
//import static gup.battle.battleoneround_movement.oneround_movement;

public class Replay extends Thread{
    public static Record record;
    public final static Object lock = new Object();

    void showoneround(Movement[] oneround_movement, action[] oneround_action)
    {
        gridPane.getChildren().clear();
        /*for(int i = 0; i < 17; i++)
                if(!oneround_movement[i].get_is_occupied() && j != 16)
                    System.out.print(' ');
                else if(!oneround_movement[i][j].get_is_occupied() && j == 16)
                    System.out.println(' ');
                else if(oneround_movement[i][j].get_is_occupied() && j != 16)
                    System.out.print(oneround_movement[i][j].gethuman().getcode());
                else
                    System.out.println(oneround_movement[i][j].gethuman().getcode());
        for (int i = 0; i < 17 ; i++) {
            for (int j = 0; j < 17; j++) {
                //T tmp = maps.get(i).get(j).getContent();
                if (oneround_movement[i][j].get_is_occupied()) {
                    //System.out.println(oneround_movement[i][j].gethuman().getpath().toString());
                    //gc.drawImage(new Image(oneround_movement[i][j].gethuman().getpath()), j * unitsize, i * unitsize, unitsize,unitsize);
                    ImageView imageView = new ImageView(new Image(oneround_movement[i][j].gethuman().getpath()));
                    gridPane.add(imageView, j, i);
                }
            }
        }*/
        /*borderPane.setCenter(trajectory);
        trajectory.setLayoutX(war.characters[0].getlocationx() * unitsize);
        trajectory.setLayoutY(war.characters[0].getlocationy() * unitsize);*/
        for (int i = 0; i < oneround_action.length; i++){
            if(oneround_movement[i].getMyself() != null) {
                //if (oneround_movement[i].isAlive) {
                    ImageView imageView =
                            new ImageView(new Image(getClass().getResourceAsStream("/"+oneround_movement[i].getMyself().getpath())));
                    gridPane.add(imageView, oneround_movement[i].getY(), oneround_movement[i].getX());
                    //oneround_movement[i].getMyself().repmove(oneround_movement[i].getMyself().getLocation());
                //}
            }
            /*for (int k = 0; k < 17 ; k++) {
                for (int j = 0; j < 17; j++) {
                    //T tmp = maps.get(i).get(j).getContent();
                    if (battlefield.replayfield[k][j].get_is_occupied()) {
                        //System.out.println(field[i][j].gethuman().getpath().toString());
                        //gc.drawImage(new Image(field[i][j].gethuman().getpath()), j * unitsize, i * unitsize, unitsize,unitsize);
                        ImageView imageView = new ImageView();
                        //imageView = new ImageView(new Image(field[i][j].gethuman().getpath()));
                        if(battlefield.replayfield[k][j].gethuman().isalive()) {
                            imageView = new ImageView(new Image(battlefield.replayfield[k][j].gethuman().getpath()));
                        }
                        else{
                            imageView = new ImageView(new Image("picture/flag.jpg"));
                        }
                        gridPane.add(imageView, j, k);
                    }
                }
            }*/
            if(oneround_action[i].isActioned()){
                //gridPane.getChildren().clear();
                Image image = new Image(getClass().getResourceAsStream("/picture/timg.jpg"));
                ImageView trajectory = new ImageView(image);
                //gc.drawImage(new Image("picture/test.jpg"), war.characters[i].getlocationx() * unitsize,war.characters[i].getPriority() * unitsize, unitsize, unitsize);
                gridPane.add(trajectory, oneround_action[i].getMyself().getlocationy(),
                        oneround_action[i].getMyself().getlocationx());
                /*trajectory.setLayoutX(war.characters[i].getlocationx() * unitsize);
                trajectory.setLayoutY(war.characters[i].getlocationy() * unitsize);
                trajectory.setTranslateX(trajectory.getTranslateX() - unitsize);*/
                gridPane.setHalignment(trajectory, HPos.CENTER);
                gridPane.setValignment(trajectory, VPos.CENTER);
                TranslateTransition tt = new TranslateTransition(Duration.millis(600), trajectory);
                tt.setToX(-oneround_action[i].getdisy() * (unitsize+10));
                tt.setToY(-oneround_action[i].getdisx() * (unitsize+10));
                tt.play();
                //gridPane.clearConstraints(trajectory);
            }
        }
    }

    void replay()
    {
        /*Iterator iterator = record.getoneround_action().iterator();
            while (iterator.hasNext()){
                action[] temp = (action[])iterator.next();
                showoneround(record.getmovementRecord().get(i), temp);
            }
        }*/
        showoneround(record.getmovementRecord().get((Counter.counter)), record.getactionRecord().get(Counter.counter));
    }


    public void run() {
        while (true) {
            synchronized (lock) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        replay();
                    }
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    lock.notifyAll();
                    lock.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
