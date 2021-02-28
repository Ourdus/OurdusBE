package ourdus.ourdusspring.service.offlineclass.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.offlineclass.order.CReservation;
import ourdus.ourdusspring.repository.offlineclass.order.CReservationRepository;

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
