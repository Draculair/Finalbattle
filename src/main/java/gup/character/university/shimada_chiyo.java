package gup.character.university;

import gup.battle.battlefield;
import gup.character.human;
import gup.character.stggc.*;//stggc_teammember;
import gup.battle.war;

import java.net.URL;

public class shimada_chiyo extends human
{
    public shimada_chiyo(int id)
    {
        this.name = "chiyo";
        this.code = 'Y';
        atk = 10;
        hp = 20;
        this.id = id;
        alive = true;
        won = false;
        filepath = "picture/"+id+".jpg";
        // filepath = this.getClass().getClassLoader().getResource("picture/"+id+".jpg");
        //filepath = path;
        location = new Location(0,0);
    }
    public void cheer(battlefield field, int x, int y, human character)
    {
        battlefield.field[x][y].setcharacter(character);
        Location newlocation = new Location(x,y);
        character.move(newlocation);
    }

    @Override
    protected void executeround()
    {
        if(alive) {
            double smallest_distance = 616;
            Location nearest_enemy_loc = new Location(0, 0);
            boolean findenemy = false;
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    if(battlefield.field[i][j].get_is_occupied()) {
                        if (stggc_teammember.class.isInstance(battlefield.field[i][j].gethuman()) || stggc_student.class.isInstance(battlefield.field[i][j].gethuman())) {
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
            if(findenemy) {
                action(smallest_distance, nearest_enemy_loc);
                //System.out.println("find stggc");
            }
            else{
                this.won = true;
                war.finished = true;
                System.out.println("university win");
            }
            /*boolean flag = true;
            for (int i = 0; i < 16; i++) {
                if (stggc_student.class.isInstance(war.characters[i]) || stggc_teammember.class.isInstance(war.characters[i])) {
                    if (war.characters[i].isalive())
                        flag = false;
                }
            }
            if (flag)
            {
                war.finished = true;
                this.won = true;
                System.out.println("university win");
            }*/
        }
    }
}
