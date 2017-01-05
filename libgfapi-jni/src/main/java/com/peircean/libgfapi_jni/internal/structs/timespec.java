package com.peircean.libgfapi_jni.internal.structs;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;

import lombok.ToString;

@JniClass(flags = ClassFlag.STRUCT)
@ToString
public class timespec {

	@JniField(cast = "time_t")
	public long tv_sec;
	@JniField(cast = "long")
	public long tv_nsec;

}
