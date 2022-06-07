package com.hwanld.EntripAPI.domain.travelSpots;

import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class TravelSpots {

    @Id @Column(name = "travelSpot_name")
    private String travelSpot_name;

    @Column
    private String photoUrl;
    private String location;
    private String description;
    private String travelFavorite;

    @Builder
    public TravelSpots (String travelSpot_name, String photoUrl, String location, String description, String travelFavorite) {
        this.travelSpot_name = travelSpot_name;
        this.photoUrl = photoUrl;
        this.location = location;
        this.description = description;
        this.travelFavorite = travelFavorite;
    }

    public void update (TravelSpotsUpdateRequestDto requestDto) {
        this.photoUrl = requestDto.getPhotoUrl();
        this.location = requestDto.getLocation();
        this.description = requestDto.getDescription();
        this.travelFavorite = requestDto.getTravelFavorite();
    }
}
