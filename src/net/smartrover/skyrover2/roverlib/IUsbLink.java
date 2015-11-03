/**
 *
 * SkyRover2 Multi-Quadcopter Client
 *
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * This file has been created with reference to the
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyflielib/IUsbLink.java
 */

package net.smartrover.skyrover2.roverlib;

/**
 * @author OROCA SmartRover Team
 */
public interface IUsbLink {

    /**
     * Release UsbInterface
     *
     */
    public void releaseInterface();

    /**
     * Returns the state of the USB connection
     *
     * @return true if USB device is connected, else false
     */
    public boolean isUsbConnected();

    //TODO: set transfer timeout?
    /**
     * Send control data
     *
     * @param requestType
     * @param request
     * @param value
     * @param index
     * @param data
     * @return
     */
    public int sendControlTransfer(int requestType, int request, int value, int index, byte[] data);

    /**
     * Sends bulk data
     *
     * @param data
     * @param receiveData
     * @return
     */
    public int sendBulkTransfer(byte[] data, byte[] receiveData);

    /**
     * Returns the firmware version of the USB device
     *
     * @return firmware version
     */
    public float getFirmwareVersion();


    /**
     * Returns the serial number of the USB device
     *
     * @return serial number
     */
    public String getSerialNumber();
}