package ewm.exception;

import java.util.function.Function;

@FunctionalInterface
public interface CheckedFunction<T,R> {
    R apply(T t) throws NotFoundException;
    static <T, R> Function<T, R> wrap(CheckedFunction<T, R> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (NotFoundException e) {
                throw new NotFoundRuntimeException(e.getMessage());
            }
        };
    }
}

