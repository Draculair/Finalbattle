package gup.character.stggc;

import gup.battle.battlefield;
import gup.character.cheer;
import gup.character.human;
import gup.character.university.shimada_alice;
import gup.character.university.shimada_chiyo;
import gup.character.university.university_student;
import gup.battle.war;

import java.net.URL;
//import gup.cheer;

public class stggc_student extends human implements cheer
{
    public stggc_student(java.lang.String name, char code, int atk, int hp, int id)
    {
        this.name = name;
        this.code = code;
        this.atk = atk;
        this.hp = hp;
        this.id = id;
        this.alive = true;
        location = new Location(0,0);
        filepath = "picture/"+id+".jpg";//this.getClass().getClassLoader().getResource("resource/picture/"+id+".jpg");
        //filepath = path;
    }
    //@Override
    public void cheer(battlefield field, int x, int y, human character)
    {
        battlefield.field[x][y].setcharacter(character);
        Location newlocation = new Location(x,y);
        character.move(newlocation);
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
    //public stggc_student(){};
    //public stggc_teammember(){};
}