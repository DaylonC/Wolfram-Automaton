import java.util.Arrays;

/**
 * This class creates a single generation for an automaton.
 * I had the help of Baraa Ahmad to complete my constructors.
 * 
 * @author Daylon Carse
 * @version 0.1
 */
public class Generation {

	private boolean[] cellStates;
	
	/**
	 * This constructs a generation based off of a boolean array of 
	 * true or false states.
	 * 
	 * @param states	A boolean array that has true and false states.
	 */
	public Generation(boolean... states) {
		
		if (states == null || states.length == 0) {
			 cellStates = new boolean[] {false};
			 return;
		}
	
		cellStates = new boolean[states.length];

		for (int i = 0; i<states.length; i++) {
			cellStates[i] = states[i];
		
		}
		
	}
	
	/**
	 * This constructs a generation based off a String of states and 
	 * the given character to resemble true.
	 * 
	 * @param states		A string of states for a generation.
	 * @param trueSymbol	A char to represent all true symbols.
	 */
	public Generation(String states, char trueSymbol) {

		if (states == null || states == "") {
			cellStates = new boolean[] {false};
			return;
		}
		
		
			cellStates = new boolean[states.length()];
			
			for (int i = 0; i<states.length(); i++) {
		
				
				if (states.charAt(i) == trueSymbol) {
					cellStates[i] = true;
				}
				
				else {
					cellStates[i] = false;
				}
				
			}
		
		}
	
	/**
	 * Gets a state of a generation at a given index.
	 * 
	 * @param idx	The index to get the state at.
	 * @return		Whether the state is true or false.
	 */
	public boolean getState(int idx) {
		
		if (cellStates[idx] == true) {
			return true;
		}
		
			else {
		return false;
			}
	}
	
	/**
	 * Gets all states of a generation.
	 * 
	 * @return	A boolean array of all true and false states.
	 */
	public boolean[] getStates() {
		
		
		return Arrays.copyOf(cellStates, cellStates.length);
	}
	
	/**
	 * Gets the states of a generation with set true and false symbols.
	 * 
	 * @param falseSymbol		A char to represent false.
	 * @param trueSymbol		A char to represent true.
	 * @return					A String of the states with the given 
	 * 							symbols.
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		
		StringBuilder end = new StringBuilder(cellStates.length);
		
		for (int i = 0; i<size(); i++) {
			
			if (cellStates[i] == true) {
				end.append(trueSymbol);
			}
			
			else {
				end.append(falseSymbol);
			}
		}
	
		return end.toString();
	
	}
	
	/**
	 * Gets the size of the generation.
	 * 
	 * @return	The size of the generation.
	 */
	public int size() {
		
		return cellStates.length;
	}
	
}