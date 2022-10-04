import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * This class creates an Automaton using the Generation and Rule classes.
 * This class was created with help by Chris White and Baraa Ahmad.
 * Chris and Ahmad helped to create the toString method and the 
 * Automaton String constructor. 
 * 
 * @author Daylon Carse
 * @version 0.1
 */
	public abstract class Automaton {


	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	public char falseSymbol;
	public char trueSymbol;
	public final char FALSE_INT = '0';
	public final char TRUE_INT = '1';
	private int numSteps = 0;
	
	
	protected Automaton(int ruleNum, Generation initial) throws RuleNumException {
	
		falseSymbol = FALSE_INT;
		trueSymbol = TRUE_INT;
		
		rule = createRule(ruleNum);
	
		generations.add(initial);
		
		}
	
	protected Automaton(String filename) throws IOException, NumberFormatException, RuleNumException {
		
		Scanner input = new Scanner(new File(filename));
		
		rule = createRule((Integer.parseInt(input.nextLine())));
		
		String[] tf = input.nextLine().split(" ");
		falseSymbol = tf[0].charAt(0);
		trueSymbol = tf[1].charAt(0);
		
		Generation genFile = new Generation(input.nextLine(), trueSymbol);
		generations.add(genFile);
		input.close();
		
	}
	
	protected abstract Rule createRule(int ruleNum) throws RuleNumException;
	
	public void evolve(int numSteps) {
		
		if (numSteps <= 0) {
			return;
		}
		else {
			
		for (int i = 0; i < numSteps; ++i) {
			generations.add(rule.evolve(generations.get((generations.size() -1))));
			
			}
		}
		this.numSteps = numSteps;
	}
	
	public Generation getGeneration(int stepNum) {
		
		if (stepNum > generations.size() - 1) {
			
			evolve(stepNum - getTotalSteps());
			
		}
		
		return generations.get(stepNum);
	}
	
	public int getRuleNum() {
		
		return this.rule.getRuleNum();
	}
	
	public int getTotalSteps() {
		
		return generations.size() - 1;
	}
	
	public void saveEvolution(String filename) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(toString());
		bw.close();
		
	}
	
	//Baraa Ahmad and Chris White helped me create this method.
	public String toString() {
		
		StringJoiner formatted = new StringJoiner(System.lineSeparator());
				
		for(int i = 0; i < generations.size(); ++i) {
			
			for (int j = 1; j < generations.get(i).getStates().length; ++j) {
				
				if (generations.get(i).getStates(falseSymbol, trueSymbol).charAt(j) == trueSymbol) 
					formatted.add(generations.get(i).getStates(falseSymbol, trueSymbol).toString());
				
				else 
					formatted.add(generations.get(i).getStates(falseSymbol, trueSymbol).toString());
					break;
				
			}
		}
		

		return formatted.toString();
	}

	public String getRuleTable() {
		
		return rule.getRuleTable(falseSymbol, trueSymbol);
	}

}
