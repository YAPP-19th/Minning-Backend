package com.yapp.project.aux.test.organization;

import com.yapp.project.organization.domain.Clause;
import com.yapp.project.organization.domain.Organization;

import java.time.LocalTime;

public class OrganizationTemplate {
    private OrganizationTemplate(){
    }
    private static Long id = 1L;
    public static final String TITLE = "명상";
    public static final String CATEGORY = "미라클모닝";
    public static final LocalTime BEGIN_TIME = LocalTime.of(0,0);
    public static final LocalTime END_TIME = LocalTime.of(23,59, 59);
    public static final String DESCRIPTION = "고요히 자기 자신을 느껴보는 시간입니다.";
    public static final String RECOMMEND = "명상을 처음 접해본다면, 호흡부터 시작해보세요.";

    private static final String SHOOT = "명상을 할 조용한 장소";

    private static final Clause CLAUSE = Clause.builder().beginTime(BEGIN_TIME).endTime(END_TIME).shoot(SHOOT)
            .description(DESCRIPTION).recommend(RECOMMEND).build();

    public static Organization makeTestOrganization(){
        return makeTestOrganization(TITLE,CATEGORY);
    }

    public static Organization makeTestOrganization(String title){
        return makeTestOrganization(title,CATEGORY);
    }

    public static Organization makeTestOrganization(String title, String category){
        Organization organization = Organization.builder().id(id++).title(title).category(category).clause(CLAUSE).rate(86).build();
        organization.defaultSetting();
        return organization;
    }

    public static Organization makeTestOrganizationForIntegration(){
        return makeTestOrganizationForIntegration(TITLE,CATEGORY);
    }

    public static Organization makeTestOrganizationForIntegration(String title){
        return makeTestOrganizationForIntegration(title,CATEGORY);
    }

    public static Organization makeTestOrganizationForIntegration(String title, String category){
        Organization organization = Organization.builder().title(title).category(category).clause(CLAUSE).rate(86).build();
        organization.defaultSetting();
        return organization;
    }

}
