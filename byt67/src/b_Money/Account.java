package b_Money;

import java.util.Hashtable;

public class Account {
	private Money content;
	private Hashtable<String, TimedPayment> timedpayments = new Hashtable<String, TimedPayment>();
	private String account_name; //to assist with testing as seen below
	
	Account(String name, Currency currency) {
		account_name=name;
		this.content = new Money(0, currency);
	}
	
	public String getAccount_Name() {
		//to get the name of account to test
		return account_name;
	}
	
	/**
	 * Add a timed payment
	 * @param id Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param tobank Bank where receiving account resides
	 * @param toaccount Id of receiving account
	 */
	
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank tobank, String toaccount) {
		if(!timedPaymentExists(id)) {
			TimedPayment tp = new TimedPayment(interval, next, amount, this, tobank, toaccount);
			timedpayments.put(id, tp);
		}else {
		System.err.println("Timed payement which you re trying to add already exists"+ id + "id of payment");
			   }
		}
	
	/**
	 * Remove a timed payment
	 * @param id Id of timed payment to remove
	 */

	public void removeTimedPayment(String id) {
		if(timedPaymentExists(id)) {
			timedpayments.remove(id);
		}else {
			System.err.println("Timed payment does not exists"+ id);
	          }
	}
	
	/**
	 * Check if a timed payment exists
	 * @param id Id of timed payment to check for
	 */
	public boolean timedPaymentExists(String id) {
		return timedpayments.containsKey(id);
	}

	/**
	 * A time unit passes in the system
	 */

	public void tick() {
		for (TimedPayment tp : timedpayments.values()) {
			 tp.tick(); 
		}
	}
	
	/**
	 * Deposit money to the account
	 * @param money Money to deposit.
	 */
	public void deposit(Money money) {
		content = content.add(money);
	}
	
	/**
	 * Withdraw money from the account
	 * @param money Money to withdraw.
	 */
	
	public void withdraw(Money money) {
		//to withdraw a certain amount successfully, one cannot obviously withdraw more money than there's ones account
		content = content.sub(money);
	}

	/**
	 * Get balance of account
	 * @return Amount of Money currently on account
	 */

	public Money getBalance() {
		return content;
	}


	private class TimedPayment {
		//TimedPayment
		private int interval, next;
		private Account fromaccount;
		private Money amount;
		private Bank tobank;
		private String toaccount;
		
		TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount) {
			this.interval = interval;
			this.next = next;
			this.amount = amount;
			this.fromaccount = fromaccount;
			this.tobank = tobank;
			this.toaccount = toaccount;
		}

		
		public Boolean tick() {
			//transfer initiated?
			if (next == 0) {
				next = interval;

				fromaccount.withdraw(amount);
				try {
					tobank.deposit(toaccount, amount);
				}
				catch (AccountDoesNotExistException e) {
					//AccountDoesNotExistException send out a notification
					fromaccount.deposit(amount);
				}
				return true;
			}
			else {
				next--;
				return false;
			}
		}
	}

}