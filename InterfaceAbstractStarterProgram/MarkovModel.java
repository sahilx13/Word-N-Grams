
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel{
    private int N;
    public MarkovModel(int n) {
        myRandom = new Random();
        N = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    public String toString() {
        return ("MarkovModel of order "+N);
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        for (int k=0; k < numChars-N; k++) {
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key "+ key + " "+ follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
}


