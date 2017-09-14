package team.skyzo.shaken.boardgamerandomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LEROYJ on 08/09/2017.
 */

public class FiltersModel {
    private final HashMap<String, List<String>> filters = new HashMap<>();
    private final List<String> gender = new ArrayList<>();
    private final List<String> origin = new ArrayList<>();
    private final List<String> type = new ArrayList<>();
    private String strGender;
    private String strOrigin;
    private String strType;

    public FiltersModel() {
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public void setStrOrigin(String strOrigin) {
        this.strOrigin = strOrigin;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public List<String> getGender() {
        return gender;
    }

    public List<String> getOrigin() {
        return origin;
    }

    public List<String> getType() {
        return type;
    }

    public HashMap<String, List<String>> getFilters() {
        filters.put(strType, this.type);
        filters.put(strOrigin, this.origin);
        filters.put(strGender, this.gender);
        return filters;
    }
}
