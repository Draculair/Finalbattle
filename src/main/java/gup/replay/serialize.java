package gup.replay;
import gup.battle.unit;
import gup.battle.war;
import gup.character.human;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;

public interface serialize {
    public static void save(String path){
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(new File(path+"/Record.txt")));
            //Record temp = new Record();
            /*for(int k = 0; k < war.record.getmovementRecord().size(); k++){
                unit[][] field = war.record.getmovementRecord().get(k);
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
            System.out.println(human.turns);
            System.out.println(war.record.getactionRecord().size());
            out.writeObject(war.record);
            System.out.println("Record save success");
            out.close();
        }   catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public static Record load(File file) {
        Record temp = new Record();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            temp = (Record) in.readObject();
            System.out.println("Record load success");
        }   catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return temp;
        /*catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }*/
    }
}



/*public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        SerializePerson();//序列化Person对象
        Person p = DeserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                p.getName(), p.getAge(), p.getSex()));
    }

    private static void SerializePerson() throws FileNotFoundException,
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    private static Person DeserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("E:/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}*/