package gup.battle;
import gup.replay.action;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;

public class battlefield
{
    public static unit[][] field;// = new unit[17][17];
    public static unit[][] replayfield;
    //public static ArrayList<ArrayList<unit>> field;
    public battlefield()
    {
        field = new unit[17][17];
        for (int i = 0; i < 17; i++)
            for (int j = 0; j < 17; j++)
                field[i][j] = new unit();
        replayfield = new unit[17][17];
        for (int i = 0; i < 17; i++)
            for (int j = 0; j < 17; j++)
                replayfield[i][j] = new unit();
        /*field = new ArrayList<ArrayList<unit>>();
        for (int i = 0; i < 17 ; i++) {
            field.add(new ArrayList<unit>()); //增加一行
            for (int j = 0; j < 17 ; j++) { //增加一列
                field.get(i).add(new unit());
            }
        }*/
    }

    public static GridPane gridPane;
    public static BorderPane borderPane;
    /*public static Canvas fieldcanvas;
    public static GraphicsContext gc;     //2D画布
    public static double canvasHeight;   //画布的高度
    public static double canvasWidth;    //画布的宽度*/
    public static double unitsize;       //单位宽度

    public static void setGridPane(GridPane gridpane){
        gridPane = gridpane;
        unitsize = 30;
    }

    /*public static void setBackground(BorderPane pane)
    {
        borderPane = pane;
        //ImageView imageView = new ImageView(new Image("picture/background.jpg"));
        //borderPane.setCenter(imageView);
        BackgroundImage image =
                new BackgroundImage(new Image(getClass().getResourceAsStream("picture/background.jpg"),917,616,false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(image));

    }*/

    /*public static void setcanvas(Canvas canvas)
    {
        fieldcanvas = canvas;
        gc = fieldcanvas.getGraphicsContext2D();
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        //unitsize = canvasHeight/17;
    }*/

    public Image getimage(String path){
        return new Image(getClass().getResourceAsStream(path));
    }

    public synchronized void show()
    {

        //trajectory.setGraphic(new ImageView(image));
        //gc.clearRect(0,0,canvasWidth,canvasHeight);//每次刷新时删除
        gridPane.getChildren().clear();
        for (int i = 0; i < 17 ; i++) {
            for (int j = 0; j < 17; j++) {
                //T tmp = maps.get(i).get(j).getContent();
                if (field[i][j].get_is_occupied()) {
                    //System.out.println(field[i][j].gethuman().getpath().toString());
                    //gc.drawImage(new Image(field[i][j].gethuman().getpath()), j * unitsize, i * unitsize, unitsize,unitsize);
                    ImageView imageView = new ImageView();
                    //imageView = new ImageView(new Image(field[i][j].gethuman().getpath()));
                    if(!field[i][j].gethuman().isalive()) {
                        imageView = new ImageView(new Image(getClass().getResourceAsStream("/picture/flag.jpg")));
                        //imageView = new ImageView(new Image(field[i][j].gethuman().getpath()));
                    }
                    else{
                        //imageView = new ImageView(new Image("picture/flag.jpg"));
                        imageView =
                                new ImageView(new Image(getClass().getResourceAsStream("/"+field[i][j].gethuman().getpath())));
                    }
                    gridPane.add(imageView, j, i);
                }
            }
        }
        /*borderPane.setCenter(trajectory);
        trajectory.setLayoutX(war.characters[0].getlocationx() * unitsize);
        trajectory.setLayoutY(war.characters[0].getlocationy() * unitsize);*/
        for (int i = 0; i < war.record.getOneround_action().length; i++){
            if(war.record.getOneround_action()[i].isActioned()){
                //gridPane.getChildren().clear();
                Image image = new Image(getClass().getResourceAsStream("/picture/timg.jpg"));
                ImageView trajectory = new ImageView(image);
                //gc.drawImage(new Image("picture/test.jpg"), war.characters[i].getlocationx() * unitsize,war.characters[i].getPriority() * unitsize, unitsize, unitsize);
                gridPane.add(trajectory, war.characters[i].getlocationy(),war.characters[i].getlocationx());
                /*trajectory.setLayoutX(war.characters[i].getlocationx() * unitsize);
                trajectory.setLayoutY(war.characters[i].getlocationy() * unitsize);
                trajectory.setTranslateX(trajectory.getTranslateX() - unitsize);*/
                gridPane.setHalignment(trajectory, HPos.CENTER);
                gridPane.setValignment(trajectory, VPos.CENTER);
                TranslateTransition tt = new TranslateTransition(Duration.millis(600), trajectory);
                tt.setToX(-war.record.getOneround_action()[i].getdisy() * (unitsize+10));
                tt.setToY(-war.record.getOneround_action()[i].getdisx() * (unitsize+10));
                tt.play();
                //gridPane.clearConstraints(trajectory);
            }
        }
    }

