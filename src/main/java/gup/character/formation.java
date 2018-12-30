package gup.character;

import gup.battle.battlefield;

public interface formation //extends human
{
    //int i=0;
    void snaketype(int x, int y, int num, human character[]);
    void snaketype(int x, int y, int num,  human leader, human character[]);
    void cranewingtype(int x, int y, int num,  human leader, human character[]);
    void gooseflyingtype(int x, int y, int num, human leader, human character[]);
    void horizontalwheeltype(int x, int y, int num,  human leader, human character[]);
    void arrowtype(int x, int y, int num, human leader, human character[]);
}

