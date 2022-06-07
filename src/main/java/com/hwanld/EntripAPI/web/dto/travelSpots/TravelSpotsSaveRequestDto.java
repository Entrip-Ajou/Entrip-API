package com.hwanld.EntripAPI.web.dto.travelSpots;

import com.hwanld.EntripAPI.domain.travelSpots.TravelSpots;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TravelSpotsSaveRequestDto {

    private String travelSpot_name;
    private String photoUrl;
    private String location;
    private String description;
    private String travelFavorite;

    @Builder
    public TravelSpotsSaveRequestDto (String travelSpot_name, String photoUrl, String location, String description, String travelFavorite) {
        this.travelSpot_name = travelSpot_name;
        this.photoUrl = photoUrl;
        this.location = location;
        this.description = description;
        this.travelFavorite = travelFavorite;
    }

    public TravelSpots toEntity() {
        return TravelSpots.builder()
                .travelSpot_name(travelSpot_name)
                .description(description)
                .travelFavorite(travelFavorite)
                .photoUrl(photoUrl)
                .location(location)
                .build();
    }
}
