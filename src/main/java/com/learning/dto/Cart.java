package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "cart")
@Builder
public class Cart {

  @Id
  @Column(name = "id")
  // @Length(min = 6)

  private String id;

  @NotNull
  private String userId;

  @NotNull
  private String productId;

  //	@NotNull
  private String productName;

  //	@NotBlank
  private String productCost;
}
