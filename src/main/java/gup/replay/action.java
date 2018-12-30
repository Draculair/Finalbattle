package gup.replay;

import gup.character.human;

import java.io.Serializable;

public class action implements Serializable {
    private human myself;
    private human target;
    private int damage;
    private distance dis;
    private class distance implements Serializable{
        double x;
        double y;
    }
    public action(){
        myself = null;
        target = null;
        damage = 0;
        dis = new distance();
    }
    public action(human myself, human target, int damage){
        this.myself = myself;
        this.target = target;
        this.damage = damage;
        dis = new distance();
        dis.x = myself.getlocationx() - target.getlocationx();
        dis.y = myself.getlocationy() - target.getlocationy();
    }

    public void setTarget(human people)
    {
        target = people;
    }
    public void setDamage(int n)
    {
        damage = n;
    }
    public void setDistance(human myself){
        dis.x = myself.getlocationx() - target.getlocationx();
        dis.y = myself.getlocationy() - target.getlocationy();
    }

    public human getMyself(){return myself;}
    public double getdisx(){
        return dis.x;
    }
    public double getdisy(){
        return dis.y;
    }

    public boolean isActioned(){
        /*if(target == null)
            return false;
        else
            return true;*/
        return target != null;
    }
}
