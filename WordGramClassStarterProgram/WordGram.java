
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(int i=0; i < myWords.length; i++) {
            ret+=myWords[i];
            ret+=" ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }

    public void shiftAdd(String word) { 
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        for (int i=0; i < myWords.length-1; i++) {
            myWords[i] = myWords[i+1];
        }
        myWords[myWords.length - 1] = word;
    }

}