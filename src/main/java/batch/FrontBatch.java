package batch;

import static dto.CustomerDTO.*;

import java.util.List;

import dto.CustomerDTO;
import dto.DBConnectDTO;
import parser.CSVParser;
import parser.YmlParser;

public class FrontBatch {

	private final String YML_PATH = "application-db.yml";

	public void startBatch(String filePath) {
		DBConnectDTO dbConnectResource = getResourceForDBConnect();
		List<CustomerDTO> convertedCSV = CSVParser.getCsvList(filePath);

		String sql = "insert into customers "
			+ "(" + COLUMNS.get(1) + ", "
			+ COLUMNS.get(2) + ", "
			+ COLUMNS.get(3) + ", "
			+ COLUMNS.get(4) + ", "
			+ COLUMNS.get(5) + ", "
			+ COLUMNS.get(6) + ", "
			+ COLUMNS.get(7) + ", "
			+ COLUMNS.get(8) + ", "
			+ COLUMNS.get(9) + ") "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		BatchService batchService = new BatchServiceDeco(dbConnectResource);
		batchService.insertDataToDBUsingCSV(sql, convertedCSV);
	}

	private DBConnectDTO getResourceForDBConnect() {
		DBConnectDTO env = YmlParser.getYmlEnv(YML_PATH);

		String url = env.getURL();
		String user = env.getUSER();
		String password = env.getPASSWORD();

		return new DBConnectDTO(url, user, password);
	}
}
