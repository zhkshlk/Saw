package org.example.Repositories;

import org.example.Model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepositories extends JpaRepository<Text, Long> {
}
