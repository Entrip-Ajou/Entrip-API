package com.hwanld.EntripAPI.web.dto.travelSpots;

import com.hwanld.EntripAPI.domain.travelSpots.TravelSpots;
import lombok.Getter;

@Getter
public class TravelSpotsResponseDto {

    private String travelSpot_name;
    private String photoUrl;
    private String location;
    private String description;
    private Long travelFavorite;

    public TravelSpotsResponseDto (TravelSpots entity) {
        this.travelFavorite = entity.getTravelFavorite();
        this.photoUrl = entity.getPhotoUrl();
        this.location = entity.getLocation();
        this.travelSpot_name = entity.getTravelSpot_name();
        this.description = entity.getDescription();
    }
}
