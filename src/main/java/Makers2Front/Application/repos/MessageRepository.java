package Makers2Front.Application.repos;

import Makers2Front.Application.entities.FormMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<FormMessage, Long> {

FormMessage getByMessageId(Long id);

List<FormMessage> findAll();
}
