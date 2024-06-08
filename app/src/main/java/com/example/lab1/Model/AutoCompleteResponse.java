package com.example.lab1.Model;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteResponse {

        private ArrayList<AutoComplete> items;

        public ArrayList<AutoComplete> getItems() {
            return items;
        }
    public  class AutoComplete{



    private String title;
    private String id;

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
    }
}
