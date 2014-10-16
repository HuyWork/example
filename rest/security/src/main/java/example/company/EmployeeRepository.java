/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.company;

import org.springframework.data.repository.CrudRepository;

/**
 * This repository has no method-level security annotations. That's because it's secured at the URL level inside
 * {@link example.company.SecurityConfiguration}.
 *
 * @author Greg Turnquist
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
