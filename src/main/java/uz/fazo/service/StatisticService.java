package uz.fazo.service;

import uz.fazo.payload.StatisticDto;

import java.util.List;

public interface StatisticService {


    List<StatisticDto> getAll();

    List<StatisticDto> getAllByUserId(int id);

    StatisticDto getById(long id);

    StatisticDto update(long id, StatisticDto statisticDto);

    StatisticDto create(StatisticDto statisticDto);

    void delete(long id);


}
