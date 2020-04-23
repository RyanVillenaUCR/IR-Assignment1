import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ExerciseB {

    public ExerciseB() {
        
        stopwords = new HashSet<String>();
        stopwords.add("and");
        stopwords.add("or");
        stopwords.add("the");
        stopwords.add("is");
        stopwords.add("a");
        stopwords.add("in");
        stopwords.add("for");
        stopwords.add("");
        
        documents = new ArrayList<String>();
        documents.add("Basketball is a sport played in many countries."
                    + "NBA is a basketball league.");
        documents.add("NBA is a basketball tournament in the US and other countries.");
        documents.add("Basketball is a sport played in many countries."
                    + "NBA is a basketball league."
                    + "Click here for more.");
        documents.add("Tropical fish include fish found in tropical environments around the world, "
                    + "including both freshwater and salt water species.");
    }
    
    private byte asciiHash(String str) {
        
        byte sum = 0;
        
        for (int i = 0; i < str.length(); i++)
            sum += (byte) str.charAt(i);

        return (byte) Math.floorMod((int) sum, 256);
    }
    
    /*
     * s 115    01110011
     * a 097    01100001
     * l 108    01101100
     * t 116    01110100
     *   436    10110100
     *   
     * slides   10110101
     * is the example wrong...?
     */
    
    /*
     * b 098    01100010
     * o 111    01101111
     * t 116    01110100
     * h 104    01101000
     * 
     *   429    10101101
     * slides   10101110
     */
    
    /*
     * f 102    01100110
     * i 105    01101001
     * s 115    01110011    
     * h 104    01101000
     *   426    10101010
     *   
     * slides   10101011
     */
    
    /*
     * t 116
     * r 114
     * o 111
     * p 112
     * i 105
     * c 099
     * a 097
     * l 108
     *   862    01011110
     *   
     * slides   01100001
     *  
     */
    
    private Map<String, Integer> getFrequencies(String document) {
        
        Map<String, Integer> freqs = new HashMap<String, Integer>();
        String[] words = document.split("[ \\p{Punct}]");    // Remove spaces and punctuation
        
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }
        
        for (String word : words) {
            
            if (!stopwords.contains(word)) {
                
                freqs.put(word, freqs.getOrDefault(word, 0) + 1);
            }
        }
        
        return freqs;
    }
    
    private boolean getBit(byte b, int posFromLeft) {
        
        return getBit(Byte.toUnsignedInt(b), posFromLeft);
    }
    
    private boolean getBit(int i, int posFromLeft) {
        
        i = i >> (7 - posFromLeft);
        return i % 2 == 1;
    }
    
    private String byteToBinaryString(byte b) {
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++)
            sb.append(getBit(b, i) ? '1' : '0');
        
        return sb.toString();
    }
    
    private byte booleanArrToByte(boolean[] arr) {
        
        int x = 0;
        for (int i = 0; i < 8; i++)
            x += arr[i] ? Math.pow(2, i) : 0;
            
        return (byte) x;
    }
    
    public byte simHash(String document) {
        
        Map<String, Integer> freqs = getFrequencies(document);
        int[] sumOfHashes = new int[8];
        
        for (Map.Entry<String, Integer> str : freqs.entrySet()) {
            
            byte hash = asciiHash(str.getKey());
            
            // Do this for every occurrence of str
            for (int i = 0; i < str.getValue(); i++) {
                
                // Iterate bit by bit
                for (int bit = 0; bit < 8; bit++) {
                    
                    sumOfHashes[bit] += getBit(hash, bit) ? 1 : -1;
                }
            }
        }
        
        boolean[] weightSum = new boolean[8];
        for (int bit = 0; bit < 8; bit++) {
            
            weightSum[bit] = sumOfHashes[bit] > 0;
        }
        
        
        
        return booleanArrToByte(weightSum);
    }
    
    public void testStuff() {
        
        System.out.println(byteToBinaryString(simHash(documents.get(3))));
    }
    
    private final Set<String> stopwords;
    private final List<String> documents;
}
