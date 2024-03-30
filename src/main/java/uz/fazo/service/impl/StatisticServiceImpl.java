package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Statistic;
import uz.fazo.mapper.StatisticMapper;
import uz.fazo.payload.StatisticDto;
import uz.fazo.repository.StatisticRepository;
import uz.fazo.service.StatisticService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;


    @Override
    public List<StatisticDto> getAll() {

        return statisticRepository.findAll().stream()
                .map(statisticMapper::statisticToStatisticDto).collect(Collectors.toList());
    }

    @Override
    public List<StatisticDto> getAllByUserId(int id) {
        return statisticRepository.findAllByUserId(id).stream().map(statisticMapper::statisticToStatisticDto).collect(Collectors.toList());
    }

    @Override
    public StatisticDto getById(long id) {
        return statisticMapper.statisticToStatisticDto(statisticRepository
                .findById(id).orElseThrow(NullPointerException::new));
    }

    @Override
    public StatisticDto update(long id, StatisticDto statisticDto) {

        Statistic statistic = statisticRepository.findById(id).orElseThrow(NullPointerException::new);
        Statistic update = statisticMapper.update(statistic, statisticDto);
        Statistic save = statisticRepository.save(update);
        return statisticMapper.statisticToStatisticDto(save);

    }

    @Override
    public StatisticDto create(StatisticDto statisticDto) {
        Statistic save = statisticRepository.save(statisticMapper.statisticDtoToStatistic(statisticDto));
        return statisticMapper.statisticToStatisticDto(save);
    }

    @Override
    public void delete(long id) {
        statisticRepository.deleteById(id);

    }
}
