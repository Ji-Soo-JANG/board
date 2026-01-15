package com.jisoo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	 @Select("select 1 from dual")
	 int ping();
}
