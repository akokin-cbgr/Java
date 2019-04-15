package ru.stqa.pft.soap;


import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("194.28.29.152");
    String substring = ipLocation20.substring(16, 18);
    assertEquals(substring, "RU");
  }

  @Test
  public void testInvalidIp() {
    String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("194.28.29.xxx");
    String substring = ipLocation20.substring(16, 18);
    assertEquals(substring, "RU");
  }

}