package pl.jaceklewinski.FunniestVideos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jaceklewinski.FunniestVideos.models.Video;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Integer> {
    List<Video> findAllByOrderByIdDesc();
}
