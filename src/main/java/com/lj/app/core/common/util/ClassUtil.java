package com.lj.app.core.common.util;

public class ClassUtil {
	public static Object newInstance(Class<?> c) {
		try {
			return c.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"cannot new instance with class:" + c.getName(), e);
		}
	}

	public static Object newInstance(String className) {
		try {
			return newInstance(Class.forName(className));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"cannot new instance with className:" + className, e);
		}
	}

	/**
	 * Return the default ClassLoader to use: typically the thread context
	 * ClassLoader, if available; the ClassLoader that loaded the ClassUtils
	 * class will be used as fallback.
	 * <p>
	 * Call this method if you intend to use the thread context ClassLoader in a
	 * scenario where you absolutely need a non-null ClassLoader reference: for
	 * example, for class path resource loading (but not necessarily for
	 * <code>Class.forName</code>, which accepts a <code>null</code> ClassLoader
	 * reference as well).
	 * 
	 * @return the default ClassLoader (never <code>null</code>)
	 * @see java.lang.Thread#getContextClassLoader()
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back to system
			// class loader...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtil.class.getClassLoader();
		}
		return cl;
	}
   
   /**
    * 根据指定的类名称加载类
    * @param className
    * @return
    * @throws ClassNotFoundException
    */
   public static Class<?> loadClass(String className) throws ClassNotFoundException {
       try {
           return Thread.currentThread().getContextClassLoader().loadClass(className);
       } catch (ClassNotFoundException e) {
           try {
               return Class.forName(className);
           } catch (ClassNotFoundException ex) {
               try {
                   return ClassLoader.class.getClassLoader().loadClass(className);
               } catch (ClassNotFoundException exc) {
                   throw exc;
               }
           }
       }
   }
   
   /**
    * 根据类的class实例化对象
    * @param clazz
    * @return
    */
	public static <T> T instantiate(Class<T> clazz) {
		if (clazz.isInterface()) {
			//log.error("所传递的class类型参数为接口，无法实例化");
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			//log.error("检查传递的class类型参数是否为抽象类?", ex.getCause());
		}
		return null;
	}
}