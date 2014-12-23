package io.craft.armor.spi;

/**
 * Armor invoker, launch the armor context invocation.
 * 
 * @author mindwind
 * @version 1.0, Dec 23, 2014
 */
public interface ArmorInvoker {
	
	/**
	 * Execute armor invocation.
	 * 
	 * @param invocation
	 * @return invocation object.
	 * @throws Throwable
	 */
	Object invoke(ArmorInvocation invocation) throws Throwable;
	
}
