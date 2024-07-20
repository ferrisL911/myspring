/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.core;

/**
 * Common interface for managing aliases. Serves as a super-interface for
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}.
 *
 * 用于管理别名的通用接口。
 * 用作 {@link org.springframework.beans.factory.support.BeanDefinitionRegistry} 的超级接口。
 */
public interface AliasRegistry {

	/**
	 * Given a name, register an alias for it.
	 *
	 * 给定一个名称，为其注册一个别名。
	 */
	void registerAlias(String name, String alias);

	/**
	 * Remove the specified alias from this registry.
	 *
	 * 从此注册表中删除指定的别名。
	 */
	void removeAlias(String alias);

	/**
	 * Determine whether the given name is defined as an alias
	 * (as opposed to the name of an actually registered component).
	 *
	 * 确定给定名称是否定义为别名（而不是实际注册的组件的名称）。
	 */
	boolean isAlias(String name);

	/**
	 * Return the aliases for the given name, if defined.
	 *
	 * 返回给定名称的别名（如果已定义）。
	 */
	String[] getAliases(String name);

}
