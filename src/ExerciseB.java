import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
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
        documents.add(DOCUMENT_0);
        documents.add(DOCUMENT_1);
        documents.add(DOCUMENT_2);
        documents.add(DOCUMENT_3);
    }
    
    private byte asciiHash(String str) {
        
        int sum = 0;
        
        for (int i = 0; i < str.length(); i++)
            sum += str.charAt(i);

        return (byte) Math.floorMod(sum, MOD_VALUE);
    }
    
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
//            System.out.println("Hashed \"" + str.getKey() + "\" to "
//                    + byteToBinaryString(hash));
            
            // Do this for every occurrence of str
            for (int i = 0; i < str.getValue(); i++) {
                
                // Iterate bit by bit
                for (int bit = 0; bit < 8; bit++) {
                    
                    sumOfHashes[bit] += getBit(hash, bit) ? 1 : -1;
                }
            }
        }
        
        boolean[] weightSum = new boolean[8];
        
        for (int bit = 0; bit < 8; bit++)
            
            weightSum[7 - bit] = sumOfHashes[bit] > 0;
        
        
        
        return booleanArrToByte(weightSum);
    }
    
    public int hammingDistance(byte b1, byte b2) {
        
        String str1 = byteToBinaryString(b1);
        String str2 = byteToBinaryString(b2);
        int sum = 0;
        
        for (int i = 0; i < str1.length(); i++) {
            
            sum += str1.charAt(i) == str2.charAt(i) ? 0 : 1;
        }
        
        return sum;
    }
    
    public void testStuff() {
        
//        for (String document : documents) {
//            
//            System.out.print("Simhash(\"" + document + "\"): ");
//            System.out.println(byteToBinaryString(simHash(document)));
//        }
        
        List<Byte> simHashes = new ArrayList<Byte>();
        for (String document : documents)
            simHashes.add(simHash(document));
        
        int lo_bound = 0;
        int hi_bound = 3;
        
        for (int i = lo_bound; i <= hi_bound - 1; i++) {
            for (int j = i + 1; j <= hi_bound; j++) {
                
                System.out.println("SimHash(D" + i + "): " + byteToBinaryString(simHashes.get(i)));
                System.out.println("SimHash(D" + j + "): " + byteToBinaryString(simHashes.get(j)));
                
                System.out.println("hammingDist(D" + i + ", D" + j + "): "
                                  + hammingDistance(simHashes.get(i), simHashes.get(j)) + "\n");
            }
        }
    }
    
    private final Set<String> stopwords;
    private final List<String> documents;
    
    private final int MOD_VALUE = 255; // 255 or 256
    
    private final String DOCUMENT_0 = "Tropical fish include fish found "
                                    + "in tropical environments around the world, "
                                    + "including both freshwater and salt water species.";
    private final String DOCUMENT_1 = "Basketball is a sport played in many countries. "
                                    + "NBA is a basketball league.";
    private final String DOCUMENT_2 = "NBA is a basketball tournament "
                                    + "in the US and other countries.";
    private final String DOCUMENT_3 = "Basketball is a sport played in many countries. "
                                    + "NBA is a basketball league. "
                                    + "Click here for more.";
}
