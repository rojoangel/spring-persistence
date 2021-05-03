package com.oreilly.persistence.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "officers")
public class Officer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Rank rank;

  @Column(nullable = false, name = "first_name")
  private String first;

  @Column(nullable = false, name = "last_name")
  private String last;

  public Officer() {
  }

  public Officer(Rank rank, String first, String last) {
    this.rank = rank;
    this.first = first;
    this.last = last;
  }

  public Officer(Integer id, Rank rank, String first, String last) {
    this.id = id;
    this.rank = rank;
    this.first = first;
    this.last = last;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  @Override
  public String toString() {
    return "Officer{" +
        "id=" + id +
        ", rank=" + rank +
        ", first='" + first + '\'' +
        ", last='" + last + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Officer officer = (Officer) o;
    return id.equals(officer.id) && rank == officer.rank && Objects.equals(first, officer.first) && Objects.equals(last, officer.last);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rank, first, last);
  }
}
