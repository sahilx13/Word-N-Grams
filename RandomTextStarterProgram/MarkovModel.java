import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
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
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        //myText = "this is a test yes this is a test.";
        String k = myText;
        while(k.indexOf(key) >= 0 && k.indexOf(key) != k.length()-1) { 
            int index = k.indexOf(key);
            follows.add(k.substring(index+key.length(),index+key.length()+1));
            pos = index+1;
            k = k.substring(pos);
        }
        //System.out.println(follows);
        return follows;
    }
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        System.out.println(getFollows("t").size());
    }
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        System.out.println(markov.getFollows("t").size());
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

