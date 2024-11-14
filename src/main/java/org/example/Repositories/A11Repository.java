package org.example.Repositories;

import org.example.Model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface A11Repository extends JpaRepository <Text, Long>{
    Text findById(long id);
}