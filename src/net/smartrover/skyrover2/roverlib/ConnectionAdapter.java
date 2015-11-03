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
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyflielib/ConnectionAdapter.java
 */

package net.smartrover.skyrover2.roverlib;

/**
 * An abstract adapter class for receiving connection events. The methods in
 * this class are empty. This class exists as convenience for creating listener
 * objects.
 *
 * @author OROCA SmartRover Team
 */
public abstract class ConnectionAdapter implements ConnectionListener {

    /*
     * (non-Javadoc)
     * @see net.smartrover.skyrover2.roverlib.ConnectionListener#connectionInitiated(net.smartrover.skyrover2.roverlib.Link)
     */
    @Override
    public void connectionInitiated(Link l) {
    }

    /*
     * (non-Javadoc)
     * @see net.smartrover.skyrover2.roverlib.ConnectionListener#connectionSetupFinished(net.smartrover.skyrover2.roverlib.Link)
     */
    @Override
    public void connectionSetupFinished(Link l) {
    }

    /*
     * (non-Javadoc)
     * @see net.smartrover.skyrover2.roverlib.ConnectionListener#disconnected(net.smartrover.skyrover2.roverlib.Link)
     */
    @Override
    public void disconnected(Link l) {
    }

    /*
     * (non-Javadoc)
     * @see net.smartrover.skyrover2.roverlib.ConnectionListener#connectionLost(net.smartrover.skyrover2.roverlib.Link)
     */
    @Override
    public void connectionLost(Link l) {
    }

    /*
     * (non-Javadoc)
     * @see net.smartrover.skyrover2.roverlib.ConnectionListener#connectionFailed(net.smartrover.skyrover2.roverlib.Link)
     */
    @Override
    public void connectionFailed(Link l) {
    }

    @Override
    public void linkQualityUpdate(Link l, int quality) {
    }

}
