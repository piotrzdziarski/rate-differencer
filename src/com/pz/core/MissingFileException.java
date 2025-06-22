package com.pz.core;

import java.io.IOException;

public class MissingFileException extends IOException {
    public MissingFileException(String message) {
        super(message);
    }
}
