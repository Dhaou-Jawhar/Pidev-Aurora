package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdvancedService implements IAdvancedService{

    @Autowired
    private ICampCenterService campCenterService;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private CampCenterRepository campCenterRepository;

    @Override

    public List<Map<String, Object>> matchUsersByCampCenter() {
        List<Reservation> allReservations = reservationService.AllReservations();
        Map<Integer, List<Reservation>> reservationsByCampCenter = allReservations.stream()
                .collect(Collectors.groupingBy(r -> r.getCampCenter().getId()));
        List<Map<String, Object>> groups = new ArrayList<>();
        for (Map.Entry<Integer, List<Reservation>> entry : reservationsByCampCenter.entrySet()) {
            Map<String, Object> group = new HashMap<>();
            List<UserApp> users = new ArrayList<>();
            for (Reservation reservation : entry.getValue()) {
                users.add(reservation.getUserApp());
            }
            group.put("campCenterName", campCenterService.retrieveCenter(entry.getKey()).getName());
            group.put("users", users);
            groups.add(group);
        }
        return groups;
    }

    @Override
    public List<CampCenter> filterCampCenters(String sortBy) {
        List<CampCenter> filteredCampCenters = new ArrayList<>();

        // Fetch all Camp Centers from the database
        List<CampCenter> allCampCenters = campCenterRepository.findAll();

        // Sort the list based on the selected criteria
        switch (sortBy) {
            case "popularity":
                // Sort by popularity, by the number of reservations or reviews
                filteredCampCenters = allCampCenters.stream()
                        .sorted(Comparator.comparingInt((CampCenter campCenter) -> campCenter.getReservations().size())
                                .reversed()) // most popular first
                        .collect(Collectors.toList());
                break;
            case "rate":
                // Sort by rating, by calculating the average rating of all reviews
                filteredCampCenters = allCampCenters.stream()
                        .sorted(Comparator.comparingDouble(campCenter -> {
                            DoubleSummaryStatistics stats = campCenter.getReviews().stream()
                                    .mapToDouble(review -> review.getNote().getValue())
                                    .summaryStatistics();
                            //return stats.getCount() > 0 ? stats.getAverage() : 0;
                            return stats.getCount() > 0 ? -stats.getAverage() : 0; // - bch ywali l ordre ml good rating lel bad
                        }))
                        .collect(Collectors.toList());
                break;
            case "price":
                // Sort by price, by selecting the cheapest Camp Centers first
                filteredCampCenters = allCampCenters.stream()
                        .sorted(Comparator.comparingDouble(CampCenter::getPrice))
                        .collect(Collectors.toList());
                break;
            case "service":
                // Sort by services, by sorting the Camp Centers that offer the most services first
                filteredCampCenters = allCampCenters.stream()
                        .sorted(Comparator.comparingInt(campCenter -> ((CampCenter)campCenter).getServices().size()).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                filteredCampCenters = allCampCenters;
                break;
        }
        return filteredCampCenters;
    }
    @Override
    public List<CampCenter> suggestCampCentersBasedOnFavorites(Integer userId) {
        List<CampCenter> favorites = campCenterRepository.findFavoriteCampCentersByUserId(userId);

        if (favorites.isEmpty()) {
            return campCenterRepository.findTop5ByOrderByPriceAsc();
        } else {
            ActivityType activityType = favorites.get(0).getCampcenterType();
            return campCenterRepository.findTop5ByCampcenterTypeOrderByPriceAsc(activityType);
        }
    }

}
