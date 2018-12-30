package gup.replay;

import gup.character.human;

import java.io.Serializable;



public class Movement implements Serializable {

    enum state{alive, dying, dead};

    private human myself;
    private int x;
    private int y;
    state isAlive;

    public Movement() {
        myself = null;
        y = 0;
        x = 0;
        isAlive = state.alive;
    }

    public Movement(int x, int y, human myself) {
        this.myself = myself;
        this.x = x;
        this.y = y;
        isAlive = state.alive;
    }

    public void setMovement(int x, int y, human myself) {
        this.myself = myself;
        this.x = x;
        this.y = y;
    }

    public human getMyself(){
        return myself;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setDead(){
        isAlive = state.dying;
    }
    public boolean isDying(){
        return (isAlive == state.dying);

    }
}
