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
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyflielib/crtp/xxxx.java
 */

package net.smartrover.skyrover2.roverlib.crtp;

import java.nio.ByteBuffer;

/**
 * Packet used for sending control set-points for the roll/pitch/yaw/thrust regulators.
 *
 * @author OROCA SmartRover Team
 */
public class CommanderPacket extends CrtpPacket {
    private final float mRoll;
    private final float mPitch;
    private final float mYaw;
    private final char mThrust;
    private final boolean mClientXmode; // client side x-mode (default is +-mode)

    /**
     * Create a new commander packet.
     *
     * @param roll (Deg.)
     * @param pitch (Deg.)
     * @param yaw (Deg./s)
     * @param thrust (0-65535)
     * @param clientXmode if true, then roll and pitch values are recalculated before sending them to the SkyRover2
     */
    public CommanderPacket(float roll, float pitch, float yaw, char thrust, boolean clientXmode) {
        super(0, CrtpPort.COMMANDER);
        this.mClientXmode = clientXmode;

        if (this.mClientXmode) {
            //offset by 45 degrees
            this.mRoll = 0.707f * (roll - pitch);
            this.mPitch = 0.707f * (roll + pitch);
        } else {
            this.mRoll = roll;
            this.mPitch = pitch;
        }
        this.mYaw = yaw;
        this.mThrust = thrust;
    }

    /**
     * Create a new commander packet with clientXmode set to false.
     *
     * @param roll (Deg.)
     * @param pitch (Deg.)
     * @param yaw (Deg./s)
     * @param thrust (0-65535)
     */
    public CommanderPacket(float roll, float pitch, float yaw, char thrust) {
        this(roll, pitch, yaw, thrust, false);
    }

    @Override
    protected void serializeData(ByteBuffer buffer) {
        buffer.putFloat(mRoll);
        buffer.putFloat(-mPitch); //invert axis
        buffer.putFloat(mYaw);
        buffer.putChar(mThrust);
    }

    @Override
    protected int getDataByteCount() {
        return 3 * 4 + 1 * 2; // 3 floats with size 4, 1 char (= uint16_t) with size 2
    }

    @Override
    public String toString() {
        return "CommanderPacket: roll: " + this.mRoll + " pitch: " + this.mPitch + " yaw: " + this.mYaw + " thrust: " + (int) this.mThrust + " xmode: " + this.mClientXmode;
    }

}
