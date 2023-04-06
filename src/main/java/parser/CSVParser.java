package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dto.CustomerDTO;

public class CSVParser {

	public static List<CustomerDTO> getCsvList(String filePath) {
		return readCSVFile(filePath);
	}

	private static List<CustomerDTO> readCSVFile(String filePath) {
		List<CustomerDTO> rows = new ArrayList<>();

		Path path = Paths.get(filePath);

		try (BufferedReader br = Files.newBufferedReader(path)) {
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] columns = line.split(",");
				CustomerDTO dto = new CustomerDTO(columns);

				rows.add(dto);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return rows;
	}
}
