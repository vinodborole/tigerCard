package com.vinodborole.tigercard.entity;

public enum Zones {
    ZONE_1(1),ZONE_2(2);
    private int zone;
    private Zones(int zone)
    {
        this.zone = zone;
    }
    public int getZone() {
        return zone;
    }
    static public boolean isValidZone(int zone) {
        Zones[] zones  = Zones.values();
        for (Zones z : zones)
            if (z.zone==zone)
                return true;
        return false;
    }
}
