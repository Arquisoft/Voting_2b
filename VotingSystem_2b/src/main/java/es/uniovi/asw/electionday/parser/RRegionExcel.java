package es.uniovi.asw.electionday.parser;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.model.Constituency;
import es.uniovi.asw.model.PollingPlace;
import es.uniovi.asw.model.Region;

public class RRegionExcel extends RRegion implements ReadRegion {

	public List<Region> readFile(String path) {
		XSSFWorkbook wb;
		XSSFSheet sheet;
		Iterator<Row> rows;
		Row row = null;
		Region region;

		Map<String, Region> regiones = new LinkedHashMap<>();
		Map<String, Constituency> constituencies = new LinkedHashMap<>();
		Map<String, PollingPlace> pollingPlaces = new LinkedHashMap<>();

		try {
			wb = new XSSFWorkbook(new File(path));
			System.out.println("Leyendo fichero " + path);
			sheet = wb.getSheetAt(0);
			rows = sheet.iterator();

			// First line (headers in excel file)
			rows.next();

			while (rows.hasNext()) {
				row = rows.next();

				region = new Region();
				region.setName(row.getCell(0) != null ? row.getCell(0).toString() : null);

				if (regiones.containsKey(region.getName()))
					region = regiones.get(region.getName());

				Constituency cons = new Constituency();
				cons.setName(row.getCell(1) != null ? row.getCell(1).toString() : null);

				if (constituencies.containsKey(cons.getName()))
					cons = constituencies.get(cons.getName());

				PollingPlace p = new PollingPlace();

				// Row empty, without cells
				if (row.getCell(0) != null || row.getCell(1) != null || row.getCell(2) != null) {
					Double id = row.getCell(2) != null ? Double.parseDouble(row.getCell(2).toString()) : null;
					if (id != null) {
						if (pollingPlaces.containsKey(String.valueOf(id.longValue())))
							p = pollingPlaces.get(p);
						p.setConstituency(cons);
						cons.setRegion(region);
						p.setId(id.longValue());
						if (!pollingPlaces.containsKey(String.valueOf(p.getId())))
							pollingPlaces.put(String.valueOf(p.getId().longValue()), p);
					}
					
					if (!regiones.containsKey(region.getName()))
						regiones.put(region.getName(), region);
					
					if (!constituencies.containsKey(cons.getName()))
						constituencies.put(cons.getName(), cons);
				}

			}

		} catch (InvalidFormatException e) {
			System.out.println("El fichero no es un .xlsx");
		} catch (Exception e) {
			String[] fileName = path.split("/");
			System.out.println("El fichero " + fileName[fileName.length - 1] + " no existe");
			e.printStackTrace();
		}

		return regiones.values().stream().collect(Collectors.toList());
	}
}
