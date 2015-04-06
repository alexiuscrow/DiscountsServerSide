package alexiuscrow.diploma.util;

import java.io.IOException;
import java.util.List;

import org.geonames.InsufficientStyleException;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.WebService;

public class GeoFinder {
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
}
