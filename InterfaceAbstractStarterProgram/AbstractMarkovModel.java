
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        //myText = "this is a test yes this is a test.";
        String k = myText;
        while(k.indexOf(key) >= 0 && k.indexOf(key)+key.length() < k.length()-1) { 
            int index = k.indexOf(key);
            follows.add(k.substring(index+key.length(),index+key.length()+1));
            pos = index+1;
            k = k.substring(pos);
        }
        //System.out.println(follows);
        return follows;
    }
   
    abstract public String getRandomText(int numChars);

}
