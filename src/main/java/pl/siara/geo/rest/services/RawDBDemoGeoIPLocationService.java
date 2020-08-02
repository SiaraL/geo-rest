package pl.siara.geo.rest.services;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import pl.siara.geo.rest.entity.GeoIp;

public class RawDBDemoGeoIPLocationService {

    private DatabaseReader dbReader;

    public RawDBDemoGeoIPLocationService() throws IOException {
        File database = new File("./GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public GeoIp getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();
        return new GeoIp(ip, latitude, longitude, new Timestamp(System.currentTimeMillis()));
    }

}
