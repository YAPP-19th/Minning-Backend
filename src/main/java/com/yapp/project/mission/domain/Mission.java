package com.yapp.project.mission.domain;

import com.yapp.project.account.domain.Account;
import com.yapp.project.aux.common.DateUtil;
import com.yapp.project.aux.common.Utils;
import com.yapp.project.capture.domain.Capture;
import com.yapp.project.mission.domain.dto.MissionDto;
import com.yapp.project.organization.domain.Organization;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor
@ToString
public class Mission {

    @Builder
    public Mission(
            Long id, Organization organization, Account account, LocalDate startDate, LocalDate finishDate,
            Boolean isFinish
    ){
        this.id = id;
        this.organization = organization;
        this.account = account;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isFinish = isFinish;
    }

    @PrePersist
    public void prePersist(){
        this.isFinish = (this.isFinish!=null)&&this.isFinish;
        this.isDelete = (this.isDelete!=null)&&this.isDelete;
        this.successCount = this.successCount!=null?this.successCount:0;
        this.failureCount = this.failureCount!=null?this.failureCount:0;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Organization.class,fetch = FetchType.EAGER)
    private Organization organization;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private final List<Cron> weeks = new ArrayList<>();

    @OneToMany(mappedBy = "mission", fetch = FetchType.EAGER)
    @ToString.Exclude
    private final List<Capture> captures = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate finishDate;

    private Integer successCount;

    private Integer failureCount;

    private Boolean isFinish;

    private Boolean isDelete;

    public void addDays(List<Cron> days){
        this.weeks.addAll(days);
    }

    public Integer getAchievementRate(){
        if (this.successCount == null){
            this.successCount = 0;
            return 0;
        } else if (this.failureCount == null){
            this.failureCount = 0;
            return 0;
        } else if (this.successCount + this.failureCount == 0){
            return 0;
        }
        return (this.successCount/(this.failureCount+this.successCount))*100;
    }

    public Integer getPeriod(){
        LocalDate today = DateUtil.KST_LOCAL_DATE_NOW();
        int period = Period.between(today,this.finishDate).getDays();
        return Math.max(period, 0);
    }

    public void updateSuccessCount(){
        this.successCount+=1;
    }

    public void remove(){
        this.isDelete=true;
    }

    public void finishMission(){
        this.isFinish=true;
    }

    public void defaultSetting(){
        this.successCount = 0;
        this.failureCount = 0;
        this.isFinish = false;
    }

    public void setCountForTest(){
        // 환경이 테스트임을 확인하는 assertion 이 필요
        this.successCount = Utils.randomNumber();
        this.failureCount = Utils.randomNumber();
    }

    public MissionDto.MissionResponse toMissionResponse(){
        return MissionDto.MissionResponse.builder().image(organization.getImage()).title(organization.getTitle())
                .achievementRate(this.getAchievementRate()).period(this.getPeriod())
                .weeks(weeks.stream().map(Cron::getWeek).collect(Collectors.toList())).build();
    }

    public MissionDto.MissionDetailResponse toMissionDetailResponse(){
        return MissionDto.MissionDetailResponse.builder()
                .category(this.organization.getCategory())
                .title(this.organization.getTitle())
                .groupAchievementRate(this.organization.getRate())
                .myAchievementRate(this.getAchievementRate())
                .participant(this.organization.getMissions().size())
                .weeks(weeks.stream().map(Cron::getWeek).collect(Collectors.toList()))
                .period(this.getPeriod())
                .endDate(finishDate)
                .shoot(this.organization.getShoot())
                .promise(this.organization.getPromise())
                .nowPeople(this.organization.getCount())
                .build();
    }
}
