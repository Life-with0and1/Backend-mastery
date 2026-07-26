package com.example.post.repository;

import com.example.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("""
        SELECT p
        FROM Post p
        WHERE p.active = true
        AND (
            6371 * acos(
                cos(radians(:latitude))
                * cos(radians(p.latitude))
                * cos(radians(p.longitude) - radians(:longitude))
                + sin(radians(:latitude))
                * sin(radians(p.latitude))
            )
        ) <= :radius
        """)
    Page<Post> findNearbyPosts(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("radius") double radius, Pageable pageable);
}
