package com.hwanld.EntripAPI.service.travelSpots;

import com.hwanld.EntripAPI.domain.travelSpots.TravelSpots;
import com.hwanld.EntripAPI.domain.travelSpots.TravelSpotsRepository;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsResponseDto;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TravelSpotsService {

    private final TravelSpotsRepository travelSpotsRepository;

    @Transactional
    public String save(TravelSpotsSaveRequestDto requestDto) {
        TravelSpots travelSpots = requestDto.toEntity();
        String travelSpot_name = travelSpotsRepository.save(travelSpots).getTravelSpot_name();
        return travelSpot_name;
    }

    @Transactional
    public String update (String travelSpot_name, TravelSpotsUpdateRequestDto requestDto) {
        TravelSpots travelSpots = travelSpotsRepository.findById(travelSpot_name).orElseThrow(
                ()->new IllegalArgumentException("Error raised at travelSpotsRepository.findById, " + travelSpot_name)
        );
        travelSpots.update(requestDto);
        return travelSpot_name;
    }

    public TravelSpotsResponseDto findById (String travelSpot_name) {
        TravelSpots travelSpots = travelSpotsRepository.findById(travelSpot_name).orElseThrow(
                ()->new IllegalArgumentException("Error raised at travelSpotsRepository.findById, " + travelSpot_name)
        );
        return new TravelSpotsResponseDto(travelSpots);
    }

    @Transactional
    public String delete (String travelSpot_name) {
        TravelSpots travelSpots = travelSpotsRepository.findById(travelSpot_name).orElseThrow(
                ()->new IllegalArgumentException("Error raised at travelSpotsRepository.findById, " + travelSpot_name)
        );
        travelSpotsRepository.delete(travelSpots);
        return travelSpot_name;
    }
}
