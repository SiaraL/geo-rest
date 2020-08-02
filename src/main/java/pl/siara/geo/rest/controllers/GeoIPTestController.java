package pl.siara.geo.rest.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.siara.geo.rest.configuration.SwaggerConfiguration;
import pl.siara.geo.rest.dao.GeoDaoImpl;
import pl.siara.geo.rest.entity.GeoIp;
import pl.siara.geo.rest.services.RawDBDemoGeoIPLocationService;

@Api(tags = { SwaggerConfiguration.LOCATION })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved response."),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.") })
@RestController
public class GeoIPTestController {

    @Autowired
    GeoDaoImpl geo;

    @ApiOperation(value = "Add geolocation of device to database")
    @GetMapping("/location")
    public GeoIp addLocation(HttpServletRequest request) throws Exception {
        GeoIp geoIp = (new RawDBDemoGeoIPLocationService()).getLocation(request.getRemoteAddr());
        geo.addLocalInfo(request.getRemoteAddr(), geoIp.getLatitude().toString(), geoIp.getLongitude().toString(),
                new Timestamp(System.currentTimeMillis()));
        return geoIp;
    }

    @ApiOperation(value = "Returns all records from database")
    @GetMapping("/records")
    public List<GeoIp> getLocation() throws Exception {
        return geo.getLocalInfo();
    }

}
