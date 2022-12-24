package com.example.rozklad.model.Options;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class Option {
    @JsonProperty
    String name;
    @JsonProperty
    String type;
    @JsonProperty
    String data;
    @JsonProperty
    Boolean nullable;

    public Option(String name, String type, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public Option(String name, String type, String data, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.nullable = nullable;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", nullable=" + nullable +
                '}';
    }
}
