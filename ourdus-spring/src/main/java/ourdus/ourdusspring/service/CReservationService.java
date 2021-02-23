package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.CReservation;
import ourdus.ourdusspring.repository.CReservationRepository;

import java.util.List;

@Service
@Transactional
public class CReservationService {

    private final CReservationRepository cReservationRepository;

    public CReservationService(CReservationRepository cReservationRepository)
    {
        this.cReservationRepository=cReservationRepository;
    }

    public List<CReservation> findAll() {return cReservationRepository.findAll();}


}
