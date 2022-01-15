package com.flitx.scool.repo;

import com.flitx.scool.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends MongoRepository <School, String>{



}
