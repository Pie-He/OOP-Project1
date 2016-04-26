package type;

import util.IO;
import util.Tools;

public enum Stock {
	GOOGLE(200, 0), BAIDU(200, 0), YAHOO(200, 0), DAX(200, 0), ALI(200, 0), TENCENT(
			200, 0), FACEBOOK(200, 0), LENOVO(200, 0), BILIBILI(200, 0), VALVE(
			200, 0);
	private int price;
	private double riseAllFall;

	Stock() {

	}

	Stock(int price, double riseAllFall) {
		this.price = price;
		this.riseAllFall = riseAllFall;
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
		price = (int) (price * (1 + this.riseAllFall));
	}

	public String getDescription() {
		String ra = new java.text.DecimalFormat("0.00%")
				.format(this.riseAllFall);
		ra = (this.riseAllFall > 0 ? "+" : "") + ra;
		return Tools.stringCover(16, this.name(), this.price + "", ra);
	}

	public void buyStock(Player player, int amount) {
		int total = amount * this.price;
		if (player.addDeposit(-total)) {
			player.addStock(this, amount);
			return;
		}
		total -= player.getDeposit();
		player.setDeposit(0);
		if (player.addCash(-total)) {
			player.addStock(this, amount);
			return;
		}
		IO.printString("金钱不够!!!");
	}

	public void sellStock(Player player, int amount) {
		int total = amount * this.price;
		if (!player.removeStock(this, amount)) {
			IO.printString("股票数输入有误！！！");
		}
		player.addDeposit(total);
	}
}
