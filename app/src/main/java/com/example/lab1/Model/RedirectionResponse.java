package com.example.lab1.Model;

import java.util.List;

public class RedirectionResponse {
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public static class Route {
        private String id;
        private List<Section> sections;

        public String getId() {
            return id;
        }

        public List<Section> getSections() {
            return sections;
        }
    }

    public static class Section {
        private String id;
        private String type;
        private Departure departure;
        private Arrival arrival;
        private String polyline;
        private Transport transport;

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public Departure getDeparture() {
            return departure;
        }

        public Arrival getArrival() {
            return arrival;
        }

        public String getPolyline() {
            return polyline;
        }

        public Transport getTransport() {
            return transport;
        }
    }

    public static class Departure {
        private String time;
        private Place place;

        public String getTime() {
            return time;
        }

        public Place getPlace() {
            return place;
        }
    }

    public static class Arrival {
        private String time;
        private Place place;

        public String getTime() {
            return time;
        }

        public Place getPlace() {
            return place;
        }
    }

    public static class Place {
        private String type;
        private Location location;
        private Location originalLocation;

        public String getType() {
            return type;
        }

        public Location getLocation() {
            return location;
        }

        public Location getOriginalLocation() {
            return originalLocation;
        }
    }

    public static class Location {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

    public static class Transport {
        private String mode;

        public String getMode() {
            return mode;
        }
    }
}
