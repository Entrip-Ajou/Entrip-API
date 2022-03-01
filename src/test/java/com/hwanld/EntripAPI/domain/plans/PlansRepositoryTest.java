package com.hwanld.EntripAPI.domain.plans;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlansRepositoryTest {

    @Autowired
    PlansRepository plansRepository;

    @After
    public void cleanup() {
        plansRepository.deleteAll();
    }

    @Test
    public void 플랜저장_불러오기() {

        //given
        String start_time = "10:30";
        String end_time = "12:00";
        String location = "192.102.123.512";

        plansRepository.save(Plans.builder()
                .start_time(start_time)
                .end_time(end_time)
                .location(location)
                .author("hwanld")
                .name("firstplan")
                .build());

        //when
        List<Plans> plansList = plansRepository.findAll();

        //then
        Plans plans = plansList.get(0);
        assertThat(plans.getStart_time()).isEqualTo(start_time);
        assertThat(plans.getEnd_time()).isEqualTo(end_time);
        assertThat(plans.getLocation()).isEqualTo(location);
    }
}
