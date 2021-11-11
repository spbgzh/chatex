package com.chatex.mapper;

import com.chatex.pojo.HobbyScore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HobbyScoreMapper {
    @Select("select * from hobby_scores where id = #{id}")
    HobbyScore getHobbyScoreByID(@Param("id") int id);

    @Select("select * from hobby_scores where id <> #{id}")
    List<HobbyScore> getHobbyScoreListWithoutID(@Param("id") int id);

    @Update("update hobby_scores set sports = #{sports}," +
            " music = #{music}, travel = #{travel}, reading = #{reading}," +
            " art = #{art}, movie = #{movie}, cartoon = #{cartoon}," +
            " games = #{games}, cooking = #{cooking}, shopping = #{shopping}" +
            " where id = #{id}")
    void updateScore(@Param("id") int id,
                    @Param("sports") int sports,
                    @Param("music") int music,
                    @Param("travel") int travel,
                    @Param("reading") int reading,
                    @Param("art") int art,
                    @Param("movie") int movie,
                    @Param("cartoon") int cartoon,
                    @Param("games") int games,
                    @Param("cooking") int cooking,
                    @Param("shopping") int shopping);

    @Insert("insert into hobby_scores "
            + "(sports, music, travel, reading, art, movie, cartoon, games, cooking, shopping) "
            + "values "
            + "(#{sports}, #{music}, #{travel}, #{reading},#{art}, #{movie}, #{cartoon}, #{games}, #{cooking}, #{shopping}) ")
    void insertHobbyScores(@Param("sports") int sports,
                          @Param("music") int music,
                          @Param("travel") int travel,
                          @Param("reading") int reading,
                          @Param("art") int art,
                          @Param("movie") int movie,
                          @Param("cartoon") int cartoon,
                          @Param("games") int games,
                          @Param("cooking") int cooking,
                          @Param("shopping") int shopping
    );
    @Delete("delete from hobby_scores where id = #{id}")
    void deleteHobbyScores(@Param("id") int id);

}
