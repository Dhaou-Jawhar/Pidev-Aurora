package pidev.tn.aurora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.chatGPT.Respons;

public interface ResponsRepository extends JpaRepository<Respons, Integer> {
}