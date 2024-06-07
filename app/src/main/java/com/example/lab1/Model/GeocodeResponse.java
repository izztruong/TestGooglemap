package com.example.lab1.Model;

import java.util.List;

public class GeocodeResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {
        private String title;
        private String id;
        private String resultType;
        private Address address;
        private Position position;
        private MapView mapView;
        private Scoring scoring;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public MapView getMapView() {
            return mapView;
        }

        public void setMapView(MapView mapView) {
            this.mapView = mapView;
        }

        public Scoring getScoring() {
            return scoring;
        }

        public void setScoring(Scoring scoring) {
            this.scoring = scoring;
        }
    }

    public class Address {
        private String label;
        private String countryCode;
        private String countryName;
        private String county;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }
    }

    public class Position {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public class MapView {
        private double west;
        private double south;
        private double east;
        private double north;

        public double getWest() {
            return west;
        }

        public void setWest(double west) {
            this.west = west;
        }

        public double getSouth() {
            return south;
        }

        public void setSouth(double south) {
            this.south = south;
        }

        public double getEast() {
            return east;
        }

        public void setEast(double east) {
            this.east = east;
        }

        public double getNorth() {
            return north;
        }

        public void setNorth(double north) {
            this.north = north;
        }
    }

    public class Scoring {
        private double queryScore;
        private FieldScore fieldScore;

        public double getQueryScore() {
            return queryScore;
        }

        public void setQueryScore(double queryScore) {
            this.queryScore = queryScore;
        }

        public FieldScore getFieldScore() {
            return fieldScore;
        }

        public void setFieldScore(FieldScore fieldScore) {
            this.fieldScore = fieldScore;
        }
    }

    public class FieldScore {
        private double county;

        public double getCounty() {
            return county;
        }

        public void setCounty(double county) {
            this.county = county;
        }
    }
}
