package com.lihd.java.mapping;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-06-01 19:29
 **/
@Mapper
public interface MapperUtil {

    MapperUtil INSTANCE = Mappers.getMapper(MapperUtil.class );

}
