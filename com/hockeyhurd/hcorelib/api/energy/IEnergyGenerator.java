package com.hockeyhurd.hcorelib.api.energy;

/**
 * Interface controlling how much energy can be created per tick.
 *
 * @author hockeyhurd
 * @version Oct 19, 2014
 */
public interface IEnergyGenerator extends IEnergyContainer {

    /** Method used to define source of power. */
    void defineSource();

    /** Getter function for getting the source of power defined. */
    PowerSource getSource();

    /** Method used for power generation */
    void generatePower();

    /** Function returning whether this machine can currently produce power. */
    boolean canProducePower();

    /** Method used to set whether this generator can currently produce its power. */
    void setPowerMode(boolean state);

    /** Method used for dispersing power to all other nodes. */
    void transferPower();

}
