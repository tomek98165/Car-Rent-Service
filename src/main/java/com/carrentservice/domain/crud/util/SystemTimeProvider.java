package com.carrentservice.domain.crud.util;
import org.springframework.stereotype.Component;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class SystemTimeProvider implements TimeProvider{
    private final Clock clock;

    public SystemTimeProvider(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Instant now() {
        return Instant.now(clock);
    }

    @Override
    public Instant minutesAgo(long minutes) {
        return now().minus(minutes, ChronoUnit.MINUTES);
    }

    @Override
    public Instant hoursAgo(long hours) {
        return now().minus(hours, ChronoUnit.HOURS);
    }

    @Override
    public Instant daysAgo(long days) {
        return now().minus(days, ChronoUnit.DAYS);
    }

    @Override
    public Instant plusMinutes(long minutes) {
        return now().plus(minutes, ChronoUnit.MINUTES);
    }

    @Override
    public Instant plusHours(long hours) {
        return now().plus(hours, ChronoUnit.HOURS);
    }

    @Override
    public Instant plusDays(long days) {
        return now().plus(days, ChronoUnit.DAYS);
    }
}
