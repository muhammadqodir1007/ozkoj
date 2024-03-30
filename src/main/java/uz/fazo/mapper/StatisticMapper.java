package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Statistic;
import uz.fazo.payload.StatisticDto;

@Mapper
public interface StatisticMapper {

    Statistic statisticDtoToStatistic(StatisticDto statisticDto);

    StatisticDto statisticToStatisticDto(Statistic statistic);


    Statistic update(Statistic statistic, StatisticDto statisticDto);
}
