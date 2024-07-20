/*
 * Copyright 2002-2021 the original author or authors.
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

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.function.Function;

/**
 * Interface defining a generic contract for attaching and accessing metadata
 * to/from arbitrary objects.
 *
 * 定义通用协定的接口，用于将元数据附加和访问到任意对象。
 */
public interface AttributeAccessor {

	/**
	 * Set the attribute defined by {@code name} to the supplied {@code value}.
	 * <p>If {@code value} is {@code null}, the attribute is {@link #removeAttribute removed}.
	 * <p>In general, users should take care to prevent overlaps with other
	 * metadata attributes by using fully-qualified names, perhaps using
	 * class or package names as prefix.
	 *
	 * 将 name 定义的属性设置为提供的 value。
	 * 如果 value 为 null，则属性为 removed。
	 * 通常，用户应注意通过使用完全限定名称（可能使用类或包名称作为前缀）来防止与其他元数据属性重叠
	 */
	void setAttribute(String name, @Nullable Object value);

	/**
	 * Get the value of the attribute identified by {@code name}.
	 * <p>Return {@code null} if the attribute doesn't exist.
	 * @param name the unique attribute key
	 * @return the current value of the attribute, if any
	 *
	 * 获取由 {@code name} 标识的属性的值。
	 * <p>如果属性不存在，则返回 {@code null}。
	 */
	@Nullable
	Object getAttribute(String name);

	/**
	 * Compute a new value for the attribute identified by {@code name} if
	 * necessary and {@linkplain #setAttribute set} the new value in this
	 * {@code AttributeAccessor}.
	 * <p>If a value for the attribute identified by {@code name} already exists
	 * in this {@code AttributeAccessor}, the existing value will be returned
	 * without applying the supplied compute function.
	 * <p>The default implementation of this method is not thread safe but can
	 * be overridden by concrete implementations of this interface.
	 * 如有必要，计算由 {@code name} 标识的属性的新值，并计算@linkplain此 {@code AttributeAccessor} 中的新值。
	 * <p>如果此 {@code AttributeAccessor} 中已存在由 {@code name} 标识的属性值，
	 * 则将返回现有值，而不应用提供的计算函数。
	 * <p>此方法的默认实现不是线程安全的，但可以被此接口的具体实现重写。
	 */
	@SuppressWarnings("unchecked")
	default <T> T computeAttribute(String name, Function<String, T> computeFunction) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(computeFunction, "Compute function must not be null");
		Object value = getAttribute(name);
		if (value == null) {
			value = computeFunction.apply(name);
			Assert.state(value != null,
					() -> String.format("Compute function must not return null for attribute named '%s'", name));
			setAttribute(name, value);
		}
		return (T) value;
	}

	/**
	 * Remove the attribute identified by {@code name} and return its value.
	 * <p>Return {@code null} if no attribute under {@code name} is found.
	 * @param name the unique attribute key
	 * @return the last value of the attribute, if any
	 */
	@Nullable
	Object removeAttribute(String name);

	/**
	 * Return {@code true} if the attribute identified by {@code name} exists.
	 * <p>Otherwise return {@code false}.
	 * @param name the unique attribute key
	 */
	boolean hasAttribute(String name);

	/**
	 * Return the names of all attributes.
	 */
	String[] attributeNames();

}
