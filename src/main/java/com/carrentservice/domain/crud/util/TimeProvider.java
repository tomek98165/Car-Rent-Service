package com.carrentservice.domain.crud.util;
import java.time.Instant;
public interface TimeProvider {
    Instant now();

    // Pomocnicze metody (opcjonalne, ale bardzo przydatne)
    Instant minutesAgo(long minutes);
    Instant hoursAgo(long hours);
    Instant daysAgo(long days);

    Instant plusMinutes(long minutes);
    Instant plusHours(long hours);
    Instant plusDays(long days);
}
