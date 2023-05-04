package dto;

import java.util.HashMap;
import java.util.Map;

public class CustomerDTO {

	public final static String ID = "ID";
	public final static String ORGANIZATION_ID = "ORGANIZATION_ID";
	public final static String NAME = "NAME";
	public final static String WEBSITE = "WEBSITE";
	public final static String COUNTRY = "COUNTRY";
	public final static String DESCRIPTION = "DESCRIPTION";
	public final static String FOUNDED = "FOUNDED";
	public final static String INDUSTRY = "INDUSTRY";
	public final static String NUMBER_OF_EMPLOYEES = "NUMBER_OF_EMPLOYEES";

	private final String id;
	private final String organizationId;
	private final String name;
	private final String website;
	private final String country;
	private final String description;
	private final String founded;
	private final String industry;
	private final String numberOfEmployees;

	public final Map<Integer, String> data;

	private CustomerDTO(
		String id,
		String organizationId,
		String name,
		String website,
		String country,
		String description,
		String founded,
		String industry,
		String numberOfEmployees,
		Map<Integer, String> data
	) {
		this.id = id;
		this.organizationId = organizationId;
		this.name = name;
		this.website = website;
		this.country = country;
		this.description = description;
		this.founded = founded;
		this.industry = industry;
		this.numberOfEmployees = numberOfEmployees;
		this.data = data;
	}

	public static CustomerDTO parse(String[] columns) {
		Map<Integer, String> setData = setMapToData(columns);
		return new CustomerDTO(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], setData);
	}

	private static Map<Integer, String> setMapToData(String[] columns) {
		Map<Integer, String> setData = new HashMap<>();

		for (int i = 1; i < 10; i++) {
			setData.put(i, columns[i - 1]);
		}

		return setData;
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
