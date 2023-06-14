package Makers2Front.Application.repos;

import Makers2Front.Application.entities.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ErrorsRepository extends JpaRepository<ErrorLog, Long> {


}