    /*public static void checkdead(){
        for(int i = 0; i < 17; i++)
            for(int j = 0; j < 17; j++){
                if(field[i][j].is_occupied)
                    if(!field[i][j].gethuman().isalive())
                        field[i][j].
            }
    }*/


    /*public static void print()
    {
        for(int i = 0; i < 17; i++)
            for(int j = 0; j < 17; j++)
                if(!field[i][j].is_occupied && j != 16)
                    System.out.print(' ');
                else if(!field[i][j].is_occupied && j == 16)
                    System.out.println(' ');
                else if(field[i][j].is_occupied && j != 16)
                    System.out.print(field[i][j].people.getcode());
                else
                    System.out.println(field[i][j].people.getcode());
    }*/

    //unit[][] oldfield = new unit[17][17];
    /*private stggc_teammember[] stggc_members;
    private stggc_student oral_gray;// = new stggc_teammember();
    private shimada_alice alice;// = new shimada_alice();
    private shimada_chiyo kasam;// = new shimada_chiyo();
    private university_student[] university_members;

    public void restart()
    {
        for (int i = 0; i < 17; i++)
            for (int j = 0; j < 17; j++)
                field[i][j].is_occupied = false;
        for(int i = 0; i < 17; i++)
            System.out.print('-');
        System.out.println(' ');
    }

    public unit[][] getfield()
    {
        return field;
    }*/

    //field = new human
    /*public battlefield()
    {
        alice = new shimada_alice();
        kasam = new shimada_chiyo();
        oral_gray = new stggc_student("oral gray", 'G');


        /*for (int i = 0; i < 17; i++)
            for (int j = 0; j < 17; j++)
                oldfield[i][j] = new unit();*/

        //stggc_members = new stggc_teammember[7];
        //stggc_members[0].no = 1;
        /*for (int i = 0; i < 7; i++)
            stggc_members[i] = new stggc_teammember();

        stggc_members[0].name = "darjeeling";
        stggc_members[0].colour = colours.red;
        stggc_members[1].name = "assam";
        stggc_members[1].colour = colours.orange;
        stggc_members[2].name = "orange pekoe";
        stggc_members[2].colour = colours.yellow;
        stggc_members[3].name = "rose hip";
        stggc_members[3].colour = colours.green;
        stggc_members[4].name = "rukuriri";
        stggc_members[4].colour = colours.cyan;
        stggc_members[5].name = "herb";
        stggc_members[5].colour = colours.blue;
        stggc_members[6].name = "cranberry";
        stggc_members[6].colour = colours.purple;
        stggc_members[0].code = 'D';
        stggc_members[1].code = 'A';
        stggc_members[2].code = 'O';
        stggc_members[3].code = 'R';
        stggc_members[4].code = 'K';
        stggc_members[5].code = 'H';
        stggc_members[6].code = 'C';*/
        /*stggc_members[0] = new stggc_teammember("darjeeling", 'D', colours.red);
        stggc_members[1] = new stggc_teammember("orange pekoe", 'O', colours.orange);
        stggc_members[2] = new stggc_teammember("assam", 'A', colours.yellow);
        stggc_members[3] = new stggc_teammember("rose hip", 'R', colours.green);
        stggc_members[4] = new stggc_teammember("rukuriri", 'K', colours.cyan);
        stggc_members[5] = new stggc_teammember("herb", 'H', colours.blue);
        stggc_members[6] = new stggc_teammember("cranberry", 'C', colours.purple);*/
        /*alice.name = "alice";
        alice.code = 'E';
        kasam.name = "chiyo";
        kasam.code = 'Y';
        oral_gray.name = "oral gray";
        oral_gray.code = 'G';*/
        /*university_members = new university_student[6];
        for(int i = 0; i < 6; i++)
            university_members[i] = new university_student();
    }*/



    /*void cheer()
    {
        field[1][0].is_occupied = true;
        field[1][0].people = oral_gray;
        field[1][16].is_occupied = true;
        field[1][16].people = kasam;
    }*/

    //变换阵型之前要先记录当前阵型
    //this has been abandoned in the second time to modify

}
