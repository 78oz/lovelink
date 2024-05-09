package com.example.ozapplication;

public class Parameter {

    private String name;
    private String value;
    private boolean isMust; //this attribute needs only for userPref not for userParam

    public Parameter() {
    }

    public Parameter(String name, String value, boolean isMust) {
        this.name = name;
        this.value = value;
        this.isMust = isMust;
    }
    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }

    //the function check if param is match
    public boolean isBasicMatch(Parameter otherParam) {
        Parameter userPrefParam = this;
        //gender
        if (this.name.equals("gender"))
            return userPrefParam.value.equals(otherParam.value);
        //minAge
        if (this.name.equals("minAge"))
            return Integer.parseInt(userPrefParam.value) <= Integer.parseInt(otherParam.value);
        //maxAge
        if (this.name.equals("maxAge"))
            return Integer.parseInt(userPrefParam.value) >= Integer.parseInt(otherParam.value);
        return true;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", isMust=" + isMust +
                '}';
    }
}
