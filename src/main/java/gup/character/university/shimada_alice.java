package gup.character.university;

import gup.battle.battlefield;
import gup.character.human;
import gup.character.stggc.stggc_teammember;

import java.net.URL;

public class shimada_alice extends university_student
{
    public shimada_alice(int id)
    {
        this.name = "alice";
        this.code = 'E';
        this.atk = 10;
        this.id = id;
        hp = 24;
        alive = true;
        won = false;
        filepath = "picture/"+id+".jpg";
        //filepath = this.getClass().getClassLoader().getResource("resource/picture/"+id+".jpg");
        //filepath = path;
        location = new Location(0,0);
    }
    /*@Override
    protected void executeround()
    {
        if(alive) {
            double smallest_distance = 616;
            Location nearest_enemy_loc = new Location(0, 0);
            boolean findenemy = false;
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    if(battlefield.field[i][j].get_is_occupied()) {
                        if (stggc_teammember.class.isInstance(battlefield.field[i][j].gethuman())) {
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
}