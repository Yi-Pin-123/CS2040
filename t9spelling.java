import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStreamReader;

public class t9spelling
{
	public static void main(String[] args) {
		Map<Character, String> keypadDict = new HashMap<>();

        keypadDict.put('a', "2");
        keypadDict.put('b', "22");
        keypadDict.put('c', "222");
        keypadDict.put('d', "3");
        keypadDict.put('e', "33");
        keypadDict.put('f', "333");
        keypadDict.put('g', "4");
        keypadDict.put('h', "44");
        keypadDict.put('i', "444");
        keypadDict.put('j', "5");
        keypadDict.put('k', "55");
        keypadDict.put('l', "555");
        keypadDict.put('m', "6");
        keypadDict.put('n', "66");
        keypadDict.put('o', "666");
        keypadDict.put('p', "7");
        keypadDict.put('q', "77");
        keypadDict.put('r', "777");
        keypadDict.put('s', "7777");
        keypadDict.put('t', "8");
        keypadDict.put('u', "88");
        keypadDict.put('v', "888");
        keypadDict.put('w', "9");
        keypadDict.put('x', "99");
        keypadDict.put('y', "999");
        keypadDict.put('z', "9999");
        keypadDict.put(' ', "0");

		try {
    		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            int cases = Integer.parseInt(input.readLine());
            StringBuilder[] output = new StringBuilder[cases];
    		
    		
    		for (int i =0 ; i < cases ; i++){
    		    String str = input.readLine();
                StringBuilder answer = new StringBuilder("Case #" + (i + 1) + ": ");
    		    
    		    for (int j =0 ; j<str.length() ; j++ ){
    		        char alphabet = str.charAt(j);
    		        String keyPresses = keypadDict.get(alphabet);
    		        
                    
                    if (j < str.length() -1){
                        char checkA = keypadDict.get(str.charAt(j+1)).charAt(0);
                        char checkB = keyPresses.charAt(0);
                        if (checkA == checkB){
                            answer = answer.append(keyPresses);
                            answer = answer.append(" ");}
                        else{answer = answer.append(keyPresses);}
                    } else { answer = answer.append(keyPresses); }
    		    }
    		    
    		    output[i] = answer;
    		}
    		for (int i =0 ; i < cases ; i++){System.out.println(output[i]);} 
    		
    		    
		} catch(Exception e) {
		    System.err.println("An error occurred while trying to read input: " + e.getMessage());
        }
	}
}



