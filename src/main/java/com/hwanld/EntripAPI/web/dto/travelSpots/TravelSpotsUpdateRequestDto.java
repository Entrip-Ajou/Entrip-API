package com.hwanld.EntripAPI.web.dto.travelSpots;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelSpotsUpdateRequestDto {

    private String photoUrl;
    private String location;
    private String description;
    private Long travelFavorite;

    @Builder
    public TravelSpotsUpdateRequestDto (String photoUrl, String location, String description, Long travelFavorite) {
        this.photoUrl = photoUrl;
        this.location = location;
        this.description = description;
        this.travelFavorite = travelFavorite;
    }
}
