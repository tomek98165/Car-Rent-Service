package com.carrentservice.domain.crud.util;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TimeProviderTestImpl implements TimeProvider{
    private Clock clock;

    public TimeProviderTestImpl(Clock clock) {
        this.clock = clock;
    }

    // Metody do przesuwania czasu — najważniejsze w testach
    public void advanceMinutes(long minutes) {
        setCurrentTime(now().plus(minutes, ChronoUnit.MINUTES));
    }

    public void advanceHours(long hours) {
        setCurrentTime(now().plus(hours, ChronoUnit.HOURS));
    }

    public void advanceDays(long days) {
        setCurrentTime(now().plus(days, ChronoUnit.DAYS));
    }

    public void setCurrentTime(Instant instant) {
        this.clock = Clock.fixed(instant, ZoneOffset.UTC);
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
