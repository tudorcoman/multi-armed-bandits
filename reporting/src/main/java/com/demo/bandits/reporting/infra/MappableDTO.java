package com.demo.bandits.reporting.infra;

public interface MappableDTO<T> {
    T toEntity();
}
