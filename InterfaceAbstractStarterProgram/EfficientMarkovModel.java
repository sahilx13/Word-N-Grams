
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String, ArrayList<String>> hmap; 
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
        hmap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    public String toString() {
        return ("MarkovModel of order "+N);
    }
    public void buildMap() {
      int pos1 = 0;
      String k = myText;
      while(pos1 != myText.length()-N+1) {
            k = myText;
            String sub = k.substring(pos1, pos1+N);
            if (! hmap.containsKey(sub)) {
                ArrayList<String> follows = new ArrayList<String>();
                int pos = 0;
                while(k.indexOf(sub) >= 0 && k.indexOf(sub)+sub.length() < k.length()) {
                    int index = k.indexOf(sub);
                    follows.add(k.substring(index+sub.length(),index+sub.length()+1));
                    pos = index+1;
                    k = k.substring(pos);
                }
                hmap.put(sub, follows);
            }
            pos1++;
      }
      printHashMapInfo();
     // System.out.println(hmap);
    }
    public void printHashMapInfo() {
        System.out.println("hashmap : "+hmap);
        System.out.println("Keys in the hashMap : "+hmap.size());
        int max = -1;
        String day = "";
        for (String key : hmap.keySet()) {
             if (hmap.get(key).size() > max) {
                max = hmap.get(key).size();
             }
        }
        System.out.println("Largest value in HashMap : "+max);
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : hmap.keySet()) {
            if (hmap.get(key).size() == max) {
                keys.add(key);
            }
        }
        System.out.println("Keys having the max counts : "+keys);
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
            ArrayList<String> follows = hmap.get(key);
            //System.out.println("Key "+ key + " "+ follows);
            if (follows.size() <= 0) {
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
