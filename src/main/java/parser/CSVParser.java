package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

	public List<List<String>> readCSVFile(String filePath) {
		List<List<String>> rows = new ArrayList<>();

		Path path = Paths.get(filePath);

		try (BufferedReader br = Files.newBufferedReader(path)) {
			String line = "";

			while ((line = br.readLine()) != null) {
				String[] columns = line.split(",");
				List<String> row = Arrays.asList(columns);

				rows.add(row);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return rows;
	}
}
