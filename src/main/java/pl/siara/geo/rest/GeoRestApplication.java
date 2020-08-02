package pl.siara.geo.rest;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxmind.geoip2.exception.GeoIp2Exception;

@SpringBootApplication
public class GeoRestApplication {

	public static void main(String[] args) throws IOException, GeoIp2Exception {
		SpringApplication.run(GeoRestApplication.class, args);
	}

}
