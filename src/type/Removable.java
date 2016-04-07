package type;

@SuppressWarnings("rawtypes")
public class Removable implements Comparable{
	private String symbol;
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof RoadBlock)
			return -1;
		return 0;
	}
	protected void setSymbol(String symbol){
		this.symbol=symbol;
	}
	
	public String getSymbol(){
		return symbol;
	}
}
