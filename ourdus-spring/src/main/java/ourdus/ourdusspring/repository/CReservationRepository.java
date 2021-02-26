package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.CReservation;
import ourdus.ourdusspring.domain.OfflineClass;


import java.util.List;
import java.util.Optional;

public interface CReservationRepository extends JpaRepository<CReservation, Long> {
    List<CReservation> findAll();
    void deleteById (Long userId);
    Optional<CReservation> findById(Long Id);
}
