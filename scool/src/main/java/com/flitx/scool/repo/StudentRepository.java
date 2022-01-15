package com.flitx.scool.repo;

import com.flitx.scool.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

  //  @RepositoryRestResource(collectionResourceRel = "people", path = "people")
    public Student findByReference(@Param("name") String reference);


}
