package com.codecool.ehotel.service.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void logInfo(String message) {
        System.out.println(LocalDateTime.now() + " -- " + message);
    }
}
