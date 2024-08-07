/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.spi.mongodb.atlas;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.util.ClassUtils;

/**
 * @author Christoph Strobl
 */
class AtlasRepositoryPostProcessor extends RepositoryBeanPostProcessor<Repository<?, ?>> {

    public AtlasRepositoryPostProcessor() {
        super(AtlasRepositoryPostProcessor::usesAtlasExtension, AtlasRepositoryPostProcessor::configure);
    }

    static boolean usesAtlasExtension(Class<?> repositoryInterface, RepositoryFactoryBeanSupport<?, ?, ?> factory) {
        return ClassUtils.isAssignable(AtlasRepository.class, repositoryInterface);
    }

    static void configure(RepositoryFactoryBeanSupport<?, ?, ?> repositoryFactory) {
        repositoryFactory.setExposeMetadata(true);
    }
}
