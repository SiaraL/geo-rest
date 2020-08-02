package pl.siara.geo.rest;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

@RunWith(SpringRunner.class)
class GeoRestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void givenIP_whenFetchingCity_thenReturnsCityData() 
	  throws IOException, GeoIp2Exception {
	    String ip = "79.184.238.217";
	    String dbLocation = "./GeoLite2-City.mmdb";
	        
	    File database = new File(dbLocation);
	    DatabaseReader dbReader = new DatabaseReader.Builder(database)
	      .build();
	        
	    InetAddress ipAddress = InetAddress.getByName(ip);
	    CityResponse response = dbReader.city(ipAddress);
	        
	    String countryName = response.getCountry().getName();
	    String cityName = response.getCity().getName();
	    String postal = response.getPostal().getCode();
	    String state = response.getLeastSpecificSubdivision().getName();
	}

}
