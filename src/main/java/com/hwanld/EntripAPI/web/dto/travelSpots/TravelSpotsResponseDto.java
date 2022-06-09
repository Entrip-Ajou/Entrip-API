package com.hwanld.EntripAPI.web.dto.travelSpots;

import com.hwanld.EntripAPI.domain.travelSpots.TravelSpots;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TravelSpotsResponseDto {

    private String name;
    private String photoUrl;
    private String address;
    private List<String> tags;

    public TravelSpotsResponseDto (TravelSpots entity) {
        this.name = entity.getTravelSpot_name();
        this.photoUrl = entity.getPhotoUrl();
        this.address = entity.getLocation();
        this.tags = new ArrayList<String>();
        this.tags = makeStringToList(entity.getTravelFavorite());
    }

    public List<String> makeStringToList (String travelFavorite) {
        List<String> tags = new ArrayList<String>();
        for (int i=0;i<travelFavorite.length();i++) {
            String tempString = travelFavorite.substring(i,i+1);
            tags.add(tempString);
        }
        return tags;
    }

}
