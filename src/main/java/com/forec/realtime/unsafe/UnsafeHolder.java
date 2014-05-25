package com.forec.realtime.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public final class UnsafeHolder {

	private static final Unsafe unsafe;
	
	static
    {
        try
        {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
	
	static Unsafe getUnsafe(){return unsafe;}
	
	//Can't create any instance of this class
	private UnsafeHolder(){}
}
