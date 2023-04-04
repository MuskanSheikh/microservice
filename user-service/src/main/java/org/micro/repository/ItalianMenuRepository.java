package org.micro.repository;

import org.micro.entity.ItalianMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItalianMenuRepository extends JpaRepository<ItalianMenu, Long> {

    @Query("SELECT C FROM ItalianMenu C WHERE C.menuId = :menuId")
    List<ItalianMenu> getByMenuId(@Param("menuId") Long menuId);
}
