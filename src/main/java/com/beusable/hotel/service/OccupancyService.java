package com.beusable.hotel.service;

import com.beusable.hotel.model.OccupancyRequest;
import com.beusable.hotel.model.OccupancyResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OccupancyService {

    public OccupancyResponse allocateRooms(OccupancyRequest request) {
        int premiumRooms = request.getPremiumRooms();
        int economyRooms = request.getEconomyRooms();
        List<Double> potentialGuests = request.getPotentialGuests();

        Map<Boolean, List<Double>> partitioned = potentialGuests.stream()
                .collect(Collectors.partitioningBy(g -> g >= 100));
        List<Double> premiumGuests = sortDescending(partitioned.get(true));
        List<Double> economyGuests = sortDescending(partitioned.get(false));

        int allocatedPremium = Math.min(premiumRooms, premiumGuests.size());
        double premiumRevenue = calculateRevenue(premiumGuests, allocatedPremium);
        int allocatedEconomy = Math.min(economyRooms, economyGuests.size());
        double economyRevenue = calculateRevenue(economyGuests, allocatedEconomy);

        if (economyGuests.size() > allocatedEconomy) {
            int freePremiumRooms = premiumRooms - allocatedPremium;
            int economyOverflow = economyGuests.size() - allocatedEconomy;
            int upgrades = Math.min(freePremiumRooms, economyOverflow);
            if (upgrades > 0) {
                List<Double> upgradedGuests = economyGuests.subList(allocatedEconomy, allocatedEconomy + upgrades);
                double upgradeRevenue = calculateRevenue(upgradedGuests, upgrades);
                premiumRevenue += upgradeRevenue;
                allocatedPremium += upgrades;
                economyRevenue -= upgradeRevenue;
                allocatedEconomy -= upgrades;
            }
        }

        return new OccupancyResponse(allocatedPremium, premiumRevenue, allocatedEconomy, economyRevenue);
    }

    private List<Double> sortDescending(List<Double> list) {
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private double calculateRevenue(List<Double> guests, int allocated) {
        return guests.stream()
                .limit(allocated)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
