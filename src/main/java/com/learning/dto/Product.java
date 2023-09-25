package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "productName")})
public class Product implements Comparable<Product> {

  @Id
  @Column(name = "id")
  // @Length(min = 6)

  private String id;

  @NotNull
  private String productName;

  @NotNull
  private String productCost;

  //	@NotNull
  private String description;

  //	@NotBlank
  private String productPic;

  //	@NotNull
  private PRODUCTTYPE productType;

  @Override
  public int compareTo(Product o) {
    // TODO Auto-generated method stub
    return this.id.compareTo(o.getId());
  }

}
