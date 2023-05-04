package batch;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dto.CustomerDTO;
import dto.DBConnectDTO;

public abstract class BatchService {

	private static final int BATCH_SIZE = 100;

	private final DBConnectDTO dbConnectDTO;

	public BatchService(DBConnectDTO dbConnectDTO) {
		this.dbConnectDTO = dbConnectDTO;
	}

	public void insertDataToDBUsingCSV(String sql, List<CustomerDTO> lists) {
		try (Connection con = connectingDB(dbConnectDTO.getURL(), dbConnectDTO.getUSER(), dbConnectDTO.getPASSWORD());
			 PreparedStatement pstmt = con.prepareStatement(sql)) {

			batchInsertDataToDB(pstmt, lists);

			con.commit();
		} catch (SQLException | FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void batchInsertDataToDB(PreparedStatement pstmt, List<CustomerDTO> lists) throws SQLException {
		long count = 0;
		long total = lists.size() - 1;

		for (CustomerDTO dto : lists) {
			Map<Integer, String> data = dto.data;

			for (int key = 1; key <= data.size(); key++) {
				if (key == 1) pstmt.setLong(key, Long.parseLong(data.get(key)));
				else pstmt.setString(key, data.get(key));
			}

			pstmt.addBatch();

			executeBatchWhenFull(pstmt, count, total);
			count++;
		}
	}

	private void executeBatchWhenFull(PreparedStatement pstmt, long count, long total) throws SQLException {
		if (count % BATCH_SIZE == 0 || count == total) {
			pstmt.executeBatch();
			pstmt.clearBatch();
		}
	}

	private Connection connectingDB(String url, String user, String pw) throws SQLException, FileNotFoundException {
		Connection con = DriverManager.getConnection(url, user, pw);
		con.setAutoCommit(false);
		return con;
	}
}
