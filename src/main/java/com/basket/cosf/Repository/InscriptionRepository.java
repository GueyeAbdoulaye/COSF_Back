package com.basket.cosf.Repository;

import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
}
