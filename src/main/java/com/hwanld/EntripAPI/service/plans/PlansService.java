package com.hwanld.EntripAPI.service.plans;

import com.hwanld.EntripAPI.domain.plans.PlansRepository;
import com.hwanld.EntripAPI.web.dto.PlansSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PlansService {
    private final PlansRepository plansRepository;

    @Transactional
    public Long save(PlansSaveRequestDto requestDto) {
        return plansRepository.save(requestDto.toEntity()).getHash_value();
    }
}
