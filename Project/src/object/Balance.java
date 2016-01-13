package object;

public class Balance {
	private double amount;
	private long lastWorked;
	
	public Balance(){
		amount = 0;
		lastWorked = 0;
	}
	
	public String earnMoney(long time){
		if(canWork(time)){
			amount += 5.0;
			lastWorked = time;
			return "did some work at the library and earned $5";
		}
		return "can not do a double shift! Wait until the first job is done!";
	}
	
	public boolean canWork(long time){
		if(((time - lastWorked)/1000) >= 10){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void subtractLateFees(int timeOverdue){
		if(timeOverdue > 1) amount = (amount - timeOverdue/1000);
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}

	public double getAmount() {
		return (Math.round(amount * 100) / 100);
	}

	public long getLastWorked() {
		return lastWorked;
	}
}
