package com.hockeyhurd.hcorelib.api.util.interfaces;

/**
 * Interfacing for proxies.
 *
 * @author hockeyhurd
 * @version 4/28/16
 */
public interface IProxy {

    /**
     * Pre-init method.
     */
    void preInit();

    /**
     * Main init method.
     */
    void init();

    /**
     * Pre-init method.
     */
    void postInit();

    /**
     * Registers input handlers.
     * i.e. keybindings and such.
     */
    void registerInputHandlers();

    /**
     * Registers render information (used exclusively
     * on client side).
     */
    void registerRenderInformation();

    /**
     * Function to get which side IProxy is on.
     *
     * @return boolean result.
     */
    boolean isClient();

    /**
     * Registers the update handler. Typically called in
     * post-init of FML event phase.
     */
    void registerUpdateHandler();

	/**
	 * Registers event handlers.
     */
    void registerEventHandlers();

}
