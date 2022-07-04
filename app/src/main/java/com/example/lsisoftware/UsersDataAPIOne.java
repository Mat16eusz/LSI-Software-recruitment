package com.example.lsisoftware;

import java.util.ArrayList;

public class UsersDataAPIOne {

    private final ArrayList<List> list = new ArrayList<>();
    private int limit;

    private List getList(int index) {
        return list.get(index);
    }

    public String getId(int index) {
        return getList(index).id;
    }

    public String getScreenname(int index) {
        return getList(index).screenname;
    }

    public int getLimit() {
        return limit;
    }
}

class List {
    public String id;
    public String screenname;
}
