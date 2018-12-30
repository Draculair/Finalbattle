package gup.character.university;

import gup.battle.battlefield;
import gup.character.*;
import gup.character.stggc.stggc_student;
import gup.character.stggc.stggc_teammember;
import gup.battle.war;

import java.net.URL;

public class university_student extends human implements formation
{
    public university_student(){}
    public university_student(int id, java.lang.String name)
    {
        this.name = name;
        this.code = 'U';
        atk = 3;
        hp = 15;
        alive = true;
        won = false;
        this.id = id;
        location = new Location(0,0);
        filepath = "picture/"+id+".jpg";
        //filepath = this.getClass().getClassLoader().getResource("resource/picture/"+id+".jpg");
        //filepath = path;
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

            }*/
        }
    }
    private void clear()
    {
        for(int i = 0; i < 17; i++)
            for(int j = 0; j < 17; j++)
                if(battlefield.field[i][j].get_is_occupied())
                    if(university_student.class.isInstance(battlefield.field[i][j].gethuman()))
                        battlefield.field[i][j].setfree();
    }

    public void snaketype(int x, int y, int num, human character[]){};
    public void snaketype(int x, int y, int num, human leader, human character[])
    {
        clear();
        battlefield.field[x][y].setcharacter(leader);
        Location newlocation = new Location(x,y);
        leader.move(newlocation);
        for (int i = 1; i < num; i++) {
            battlefield.field[x + i][y].setcharacter(character[i - 1]);
            newlocation = new Location(x+i,y);
            character[i - 1].move(newlocation);
        }
    }

    public void cranewingtype(int x, int y, int num, human leader, human character[])//x = 10,y = 13
    {
        /*for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }*/
        Location newlocation;
        clear();
        for(int i = 0; i < num/2; i++)//x = 7
        {
            battlefield.field[x - 3 + i][y - 3 + i].setcharacter(character[i]);// = university_members[i - 7];
            newlocation = new Location(x+i,y);
            character[i].move(newlocation);
            //field[i][i + 3].is_occupied = true;
        }
        for(int i = 0; i < num/2; i++)
        {
            battlefield.field[x - i - 1][y + i + 1].setcharacter(character[num/2 + i]);// = university_members[i - 11];
            newlocation = new Location(x+i,y);
            character[num/2 + i].move(newlocation);
            //field[23 - i][i].is_occupied = true;
        }
        battlefield.field[x][y].setcharacter(leader);// = alice;
        //field[10][13].is_occupied = true;

    }

    public void gooseflyingtype(int x, int y, int num, human leader, human character[])//(11,10)
    {
        /*for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }*/
        clear();
        Location newlocation = new Location(x,y);
        battlefield.field[x][y].setcharacter(leader);//is_occupied = true;
        leader.move(newlocation);
        //field[11][10].people = alice;
        for(int i = 0; i < num - 1; i++)
        {
            battlefield.field[x + 1 + i][y - 1 - i].setcharacter(character[i]);//is_occupied = true;
            newlocation = new Location(x+i,y);
            character[i].move(newlocation);
            //field[21 - i][i].people = university_members[i - 11];
        }
    }

    public void horizontalwheeltype(int x, int y, int num, human leader, human character[])//(5,15)
    {
        /*for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }*/
        clear();
        battlefield.field[x][y].setcharacter(leader);//is_occupied = true;
        Location newlocation = new Location(x,y);
        leader.move(newlocation);
        //field[5][15].people = alice;
        for(int i = 0; i < num - 1; i+=2)
        {
            battlefield.field[x + 2 + i][y].setcharacter(character[i]);//is_occupied = true;
            newlocation = new Location(x+i,y);
            character[i].move(newlocation);
            //field[i][15].people = university_members[i - 7];
        }
        for(int i = 1; i < num - 1; i+=2)
        {
            battlefield.field[x + i][y + 1].setcharacter(character[i]);//is_occupied = true;
            newlocation = new Location(x+i,y);
            character[i - 1].move(newlocation);
            //field[i][16].people = university_members[i - 5];
        }
    }

    public void arrowtype(int x, int y, int num, human leader, human character[])//(9,15)
    {
        /*for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }*/
        clear();
        battlefield.field[x][y].setcharacter(leader);//is_occupied = true;
        Location newlocation = new Location(x,y);
        leader.move(newlocation);
        //field[6][14].people = alice;
        battlefield.field[x + 1][y - 1].setcharacter(character[0]);//is_occupied = true;
        newlocation = new Location(x+1,y-1);
        character[0].move(newlocation);
        //field[7][13].people = university_members[0];
        battlefield.field[x + 1][y + 1].setcharacter(character[1]);//is_occupied = true;
        newlocation = new Location(x+1,y+1);
        character[1].move(newlocation);
        //field[7][15].people = university_members[1];
        battlefield.field[x + 2][y - 2].setcharacter(character[2]);//is_occupied = true;
        newlocation = new Location(x+2,y-2);
        character[2].move(newlocation);
        //field[8][12].people = university_members[2];
        battlefield.field[x + 2][y + 2].setcharacter(character[3]);//is_occupied = true;
        newlocation = new Location(x+2,y+2);
        character[3].move(newlocation);
        //field[8][14].people = university_members[3];
        battlefield.field[x + 2][y].setcharacter(character[4]);//is_occupied = true;
        newlocation = new Location(x+2,y);
        character[4].move(newlocation);
        //field[8][16].people = university_members[4];
        battlefield.field[x + 4][y].setcharacter(character[5]);//is_occupied = true;
        newlocation = new Location(x+4,y);
        character[5].move(newlocation);
        //field[9][14].people = university_members[5];*/
        /*for(int i = 1; i <= num; i++)
        {
            for(int j = 1; j < i; j++)
            battlefield.field[x]
        }*/

    }

}
