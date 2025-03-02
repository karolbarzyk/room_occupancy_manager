package com.beusable.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupancyResponse {
    private int usagePremium;
    private double revenuePremium;
    private int usageEconomy;
    private double revenueEconomy;
}
