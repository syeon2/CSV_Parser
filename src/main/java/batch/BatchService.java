package batch;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import parser.CSVParser;

public abstract class BatchService {

	private static final int batchSize = 100;

	private final String url;
	private final String user;
	private final String pw;

	public BatchService(String url, String user, String pw) {
		this.url = url;
		this.user = user;
		this.pw = pw;
	}

	public void insertDataToDBUsingCSV(String sql, String filePath) {
		try (Connection con = connectingDB(url, user, pw);
			 PreparedStatement pstmt = con.prepareStatement(sql)) {

			CSVParser csvParser = new CSVParser();
			List<List<String>> lists = csvParser.readCSVFile(filePath);

			batchInsertDataToDB(pstmt, lists);

			con.commit();
		} catch (SQLException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void batchInsertDataToDB(PreparedStatement pstmt, List<List<String>> lists) throws SQLException {
		for (int i = 1; i < lists.size(); i++) {
			List<String> item = lists.get(i);

			pstmt.setLong(1, Long.parseLong(item.get(0)));
			pstmt.setString(2, item.get(1));
			pstmt.setString(3, item.get(2));
			pstmt.setString(4, item.get(3));
			pstmt.setString(5, item.get(4));
			pstmt.setString(6, item.get(5));
			pstmt.setString(7, item.get(6));
			pstmt.setString(8, item.get(7));
			pstmt.setString(9, item.get(8));
			pstmt.addBatch();

			if (i % batchSize == 0 || i == lists.size() - 1) {
				pstmt.executeBatch();
				pstmt.clearBatch();
			}
		}
	}

	private Connection connectingDB(String url, String user, String pw) throws SQLException, FileNotFoundException {
		Connection con = DriverManager.getConnection(url, user, pw);
		con.setAutoCommit(false);
		return con;
	}
}
