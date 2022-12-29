package com.its.memberboard.repository;

import com.its.memberboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits + 1 where b.id = :id")
    void updateHits(@Param("id") Long id);

    List<BoardEntity> findByBoardWriterContainingOrderByIdDesc(String q);
    List<BoardEntity> findByBoardTitleContainingOrderByIdDesc(String q);
    List<BoardEntity> findByBoardTitleContainingOrBoardWriterContainingOrderByIdDesc(String title, String writer);
}
