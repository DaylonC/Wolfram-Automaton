import java.util.Arrays;
import java.util.StringJoiner;

public class TotalisticRule extends Rule {
	
	
	private static boolean[] neighborhood;
	private String binaryNum;
	private final int BINARY_LENGTH = 6;
	private final int TRUE_AMOUNTS = 5;
	private final char TRUE = '1';
	private final char FALSE = '0';
	
	
	public TotalisticRule(int ruleNum) throws RuleNumException {
		super(ruleNum);
		
		if (ruleNum > 63 || ruleNum < 0) {
			throw new RuleNumException(0, 63);
		}
		
	this.binaryNum = Integer.toBinaryString(ruleNum);
	
	}

	
	public boolean evolve(boolean[] neighborhood) {
		
		int t = 0;
		
		String num = getPadBinary(binaryNum);
		
		for (int i = 0; i < neighborhood.length; i++) {
			if (neighborhood[i] == true) {
				t++;
			}
		
		}
		
		int checkAt = TRUE_AMOUNTS - t;
		
		char tf = num.charAt(checkAt);
		
		if (tf == FALSE) {
			return false;
		}
		
		else {
			return true;
		}
		
	}

	public boolean[] getNeighborhood(int idx, Generation gen) {
		
		boolean[] ngh = new boolean[5];
		
		if (gen.size() == 1) {
			
			Arrays.fill(ngh, gen.getState(idx));
			
		}
		
		else if (gen.size() == 2) {
			
			if (idx == 0) {
				ngh[0] = gen.getState(idx);
				ngh[1] = gen.getState(idx + 1);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx + 1);
				ngh[4] = gen.getState(idx);
			}
			else {
				ngh[0] = gen.getState(idx);
				ngh[1] = gen.getState(idx - 1);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx - 1);
				ngh[4] = gen.getState(idx);
			}
			
		}
		
		else if (gen.size() == 4) {
			
			if (idx == 0) {
				ngh[0] = gen.getState(idx + 2);
				ngh[1] = gen.getState(idx + 3);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx + 1);
				ngh[4] = gen.getState(idx + 2);
				
			}
			
			else if (idx == 1) {
				ngh[0] = gen.getState(idx + 2);
				ngh[1] = gen.getState(idx - 1);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx + 1);
				ngh[4] = gen.getState(idx + 2);
			}
			
			else if (idx == 2) {
				ngh[0] = gen.getState(idx - 2);
				ngh[1] = gen.getState(idx - 1);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx + 1);
				ngh[4] = gen.getState(idx - 2);
			}
			
			else {
				ngh[0] = gen.getState(idx - 2);
				ngh[1] = gen.getState(idx - 1);
				ngh[2] = gen.getState(idx);
				ngh[3] = gen.getState(idx - 3);
				ngh[4] = gen.getState(idx - 2);
			}
			
		}
		
		else {
		
		if (idx == 0) {
			ngh[0] = gen.getState(gen.size() - 2);
			ngh[1] = gen.getState(gen.size() - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(idx + 2);
			}
				
		else if (idx == 1) {
			ngh[0] = gen.getState(gen.size() - 1);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(idx + 2);
		}
		
		else if (idx == 2) {
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(idx + 2);
		}
		else if (idx == 3 && gen.size() < 7) {
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(1);
		}
		else if (idx == 4 && gen.size() < 7){
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx - 3);
			ngh[4] = gen.getState(idx - 4);
		}
		else if (idx == 5 && gen.size() < 7){
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx - 5);
			ngh[4] = gen.getState(idx - 4);
		}
		else if (idx == gen.size() - 2){
			
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(0);
			
		}
		
		else if (idx == gen.size() - 1){
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(0);
			ngh[4] = gen.getState(1);
		}
		else {
			ngh[0] = gen.getState(idx - 2);
			ngh[1] = gen.getState(idx - 1);
			ngh[2] = gen.getState(idx);
			ngh[3] = gen.getState(idx + 1);
			ngh[4] = gen.getState(idx + 2);
		
		}
		
		neighborhood = Arrays.copyOf(ngh, ngh.length);
		
		}
		
	return ngh;
		
	}
	
	public String getPadBinary(String binaryNum) {
		
		String padded = "";
		
		int amount = BINARY_LENGTH - binaryNum.length();
		
		for (int i = 0; i < BINARY_LENGTH; ++i) {
			
			 if (i < amount) 
			 {
				 padded += 0;
			 }
			
			 else 
			 {
				 padded += binaryNum.charAt(i - amount);
			 }
		}
			return padded;
	}	
	
	
	public String neighborhoodToString(boolean[] neighborhood) {
		
		StringBuffer sb = new StringBuffer();
		
	      for(int i = 0; i < neighborhood.length; i++) {
	    	  
	         sb.append(neighborhood[i]);
	      }
	      
	      String str = sb.toString();
		
		return str;
	}
	
	public boolean switchCheck(String neighborhood) {
		
		String testNum = getPadBinary(binaryNum);
		
		switch(neighborhood) {
		
		case "111":
			if (testNum.charAt(0) == TRUE) {
				return true;
			}
			else {
				return false;
			}
		
		case "110":
			if (testNum.charAt(1) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "101":
			if (testNum.charAt(2) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "100":
			if (testNum.charAt(3) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "011":
			if (testNum.charAt(4) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "010":
			if (testNum.charAt(5) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "001":
			if (testNum.charAt(6) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
		case "000":
			if (testNum.charAt(7) == TRUE) {
				return true;
			}
			else {
				return false;
			}
			
			default: 
				return false;
		}
		
		
	}

	// I used StackOverflow to get help on adding spaces between characters.
	@Override
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		
		StringJoiner join = new StringJoiner(System.lineSeparator());
		Generation gen = new Generation(getPadBinary(binaryNum), '0');
		
		char f = falseSymbol;
		char t = trueSymbol;
		
		String padded = "";
		int amount = BINARY_LENGTH - binaryNum.length();
		
		for (int i = 0; i < BINARY_LENGTH; ++i) {
			
			 if (i < amount) 
			 {
				 padded += 0;
			 }
			
			 else 
			 {
				 padded += binaryNum.charAt(i - amount);
			 }
			
		}
		
		String firstLine = "5 4 3 2 1 0";
		String secondLine = "";
		
		join.add(firstLine);
	
		
		for (int i = 0; i < padded.length(); i++) {
			
			if (padded.charAt(i) == '0') {
				
				secondLine += falseSymbol;
				
			}

			else {
				secondLine += trueSymbol;
			}
		}
		
		StringBuilder result = new StringBuilder();

		for(int i = 0 ; i < secondLine.length(); i++)
		{
		   result = result.append(secondLine.charAt(i));
		   
		   if(i == secondLine.length()-1)
		      break;
		   result = result.append(' ');
		}
		
		join.add(result);
		
		return join.toString();
	}

}	
