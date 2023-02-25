package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.entities.User.Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdvancedService implements IAdvancedService{

    @Autowired
    private ICampCenterService campCenterService;
    @Autowired
    private IReservationService reservationService;
    @Override

    public List<Map<String, Object>> matchUsersByCampCenter() {
        List<Reservation> allReservations = reservationService.AllReservations();
        Map<Integer, List<Reservation>> reservationsByCampCenter = allReservations.stream()
                .collect(Collectors.groupingBy(r -> r.getCampCenter().getId()));
        List<Map<String, Object>> groups = new ArrayList<>();
        for (Map.Entry<Integer, List<Reservation>> entry : reservationsByCampCenter.entrySet()) {
            Map<String, Object> group = new HashMap<>();
            List<Users> users = new ArrayList<>();
            for (Reservation reservation : entry.getValue()) {
                users.add(reservation.getUsers());
            }
            group.put("campCenterName", campCenterService.retrieveCenter(entry.getKey()).getName());
            group.put("users", users);
            groups.add(group);
        }
        return groups;
    }
    /*public List<List<Users>> matchUsersByCampCenter() {
     List<Reservation> allReservations = reservationService.AllReservations();
     Map<Integer, List<Reservation>> reservationsByCampCenter = allReservations.stream()
                .collect(Collectors.groupingBy(r -> r.getCampCenter().getId()));
        List<List<Users>> groups = new ArrayList<>();
        for (List<Reservation> reservations : reservationsByCampCenter.values()) {
            List<Users> group = new ArrayList<>();
            for (Reservation reservation : reservations) {
                group.add(reservation.getUsers());
            }
            groups.add(group);
        }
        return groups;
    }*/








}
