package dto;

import java.util.HashMap;
import java.util.Map;

public class CustomerDTO {

	public static Map<Integer, String> COLUMNS = new HashMap<>();
	public Map<Integer, String> data = new HashMap<>();

	static {
		COLUMNS.put(1, "ID");
		COLUMNS.put(2, "ORGANIZATION_ID");
		COLUMNS.put(3, "NAME");
		COLUMNS.put(4, "WEBSITE");
		COLUMNS.put(5, "COUNTRY");
		COLUMNS.put(6, "DESCRIPTION");
		COLUMNS.put(7, "FOUNDED");
		COLUMNS.put(8, "INDUSTRY");
		COLUMNS.put(9, "NUMBER_OF_EMPLOYEES");
	}

	private final String id;
	private final String organizationId;
	private final String name;
	private final String website;
	private final String country;
	private final String description;
	private final String founded;
	private final String industry;
	private final String numberOfEmployees;

	public CustomerDTO(String[] columns) {
		this.id = columns[0];
		this.organizationId = columns[1];
		this.name = columns[2];
		this.website = columns[3];
		this.country = columns[4];
		this.description = columns[5];
		this.founded = columns[6];
		this.industry = columns[7];
		this.numberOfEmployees = columns[8];

		setMapToData(columns);
	}

	private void setMapToData(String[] columns) {
		for (int i = 1; i < 10; i++) {
			data.put(i, columns[i - 1]);
		}
	}

	@Override
	public String toString() {
		return "CustomerDTO{" +
			"id=" + id +
			", organizationId='" + organizationId + '\'' +
			", name='" + name + '\'' +
			", website='" + website + '\'' +
			", country='" + country + '\'' +
			", description='" + description + '\'' +
			", founded='" + founded + '\'' +
			", industry='" + industry + '\'' +
			", numberOfEmployees='" + numberOfEmployees + '\'' +
			'}';
	}
}
