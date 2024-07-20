package org.springframework.core;

import org.springframework.lang.Nullable;

public abstract class NestedExceptionUtils {

    public static void main(String[] args) {
        String s = NestedExceptionUtils.buildMessage("", new NullPointerException());
        System.out.println(s);
    }

    @Deprecated(since = "6.0")
    @Nullable
    public static String buildMessage(@Nullable String message, @Nullable Throwable cause){
        if (cause == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(64);
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(cause);
        return sb.toString();
    }

    /**
     * Retrieve the innermost cause of the given exception, if any.
     * 检索给定异常的最内层原因（如果有）。
     */
    public static Throwable getRootCause(@Nullable Throwable original) {
        if (original == null){
            return null;
        }
        Throwable rootCause = null;
        Throwable cause = original.getCause();
        while (cause != null && cause != rootCause){
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    /**
     * Retrieve the most specific cause of the given exception, that is,
     * either the innermost cause (root cause) or the exception itself.
     * <p>Differs from {@link #getRootCause} in that it falls back
     * to the original exception if there is no root cause.
     *
     * 检索给定异常的最具体原因，即最内层的原因（根本原因）或异常本身。
     * <p>与 {@link getRootCause} 的不同之处在于，如果没有根本原因，它会回退到原始异常。
     */
    public static Throwable getMostSpecificCause(Throwable original) {
        Throwable rootCause = getRootCause(original);
        return (rootCause != null ? rootCause : original);
    }}
