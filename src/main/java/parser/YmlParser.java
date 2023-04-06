package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import dto.DBConnectDTO;

public class YmlParser {

	public static DBConnectDTO getYmlEnv(String path) {
		Map<String, Object> getDBEnv = readYmlFile(path);
		Map<String, String> dbKeys = (Map<String, String>) getDBEnv.get(DBConnectDTO.DB_KEY);

		return new DBConnectDTO(
			dbKeys.get(DBConnectDTO.URL_KEY),
			dbKeys.get(DBConnectDTO.USER_KEY),
			dbKeys.get(DBConnectDTO.PASSWORD_KEY));
	}

	private static Map<String, Object> readYmlFile(String path) {
		try (InputStream input = new FileInputStream(new File(path))) {
			Yaml yaml = new Yaml();

			return yaml.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
