package com.deve.pig.mapper;

import com.deve.pig.model.Priv;
import com.deve.pig.model.PrivExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PrivMapper {
    int countByExample(PrivExample example);

    int deleteByExample(PrivExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Priv record);

    int insertSelective(Priv record);

    List<Priv> selectByExample(PrivExample example);

    Priv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Priv record, @Param("example") PrivExample example);

    int updateByExample(@Param("record") Priv record, @Param("example") PrivExample example);

    int updateByPrimaryKeySelective(Priv record);

    int updateByPrimaryKey(Priv record);

	List<Priv> selectByRoleId(Long id);
}