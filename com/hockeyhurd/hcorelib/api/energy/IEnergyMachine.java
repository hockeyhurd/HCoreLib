package com.hockeyhurd.hcorelib.api.energy;

/**
 * Interface for all machines
 *
 * @author hockeyhurd
 * @version Oct 23, 2014
 */
public interface IEnergyMachine extends IEnergyContainer {

    /** Method used to set the status of machine (i.e. is it 'on') */
    void setPowerMode(boolean val);

    /** Function used to get whether the machine is on. */
    boolean isPoweredOn();

    /** Sets the energy burn rate of the machine. */
    void setEnergyBurnRate(int val);

    /** Gets the energy burn rate of the machine. */
    int getEnergyBurnRate();

    /** Method used to burn energy while in use. */
    void burnEnergy();

}
