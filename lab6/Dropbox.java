public class Dropbox {
	private int number;
	private boolean evenNumber = false;
	private boolean available = false;

	public synchronized int take(final boolean even) {

		while(available == false) {
			try {
				wait();
			}
			catch (InterruptedException e) { }
		}
		if(even != evenNumber) {
			return 0;
		}
		System.out.format("%s CONSUMIDOR obter %d.%n", even ? "PAR" : "IMPAR", number);
		this.available = false;
		notifyAll();
		return number;
	}

	public synchronized void put(int number) {
		while(available == true) {
			try {
        wait();
			}
			catch (InterruptedException e) { }
		}
		this.number = number;
		this.available = true;
		evenNumber = number % 2 == 0;
		System.out.format("PRODUTOR gera %d.%n", number);
		notifyAll();
	}
}
