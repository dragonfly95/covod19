package com.project.covid19.vo;

import java.util.ArrayList;
import java.util.List;

public interface PersonMap {
    Long getId();
    String getName();
    PaddressMap getAddress();

    interface PaddressMap {
        Long getId();
        String getZipcode();
        String getStreet();
    }
}
