package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
