package com.stackoverflow.clone.util;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


public class TimeElapsedFormatter {
    public static String formatTimeElapsed(Date createdAt) {
        Instant now = Instant.now();
        Instant createdAtInstant = createdAt.toInstant();
        LocalDateTime createdAtDateTime = LocalDateTime.ofInstant(createdAtInstant, ZoneOffset.UTC);
        LocalDateTime nowDateTime = LocalDateTime.ofInstant(now, ZoneOffset.UTC);

        Duration duration = Duration.between(createdAtDateTime, nowDateTime);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();

        if (days > 0) {
            return days + " day(s) ago";
        } else if (hours > 0) {
            return hours + " hour(s) ago";
        } else {
            return minutes + " minute(s) ago";
        }
    }
}
