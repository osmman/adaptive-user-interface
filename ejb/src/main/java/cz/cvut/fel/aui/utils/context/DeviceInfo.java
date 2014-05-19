package cz.cvut.fel.aui.utils.context;

import cz.cvut.fel.aui.model.context.Device;
import cz.cvut.fel.aui.model.context.ScreenSize;
import cz.cvut.fel.caf.ContextItem;

/**
 * Created by Tomáš on 8. 5. 2014.
 */
public class DeviceInfo implements ContextItem {

    private Device device;

    private ScreenSize screenSize;

    public DeviceInfo(Device device, ScreenSize screenSize) {
        this.device = device;
        this.screenSize = screenSize;
    }

    public Device getDevice() {
        return device;
    }

    public ScreenSize getScreenSize() {
        return screenSize;
    }
}
