package gup.battle;

import gup.character.stggc.*;
import gup.character.human;
import gup.colour.colours;
import gup.character.university.*;
import gup.replay.Record;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class war {
    public static human[] characters = new human[17];
    public static boolean finished = false;
    public static Record record;
    private stggc_teammember[] stggc_members = new stggc_teammember[7];
    private stggc_teammember darjeeling = new stggc_teammember("darjeeling", 'D', colours.red, 9, 17, 0);
    private stggc_teammember orange_pekoe = new stggc_teammember("orange pekoe", 'O', colours.orange, 6,16, 1);
    private stggc_teammember assam= new stggc_teammember("assam", 'A', colours.yellow, 7,20, 2);
    private stggc_teammember rose_hip= new stggc_teammember("rose hip", 'R', colours.green,6,16, 3);
    private stggc_teammember rukuriri = new stggc_teammember("rukuriri", 'K', colours.cyan,3,15,4);
    private stggc_teammember herb = new stggc_teammember("herb", 'H', colours.blue,3,15, 5);
    private stggc_teammember cranberry = new stggc_teammember("cranberry", 'C', colours.purple,3,15, 6);
    private stggc_student oral_gray;
    private shimada_alice alice;
    private shimada_chiyo kasam;
    private university_student[] university_members = new university_student[6];
    private human referee;
    public void initialgame()
    {
        finished = false;
        record = new Record();
        battlefield bf = new battlefield();
        stggc_members[0] = darjeeling;//new stggc_teammember("darjeeling", 'D', colours.red);
        stggc_members[1] = orange_pekoe;//new stggc_teammember("orange pekoe", 'O', colours.orange);
        stggc_members[2] = assam;//new stggc_teammember("assam", 'A', colours.yellow);
        stggc_members[3] = rose_hip;//new stggc_teammember("rose hip", 'R', colours.green);
        stggc_members[4] = rukuriri;//new stggc_teammember("rukuriri", 'K', colours.cyan);
        stggc_members[5] = herb;//new stggc_teammember("herb", 'H', colours.blue);
        stggc_members[6] = cranberry;//new stggc_teammember("cranberry", 'C', colours.purple);
        for(int i = 0; i < 7; i++)
            characters[i] = stggc_members[i];
        oral_gray = new stggc_student("oral gray", 'G', 9,17, 7);
        characters[7] = oral_gray;
        alice = new shimada_alice(8);// = new shimada_alice();
        characters[8] = alice;
        kasam= new shimada_chiyo(9);
        characters[9] = kasam;

        for(int i = 0; i < 6; i++)
            university_members[i] = new university_student(i+10, "student"+i);
        for(int i = 10; i < 16; i++)
            characters[i] = university_members[i - 10];
        referee = new human(17);
        characters[16] = referee;
        darjeeling.snaketype(5,3,7, stggc_members);
        alice.arrowtype(6,12,7,alice,university_members);
        oral_gray.cheer(bf, 8,1,oral_gray);
        kasam.cheer(bf,8,16,kasam);
    }

    public void fight()
    {
        for(int i = 0; i < 16; i++)
            characters[i].start();
    }
}
