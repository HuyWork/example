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

import java.util.function.BiPredicate;
import java.util.function.Consumer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * @author Christoph Strobl
 */
class RepositoryBeanPostProcessor<T extends Repository<?, ?>> implements BeanPostProcessor {

    private BiPredicate<Class<?>, RepositoryFactoryBeanSupport<?, ?, ?>> filter;
    private Consumer<RepositoryFactoryBeanSupport<T, ?, ?>> configurator;

    RepositoryBeanPostProcessor(BiPredicate<Class<?>, RepositoryFactoryBeanSupport<?, ?, ?>> filter, Consumer<RepositoryFactoryBeanSupport<T, ?, ?>> configurer) {

        this.filter = filter;
        this.configurator = configurer;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof RepositoryFactoryBeanSupport repositoryFactory) {
            if (filter.test(repositoryFactory.getObjectType(), repositoryFactory)) {
                configurator.accept(repositoryFactory);
            }
        }

        return bean;
    }
}
