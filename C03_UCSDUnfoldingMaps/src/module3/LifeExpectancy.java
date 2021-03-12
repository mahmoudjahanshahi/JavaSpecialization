package module3;

import java.util.*;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

/** LifeExpectancy
 * Just practicing along the lecture videos
 * @author mahmoudjs14 (03/12/2021)
 * */
public class LifeExpectancy extends PApplet
{
	private static final long serialVersionUID = 1L;
	
	private UnfoldingMap map;
	private Map<String, Float> lifeExpByCountry;
	private List<Feature> countries;
	private List<Marker> countryMarkers;
	
	private Map<String, Float> loadLifeExpFromCSV(String filename)
	{
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(filename);
		for (String row: rows) {
			String[] columns = row.split(",");
			try {
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
            } 
			catch (Exception e) {
                continue;
            }
		}
		return lifeExpMap;
	}
	
	private void shadeCountries()
	{
		for (Marker marker : countryMarkers) {
			String countryID = marker.getId();
			if (lifeExpByCountry.containsKey(countryID)) {
				float lifeExp = lifeExpByCountry.get(countryID);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150, 150, 150));
			}
		}
	}
	
	public void setup()
	{
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpFromCSV("LifeExpectancyWorldBankModule3.csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw()
	{
		map.draw();
	}
}
