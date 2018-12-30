package gup.character.stggc;

import gup.battle.battlefield;
import gup.character.*;
import gup.character.university.shimada_alice;
import gup.character.university.shimada_chiyo;
import gup.character.university.university_student;
import gup.colour.*;
import gup.battle.war;

import java.net.URL;


public class stggc_teammember extends human implements formation
{
    private colours colour;
    public stggc_teammember(java.lang.String name, char code, colours colour, int atk, int hp, int id)
    {
        this.name = name;
        this.code = code;
        this.colour = colour;
        this.atk = atk;
        this.hp = hp;
        this.alive = true;
        won = false;
        this.id = id;
        location = new Location(0,0);
        filepath = "picture/"+id+".jpg";
        //filepath = this.getClass().getClassLoader().getResource("resource/picture/"+id+".jpg");//有待修改
        //location.x = location.y = 0;
    }

    @Override
    protected void executeround(/*battlefield field*/)
    {
        if(alive) {
            double smallest_distance = 616;
            boolean findenemy = false;
            Location nearest_enemy_loc = new Location(0, 0);
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    if (battlefield.field[i][j].get_is_occupied()) {
                        if (shimada_alice.class.isInstance(battlefield.field[i][j].gethuman()) || shimada_chiyo.class.isInstance(battlefield.field[i][j].gethuman()) || university_student.class.isInstance(battlefield.field[i][j].gethuman())){
                            if(battlefield.field[i][j].gethuman().isalive()) {
                                double distance = Math.sqrt(Math.pow(location.x - i, 2) + Math.pow(location.y - j, 2));
                                if (distance < smallest_distance) {
                                    findenemy = true;
                                    smallest_distance = distance;
                                    nearest_enemy_loc.x = i;
                                    nearest_enemy_loc.y = j;
                                }
                            }
                        }
                    }
                }
            }
            if (findenemy) {
                action(smallest_distance, nearest_enemy_loc);
                //System.out.println(this.name+"find university");
            }
            else{
                this.won = true;
                war.finished = true;
                System.out.println("stggc win");
            }
            /*boolean flag = true;
            for (int i = 0; i < 16; i++) {
                if (shimada_chiyo.class.isInstance(war.characters[i]) || shimada_alice.class.isInstance(war.characters[i]) || university_student.class.isInstance(war.characters[i])) {
                    if (war.characters[i].isalive())
                        flag = false;
                }
            }
            if (flag)
            {
                this.won = true;
                war.finished = true;
                System.out.println("stggc win");
            }*/
        }
    }

    public void snaketype(int x, int y, int num, human character[]) {
        //battlefield.field[x][y].setcharacter(this);
        for (int i = 0; i < num; i++) {
            battlefield.field[x + i][y].setcharacter(character[i]);
            Location newlocation = new Location(x+i,y);
            character[i].move(newlocation);
            //character[i].Location.y = y;
        }
        //formation.i = 1;
    }
    public void snaketype(int x, int y, int num, human leader, human character[]){};
    public void cranewingtype(int x, int y, int num, human leader, human character[]){};
    public void gooseflyingtype(int x, int y, int num, human leader, human character[]){};
    public void horizontalwheeltype(int x, int y, int num, human leader, human character[]){};
    public void arrowtype(int x, int y, int num, human leader, human character[]){};
    /*@Override
    protected void executeround()
    {
        if(alive) {
            double smallest_distance = 616;
            boolean findenemy = false;
            Location nearest_enemy_loc = new Location(0, 0);
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    if(battlefield.field[i][j].get_is_occupied()) {
                        if (shimada_alice.class.isInstance(battlefield.field[i][j].gethuman()) || shimada_chiyo.class.isInstance(battlefield.field[i][j].gethuman()) || university_student.class.isInstance(battlefield.field[i][j].gethuman())) {
                            double distance = Math.sqrt(Math.pow(location.x - i, 2) + Math.pow(location.y - j, 2));
                            if (distance < smallest_distance) {
                                findenemy = true;
                                smallest_distance = distance;
                                nearest_enemy_loc.x = i;
                                nearest_enemy_loc.y = j;
                            }
                        }
                    }
                }
            }
            if(findenemy) {
                action(smallest_distance, nearest_enemy_loc);
            }
        }
    }*/

    /*private TEAM ;
    enum SGTTC{
        darjeeling("darjeeling", colours.red);// assam

        private java.lang.String name;
        private colours colour;

        TEAM(java.lang.String name, colours colour)
        {
            this.colour = colour;
            this.name = name;
        }
    }*/
    /*private TEAM team;
    enum TEAM{
        //FIRST("老大", colours.red), SECOND("老二", colours.orange), THIRD("老三", "黄色"),
       //FORTH("老四", "绿色"), FIFTH("老五","青色"), SIXTH("老六", "蓝色"), SEVENTH("老七", "紫色");
        darjeeling("darjeeling", colours.red), assam("assam", colours.yellow),
        orange_pekoe("orange pekoe", colours.orange), rose_hip("rose hip", colours.green),
        rukuriri("rukuriri", colours.cyan), hurb("hurb", colours.blue),
        cranberry("cranberry", colours.purple);

        private java.lang.String name;
        private colours colour;

        TEAM(java.lang.String name, colours colour)
        {
            this.colour = colour;
            this.name = name;
        }
    }*/
}
