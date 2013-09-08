/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.nativebinaries;

import org.gradle.api.ExtensiblePolymorphicDomainObjectContainer;
import org.gradle.api.Incubating;

import java.util.List;

/**
 * A container for {@link ToolChain}s.
 */
@Incubating
public interface ToolChainRegistry extends ExtensiblePolymorphicDomainObjectContainer<ToolChain> {
    /**
     * Registers a default ToolChain, which may later be added to the registry via {@link #addDefaultToolChain()}.
     */
    void registerDefaultToolChain(String name, Class<? extends ToolChain> type);

    /**
     * Adds the first available default tool chain to the registry.
     */
    void addDefaultToolChain();

    /**
     * Returns all registered {@link ToolChain}s that are available.
     */
    List<? extends ToolChain> getAvailableToolChains();

    /**
     * Returns the first registered {@link ToolChain} that is available.
     */
    ToolChain getDefaultToolChain();
}