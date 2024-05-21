package work.education.padroes.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import work.education.padroes.spring.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
