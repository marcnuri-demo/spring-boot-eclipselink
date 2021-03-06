/*
 * Copyright 2020 Marc Nuri San Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created on 2020-06-28, 6:52
 */
package com.marcnuri.demo.springeclipselink.repository;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name = "CLIMBERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Climber {
  @Id
  @UuidGenerator(name = "climber-id")
  @GeneratedValue(generator = "climber-id")
  private String id;
  private String name;
  @Singular
  @OneToMany
  private Set<Mount> climbedMounts;
  @Singular
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ClimberPhoneNumber> phoneNumbers;

}
