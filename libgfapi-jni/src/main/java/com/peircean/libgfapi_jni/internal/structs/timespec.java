package com.peircean.libgfapi_jni.internal.structs;

import static org.fusesource.hawtjni.runtime.ArgFlag.CRITICAL;
import static org.fusesource.hawtjni.runtime.ArgFlag.NO_IN;
import static org.fusesource.hawtjni.runtime.ArgFlag.NO_OUT;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

/**
 * Used to set utime and atime on files
 *
 * @author Grigoras Cristinel
 *
 */
@JniClass(flags = { ClassFlag.STRUCT })
public class timespec {

	@JniMethod(flags = { MethodFlag.CONSTANT_INITIALIZER })
	private static final native void init();

	@JniField(flags = { FieldFlag.CONSTANT }, accessor = "sizeof(struct timespec)")
	public static int SIZEOF;

	@JniField(cast = "time_t")
	public long tv_sec;
	@JniField(cast = "long")
	public long tv_nsec;

	public static final native void memmove(@JniArg(cast = "void *", flags = { NO_IN, CRITICAL }) timespec dest,
			@JniArg(cast = "const void *") long src, @JniArg(cast = "size_t") long size);

	public static final native void memmove(@JniArg(cast = "void *") long dest,
			@JniArg(cast = "const void *", flags = { NO_OUT, CRITICAL }) timespec src,
			@JniArg(cast = "size_t") long size);

}
