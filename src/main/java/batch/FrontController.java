package batch;

import static dto.CustomerDTO.*;

import java.util.List;

import dto.CustomerDTO;
import dto.DBConnectDTO;
import parser.CSVParser;
import parser.YmlParser;

public class FrontController {

	private final String YML_PATH = "application-db.yml";

	public void startBatch(String filePath) {
		DBConnectDTO dbConnectResource = getResourceForDBConnect();
		List<CustomerDTO> convertedCSV = CSVParser.getCsvList(filePath);

		String sql = "insert into customers "
			+ "(" + ID + ", "
			+ ORGANIZATION_ID + ", "
			+ NAME + ", "
			+ WEBSITE + ", "
			+ COUNTRY + ", "
			+ DESCRIPTION + ", "
			+ FOUNDED + ", "
			+ INDUSTRY + ", "
			+ NUMBER_OF_EMPLOYEES + ") "
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
