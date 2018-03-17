package br.com.transferr.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.transferr.core.metadata.CoordinatesAmplitude;
import br.com.transferr.core.metadata.CoordinatesQuadrant;

public class CoordinatesUtil {
	
	public static CoordinatesAmplitude defineCoordinates(CoordinatesQuadrant quadrant) {
		CoordinatesAmplitude amplitude = new CoordinatesAmplitude();

		List<Double> listLon = new ArrayList<>();
		List<Double> listLat = new ArrayList<>();
		
		listLon.add(quadrant.getCoordFarLeft().getLongitude());
		listLat.add(quadrant.getCoordFarLeft().getLatitude());
		
		listLon.add(quadrant.getCoordFarRight().getLongitude());
		listLat.add(quadrant.getCoordFarRight().getLatitude());
		
		listLon.add(quadrant.getCoordNearLeft().getLongitude());
		listLat.add(quadrant.getCoordNearLeft().getLatitude());
		
		listLon.add(quadrant.getCoordNearRight().getLongitude());
		listLat.add(quadrant.getCoordNearRight().getLatitude());

		Collections.sort(listLon);
		Collections.sort(listLat);
		double maxLong = Collections.max(listLon);
		double minLong = Collections.min(listLon);
		double maxLat  = Collections.max(listLat);
		double minLat  = Collections.min(listLat);
		amplitude.setMaxLatitude(maxLat);
		amplitude.setMaxLongitude(maxLong);
		amplitude.setMinLatitude(minLat);
		amplitude.setMinLongitude(minLong);
		return amplitude;
	}
}
