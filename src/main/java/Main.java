import java.util.Map;

import batch.BatchServiceProxy;
import parser.YmlParser;

public class Main {
	public static void main(String[] args) {
		Map<String, Object> dbEnvObj = YmlParser.parsingYml("application-db.yml");
		Map<String, String> env = (Map<String, String>) dbEnvObj.get(DBEnv.DB.name());
		String url = env.get(DBEnv.URL.name());
		String user = env.get(DBEnv.USER.name());
		String password = env.get(DBEnv.PASSWORD.name());

		String sql = "insert into customers "
			+ "(id, organization_id, name, website, country, description, founded, industry, number_of_employees) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		BatchServiceProxy batchService = new BatchServiceProxy(url, user, password);
		batchService.insertDataToDBUsingCSV(sql, "organizations-2000000.csv");
	}
}
