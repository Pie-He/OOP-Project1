package util;

public enum Const {
	//common
	CASH_NOT_ENOUGH("现金不足!"), 
	DEPO_NOT_ENOUGH("存款余额不足!"), 
	MONEY_NOT_ENOUGH("金钱不足！"),
	SUCCESS("成功!"), 
	BUY_OR_NOT("是否需要购买？"),
	COUPON_NOT_ENOUGH("点券不足！"),
	GAME_OVER("游戏结束！"),
	WIN("获胜！"),
	FAIL("失败!"),
	READY("请准备好！"),
	
	//house	
	HOUSE_NOT_OWNER("(可供出售状态)"),
	HOUSE_MAX_LEVEL("已最高级！"),
	HOUSE_LEVELUP_OR_NOT("是否升级？"),
	HOUSE_CASH_NOT_ENOUGH("现金不足，银行存款抵扣！"),
	HOUSE_DEPO_NOT_ENOUGH("银行存款不足，变卖土地抵扣！"),
	
	//lottery
	LOTTERY_BUT_OR_NOT("是否要支付2000元买彩票？"), 
	LOTTERY_SUCCESS("恭喜中奖！"),
	
	//news
	NEWS_DEPO_PRIZE("银行加发储金红利每个人得到存款10%"),
	NEWS_DEPO_FINE("所有人缴纳财产税10%"),
	NEWS_CARD_PRIZE("每个人得到一张卡片"),
	NEWS_MAXHOUSE("公开表扬第一地主! "),
	NEWS_MINHOUSE("公开补助土地最少者 "),
	
	//time
	TIME_DEPO_PRIZE("月底发放储金利息！！！"),
	TIME_INTERESTS("获得利息"),
	TIME_BANK_CLOSE("周末休市！"),
	
	//block
	BLOCK_NO("前方无路障！"),
	BLOCK_EXSITED("该位置已放置路障"),
	
	//search
	SEARCH("请输入您想查询的点与您相差的步数(后方用负数表示，x-退出)"),
	
	//prop
	PROP_NO_PROP("无道具!"),
	PROP_AVERAGE("所有人现金平分使用"),
	PROP_DEPO_FINE("缴交30%存款"),
	PROP_CHOOSE("请输入前后方8格内的数字(负数表示后方,x-取消)"),
	
	//stock
	STOCK_AMOUNT_ERROR("股票数输入有误！！！"),
	;
	private String message;

	Const(String str) {
		this.message = str;
	}

	public String toString() {
		return this.message;
	}
}
