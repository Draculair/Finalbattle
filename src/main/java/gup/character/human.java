package gup.character;

import gup.battle.battlefield;

import java.io.Serializable;
import java.net.URL;
import java.util.Random;
import gup.character.university.*;
import gup.battle.war;
import gup.replay.action;
import javafx.scene.image.Image;

import java.lang.Math;

//import static gup.replay.Record.oneround_action;


public class human extends Thread implements Serializable {
    protected class Location implements Serializable{
        public int x;
        public int y;

        public Location(int a, int b) {
            x = a;
            y = b;
        }

    }

    //public static boolean sbmove = false;
    protected java.lang.String name;
    protected char code;
    protected int id;
    protected int atk;
    protected int hp;
    protected boolean alive;
    protected boolean won;
    protected Location location;
    public static int turn = 0;
    protected String filepath;
    //Image image;
    public static int turns = 0;

    public human(){}

    public human(int id)
    {
        this.id = id;
        alive = true;
    }

    public void setFilepath(String newpath){
        filepath = newpath;
    }

    protected void executeround(/*battlefield field*/) {
    }

    public String getpath(){
        return this.filepath;}

    public final static Object lock = new Object();

    public void run()
    {
        synchronized (lock) {
            //System.out.println("i am:"+this.alive);
            while (this.alive && !won) {
                    //System.out.println(this.name+this.id);
                    //executeround();
                    if (this.id == turn) {
                        this.executeround();
                        war.record.getOneround_movement()[this.id].setMovement(this.location.x, this.location.y, this);
                        //System.out.println(this.name + this.location.x + "," + this.location.y);
                        do {
                            human.turn = (human.turn + 1) % 17;
                        } while (!war.characters[turn].alive);
                        //System.out.println("turn"+turn);
                        try {
                            lock.notifyAll();
                            //this.notify();
                            //System.out.println("i notify all");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            //System.out.println(this.name + ":this is not my turn, my id is:" + this.id);
                            //notifyAll();
                            lock.wait();
                            //System.out.println(this.name + ":this is not my turn, my id is:" + this.id);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //return;
                    }
                //System.out.println("1");
            }
        }
    }


    public void move(Location newlocation)
    {
        battlefield.field[this.location.x][this.location.y].setfree();
        this.location = newlocation;
        battlefield.field[this.location.x][this.location.y].setcharacter(this);
        //System.out.println(this.name+this.location.x+","+this.location.y);
    }

    /*public void repmove(int x, int y){
        battlefield.replayfield[this.location.x][this.location.y].setfree();
        this.location = newlocation;
        battlefield.replayfield[this.location.x][this.location.y].setcharacter(this);
    }*/

    private void attack(human enemy)
    {
        Random random = new Random();
        int damage = random.nextInt(this.atk);
        enemy.hp = enemy.hp-damage;
        System.out.println(this.name+" hit "+enemy.name+" damage is:"+damage+" and enemy hp is:"+enemy.hp+" my hp " +
                "is:"+this.hp);
        /*action temp = new action();
        temp.setDamage(damage);
        temp.setTarget(enemy);
        oneround_action.add(this.id, temp);*/
        //oneround_action.add(temp);
        /*war.record.getOneround_action()[this.id].setTarget(enemy);
        war.record.getOneround_action()[this.id].setDamage(damage);
        war.record.getOneround_action()[this.id].setDistance(this);*/
        war.record.getOneround_action()[this.id] = new action(this, enemy, damage);
        if(enemy.hp <= 0)
        {
            enemy.alive = false;
            war.record.getOneround_movement()[enemy.id].setDead();
            //enemy.setFilepath("picture/flag.jpg");
            //System.out.println(this.name+" kill "+enemy.name);
        }
    }

    protected void action(double smallest_distance, Location nearest_enemy_loc)
    {
        //sbmove = true;
        if (smallest_distance > 5) {
            //System.out.println("too far");
            if (location.x < nearest_enemy_loc.x) {
                if (!battlefield.field[location.x + 1][location.y].get_is_occupied())
                    move(new Location(location.x + 1, location.y));
                else if(battlefield.field[location.x + 1][location.y].gethuman() != null) {
                    if (!battlefield.field[location.x + 1][location.y].gethuman().alive)
                        move(new Location(location.x + 1, location.y));
                }
            }
            else if (location.x > nearest_enemy_loc.x) {
                if (!battlefield.field[location.x - 1][location.y].get_is_occupied())
                    move(new Location(location.x - 1, location.y));
                else if(battlefield.field[location.x + 1][location.y].gethuman() != null) {
                    if (!battlefield.field[location.x + 1][location.y].gethuman().alive)
                        move(new Location(location.x + 1, location.y));
                }
            }

            if (location.y < nearest_enemy_loc.y) {
                if (!battlefield.field[location.x][location.y + 1].get_is_occupied())
                    move(new Location(location.x, location.y + 1));
                else if(battlefield.field[location.x][location.y + 1].gethuman() != null){
                    if (!battlefield.field[location.x][location.y + 1].gethuman().alive)
                        move(new Location(location.x, location.y + 1));
                }
            }
            else if (location.y > nearest_enemy_loc.y) {
                if (!battlefield.field[location.x][location.y - 1].get_is_occupied())
                    move(new Location(location.x, location.y - 1));
                else if(battlefield.field[location.x][location.y - 1].gethuman() != null) {
                    if (!battlefield.field[location.x][location.y - 1].gethuman().alive)
                        move(new Location(location.x, location.y - 1));
                }
            }
        } else {
            //System.out.println("attack");
            human enemy = battlefield.field[nearest_enemy_loc.x][nearest_enemy_loc.y].gethuman();
            attack(enemy);
        }
        //war.record.getOneround_movement()[this.id].setMovement(this.location.x, this.location.y, this);
    }

    public char getcode()
    {
        return this.code;
    }

    public boolean isalive()
    {
        return this.alive;
    }

    public Location getLocation(){
        return location;
    }

    public int getlocationx(){
        return location.x;
    }

    public int getlocationy(){
        return location.y;
    }


    /*public void printfield()
    {
        for(int i = 0; i < 17; i++)
            for(int j = 0; j < 17; j++)
                if(!battlefield.field[i][j].is_occupied && j != 16)
                    System.out.print(' ');
                else if(!field[i][j].is_occupied && j == 16)
                    System.out.println(' ');
                else if(field[i][j].is_occupied && j != 16)
                    System.out.print(field[i][j].people.getcode());
                else
                    System.out.println(field[i][j].people.getcode());
    }*/
    //int no;
    //colours colour;
    //int row;
    //int col;
    /*void stggc_snaketype()
    {
        /*for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }
        for(int i = 5; i < 12; i++)
        {
            field[i][0].is_occupied = true;
            field[i][0].people = stggc_members[i - 5];
        }

        //field[0][1].people = stggc_members[0];
    }*/

    /*public void snaketype(int x, int y, int num, battlefield field, human character[])
    {
        for(int i = 0; i < 17; i++)
        {
            oldfield[i][0].is_occupied = field[i][0].is_occupied;
            oldfield[i][0].people = field[i][0].people;
        }
        field[5][16].is_occupied = true;
        field[5][16].people = alice;
        for(int i = 6; i < 12; i++)
        {
            field[i][16].is_occupied = true;
            field[i][16].people = university_members[i - 6];
        }
        battlefield.field[x][y].setcharacter(leader);
        for(int i = 0; i < num; i++) {
            battlefield.field[x + i][y].setcharacter(character[i]);
            battlefield.field[x][y + 1].setcharacter(character[i+1]);
            battlefield.field[x][y + 2].setcharacter(character[i2]);
            battlefield.field[x][y + 3].setcharacter(character[3]);
            battlefield.field[x][y + 4].setcharacter(character[4]);
            battlefield.field[x][y + 5].setcharacter(character[5]);
            battlefield.field[x][y + 6].setcharacter(character[6]);
        }
    }*/
}