package batch;

public class BatchServiceProxy extends BatchService {

	public BatchServiceProxy(String url, String user, String pw) {
		super(url, user, pw);
	}

	@Override
	public void insertDataToDBUsingCSV(String sql, String filePath) {
		long curLong = System.nanoTime();

		super.insertDataToDBUsingCSV(sql, filePath);

		long endLong = System.nanoTime();
		System.out.println(((endLong - curLong) / 1000000000) + " 초 걸림");
	}
}
