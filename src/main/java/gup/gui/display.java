package gup.gui;

import gup.Main;
import gup.battle.battlefield;
import gup.character.human;
import gup.replay.Record;
import gup.battle.war;
import javafx.application.Application;
import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

//import static gup.replay.Record;

public class display extends Thread{
    public void run ()
    {

        while (!war.finished) {
            synchronized (human.lock) {
                if (human.turn == 16) {
                    //war.record.addfieldrecord();
                    war.record.addoneround();
                    /*for(int i = 0; i < 16; i++){
                        if(war.record.)
                    }*/
                    human.turns++;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Main.bf.show();
                        }
                    });
                    //Record.movementRecord.add(battlefield.field);
                    //Record.actionRecord.add(Record.oneround_action);
                    displaypostpone(1000);
                    do {
                        human.turn = (human.turn + 1) % 17;
                    } while (!war.characters[human.turn].isalive());
                    try {
                        human.lock.notifyAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    war.record.clearround();
                } else {
                    try {
                        human.lock.wait();
                        //System.out.println(this.name + ":this is not my turn, my id is:" + this.id);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //displaypostpone(6000);
        }
    }

    private void displaypostpone(int ms){
        try{
            TimeUnit.MILLISECONDS.sleep(ms);
        }catch (InterruptedException e){
            System.out.println(e.toString());
        }
    }

}
