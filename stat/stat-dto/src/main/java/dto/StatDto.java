package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatDto {

    private String app;
    private String uri;
    private Long hits;

    public Long extractIdFromUri(String uri) {
        return Long.parseLong(uri.replace("/events/", ""));
    }
}
