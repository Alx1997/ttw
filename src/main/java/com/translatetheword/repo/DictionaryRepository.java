package com.translatetheword.translatetheword.repo;

import com.translatetheword.translatetheword.models.Dictionary;
import com.translatetheword.translatetheword.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

        List<Dictionary> findByEngwordOrRusword(String engword, String rusword);
        ArrayList<Dictionary> findByAuthor(User user);
//        ArrayList<Dictionary> findByAuthor(User user);

}
