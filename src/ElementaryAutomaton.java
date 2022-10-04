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



	public class ElementaryAutomaton extends Automaton {


	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList<Generation>();
	public char falseSymbol;
	public char trueSymbol;
	public final char FALSE_INT = '0';
	public final char TRUE_INT = '1';
	private int numSteps = 0;
	
	
	public ElementaryAutomaton(int ruleNum, Generation initial) throws RuleNumException {
		
		super(ruleNum, initial);
		
		}
	
	
	public ElementaryAutomaton(String filename) throws IOException , RuleNumException {
		super(filename);
		
	}
	
	@Override
	protected Rule createRule(int ruleNum) throws RuleNumException {
		
		
		try {
			rule = new ElementaryRule(ruleNum);
			return rule;
		} catch (RuleNumException e) {
			
			e.printStackTrace();
		}
		
		
		
		return createRule(ruleNum);
	}
	
	
}
