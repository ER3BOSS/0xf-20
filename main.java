import com.oracle.tools.packager.IOUtils;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class main {
    static private List<String> wordlist = new ArrayList<>();
    static private String target = "4167f8c00b5eac0c81cf8828e54f4bfd";
    public static void main(String args[]){
        try {
            //do everything in here
            readFile();
            for (int i = 0; i < wordlist.size(); i++){
                for (int j = 0; j < wordlist.size(); j++){
                    if (getStringHash(wordlist.get(i)+wordlist.get(j)).equals(target)){
                        System.out.println("Match found: "+wordlist.get(i)+wordlist.get(j));
                        System.out.println("Trial number: "+ ((i*wordlist.size())+(j+1)));
                        return;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    private static void readFile() throws IOException, NoSuchAlgorithmException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("wordlist.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // process the line.
            wordlist.add(line);
        }
    }
    private static String getStringHash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(input.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hashString.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return (hashString.toString());
    }
}
