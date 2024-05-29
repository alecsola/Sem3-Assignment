package fontys.sem3.school.persistence.converters;

import fontys.sem3.school.domain.Event;
import fontys.sem3.school.domain.Image;
import fontys.sem3.school.domain.Zone;
import fontys.sem3.school.persistence.JPAmappers.ImageJPAmapper;
import fontys.sem3.school.persistence.JPAmappers.ZoneJPAmapper;

import java.util.ArrayList;
import java.util.List;

public class ZoneConverter {

    public static List<ZoneJPAmapper> mapToJpaZones(List<Zone> zones) {
        List<ZoneJPAmapper> jpaZones = new ArrayList<>();
        if (zones != null) {
            for (Zone zone : zones) {
                jpaZones.add(createJpaZone(zone.getId(), zone.getPrice(), zone.getAvailableSeats()));
            }
        }
        return jpaZones;
    }

    private static ZoneJPAmapper createJpaZone(Long id, int price, int availableSeats) {
        return ZoneJPAmapper.builder()
                .Id(id)
                .Price(price)
                .AvailableSeats(availableSeats)

                .build();
    }

    public static List<Zone> mapToZones(List<ZoneJPAmapper> jpaZones) {
        List<Zone> zones = new ArrayList<>();
        if (jpaZones != null) {
            for (ZoneJPAmapper jpaZone : jpaZones) {
                zones.add(createZone(jpaZone.getId(), jpaZone.getPrice(), jpaZone.getAvailableSeats()));
            }
        }
        return zones;
    }

    private static Zone createZone(Long id, int price, int availableSeats) {
        return Zone.builder()
                .id(id)
                .price(price)
                .availableSeats(availableSeats)
                .build();
    }

}
