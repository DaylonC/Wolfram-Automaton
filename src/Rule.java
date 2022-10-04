
	public abstract class Rule {
	
	private int ruleNum;
	protected String binaryNum;
	
	
	protected Rule(int ruleNum) {
		
		this.ruleNum = ruleNum;
		this.binaryNum = Integer.toBinaryString(ruleNum);
	}
	
	public Generation evolve(Generation gen) {
		
		boolean[] sts = gen.getStates();
		boolean[] ret = new boolean[sts.length];
		
		for (int i = 0; i<gen.size(); i++) {
			
			ret[i] = evolve(getNeighborhood(i, gen));
		}
		
		
		
		return new Generation(ret);

	}
	
	abstract boolean evolve(boolean[] neighborhood);
		
	
	abstract boolean[] getNeighborhood(int idx, Generation gen);
	
	
	public int getRuleNum() {
		return ruleNum;
	}

	abstract String getRuleTable(char falseSymbol, char trueSymbol);
	
	
	
	
	
}