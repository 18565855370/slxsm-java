package com.slxsm.mybatis.dao;

import com.slxsm.mybatis.model.Team;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    List<Team> queryTeamInfos();
}
