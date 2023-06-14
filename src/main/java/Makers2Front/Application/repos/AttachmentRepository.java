package Makers2Front.Application.repos;

import Makers2Front.Application.entities.AttachmentObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends CrudRepository<AttachmentObject, Long> {

    List<AttachmentObject> getAllByMainTag(String tag);

    List<AttachmentObject> findAll();
    AttachmentObject getByAttId(Long id);


}
