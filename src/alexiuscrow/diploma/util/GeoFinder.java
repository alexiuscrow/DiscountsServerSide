package alexiuscrow.diploma.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.geonames.InsufficientStyleException;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.WebService;

import alexiuscrow.diploma.entity.Shops;

public abstract class GeoFinder {
	public static String getLocalityName(Double lat, Double lng){
		WebService.setUserName("alexiuscrow");
		WebService.setDefaultStyle(Style.FULL);
		 List<Toponym> toponymList = null;

		 try {
			toponymList = WebService.findNearbyPlaceName(lat, lng);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (toponymList != null && toponymList.size() != 0){
			Toponym toponym = toponymList.get(0);
			StringBuffer localityName = new StringBuffer();
			
			try {
				localityName.append(toponym.getAdminName1());
//				localityName.append(" ");
//				localityName.append(toponym.getCountryCode());
			} catch (InsufficientStyleException e) {
				e.printStackTrace();
			}
			
			return localityName.toString();
		}
		
		return null;
	}
	
	public static Double getDistance(Double currentLat, Double currentLng,
			Double remoteLat, Double remoteLng){
		
		Double result = 1000d * 6371d * Math.acos(Math.cos(Math.toRadians(currentLat)) 
				* Math.cos(Math.toRadians(remoteLat)) * Math.cos(Math.toRadians(remoteLng)
				- Math.toRadians(currentLng)) + Math.sin(Math.toRadians(currentLat))
				* Math.sin(Math.toRadians(remoteLat)));
		
		return new BigDecimal(result).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static Double getDistance(Double currentLat, Double currentLng, Shops shop){
		
		return getDistance(currentLat, currentLng, shop.getLatitude(), shop.getLongitude());
	}
	
	public static double toRadians(double angdeg) {
        return angdeg * (Math.PI/180);
    }
}
