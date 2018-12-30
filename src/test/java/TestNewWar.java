import gup.battle.war;
import org.junit.Assert;
import org.junit.Test;

public class TestNewWar {
    @Test
    public void testNewWar(){
        war thewar = new war();
        thewar.initialgame();
        int sum = 0;
        for(int i = 0; i < thewar.characters.length; i++)
            if(thewar.characters[i].isalive())
                sum++;
        Assert.assertEquals(thewar.characters.length, sum);
    }
}
