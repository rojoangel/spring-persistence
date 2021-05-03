package com.oreilly.persistence.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Transactional
@SpringBootTest
class OfficerRepositoryTest {

  @Autowired
  private OfficerRepository repository;

  @Autowired
  private JdbcTemplate template;

  // private method to retrieve the current ids in the database
  private List<Integer> getIds() {
    return template.query("select id from officers", (rs, num) -> rs.getInt("id"));
  }

  @Test
  public void save() {
    Officer officer = new Officer(
        Rank.LIEUTENANT,
        "Nyota",
        "Uhuru"
    );
    officer = repository.save(officer);
    assertNotNull(officer.getId());
  }

  @Test
  public void findByIdThatExists() {
    getIds().forEach(id -> {
      Optional<Officer> officer = repository.findById(1);
      assertTrue(officer.isPresent());
      assertEquals(1, officer.get().getId().intValue());
    });
  }

  @Test
  public void findByIdThatDoesNotExist() {
    Optional<Officer> officer = repository.findById(999);
    assertFalse(officer.isPresent());
  }

  @Test
  public void count() {
    assertEquals(5, repository.count());
  }

  @Test
  public void findAll() {
    List<String> names = repository.findAll()
        .stream()
        .map(Officer::getLast)
        .collect(Collectors.toList());

    assertThat(names, containsInAnyOrder(
        "Kirk", "Picard", "Sisko", "Janeway", "Archer"));
  }

  @Test
  public void delete() {
    getIds()
        .forEach(id -> {
          Optional<Officer> officer = repository.findById(id);
          assertTrue(officer.isPresent());
          repository.delete(officer.get());
        });

    assertEquals(0, repository.count());
  }

  @Test
  public void existsById() {
    getIds()
        .forEach(id -> assertTrue(repository.existsById(id)));
  }
}