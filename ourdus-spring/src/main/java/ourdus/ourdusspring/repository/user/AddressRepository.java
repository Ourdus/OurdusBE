package ourdus.ourdusspring.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.user.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address save(Address address);
}
