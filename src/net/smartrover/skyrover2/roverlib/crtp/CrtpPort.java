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
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyflielib/crtp/CrtpPort.java
 */

package net.smartrover.skyrover2.roverlib.crtp;


/**
 * Lists the available ports for the CRTP.
 *
 * @author OROCA SmartRover Team
 */
public enum CrtpPort {
    CONSOLE(0),
    PARAMETERS(2),
    COMMANDER(3),
    MEMORY(4),
    LOGGING(5),
    DEBUGDRIVER(14),
    LINKCTRL(15),
    ALL(255),
    UNKNOWN(-1); //FIXME

    private byte mNumber;

    private CrtpPort(int number) {
        this.mNumber = (byte) number;
    }

    /**
     * Get the number associated with this port.
     *
     * @return the number of the port
     */
    public byte getNumber() {
        return mNumber;
    }

    /**
     * Get the port with a specific number.
     *
     * @param number
     *            the number of the port.
     * @return the port or <code>null</code> if no port with the specified number exists.
     */
    public static CrtpPort getByNumber(byte number) {
        for (CrtpPort p : CrtpPort.values()) {
            if (p.getNumber() == number) {
                return p;
            }
        }
        return null;
    }
}
