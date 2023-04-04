package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YmlParser {

	public static Map<String, Object> parsingYml(String path) {
		try (InputStream input = new FileInputStream(new File(path))) {
			Yaml yaml = new Yaml();
			Map<String, Object> data = yaml.load(input);

			return data;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
