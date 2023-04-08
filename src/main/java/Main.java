import batch.FrontController;

public class Main {
	public static void main(String[] args) {
		FrontController batch = new FrontController();

		// String filePath = args[0];
		batch.startBatch("organizations-100.csv");
	}
}
