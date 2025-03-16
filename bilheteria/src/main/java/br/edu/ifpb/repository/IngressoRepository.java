package main.java.br.edu.ifpb.repository;

import br.edu.ifpb.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    Optional<Ingresso> findById(Long id);
}