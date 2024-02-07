package com.alibou.security.service;

import com.alibou.security.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class HomeService {

    MaterialRepository materialRepository;
    NewsRepository newsRepository;
    PartnersRepository partnersRepository;
    ResourceRepository resourceRepository;
    SpeakerRepository speakerRepository;
    WebinarRepository webinarRepository;


    public Map<String, Long> map() {
        Map<String, Long> map = new HashMap<>();


        long material = materialRepository.count();
        long news = newsRepository.count();
        long partners = partnersRepository.count();
        long resource = resourceRepository.count();
        long speaker = speakerRepository.count();
        long webinar = webinarRepository.count();

        map.put("material", material);
        map.put("news", news);
        map.put("partners", partners);
        map.put("speaker", speaker);
        map.put("resources", resource);
        map.put("webinar", webinar);
        return map;
    }

}
