package batch;

import java.util.List;

import dto.CustomerDTO;
import dto.DBConnectDTO;

public class BatchServiceDeco extends BatchService {

	public BatchServiceDeco(DBConnectDTO dbConnectDTO) {
		super(dbConnectDTO);
	}

	@Override
	public void insertDataToDBUsingCSV(String sql, List<CustomerDTO> lists) {
		long curLong = System.nanoTime();

		super.insertDataToDBUsingCSV(sql, lists);

		long endLong = System.nanoTime();
		System.out.println(((endLong - curLong) / 1000000000) + " 초 걸림");
	}
}
