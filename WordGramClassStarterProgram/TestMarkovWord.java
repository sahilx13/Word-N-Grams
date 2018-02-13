
/**
 * Write a description of TestMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestMarkovWord {
    public void testMarkovWord() {
        FileResource fr =new FileResource();
        String text = fr.asString();
        MarkovWord mw = new MarkovWord(3);
        mw.setRandom(643);
        mw.setTraining(text);
        
    }
}
