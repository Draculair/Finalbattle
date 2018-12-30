package gup.replay;

import gup.battle.battlefield;
import gup.battle.unit;
import gup.battle.war;

import java.io.*;
import java.util.ArrayList;

public class Record implements Serializable{
    private static final long serialVersionUID = -8138164729988926805L;
    private ArrayList<Movement[]> movementRecord;// = new ArrayList<>();
    private ArrayList<action[]> actionRecord;// = new ArrayList<>();
    //public static ArrayList<action> oneround_action = new ArrayList<>();
    private action[] oneround_action = new action[16];
    private Movement[] oneround_movement = new Movement[16];
    public Record(){
        movementRecord = new ArrayList<>();
        actionRecord = new ArrayList<>();
        movementRecord.ensureCapacity(60);
        actionRecord.ensureCapacity(60);
        for(int i = 0; i < 16; i++)
        {
            oneround_action[i] = new action();
            oneround_movement[i] = new Movement();
        }
        //oneround_action.ensureCapacity(16);
    }

    public void addoneround(){
        actionRecord.add(war.record.oneround_action);
        movementRecord.add(war.record.oneround_movement);
    }

    public action[] getOneround_action(){
        return oneround_action;
    }
    public Movement[] getOneround_movement(){
        return oneround_movement;
    }
    public void clearround(){
        oneround_movement = new Movement[16];
        oneround_action = new action[16];
        for(int i = 0; i < 16; i++) {
            oneround_action[i] = new action();
            oneround_movement[i] = new Movement();
        }
    }

    public ArrayList<action[]> getactionRecord(){
        return actionRecord;
    }
    public ArrayList<Movement[]> getmovementRecord(){
        return movementRecord;
    }
    /*public void save() throws FileNotFoundException, IOException {
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(new File("D:/大学资料/大三上/java/project/Finalwar/record.txt")));
        record temp = new record();
        out.writeObject(temp);
        System.out.println("record序列化成功");
        out.close();
    }
    public void load() throws Exception, IOException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("D:/大学资料/大三上/java/project/Finalwar" +
                "/record.txt")));
        record temp = (record)in.readObject();
        System.out.println("record反序列化成功");
    }*/

    /*public void addfieldrecord(){
        unit[][] field = battlefield.field;
        movementRecord.add(field);
        //unit[][] field = war.record.getmovementRecord().get(war.record.getmovementRecord().size() - 1);
        for(int i = 0; i < 17; i++)
            for(int j = 0; j < 17; j++)
                if(!field[i][j].get_is_occupied() && j != 16)
                    System.out.print(' ');
                else if(!field[i][j].get_is_occupied() && j == 16)
                    System.out.println(' ');
                else if(field[i][j].get_is_occupied() && j != 16)
                    System.out.print(field[i][j].gethuman().getcode());
                else
                    System.out.println(field[i][j].gethuman().getcode());
    }*/
}

