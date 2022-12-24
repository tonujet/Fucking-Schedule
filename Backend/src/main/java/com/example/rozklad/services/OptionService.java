package com.example.rozklad.services;

import com.example.rozklad.model.Options.Options;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OptionService {
    private final Options options;

    public OptionService(@Qualifier("default") Options options) {
        this.options = options;
    }

    public Options getOptions(){
        return this.options;
    }
}
