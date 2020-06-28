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
 * Created on 2020-06-28, 6:56
 */
package com.marcnuri.demo.springeclipselink;

import com.marcnuri.demo.springeclipselink.repository.Climber;
import com.marcnuri.demo.springeclipselink.repository.ClimberDao;
import com.marcnuri.demo.springeclipselink.repository.ClimberPhoneNumber;
import com.marcnuri.demo.springeclipselink.repository.Mount;
import com.marcnuri.demo.springeclipselink.repository.MountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDemoDataCreator implements CommandLineRunner {

  private final MountDao mountDao;
  private final ClimberDao climberDao;

  @Autowired
  public ApplicationDemoDataCreator(MountDao mountDao, ClimberDao climberDao) {
    this.mountDao = mountDao;
    this.climberDao = climberDao;
  }

  @Override
  public void run(String... args) {
    final Mount everest = mountDao.save(Mount.builder().alias("everest").name("Mount Everest").build());
    final Mount k2 = mountDao.save(Mount.builder().alias("k2").name("K2").build());
    climberDao.save(Climber.builder()
      .name("Alex")
      .phoneNumber(ClimberPhoneNumber.builder().number("+00 123 45 78").build())
      .climbedMount(everest)
      .climbedMount(mountDao.save(Mount.builder().alias("aitana").name("Aitana").build()))
      .build());
    climberDao.save(Climber.builder()
      .name("Charles")
      .phoneNumber(ClimberPhoneNumber.builder().number("+11 555 13 37").build())
      .climbedMount(k2)
      .climbedMount(everest)
      .build());
    climberDao.save(Climber.builder()
      .name("Julia")
      .climbedMount(k2)
      .climbedMount(everest)
      .build());
  }
}
