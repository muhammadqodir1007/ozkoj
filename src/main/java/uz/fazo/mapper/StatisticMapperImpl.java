package uz.fazo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.fazo.entity.Statistic;
import uz.fazo.payload.StatisticDto;
import uz.fazo.user.User;
import uz.fazo.user.UserRepository;

@Component
@AllArgsConstructor
public class StatisticMapperImpl implements StatisticMapper {

    UserRepository userRepository;

    @Override
    public Statistic statisticDtoToStatistic(StatisticDto statisticDto) {
        if (statisticDto == null) {
            return null;
        }
        User user = userRepository.findById(statisticDto.getUserId()).orElseThrow(NullPointerException::new);

        return Statistic.builder()
                .link(statisticDto.getLink())
                .MHOBT(statisticDto.getMHOBT())
                .type(statisticDto.getType())
                .XXTUT(statisticDto.getXXTUT())
                .KTUT(statisticDto.getKTUT())
                .year(statisticDto.getYear())
                .location(statisticDto.getLocation())
                .period(statisticDto.getPeriod())
                .user(user).build();


    }

    @Override
    public StatisticDto statisticToStatisticDto(Statistic statistic) {
        if (statistic == null) return null;


        return StatisticDto.builder()
                .userId(statistic.getUser().getId())
                .MHOBT(statistic.getMHOBT())
                .period(statistic.getPeriod())
                .KTUT(statistic.getKTUT())
                .XXTUT(statistic.getXXTUT())
                .year(statistic.getYear())
                .location(statistic.getLocation())
                .link(statistic.getLink())
                .type(statistic.getType())
                .build();

    }

    @Override
    public Statistic update(Statistic statistic, StatisticDto statisticDto) {
        if (statisticDto == null || statistic == null) return null;

        if (statisticDto.getLink() != null) {
            statistic.setLink(statisticDto.getLink());
        }
        if (statisticDto.getKTUT() != null) {
            statistic.setKTUT(statisticDto.getKTUT());
        }
        if (statisticDto.getPeriod() != null) {
            statistic.setPeriod(statistic.getPeriod());
        }
        if (statisticDto.getType() != null) {
            statistic.setType(statisticDto.getType());
        }
        if (statisticDto.getLocation() != null) {
            statistic.setLocation(statisticDto.getLocation());
        }
        if (statisticDto.getXXTUT() != null) {
            statistic.setXXTUT(statisticDto.getXXTUT());
        }
        if (statisticDto.getMHOBT() != null) {
            statistic.setMHOBT(statistic.getMHOBT());
        }
        return statistic;

    }
}
