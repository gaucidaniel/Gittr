package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/27/17.
 */

public class Label {

    private Integer id;
    private String url;
    private String name;
    private String color;
    @SerializedName("default")
    private Boolean _default;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

}
