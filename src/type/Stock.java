package type;

public enum Stock {
	GOOGLE(200, 0), BAIDU(200, 0), YAHOO(200, 0), DAX(200, 0), ALI(200, 0), TENCENT(
			200, 0), FACEBOOK(200, 0), LENOVO(200, 0), BILIBILI(200, 0), VALVE(
			200, 0);
	private int price;
	private double riseAllFall;

	Stock() {

	}

	Stock(int price, double riseAllFall) {

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getRiseAllFall() {
		return riseAllFall;
	}

	public void setRiseAllFall(double riseAllFall) {
		this.riseAllFall = riseAllFall;
	}

	public void change() {
		int tmp = (int) (Math.random() * 2001) - 1000;
		this.riseAllFall = (double) tmp / 10000;
		price=(int) (price*(1+this.riseAllFall));
	}

	public String getDescription() {
		return this.name() + "\t\t" + this.price + "\t\t"
				+ (this.riseAllFall > 0 ? "+" : "") + this.riseAllFall;
	}
}
