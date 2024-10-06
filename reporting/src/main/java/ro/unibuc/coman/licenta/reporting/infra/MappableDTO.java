package ro.unibuc.coman.licenta.reporting.infra;

public interface MappableDTO<T> {
    T toEntity();
}
