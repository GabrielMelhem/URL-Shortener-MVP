package com.example.domain.factory;

import com.example.domain.adapter.URLInputAdapter;
import com.example.domain.port.URLOutputPort;

public class URLInputAdapterFactory {

    public static URLInputAdapter createURLInputAdapter(URLOutputPort urlOutputPort) {
        return new URLInputAdapter(urlOutputPort);
    }
}
