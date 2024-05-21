package work.education.padroes.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import work.education.padroes.spring.model.Adress;
@Repository
public interface AdressRepository extends CrudRepository<Adress, String> {
}
