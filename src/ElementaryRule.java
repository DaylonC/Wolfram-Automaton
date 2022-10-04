import java.util.Arrays;
import java.util.StringJoiner;

public class ElementaryRule extends Rule {

	
	private static boolean[] neighborhood;
	private String binaryNum;
	private final int NEIGHBORHOOD_SIZE = 3;
	private final int BINARY_LENGTH = 8;
	private final char TRUE = '1';
	private final char FALSE = '0';
	
	
	public ElementaryRule(int ruleNum) throws RuleNumException {
		
		super(ruleNum);
		
		if (ruleNum > 255 || ruleNum < 0) {
			throw new RuleNumException(0, 255);
		}
	
		this.binaryNum = Integer.toBinaryString(ruleNum);
	
	}
	
	public boolean evolve(boolean[] neighborhood) {
		
		String evolved = "";
		
		for (int i = 0; i < neighborhood.length; ++i) {
			
			if (neighborhood[i]) {
				evolved += TRUE;
			}
			else {
				evolved += FALSE;
			}
		}
	
	return switchCheck(evolved);
	
	}

	public boolean[] getNeighborhood(int idx, Generation gen) {
		
		boolean[] ngh = new boolean[NEIGHBORHOOD_SIZE];
		
		if (gen.size() == 1) {
			Arrays.fill(ngh, gen.getState(idx));
		}
		
		else {
		
		if (idx == 0) {
			ngh[0] = gen.getState(gen.size() - 1);
			ngh[1] = gen.getState(idx);
			ngh[2] = gen.getState(idx + 1);
			
			}
				
		else if (idx == gen.size() - 1) {
			ngh[0] = gen.getState(idx-1);
			ngh[1] = gen.getState(idx);
			ngh[2] = gen.getState(0);
		}
		
		else  {
			ngh[0] = gen.getState(idx-1);
			ngh[1] = gen.getState(idx);
			ngh[2] = gen.getState(idx+1);
		}
		}
		neighborhood = Arrays.copyOf(ngh, ngh.length);
		
	return neighborhood;
	
	}
	
	public String getPadBinary(String binaryNum) {
		
		String padded = "";
		int padNum = BINARY_LENGTH - binaryNum.length();
		
		for (int i = 0; i < BINARY_LENGTH; ++i) {
			
			 if (i < padNum) 
			 {
				 padded += FALSE;
			 }
			
			 else 
			 {
				 padded += binaryNum.charAt(i - padNum);
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

	
	public String getRuleTable(char falseSymbol, char trueSymbol) {
		
		
		StringJoiner join = new StringJoiner(System.lineSeparator());
		Generation gen = new Generation(getPadBinary(binaryNum), '0');
		
		char f = falseSymbol;
		char t = trueSymbol;
		
		String str1 = t + "" + t + "" + t + " ";
		String str2 = t + "" + t + "" + f + " ";
		String str3 = t + "" + f + "" + t + " ";
		String str4 = t + "" + f + "" + f + " ";
		String str5 = f + "" + t + "" + t + " ";
		String str6 = f + "" + t + "" + f + " ";
		String str7 = f + "" + f + "" + t + " ";
		String str8 = f + "" + f + "" + f + "";
				
		String firstLine = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8;
		
		join.add(firstLine);
		
		String states = gen.getStates(trueSymbol, falseSymbol);
		
		char ch1 = states.charAt(0);
		char ch2 = states.charAt(1);
		char ch3 = states.charAt(2);
		char ch4 = states.charAt(3);
		char ch5 = states.charAt(4);
		char ch6 = states.charAt(5);
		char ch7 = states.charAt(6);
		char ch8 = states.charAt(7);
		
		String secondLine = " " + ch1 + "   " + ch2 + "   " + ch3 + "   " + ch4 +
				"   " + ch5 + "   " + ch6 + "   " + ch7 + "   " + ch8 + " ";
		
		join.add(secondLine);
	
		
		return join.toString();
	}
	
	
}	

