
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    private int indexOf(String[] words, WordGram target, int start) {
       for (int i=start; i < words.length; i++) {
           if (words[i].equals(target.toString())) {
               return i;
            }
        }
       return -1;
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        WordGram wg = new WordGram(myText, index, 1);
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
            wg = new WordGram(myText, index, 1);
        }
        return sb.toString().trim();
    }
    /*public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String mtext[] = text.split("\\s+");
        System.out.println(indexOf(mtext, "this", 0));
        System.out.println(indexOf(mtext, "this", 3));
        System.out.println(indexOf(mtext, "frog", 5));
    }*/
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = indexOf(myText, kGram, 0);
        while (idx != -1 && idx+1 != myText.length) {
            idx++;
            follows.add(myText[idx]);
            idx = indexOf(myText, kGram, idx);
        }
        return follows;
    }

}
