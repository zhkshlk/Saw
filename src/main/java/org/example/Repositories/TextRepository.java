package org.example.Repositories;

import org.example.Model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository <Text, Long> {

}
