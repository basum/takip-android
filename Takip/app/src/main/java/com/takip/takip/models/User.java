package com.takip.takip.models;
/*

ViitaApp

Created by Mehmet Tarhan on 3/11/19.
Copyright © 2018 VIITA Watches GmbH. All rights reserved.

*/

import java.util.HashMap;

public class User {

    public static User currentUser = new User();

    public UserType type;
    public String id;

    private User() {

    }

    public User(HashMap<String, Object> map) {
        this.id = (String) map.get("id");

        long type = (long) map.get("type");
        if (type == 0) {
            this.type = UserType.MINISTRY;
        } else if (type == 1) {
            this.type = UserType.PRODUCER;
        } else if (type == 2) {
            this.type = UserType.RECYCLE_MANAGER;
        } else {
            this.type = UserType.SUPPLIER;
        }

    }
}
